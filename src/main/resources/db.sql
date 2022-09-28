
# 创建数据库

drop database courseDesign;

create database if not exists courseDesign;

use courseDesign;


# 普通用户表
drop table if exists user;

create table if not exists user(
        userid int primary key auto_increment,
        username varchar(10) not null unique ,
        password varchar(10) not null
);

# 管理员用户表，不对外开放,只支持在数据库中增删改查

drop table if exists administrator;

create table if not exists administrator(
        id int primary key auto_increment,
        name varchar(10) not null unique ,
        password varchar(10) not null
);

# 管理员账户信息表


# 设备信息表

drop table if exists equipment;

create table if not exists equipment(
    id int primary key auto_increment,
    name varchar(20) not null unique ,
    typeid int,
    typename varchar(20),
    number int,
    unit varchar(20),
    location varchar(20),
    purchaseDate varchar(20),
    price decimal(4,2)
);

# 设备类别表

drop table if exists equipmentType;

create table if not exists equipmentType(
    typeid int(20) primary key ,
    typeName varchar(20)
);

# 设备申请表

drop table if exists equipmentBorrow;

create table if not exists equipmentBorrow(
    borrowId int primary key auto_increment,
    name varchar(20),
    typename varchar(20),
    borrowDate varchar(20),
    returnDate varchar(20),
    borrowNumber int,
    returnNumber int,
    peopel varchar(20),
    state varchar(20) default '未归还'
);

# 申请人员信息表

drop table if exists BorrowPeopel;

create table if not exists BorrowPeopel(
    peopelId varchar(20) primary key ,
    peopelName varchar(20),
    peopelTypeName varchar(20),
    sex varchar(20),
    age int,
    phone varchar(20)
);

# 申请人员类别表

drop table if exists BorrowPeopelType;

create table if not exists BorrowPeopelType(
    peopelTypeId varchar(20) primary key ,
    peopelTypeName varchar(20)
);

# 设备维修表

drop table if exists repair;

create table if not exists Repair(
    id int primary key auto_increment,
    name varchar(20) not null unique ,
    typename varchar(20),
    number int,
    workerName varchar(20),
    workDate varchar(20),
    agree varchar(20) default '未批准'
);