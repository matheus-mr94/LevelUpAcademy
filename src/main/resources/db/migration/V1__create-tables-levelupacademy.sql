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

CREATE TABLE Activity (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    `code` VARCHAR(100) UNIQUE NOT NULL,
    sequence INT UNSIGNED,
    section_id BIGINT NOT NULL,
    `active` BOOLEAN,
    CONSTRAINT `fk_activity_section` FOREIGN KEY(`section_id`) REFERENCES `Activity`(`id`)
);

CREATE TABLE Video (
    url VARCHAR(255) NOT NULL,
    duration_in_minutes INT UNSIGNED,
    transcription VARCHAR(255),
    activity_id BIGINT NOT NULL,
    CONSTRAINT `fk_video_activity` FOREIGN KEY(`activity_id`) REFERENCES `Activity`(`id`)
);

CREATE TABLE Question (
    statement VARCHAR(255) NOT NULL,
    question_type ENUM('SINGLE_CHOICE','MULTIPLES_CHOICES','TRUE_OR_FALSE'),
    activity_id BIGINT NOT NULL,
    CONSTRAINT `fk_question_activity` FOREIGN KEY(`activity_id`) REFERENCES `Activity`(`id`)
);

CREATE TABLE Explanation (
    `text` VARCHAR(255),
    activity_id BIGINT NOT NULL,
    CONSTRAINT `fk_explanation_activity` FOREIGN KEY(`activity_id`) REFERENCES `Activity`(`id`)
);

CREATE TABLE Alternative (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `text` VARCHAR(150),
    sequence INT UNSIGNED,
    correct BOOLEAN,
    justification VARCHAR(500),
    question_id BIGINT NOT NULL,
    CONSTRAINT `fk_alternative_question` FOREIGN KEY(`question_id`) REFERENCES `Question`(`activity_id`)
);

CREATE TABLE `Role` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `User` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);

CREATE TABLE `User_Role` (
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    KEY `fk_userRole_role` (`role_id`),
    KEY `fk_userRole_user` (`user_id`),
    CONSTRAINT `fk_userRole_role` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`),
CONSTRAINT `fk_userRole_user` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);