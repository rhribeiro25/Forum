create table course(
    id bigint not null auto_increment,
    name varchar(50) not null,
    category varchar(50) not null,
    primary key(id)
);

insert into course values(1, 'Kotlin', 'Back-end');
insert into course values(2, 'HTML', 'Front-end');