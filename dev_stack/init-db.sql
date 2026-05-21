
create database yastodev_local;

create user user_local with password 'yastodev_local';

grant all privileges on database yastodev_local to user_local;

alter database yastodev_local owner to user_local;
