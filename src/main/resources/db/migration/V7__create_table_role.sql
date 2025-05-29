CREATE TABLE role(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);

INSERT INTO `role` (`id`, `name`) VALUES (1, 'READ')
INSERT INTO `role` (`id`, `name`) VALUES (2, 'WRITE')
INSERT INTO `role` (`id`, `name`) VALUES (3, 'DELETE')
INSERT INTO `role` (`id`, `name`) VALUES (4, 'ADMIN')
