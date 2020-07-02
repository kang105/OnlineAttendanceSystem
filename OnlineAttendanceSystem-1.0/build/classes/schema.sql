
create table AbsenceType(
id int not null primary key auto_increment,
type varchar(20),
delect int not null
);

create table Department(
id int not null primary key auto_increment, 
department varchar(20),
upperDepartment_id int not null
);

create table Manager(
id int not null primary key auto_increment,
username varchar(25) not null,
password varchar(25) not null,
delect int
);

create table Member(
id int not null primary key auto_increment,
username varchar(25) not null,
password varchar(25) not null,
phone varchar(18) not null,
email varchar(30) not null,
sex varchar(4),
department_id int not null,
delect int,
foreign key (department_id) references Department(id)
);

create table Attendance(
id int not null primary key auto_increment,
member_id int not null,
date timestamp not null,
foreign key(member_id) references Member(id)
);

create table Absence(
id int not null primary key auto_increment,
member_id int not null,
type_id int not null,
decription varchar(50),
date datetime not null,
isChecked boolean not null,
checker_id int,
check_time datetime,
foreign key (member_id) references Member(id),
foreign key (type_id) references AbsenceType(id),
foreign key (checker_id) references Manager(id)
);

insert into department(department, upperDepartment_id) values ('公司',1);
insert into department(department, upperDepartment_id) values ('总裁',1);
insert into department(department, upperDepartment_id) values ('董事长',1);
insert into department(department, upperDepartment_id) values ('部长',3);
insert into department(department, upperDepartment_id) values ('人事部门',4);
insert into department(department, upperDepartment_id)values ('财务部门',4);
insert into department(department, upperDepartment_id) values ('员工',5);

insert into absenceType(type, delect) values('病假',0);
insert into absenceType(type, delect) values('急事',0);
insert into absenceType(type, delect) values('年假',0);
insert into absenceType(type, delect) values('看父母',0);
insert into absenceType(type, delect) values('法定假期',0);

insert into manager(id, username,password,delect) values (1,'admin','12345',0);


insert into member(id, username,password, phone ,email, sex, department_id,delect) values(1, 'user','12345','12345','12345@qq.com','男', 1,0);
