CREATE TABLE user_role(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY(`role_id`) REFERENCES `role` (`id`)
);

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (2, 1, 2);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (3, 1, 3);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (4, 1, 4);

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (5, 2, 1);

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (6, 3, 3);