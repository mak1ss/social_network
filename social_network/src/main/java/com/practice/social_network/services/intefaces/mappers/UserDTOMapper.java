package com.practice.social_network.services.intefaces.mappers;

import com.practice.social_network.dtos.UserDTO;
import com.practice.social_network.entities.User;
import org.mapstruct.*;


@Mapper
public interface UserDTOMapper {
    @Mapping(target="posts", expression ="java(user.getPosts().size())")
    @Mapping(target="followings", expression="java(user.getFollowings().size())")
    @Mapping(target="comments", expression="java(user.getComments().size())")
    UserDTO userToDto(User user);

    @Mapping(target="followings", ignore = true)
    @Mapping(target="comments", ignore = true)
    @Mapping(target="posts", ignore = true)
    User dtoToUser(UserDTO userDTO);


    @Mapping(target="fullName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="nickname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @BeanMapping(ignoreByDefault = true)
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User user);
}

