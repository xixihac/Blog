CREATE TABLE reply (
`id`  int NOT NULL AUTO_INCREMENT ,
`question_id`  int NULL ,
`sender`  int NULL ,
`receiver`  int NULL ,
`type`  int NULL ,
`gmt_create`  bigint NULL ,
`is_read` int NULL ,
PRIMARY KEY (`id`)
)
;