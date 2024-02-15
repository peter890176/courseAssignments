CREATE TABLE leaderboard (
    number_of_donations INT,
    most_donations_donor VARCHAR(100)
);
CREATE TABLE blood_donation_contest (
    contest_name VARCHAR(100),
    start_date VARCHAR(100),
    end_date VARCHAR(100),
    prize INT
);

CREATE TABLE clinic (
	clinicNo INT,
    clinic_name VARCHAR(100),
    address VARCHAR(100),
    FOREIGN KEY(contest_name) REFERENCES blood_donation_contest(contest_name),
	FOREIGN KEY(empNo) REFERENCES staff(empNo),
    FOREIGN KEY(serial_number) REFERENCES blood_bag(serial_number)
);
CREATE TABLE staff (
    staff_name VARCHAR(100),
    empNo INT,
    starting_date VARCHAR(240),
    FOREIGN KEY(blood_Rh_type) REFERENCES blood(blood_Rh_type),
    FOREIGN KEY(blood_type) REFERENCES blood(blood_type),
    FOREIGN KEY(patient_id) REFERENCES donor(patient_id)
);

CREATE TABLE blood_bag (
    serial_number INT,
	empty_bag INT,
    date_of_the_blood VARCHAR(100)
);

CREATE TABLE contestant (
    username VARCHAR(100),
    FOREIGN KEY(contest_name) REFERENCES blood_donation_contest(contest_name)
);
CREATE TABLE donor (
    biological_gender VARCHAR(100),
    age INT,
    patient_id INT,
    donor_name VARCHAR(100),
    FOREIGN KEY(clinicNo) REFERENCES clinic(clinicNo),
    FOREIGN KEY(blood_Rh_type) REFERENCES blood(blood_Rh_type),
    FOREIGN KEY(blood_type) REFERENCES blood(blood_type)
);
CREATE TABLE blood (
    blood_type VARCHAR(10),
    blood_Rh_type VARCHAR(10)
);

