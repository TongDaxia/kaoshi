-- kaoshi.exam definition

CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '试卷名称',
  `info` varchar(100) DEFAULT NULL COMMENT '试卷介绍',
  `subject_id` bigint(20) DEFAULT NULL COMMENT '科目id',
  `content` varchar(100) DEFAULT NULL COMMENT '题目号，逗号分隔',
  `cretae_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='试卷表';



-- kaoshi.exercise_option definition

CREATE TABLE `exercise_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '选项内容',
  `exercise_title_id` bigint(20) DEFAULT NULL COMMENT '题干id',
  `subject_name` varchar(100) DEFAULT NULL COMMENT '科目名称',
  `exercise_type` int(10) DEFAULT NULL COMMENT '类型 0 单选题，1多项选择题，2 判断题，3，母题（只有题干）',
  `isTrue` int(1) DEFAULT NULL COMMENT '是否正确答案,0否 1是',
  `cretae_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `exercise_title_id` (`exercise_title_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='题目选项表';