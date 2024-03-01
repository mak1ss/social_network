package com.practice.social_network.services.intefaces.mappers;

import com.practice.social_network.dtos.PostDTO;
import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.PostComment;
import com.practice.social_network.dtos.PostCommentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostDTOMapper {
    @Mapping(target="userId", expression = "java(post.getUser().getId())")
    @Mapping(target="likesAmount", expression = "java(post.getLikes().size())")
    @Mapping(target="comments", expression = "java(post.getComments().size())")
    PostDTO postToDto(Post post);

    @Mapping(target="postBody", expression = "java(postDTO.getPostBody())")
    @Mapping(target="creationDate", expression = "java(postDTO.getCreationDate())")
    @BeanMapping(ignoreByDefault = true)
    Post dtoToPost(PostDTO postDTO);

    @Mapping(target="userId", expression = "java(comment.getUser().getId())")
    PostCommentDTO commentToDto(PostComment comment);

}
