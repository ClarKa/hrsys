-- Version Info
-- +-------------------------+------------------------------+
-- | innodb_version          | 5.7.9                        |
-- | protocol_version        | 10                           |
-- | slave_type_conversions  |                              |
-- | version                 | 5.7.9                        |
-- | version_comment         | MySQL Community Server (GPL) |
-- | version_compile_machine | x86_64                       |
-- | version_compile_os      | osx10.9                      |
-- +-------------------------+------------------------------+


DROP DATABASE IF EXISTS HRSYS;
CREATE DATABASE HRSYS;
USE HRSYS;

-- ----------------------------
-- Spring Secrurity default database schema
-- ----------------------------

CREATE TABLE users(
  username varchar(50) NOT NULL PRIMARY KEY,
  password varchar(50) NOT NULL,
  enabled boolean NOT NULL,
  us_employee_id INT DEFAULT NULL,
  us_role_id INT DEFAULT 1
);

INSERT INTO users VALUES ('a', '1', TRUE, 1, 1);
INSERT INTO users VALUES ('b', '1', TRUE, 2, 2);
INSERT INTO users VALUES ('c', '1', TRUE, 3, 1);
INSERT INTO users VALUES ('d', '1', TRUE, null, 1);

CREATE TABLE roles (
  rl_role_id INT PRIMARY KEY,
  -- username VARCHAR(50) NOT NULL,
  rl_role_name VARCHAR(50) NOT NULL UNIQUE
  -- CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

-- CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles VALUES (3, 'ROLE_SUPERADMIN');

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
INSERT INTO department VALUES (null, 'Undecided');
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

INSERT INTO employee VALUES (null, 'Kaifu', 'Wang', 'Male', '1991-10-08', '123-45-4446', 'Married', 'CN', 'Master', '2014-03-03', 'kaifuww@gmail.com', 'Developer', '4129999999', 'pittsburgh', null, 03);
INSERT INTO employee VALUES (null, 'Zac', 'Zhou', 'Male', '1991-01-08', '123-45-5556', 'Married', 'CN', 'Master', '2016-03-03', 'kaifuw@gmail.com', 'Developer', '4129999899', 'pittsburgh', null, 02);
INSERT INTO employee VALUES (null, 'Menglei', 'Zhang', 'Male', '1991-09-25', '123-45-5557', 'Single', 'CN', 'Bachelor', '2015-04-03', 'mengleizhangm@gmail.com', 'Developer', '2134445559', 'Connecticut', null, 02);


-- ----------------------------
-- Table structure for `attendance`
-- ----------------------------
DROP TABLE IF EXISTS attendance;
CREATE TABLE attendance (
  at_employee_id INT NOT NULL,
  at_date date NOT NULL,
  at_in_time time DEFAULT NULL,
  at_out_time time DEFAULT NULL,
  at_status varchar(20) DEFAULT NULL,
  at_comment varchar(200) DEFAULT NULL,
  PRIMARY KEY (at_employee_id, at_date),
  CONSTRAINT FK_att_emp FOREIGN KEY (at_employee_id) REFERENCES employee (em_employee_id)
  ON DELETE CASCADE
  );

INSERT INTO attendance VALUES(1, DATE("2015-9-11"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2015-9-12"), TIME("09:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2015-9-13"), TIME("10:00:00"), TIME("19:00:00"), "Late", "Im sick");
INSERT INTO attendance VALUES(1, DATE("2015-9-14"), TIME("09:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2015-9-15"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2015-9-16"), TIME("08:30:00"), TIME("15:00:00"), "Early Leave", "Period");
INSERT INTO attendance VALUES(1, DATE("2015-9-17"), TIME("08:20:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2015-9-18"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2015-9-19"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2015-9-20"), TIME("08:10:00"), TIME("12:00:00"), "Early Leave", "waichu");
INSERT INTO attendance VALUES(1, DATE("2015-9-21"), null, null, "absence", "Im the boss");

INSERT INTO attendance VALUES(2, DATE("2016-9-11"), NOW(), NOW(), "Absence", "blah");
INSERT INTO attendance VALUES(1, DATE("2016-9-11"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2016-9-12"), TIME("09:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2016-9-13"), TIME("10:00:00"), TIME("19:00:00"), "Late", "rain");
INSERT INTO attendance VALUES(1, DATE("2016-9-14"), TIME("09:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2016-9-15"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2016-9-16"), TIME("08:30:00"), TIME("15:00:00"), "Early Leave", "good");
INSERT INTO attendance VALUES(1, DATE("2016-9-17"), TIME("08:20:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2016-9-18"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2016-9-19"), TIME("08:00:00"), TIME("19:00:00"), "Normal", "");
INSERT INTO attendance VALUES(1, DATE("2016-9-20"), TIME("08:10:00"), TIME("12:00:00"), "Early Leave", "Im the boss too");

-- ----------------------------
-- Table structure for `Training`
-- ----------------------------
DROP TABLE IF EXISTS Training;
CREATE TABLE training (
  tr_employee_id INT NOT NULL,
  tr_date date NOT NULL,
  tr_hour INT NOT NULL DEFAULT 0,
  tr_approved boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (tr_employee_id, tr_date),
  CONSTRAINT FK_tr_emp FOREIGN KEY (tr_employee_id) REFERENCES employee (em_employee_id)
  ON DELETE CASCADE
  );

INSERT INTO training VALUES(1, DATE("2016-10-11"), 8, false);
INSERT INTO training VALUES(1, DATE("2016-10-12"), 8, false);
INSERT INTO training VALUES(1, DATE("2016-10-13"), 8, false);
INSERT INTO training VALUES(1, DATE("2016-10-1"), 8, true);
INSERT INTO training VALUES(1, DATE("2016-10-2"), 8, true);
INSERT INTO training VALUES(1, DATE("2016-10-3"), 8, false);
INSERT INTO training VALUES(1, DATE("2016-10-4"), 8, true);
INSERT INTO training VALUES(2, DATE("2016-9-11"), 8, false);
INSERT INTO training VALUES(2, DATE("2016-9-12"), 8, false);
INSERT INTO training VALUES(2, DATE("2016-9-13"), 8, false);
INSERT INTO training VALUES(2, DATE("2016-9-1"), 8, true);
INSERT INTO training VALUES(2, DATE("2016-9-2"), 8, true);
INSERT INTO training VALUES(2, DATE("2016-9-3"), 8, false);
INSERT INTO training VALUES(3, DATE("2016-10-3"), 5, false);

-- ----------------------------
-- Table structure for `bank`
-- ----------------------------
DROP TABLE IF EXISTS bank;
CREATE TABLE bank (
  bk_employee_id INT NOT NULL,
  bk_id INT NOT NULL,
  bk_nickname VARCHAR(20) NOT NULL,
  bk_account_type VARCHAR(10) NOT NULL,
  bk_routing_number INT(9) NOT NULL,
  bk_account_number VARCHAR(50) NOT NULL,
  bk_percent SMALLINT DEFAULT 0,
  PRIMARY KEY (bk_employee_id, bk_id),
  CONSTRAINT FK_bk_emp FOREIGN KEY (bk_employee_id) REFERENCES employee (em_employee_id)
  ON DELETE CASCADE
  );

DROP TRIGGER IF EXISTS check_bank_insert;
DROP TRIGGER IF EXISTS check_bank_update;
DELIMITER $$

CREATE TRIGGER check_bank_insert
  BEFORE INSERT
  ON bank
  FOR EACH ROW
BEGIN
  DECLARE sumPercent INT DEFAULT 0;

  IF NEW.bk_nickname in (SELECT bk_nickname FROM bank b WHERE b.bk_employee_id = NEW.bk_employee_id) THEN
    SIGNAL sqlstate '45000' set message_text = 'Bank account nickname already exists.';
  END IF;

  IF NEW.bk_percent < 0 OR NEW.bk_percent > 100 THEN
    SIGNAL sqlstate '45000' set message_text = 'Invalid percent value!';
  END IF;

  IF IFNULL(NEW.bk_id, 0) = 0 THEN
    SET NEW.bk_id = (SELECT COUNT(1) FROM bank b WHERE b.bk_employee_id = NEW.bk_employee_id) + 1;
  END IF;

  SET @sumPercent = (SELECT SUM(bk_percent) FROM bank b WHERE b.bk_employee_id = NEW.bk_employee_id) + NEW.bk_percent;
  IF @sumPercent  > 100 THEN
    SIGNAL sqlstate '45000' set message_text = 'Total percent cannot exceed 100!';
  END IF;
END$$

CREATE TRIGGER check_bank_update
  BEFORE UPDATE
  ON bank
  FOR EACH ROW
BEGIN
  DECLARE sumPercent INT DEFAULT 0;

  IF NEW.bk_percent<0 OR NEW.bk_percent>100 THEN
    SIGNAL sqlstate '45000' set message_text = 'Invalid percent value!';
  END IF;

  -- SET @sumPercent = (SELECT SUM(bk_percent) FROM bank b WHERE b.bk_employee_id = NEW.bk_employee_id AND b.bk_id <> NEW.bk_id) + NEW.bk_percent;
  -- IF @sumPercent  > 100 THEN
  --   SIGNAL sqlstate '45000' set message_text = 'Total percent cannot exceed 100!';
  -- END IF;
END$$

DELIMITER ;


INSERT INTO bank VALUES(1, NULL, "PNC", "CHECKING", "1234567", "1234567788", 100);
INSERT INTO bank VALUES(2, NULL, "PNC", "CHECKING", "1234567", "1234567788", 90);
INSERT INTO bank VALUES(2, NULL, "Chase", "SAVING", "1234567", "1234567788", 10);
INSERT INTO bank VALUES(3, NULL, "PNC", "CHECKING", "1234567", "1234567788", 100);
-- INSERT INTO bank VALUES(1, NULL, "BOAaaa", "CHECKING", "1234567", "1234567788", 100);
-- UPDATE bank SET bk_percent = 101 WHERE bk_employee_id = 1;
-- UPDATE bank SET bk_percent = -1 WHERE bk_employee_id = 1;

-- ----------------------------
-- Table structure for `paychecks`
-- ----------------------------
DROP TABLE IF EXISTS paychecks;
CREATE TABLE paychecks (
  pc_employee_id INT NOT NULL,
  pc_payment_method VARCHAR(10) NOT NULL,
  PRIMARY KEY (pc_employee_id),
  CONSTRAINT FK_pc_emp FOREIGN KEY (pc_employee_id) REFERENCES employee (em_employee_id)
  ON DELETE CASCADE
  );

INSERT INTO paychecks VALUES(1, "PC");
INSERT INTO paychecks VALUES(2, "DD");
INSERT INTO paychecks VALUES(3, "DD");
