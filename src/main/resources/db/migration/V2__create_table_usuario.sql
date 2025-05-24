create table user(
    id bigint not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

insert into user values(1, 'Renan Ribeiro', 'rhribeiro_25@hotmail.com');