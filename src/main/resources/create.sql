drop table if exists student;
CREATE TABLE student (
    studentId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    studentName NVARCHAR(32),
    studentAge INT,
    studentSex NVARCHAR(8),
    classId INT
);
insert into student (studentName,studentAge,studentSex,classId ) values ("郭靖", 10, "男", 1);
insert into student (studentName,studentAge,studentSex,classId ) values ("黄蓉", 10, "女", 1);


drop table if exists course;
CREATE TABLE course (
    courseId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    courseName NVARCHAR(32)
);
insert into course (courseName ) values ("语文");
insert into course (courseName ) values ("数学");
insert into course (courseName ) values ("英语");
  
  
drop table if exists score;
CREATE TABLE score (
    studentId INT ,
    courseId INT,
    score INT
);



drop table if exists teacher;
CREATE TABLE teacher (
    teacherId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    teacherName NVARCHAR(16)
);



drop table if exists class;
CREATE TABLE class (
    classId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    className NVARCHAR(16)
);
insert into class (className ) values ("1班");
