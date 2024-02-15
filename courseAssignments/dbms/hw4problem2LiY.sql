CREATE TABLE pupil (
    identification_number INT,
	first_name VARCHAR(100),
    last_name VARCHAR(100),
    sex VARCHAR(100),
    date_of_birth VARCHAR(100),
    FOREIGN KEY(class_number) REFERENCES class(class_number),
    FOREIGN KEY(school_id) REFERENCES school(school_id)
);
CREATE TABLE class (
    class_number INT,
    FOREIGN KEY(name_of_subject) REFERENCES class_subject(name_of_subject),
    FOREIGN KEY(school_id) REFERENCES school(school_id)
);
CREATE TABLE class_subject (
    name_of_subject VARCHAR(100),
    subject_level VARCHAR(100),
    abbreviation VARCHAR(100)
);
CREATE TABLE school (
	school_id INT,
    school_name VARCHAR(100),
    address VARCHAR(100),
    phone VARCHAR(100)
);
CREATE TABLE teacher (
    national_insurance_number INT,
	first_name VARCHAR(100),
    last_name VARCHAR(100),
    sex VARCHAR(100),
    qualifications VARCHAR(100),
    teacher_type VARCHAR(100),
    FOREIGN KEY(class_number) REFERENCES class(class_number),
    FOREIGN KEY(school_id) REFERENCES school(school_id)
);
