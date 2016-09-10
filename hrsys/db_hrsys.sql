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
  );

INSERT INTO employee VALUES (null, 'Kaifu', 'Wang', 'Male', '1991-10-08', '123456', 'Married', 'China', 'Master', '2016-03-03', 'kaifuww@gmail.com', 'Developer', '4129999999', 'pittsburgh', null, 03);
