CREATE DATABASE course_project;

CREATE TABLE course_student
(
  user_id BIGSERIAL REFERENCES users (id),
  course_id BIGSERIAL REFERENCES course (id),
  result int,
  PRIMARY KEY (user_id, course_id)
);

CREATE TABLE course_teacher
(
  user_id BIGSERIAL REFERENCES users (id),
  course_id BIGSERIAL REFERENCES course (id),
  PRIMARY KEY (user_id, course_id)
);

CREATE TABLE course
(
  id BIGSERIAL PRIMARY KEY,
  description VARCHAR(255),
  duration_hours INT,
  name VARCHAR(255),
  plan VARCHAR(255),
  start_date DATE,
  type VARCHAR(255)
);

create table course_comments
(
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users (id),
  comment VARCHAR(255),
  course_id BIGSERIAL REFERENCES course (id)
);

CREATE TABLE kb_comments
(
  id BIGSERIAL PRIMARY KEY,
  user_id BIGSERIAL REFERENCES users (id),
  comment VARCHAR(255),
  knowlege_base_id BIGSERIAL REFERENCES knowlege_base (id)
);

CREATE TABLE knowlege_base
(
  id BIGSERIAL PRIMARY KEY,
  course_id BIGSERIAL REFERENCES course (id),
  user_id BIGSERIAL REFERENCES users (id),
  date DATE,
  text VARCHAR(255)
);

CREATE TABLE users
(
  dtype VARCHAR(31) NOT NULL,
  id BIGSERIAL PRIMARY KEY,
  e_mail VARCHAR(255),
  password VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  middle_name VARCHAR(255),
  phone VARCHAR(255),
  role VARCHAR(255),
  result INT,
  block_list BOOLEAN DEFAULT false
);