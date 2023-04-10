ALTER TABLE followings
    ADD CONSTRAINT unique_following_followed UNIQUE (following_user_id, followed_user_id);

ALTER TABLE users
    ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT unique_nickname UNIQUE (nickname);

ALTER TABLE posts
    DROP CONSTRAINT fk_user_id;
ALTER TABLE posts
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE followings
    DROP CONSTRAINT fk_followed_user_id;
ALTER TABLE followings
    ADD CONSTRAINT fk_followed_user_id FOREIGN KEY (followed_user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE followings
    DROP CONSTRAINT fk_following_user_id;
ALTER TABLE followings
    ADD CONSTRAINT fk_following_user_id FOREIGN KEY (following_user_id) REFERENCES users(id) ON DELETE CASCADE;