CREATE DATABASE LevelUpAcademy DEFAULT CHARACTER SET utf8;

USE LevelUpAcademy;

CREATE TABLE Category (
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(25) NOT NULL UNIQUE PRIMARY KEY,
`description` VARCHAR(150),
study_guide VARCHAR(255),
`active` BOOLEAN,
sequence INT UNSIGNED,
url_image VARCHAR(255),
hex_code VARCHAR(10)
);

CREATE TABLE Subcategory (
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(25) NOT NULL UNIQUE PRIMARY KEY,
`description` VARCHAR(150),
study_guide VARCHAR(255),
`active` BOOLEAN,
sequence INT UNSIGNED,
category_code VARCHAR(25) NOT NULL ,
CONSTRAINT `fk_subcategory_category` FOREIGN KEY(`category_code`) REFERENCES `Category`(`code`)
);

CREATE TABLE Course (
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(25) NOT NULL UNIQUE PRIMARY KEY,
estimated_time_in_hours INT UNSIGNED NOT NULL,
`visible` BOOLEAN,
target VARCHAR(150),
instructor VARCHAR(50) NOT NULL,
syllabus VARCHAR(255),
developed_skills VARCHAR(200),
subcategory_code VARCHAR(25) NOT NULL,
CONSTRAINT `fk_course_subcategory` FOREIGN KEY(`subcategory_code`) REFERENCES `Subcategory`(`code`)
);

CREATE TABLE Section (
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(25) NOT NULL UNIQUE PRIMARY KEY,
sequence INT UNSIGNED,
`active` BOOLEAN,
exam BOOLEAN,
course_code VARCHAR(25) NOT NULL,
CONSTRAINT `fk_section_course` FOREIGN KEY(`course_code`) REFERENCES `Course`(`code`) 
);

CREATE TABLE Video (
title VARCHAR(100) NOT NULL,
`code` VARCHAR(25) NOT NULL UNIQUE PRIMARY KEY,
sequence INT UNSIGNED,
section_code VARCHAR(25) NOT NULL,
url VARCHAR(255) NOT NULL,
duration_in_minutes INT UNSIGNED,
transcription VARCHAR(255),
CONSTRAINT `fk_video_section` FOREIGN KEY(`section_code`) REFERENCES `Section`(`code`)
);

CREATE TABLE Question (
title VARCHAR(100) NOT NULL,
`code` VARCHAR(25) NOT NULL UNIQUE PRIMARY KEY,
sequence INT UNSIGNED,
section_code VARCHAR(25) NOT NULL,
statement VARCHAR(150) NOT NULL,
question_type VARCHAR(30),
CONSTRAINT `fk_question_section` FOREIGN KEY(`section_code`) REFERENCES `Section`(`code`)
);

CREATE TABLE Explanation (
title VARCHAR(100) NOT NULL,
`code` VARCHAR(25) NOT NULL UNIQUE PRIMARY KEY,
sequence INT UNSIGNED,
section_code VARCHAR(25) NOT NULL,
`text` VARCHAR(255),
CONSTRAINT `fk_explanation_section` FOREIGN KEY(`section_code`) REFERENCES `Section`(`code`)
);





