CREATE DATABASE LevelUpAcademy_test DEFAULT CHARACTER SET utf8;

USE LevelUpAcademy_test;

CREATE TABLE `Category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `study_guide` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `url_image` varchar(255) DEFAULT NULL,
  `hex_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.`Role` definition

CREATE TABLE `Role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.`User` definition

CREATE TABLE `User` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.Subcategory definition

CREATE TABLE `Subcategory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `study_guide` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_subcategory_category` (`category_id`),
  CONSTRAINT `fk_subcategory_category` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.User_Role definition

CREATE TABLE `User_Role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `fk_userRole_role` (`role_id`),
  KEY `fk_userRole_user` (`user_id`),
  CONSTRAINT `fk_userRole_role` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`),
  CONSTRAINT `fk_userRole_user` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.Course definition

CREATE TABLE `Course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `estimated_time_in_hours` int unsigned NOT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `instructor` varchar(50) NOT NULL,
  `syllabus` text,
  `developed_skills` text,
  `subcategory_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_course_subcategory` (`subcategory_id`),
  CONSTRAINT `fk_course_subcategory` FOREIGN KEY (`subcategory_id`) REFERENCES `Subcategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.`Section` definition

CREATE TABLE `Section` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `exam` tinyint(1) DEFAULT NULL,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_section_course` (`course_id`),
  CONSTRAINT `fk_section_course` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.Activity definition

CREATE TABLE `Activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `section_id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_activity_section` (section_id),
  CONSTRAINT `fk_activity_section` FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.Explanation definition

CREATE TABLE `Explanation` (
  `text` varchar(255) DEFAULT NULL,
  `activity_id` bigint NOT NULL,
  KEY `fk_explanation_activity` (`activity_id`),
  CONSTRAINT `fk_explanation_activity` FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.Question definition

CREATE TABLE `Question` (
  `statement` varchar(255) NOT NULL,
  `question_type` enum('SINGLE_CHOICE','MULTIPLES_CHOICES','TRUE_OR_FALSE') DEFAULT NULL,
  `activity_id` bigint NOT NULL,
  KEY `fk_question_activity` (`activity_id`),
  CONSTRAINT `fk_question_activity` FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.Video definition

CREATE TABLE `Video` (
  `url` varchar(255) NOT NULL,
  `duration_in_minutes` int unsigned DEFAULT NULL,
  `transcription` varchar(255) DEFAULT NULL,
  `activity_id` bigint NOT NULL,
  KEY `fk_video_activity` (`activity_id`),
  CONSTRAINT `fk_video_activity` FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- LevelUpAcademy.Alternative definition

CREATE TABLE `Alternative` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `text` varchar(150) DEFAULT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `correct` tinyint(1) DEFAULT NULL,
  `justification` varchar(500) DEFAULT NULL,
  `question_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_alternative_question` (`question_id`),
  CONSTRAINT `fk_alternative_question` FOREIGN KEY (`question_id`) REFERENCES `Question` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
