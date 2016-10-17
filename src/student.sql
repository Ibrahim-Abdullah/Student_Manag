create database `studentdatabase`;
use studentdatabase;

create table studentdata (
StudentID varchar(10) not null,
FirstName varchar(20),
Surname varchar(20),
AdmissionYear integer,
GPA float,
Program varchar(30),PRIMARY KEY(StudentID 
));