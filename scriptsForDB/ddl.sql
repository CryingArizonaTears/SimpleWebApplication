CREATE TYPE gender_enum AS ENUM ('MALE','FEMALE','OTHER');

CREATE TABLE employee (
	employee_id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	department_id BIGINT NOT NULL,
	job_title VARCHAR(50) NOT NULL,
	gender gender_enum NOT NULL,
	date_of_birth DATE NOT NULL	
);