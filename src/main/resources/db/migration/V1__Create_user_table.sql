CREATE table user
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    account_id varchar(100),
    name varchar(50),
    token char(36),
    gmt_create BIGINT,
    gmt_modified BIGINT

);