DROP TABLE survey;
DROP TABLE prompt;
DROP TABLE prompt_dialog_mapping;
DROP TABLE prompt_response_type;
DROP TABLE patient_survey;
DROP TABLE patient_answer;

CREATE TABLE IF NOT EXISTS survey (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
	tenant VARCHAR(100) NOT NULL,
	prompt_ids text[]
);

CREATE TABLE IF NOT EXISTS prompt (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    survey_id VARCHAR(255) NOT NULL,
    text text NOT NULL
);

CREATE TABLE IF NOT EXISTS  prompt_dialog_mapping (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    prompt_id VARCHAR(255) NOT NULL,
    mappings JSON NOT NULL
);

CREATE TABLE IF NOT EXISTS prompt_response_type (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    prompt_id VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL,
    options JSON NULL
);

CREATE TABLE IF NOT EXISTS patient_survey (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    patient_data_id VARCHAR(255) NOT NULL,
    survey_id VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS patient_answer (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    prompt_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    patient_survey_id VARCHAR(255) NOT NULL,
    value JSON NULL
);

CREATE TABLE IF NOT EXISTS patient_data (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    data JSON NOT NULL
);