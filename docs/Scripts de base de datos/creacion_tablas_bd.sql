CREATE TABLE apalpro.roles (
	id bigint auto_increment NOT NULL,
	created_at datetime DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	updated_at datetime NULL,
	status tinyint(1) DEFAULT 1 NOT NULL,
	name varchar(64) NOT NULL,
	description varchar(512) NULL,
	CONSTRAINT roles_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_ci;

CREATE TABLE apalpro.users (
	id bigint auto_increment NOT NULL,
	created_at datetime DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	updated_at datetime NULL,
	status tinyint(1) DEFAULT 1 NOT NULL,
	uname varchar(128) NOT NULL,
	pwd varchar(128) NOT NULL,
	names varchar(256) NOT NULL,
	surnames varchar(256) NOT NULL,
	role_id bigint NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT users_un UNIQUE KEY (uname),
	CONSTRAINT users_FK FOREIGN KEY (role_id) REFERENCES apalpro.roles(id) ON DELETE RESTRICT ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_ci;

CREATE TABLE apalpro.exercises (
	id bigint auto_increment NOT NULL,
	created_at datetime DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	updated_at datetime NULL,
	status tinyint(1) DEFAULT 1 NOT NULL,
	name varchar(256) NOT NULL,
	description text NULL,
	CONSTRAINT exercises_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_ci;

CREATE TABLE apalpro.test_cases (
	id bigint auto_increment NOT NULL,
	created_at datetime DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	updated_at datetime NULL,
	status tinyint(1) DEFAULT 1 NOT NULL,
	exercise_id bigint NOT NULL,
	input_data text NULL,
	expected_output text NULL,
	is_visible tinyint(1) DEFAULT 0 NOT NULL,
	CONSTRAINT test_cases_pk PRIMARY KEY (id),
	CONSTRAINT test_cases_FK FOREIGN KEY (exercise_id) REFERENCES apalpro.exercises(id) ON DELETE RESTRICT ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_ci;

CREATE TABLE apalpro.assessment_results (
	id bigint auto_increment NOT NULL,
	created_at datetime DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	updated_at datetime NULL,
	status tinyint(1) DEFAULT 1 NOT NULL,
	name varchar(64) NOT NULL,
	description varchar(512) NULL,
	CONSTRAINT assessment_results_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_ci;

CREATE TABLE apalpro.solution_proposals (
	id bigint auto_increment NOT NULL,
	created_at datetime DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	updated_at datetime NULL,
	status tinyint(1) DEFAULT 1 NOT NULL,
	user_id bigint NOT NULL,
	code mediumtext NULL,
	assessment_result_id bigint NOT NULL,
	CONSTRAINT solution_proposals_pk PRIMARY KEY (id),
	CONSTRAINT solution_proposals_FK FOREIGN KEY (user_id) REFERENCES apalpro.users(id) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT solution_proposals_FK_1 FOREIGN KEY (assessment_result_id) REFERENCES apalpro.assessment_results(id) ON DELETE RESTRICT ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_ci;

CREATE TABLE apalpro.solution_proposal_test_case_assessments (
	id bigint auto_increment NOT NULL,
	created_at datetime DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	updated_at datetime NULL,
	status tinyint(1) DEFAULT 1 NOT NULL,
	solution_proposal_id bigint NOT NULL,
	test_case_id bigint NOT NULL,
	output_data text NULL,
	assessment_result_id bigint NOT NULL,
	CONSTRAINT solution_proposal_test_case_assessments_pk PRIMARY KEY (id),
	CONSTRAINT solution_proposal_test_case_assessments_FK FOREIGN KEY (solution_proposal_id) REFERENCES apalpro.solution_proposals(id) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT solution_proposal_test_case_assessments_FK_1 FOREIGN KEY (test_case_id) REFERENCES apalpro.test_cases(id) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT solution_proposal_test_case_assessments_FK_2 FOREIGN KEY (assessment_result_id) REFERENCES apalpro.assessment_results(id) ON DELETE RESTRICT ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_ci;
