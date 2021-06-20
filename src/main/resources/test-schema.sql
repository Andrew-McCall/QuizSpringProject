DROP TABLE IF EXISTS `quiz` CASCADE;
CREATE TABLE quiz (
    id BIGINT(20) AUTO_INCREMENT,
    description VARCHAR(255),
    name VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `question` CASCADE;
CREATE TABLE question (
    id BIGINT(20) AUTO_INCREMENT,
    answer VARCHAR(255),
    question VARCHAR(255),
    quiz_id BIGINT(20),
    PRIMARY KEY (id)
);