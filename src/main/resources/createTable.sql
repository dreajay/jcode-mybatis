drop database if exists mybatis;
create database mybatis;
use mybatis;
drop table if exists user;
create table user (
	userId int auto_increment primary key,
	userName varchar(20),
	sex varchar(2),
	address varchar(50)
);
	
drop table if exists role;
create table role (
	roleId int auto_increment primary key,
	roleName varchar(20),
	parentId int
);

drop table if exists user_role;
create table user_role (
	userId int auto_increment primary key,
	roleId varchar(16)
);



insert into user values (null, '张三', '男', '中国');

