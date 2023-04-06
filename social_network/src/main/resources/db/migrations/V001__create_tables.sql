CREATE TABLE users
(
    id        int PRIMARY KEY auto_increment NOT NULL,
    full_name varchar(100) NOT NULL,
    nickname  varchar(40)  NOT NULL,
    email     varchar(100) NOT NULL,
    password  varchar(255) NOT NULL
);

CREATE TABLE posts
(
    id            int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id       int      NOT NULL,
    creation_date datetime NOT NULL,
    post_body     text     NOT NULL
);

CREATE TABLE followings
(
    following_user_id int NOT NULL,
    followed_user_id  int NOT NULL
);

ALTER TABLE posts
    ADD CONSTRAINT fk_user_id foreign key (user_id) references users (id);

ALTER TABLE followings
    ADD CONSTRAINT fk_following_user_id foreign key (following_user_id) references users (id);

ALTER TABLE followings
    ADD CONSTRAINT fk_followed_user_id foreign key (followed_user_id) references users (id);
