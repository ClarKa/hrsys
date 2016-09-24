DROP DATABASE IF EXISTS HRSYS;
CREATE DATABASE HRSYS;
USE HRSYS;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS department;
CREATE TABLE department (
  dp_department_id int NOT NULL AUTO_INCREMENT,
  dp_department_name varchar(100) NOT NULL,
  PRIMARY KEY (dp_department_id)
);

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO department VALUES (0, 'Undecided');
INSERT INTO department VALUES (null, 'Admin Office');
INSERT INTO department VALUES (null, 'Human Resource');
INSERT INTO department VALUES (null, 'Technique');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  em_employee_id INT NOT NULL AUTO_INCREMENT,
  em_firstname varchar(100) NOT NULL,
  em_lastname varchar(100) NOT NULL,
  em_gender varchar(11) DEFAULT NULL,
  em_birth date DEFAULT NULL,
  em_ssn varchar(11) DEFAULT NULL UNIQUE,
  em_marriage varchar(20) DEFAULT NULL,
  em_nationality varchar(50) DEFAULT NULL,
  em_education varchar(20) DEFAULT NULL,
  em_enrollment_date date DEFAULT NULL,
  em_email varchar(50) DEFAULT NULL UNIQUE,
  em_position varchar(100) DEFAULT NULL,
  em_phone varchar(11) DEFAULT NULL UNIQUE,
  em_address varchar(200) DEFAULT NULL,
  em_comment text,
  em_department_id int DEFAULT NULL,
  PRIMARY KEY (em_employee_id),
  CONSTRAINT FK_dep_emp FOREIGN KEY (em_department_id) REFERENCES department(dp_department_id) 
  ON DELETE SET NULL
  );

INSERT INTO employee VALUES (null, 'Kaifu', 'Wang', 'Male', '1991-10-08', '123-45-4446', 'Married', 'China', 'Master', '2014-03-03', 'kaifuww@gmail.com', 'Developer', '4129999999', 'pittsburgh', null, 03);
INSERT INTO employee VALUES (null, 'Kevin', 'Zhang', 'Male', '1991-10-08', '123-45-5556', 'Married', 'China', 'Master', '2016-03-03', 'kaifuw@gmail.com', 'Developer', '4129999899', 'pittsburgh', null, 02);


-- ----------------------------
-- Table structure for `attendance`
-- ----------------------------
DROP TABLE IF EXISTS attendance;
CREATE TABLE attendance (
  at_employee_id INT NOT NULL,
  at_date date NOT NULL,
  at_in_time time default null,
  at_out_time time default null,
  at_comment varchar(200) default null,
  PRIMARY KEY (at_employee_id, at_date),
  CONSTRAINT FK_att_emp FOREIGN KEY (at_employee_id) REFERENCES employee (em_employee_id)
  ON DELETE CASCADE
  );

INSERT INTO attendance VALUES(1, DATE("2015-9-11"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2015-9-12"), TIME("09:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2015-9-13"), TIME("10:00:00"), TIME("19:00:00"), "Late");
INSERT INTO attendance VALUES(1, DATE("2015-9-14"), TIME("09:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2015-9-15"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2015-9-16"), TIME("08:30:00"), TIME("15:00:00"), "Early Leave");
INSERT INTO attendance VALUES(1, DATE("2015-9-17"), TIME("08:20:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2015-9-18"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2015-9-19"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2015-9-20"), TIME("08:10:00"), TIME("12:00:00"), "Early Leave");
INSERT INTO attendance VALUES(1, DATE("2015-9-21"), null, null, "absence");

INSERT INTO attendance VALUES(2, DATE("2016-9-11"), NOW(), NOW(), "Absence");
INSERT INTO attendance VALUES(1, DATE("2016-9-11"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2016-9-12"), TIME("09:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2016-9-13"), TIME("10:00:00"), TIME("19:00:00"), "Late");
INSERT INTO attendance VALUES(1, DATE("2016-9-14"), TIME("09:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2016-9-15"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2016-9-16"), TIME("08:30:00"), TIME("15:00:00"), "Early Leave");
INSERT INTO attendance VALUES(1, DATE("2016-9-17"), TIME("08:20:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2016-9-18"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2016-9-19"), TIME("08:00:00"), TIME("19:00:00"), "Normal");
INSERT INTO attendance VALUES(1, DATE("2016-9-20"), TIME("08:10:00"), TIME("12:00:00"), "Early Leave");


