package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.entities.PostComment;
import com.practice.social_network.mappers.PostCommentMapper;
import com.practice.social_network.repositories.PostCommentRepository;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.services.intefaces.PostCommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private PostCommentRepository commentRepository;
    private PostRepository postRepository;
    private PostCommentMapper mapper;


    @Override
    public PostCommentResponse createPostComment(PostCommentRequest postCommentRequest) {
        checkIfPostExists(postCommentRequest.getPostId());
        PostComment entity = commentRepository.save(mapper.requestToEntity(postCommentRequest));
        return mapper.entityToResponse(entity);
    }

    @Override
    public PostCommentResponse updatePostComment(PostCommentRequest postCommentRequest) {
        checkIfPostExists(postCommentRequest.getPostId());
        if(postCommentRequest.getId() == null || !commentRepository.existsById(postCommentRequest.getId())) {
            throw new IllegalArgumentException("Post comment doesn't exist");
        }
        PostComment entity = commentRepository.save(mapper.requestToEntity(postCommentRequest));
        return mapper.entityToResponse(entity);
    }

    @Override
    public PostCommentResponse deletePostComment(Integer postCommentId) {
        checkIfPostExists(postCommentId);
        if(!commentRepository.existsById(postCommentId)) {
            throw new IllegalArgumentException("Post comment doesn't exist");
        }
        commentRepository.deleteById(postCommentId);
        return null;
    }

    @Override
    public PostCommentResponse getPostComment(Integer postCommentId) {
        checkIfPostExists(postCommentId);
        PostComment entity = commentRepository.findById(postCommentId)
                .orElseThrow(() -> new IllegalArgumentException("Post comment doesn't exist"));
        return mapper.entityToResponse(entity);
    }

    @Override
    public List<PostCommentResponse> getPostComments(Integer postId, Integer pageNumber) {
        checkIfPostExists(postId);

        return mapper.entitiesToListResponse(commentRepository
                .findByPostId(postId, PageRequest.of(pageNumber, 5))
                .orElseThrow());
    }

    private void checkIfPostExists(Integer postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post does not exist");
        }
    }
}
