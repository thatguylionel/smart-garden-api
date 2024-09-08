-- Create tables

CREATE TABLE IF NOT EXISTS gardens
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS plant_types
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    description       TEXT,
    water_needs       DOUBLE,
    sunlight_needs    VARCHAR(50),
    ideal_temperature DOUBLE
);

CREATE TABLE IF NOT EXISTS garden_plant_types
(
    garden_id     BIGINT,
    plant_type_id BIGINT,
    PRIMARY KEY (garden_id, plant_type_id),
    FOREIGN KEY (garden_id) REFERENCES gardens (id),
    FOREIGN KEY (plant_type_id) REFERENCES plant_types (id)
);

CREATE TABLE IF NOT EXISTS sensors
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    garden_id           BIGINT      NOT NULL,
    sensor_type         VARCHAR(50) NOT NULL,
    sensor_value        DOUBLE      NOT NULL,
    unit_of_measurement VARCHAR(50) NOT NULL,
    last_reading_at     TIMESTAMP   NOT NULL,
    FOREIGN KEY (garden_id) REFERENCES gardens (id)
);

CREATE TABLE IF NOT EXISTS notifications
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    garden_id         BIGINT      NOT NULL,
    message           TEXT        NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    created_at        TIMESTAMP   NOT NULL,
    read_at           TIMESTAMP,
    FOREIGN KEY (garden_id) REFERENCES gardens (id)
);

CREATE TABLE IF NOT EXISTS watering_events
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    garden_id    BIGINT    NOT NULL,
    water_amount DOUBLE    NOT NULL,
    timestamp    TIMESTAMP NOT NULL,
    notes        VARCHAR(500),
    FOREIGN KEY (garden_id) REFERENCES gardens (id)
);

-- Insert sample data.

-- Gardens 3
INSERT INTO gardens (id, name, description)
VALUES (1, 'Rooftop Garden', 'A beautiful garden on the roof of our building'),
       (2, 'Backyard Oasis', 'A lush garden in the backyard with various plants'),
       (3, 'Indoor Herb Garden', 'A small indoor garden for fresh herbs');

-- Plant Types
INSERT INTO plant_types (id, name, description, water_needs, sunlight_needs, ideal_temperature)
VALUES (1, 'Tomato', 'Juicy red fruit', 2.0, 'Full Sun', 25.0),
       (2, 'Basil', 'Fragrant herb', 1.5, 'Partial Shade', 22.0),
       (3, 'Rose', 'Beautiful flowers', 1.8, 'Full Sun', 20.0),
       (4, 'Fern', 'Lush green plant', 2.5, 'Shade', 18.0);

-- Garden Plant Types
INSERT INTO garden_plant_types (garden_id, plant_type_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 3),
       (2, 4),
       (3, 2);



-- Notifications
INSERT INTO notifications (id, garden_id, message, notification_type, created_at)
VALUES (1, 1, 'Water needed for Rooftop Garden', 'WATER_NEEDED', CURRENT_TIMESTAMP()),
       (2, 2, 'Pest detected in Backyard Oasis', 'PEST_DETECTED', CURRENT_TIMESTAMP()),
       (3, 3, 'Temperature alert for Indoor Herb Garden', 'TEMPERATURE_ALERT', CURRENT_TIMESTAMP());

-- Watering Events
INSERT INTO watering_events (id, garden_id, water_amount, timestamp, notes)
VALUES (1, 1, 2.5, CURRENT_TIMESTAMP(), 'Regular watering'),
       (2, 2, 3.0, DATEADD('DAY', -1, CURRENT_TIMESTAMP()), 'Extra water due to hot weather'),
       (3, 3, 1.0, DATEADD('DAY', -2, CURRENT_TIMESTAMP()), 'Light watering for herbs');