drop table if exists user cascade;
drop table if exists dictionary cascade;

create table user (
  id bigint primary key,
  username varchar_ignorecase(50) not null,
  time_created timestamp not null,
  time_updated timestamp not null
);

create index idx_user_username on user (username);
create sequence user_id_seq start with 1 increment by 1;
alter table user alter column id set default nextval('user_id_seq');
create unique index unique_user_username on user (username);

create table dictionary (
  id bigint primary key,
  word varchar_ignorecase(50) not null,
  time_created timestamp not null,
  time_updated timestamp not null
);

create index idx_dictionary_word on dictionary (word);
create sequence dictionary_id_seq start with 1 increment by 1;
alter table dictionary alter column id set default nextval('dictionary_id_seq');
create unique index unique_dictionary_word on dictionary (word);