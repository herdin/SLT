USE testdb;
SHOW VARIABLES LIKE 'char%';
set names utf8;

CREATE TABLE TEST_DATA (
  ID VARCHAR(50) NOT NULL,
  DE VARCHAR(50) NOT NULL,
  PRIMARY KEY(ID)
);
SELECT * FROM TEST_DATA;
SELECT COUNT(1) FROM TEST_DATA;
DELETE FROM TEST_DATA;
INSERT INTO TEST_DATA VALUES('10001', 'TEST_NAME_A');
INSERT INTO TEST_DATA VALUES('10002', 'TEST_NAME_B');
INSERT INTO TEST_DATA VALUES('10003', 'AWS_TEST_NAME_C');
INSERT INTO TEST_DATA VALUES('10004', 'TEST_NAME_D');
INSERT INTO TEST_DATA VALUES('10005', 'TEST_NAME_E');
INSERT INTO TEST_DATA VALUES('10006', 'TEST_NAME_F');
INSERT INTO TEST_DATA VALUES('10007', 'TEST_NAME_G');
DROP TABLE TEST_DATA;
;
CREATE TABLE TEST_USER (
  USERNAME VARCHAR(50) NOT NULL,
  PASSWORD VARCHAR(64) NOT NULL,
  PRIMARY KEY(USERNAME)
);
INSERT INTO TEST_USER VALUES('testherdin', SHA2('12341234', 256));
SELECT * FROM TEST_USER;
DROP TABLE TEST_USER
;

SELECT ID, DE FROM TEST_DATA WHERE 1=1 AND ID like CONCAT('%', '000', '%')