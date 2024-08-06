package com.network.services;

import com.network.daos.AbstractDao;
import com.network.daos.PostDao;
import com.network.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService extends AbstractService<Post> {

    private final PostDao dao;
    private final AuthorizationService authService;

    @Override
    protected AbstractDao<Post> getDao() {
        return dao;
    }


    @Override
    protected void beforeDelete(Post entity) {
        if(authService.isOperationAuthorizedOrPerformedByAdmin(entity.getUser().getId())){
            return;
        }

        throw new AccessDeniedException("You do not have permission to update this object");
    }

    @Override
    protected void beforeUpdate(Post entity) {
        if(authService.isOperationAuthorizedOrPerformedByAdmin(entity.getUser().getId())){
            return;
        }

        throw new AccessDeniedException("You do not have permission to delete this object");
    }


}
