CREATE TABLE political_territory (
    territory_name VARCHAR(100),
    territory_type VARCHAR(100),
    territory_adjacent VARCHAR(100)
);
CREATE TABLE national_park (
    park_name VARCHAR(100),
    photo VARCHAR(100),
    direction VARCHAR(100),
    park_description VARCHAR(240),
    FOREIGN KEY(territory_name) REFERENCES political_territory(territory_name),
    FOREIGN KEY(accommodation_id) REFERENCES accommodation(accommodation_id),
    FOREIGN KEY(site_name) REFERENCES site(site_name),
    FOREIGN KEY(location) REFERENCES global_location(location)
);

CREATE TABLE accommodation (
	accommodation_id INT,
    accommodation_name VARCHAR(100),
    destination_direction VARCHAR(100),
    cost_per_night INT,
    telephone_number INT,
    accommodation_type VARCHAR(100),
    FOREIGN KEY(location) REFERENCES global_location(location)
);
CREATE TABLE site (
    site_name VARCHAR(100),
    textual_description VARCHAR(240),
    photo VARCHAR(100),
    driving_direction VARCHAR(100),
    phone VARCHAR(100),
    FOREIGN KEY(location) REFERENCES global_location(location)
);

CREATE TABLE global_location (
    location VARCHAR(100),
	latitude VARCHAR(100),
	longitude VARCHAR(100)
);
