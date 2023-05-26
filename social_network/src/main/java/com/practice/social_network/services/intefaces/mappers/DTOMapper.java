package com.practice.social_network.services.intefaces.mappers;

import com.practice.social_network.dtos.PostDTO;
import com.practice.social_network.dtos.UserDTO;
import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.User;
import org.mapstruct.*;


@Mapper
public interface DTOMapper {
    UserDTO userToDto(User user);

    User dtoToUser(UserDTO user);

    @Mapping(target = "userId", expression = "java(post.getUser().getId())")
    PostDTO postToPostDto(Post post);

    Post postDtoToPost(PostDTO postDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User user);
}
