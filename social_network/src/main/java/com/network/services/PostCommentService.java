package com.network.services;

import com.network.daos.AbstractDao;
import com.network.daos.PostCommentDao;
import com.network.model.PostComment;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostCommentService extends AbstractService<PostComment> {

    private final PostCommentDao dao;
    private final AuthorizationService authService;

    @Override
    protected AbstractDao<PostComment> getDao() {
        return dao;
    }

    @Override
    protected void beforeUpdate(PostComment entity) {
        if(authService.isOperationAuthorizedOrPerformedByAdmin(entity.getUser().getId())) {
            return;
        }

        throw new AccessDeniedException("You do not have permission to update this object");
    }

    @Override
    protected void beforeDelete(PostComment entity) {
        if(authService.isOperationAuthorizedOrPerformedByAdmin(entity.getUser().getId())) {
            return;
        }

        throw new AccessDeniedException("You do not have permission to delete this object");
    }
}
