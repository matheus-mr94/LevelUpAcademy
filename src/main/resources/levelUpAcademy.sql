CREATE DATABASE LevelUpAcademy DEFAULT CHARACTER SET utf8;

USE LevelUpAcademy;

CREATE TABLE Category (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
`description` VARCHAR(255),
study_guide VARCHAR(255),
`active` BOOLEAN,
sequence INT UNSIGNED,
url_image VARCHAR(255),
hex_code VARCHAR(10)
);

CREATE TABLE Subcategory (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
`description` VARCHAR(255),
study_guide VARCHAR(255),
`active` BOOLEAN,
sequence INT UNSIGNED,
category_id BIGINT NOT NULL ,
CONSTRAINT `fk_subcategory_category` FOREIGN KEY(`category_id`) REFERENCES `Category`(`id`)
);

CREATE TABLE Course (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
estimated_time_in_hours INT UNSIGNED NOT NULL,
`visible` BOOLEAN,
target VARCHAR(255),
instructor VARCHAR(50) NOT NULL,
syllabus TEXT,
developed_skills TEXT,
subcategory_id BIGINT NOT NULL,
CONSTRAINT `fk_course_subcategory` FOREIGN KEY(`subcategory_id`) REFERENCES `Subcategory`(`id`)
);

CREATE TABLE Section (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
sequence INT UNSIGNED,
`active` BOOLEAN,
exam BOOLEAN,
course_id BIGINT NOT NULL,
CONSTRAINT `fk_section_course` FOREIGN KEY(`course_id`) REFERENCES `Course`(`id`) 
);

CREATE TABLE Video (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
sequence INT UNSIGNED,
section_id BIGINT NOT NULL,
url VARCHAR(255) NOT NULL,
duration_in_minutes INT UNSIGNED,
transcription VARCHAR(255),
CONSTRAINT `fk_video_section` FOREIGN KEY(`section_id`) REFERENCES `Section`(`id`)
);

CREATE TABLE Question (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
sequence INT UNSIGNED,
section_id BIGINT NOT NULL,
statement VARCHAR(255) NOT NULL,
question_type ENUM('SINGLE_CHOICE','MULTIPLES_CHOICES','TRUE_OR_FALSE'),
CONSTRAINT `fk_question_section` FOREIGN KEY(`section_id`) REFERENCES `Section`(`id`)
);

CREATE TABLE Explanation (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(150) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
sequence INT UNSIGNED,
section_id BIGINT NOT NULL,
`text` VARCHAR(255),
CONSTRAINT `fk_explanation_section` FOREIGN KEY(`section_id`) REFERENCES `Section`(`id`)
);

CREATE TABLE Alternative (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
`text` VARCHAR(150),
sequence INT UNSIGNED,
correct BOOLEAN,
justification VARCHAR(500),
question_id BIGINT NOT NULL,
CONSTRAINT `fk_alternative_question` FOREIGN KEY(`question_id`) REFERENCES `Question`(`id`)
);







