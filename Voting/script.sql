CREATE TABLE UserInfo
(
   email varchar(255) PRIMARY KEY NOT NULL,
   registerDate TIMESTAMP NOT NULL,
   age NUMERIC NOT NULL
);

CREATE TABLE Course
(
   vote_id NUMERIC PRIMARY KEY NOT NULL,
   email VARCHAR(255) NOT NULL,
   courseName VARCHAR(255)
);

ALTER TABLE Course
ADD CONSTRAINT fk_UserInfo
FOREIGN KEY (email)
REFERENCES UserInfo(email);

create sequence VOTE_SEQ start with 1;
