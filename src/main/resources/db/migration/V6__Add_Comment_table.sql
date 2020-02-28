create table comment(
id bigint AUTO_INCREMENT primary key,
context varchar(1024) not null ,
parent_id bigint not null ,
type int not null,
commentator int not null,
gmt_create bigint not null ,
gmt_modified bigint not null ,
like_count bigint default 0

);