drop table if exists file_t;
create table file_t
(
  id     int auto_increment primary key,
  fileId   varchar(64) not null,
  fileName varchar(64) default ''          not null,
  fileFullPath    varchar(512)                    not null,
  fileRealName    varchar(256)                    not null,
  constraint file_u_index unique (id, fileId)
)
comment 'file';