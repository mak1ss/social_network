CREATE TABLE token
(
    id              INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id         INT          NOT NULL UNIQUE,
    refresh_token   VARCHAR(255) NOT NULL,
    expiration_date DATETIME     NOT NULL
);

ALTER TABLE token
    ADD CONSTRAINT fk_token_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)