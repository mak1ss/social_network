CREATE TABLE posts_likes (
    post_id int NOT NULL,
    user_id int NOT NULL
);

ALTER TABLE posts_likes
    ADD CONSTRAINT fk_likes_post_id FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE;

ALTER TABLE posts_likes
    ADD CONSTRAINT fk_likes_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

-- Adding unique in order to user cannot to like twice a certain post
ALTER TABLE posts_likes
    ADD CONSTRAINT unique_post_user_id UNIQUE  (post_id, user_id);


CREATE TABLE posts_comments (
    id int  PRIMARY KEY AUTO_INCREMENT NOT NULL,
    post_id int NOT NULL,
    user_id int NOT NULL,
    comment_body varchar(200) NOT NULL
);

ALTER TABLE posts_comments
    ADD CONSTRAINT fk_comments_post_id FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE;

ALTER TABLE posts_comments
    ADD CONSTRAINT fk_comments_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;