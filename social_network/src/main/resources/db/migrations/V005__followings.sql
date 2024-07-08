ALTER TABLE followings
    CHANGE COLUMN following_user_id follower_id INT NOT NULL,
    CHANGE COLUMN followed_user_id followed_id INT NOT NULL,
    ADD COLUMN id INT NOT NULL AUTO_INCREMENT,
    ADD COLUMN subscription_date DATETIME,
    ADD CONSTRAINT primary key (id);