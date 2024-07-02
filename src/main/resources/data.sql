CREATE TABLE Garden_PlantTypes (
                                   Garden_id BIGINT NOT NULL,
                                   PlantTypes_id BIGINT NOT NULL,
                                   PRIMARY KEY (Garden_id, PlantTypes_id),
                                   FOREIGN KEY (Garden_id) REFERENCES Garden(id),
                                   FOREIGN KEY (PlantTypes_id) REFERENCES Plant_Types(id)
);

-- Insert some sample plant types
INSERT INTO Plant_Types (name)
VALUES ('Tomato'),
       ('Cucumber'),
       ('Carrot'),
       ('Lettuce'),
       ('Radish');

-- Insert some sample gardens with associated plant types
INSERT INTO Garden (name, description)
VALUES ('My Backyard Garden', 'A small garden in my backyard'),
       ('Community Garden', 'A shared garden in the community'),
       ('Indoor Garden', 'A small indoor garden on my balcony');

INSERT INTO Garden_PlantTypes (Garden_id, PlantTypes_id)
VALUES (1, 1),
       (1, 2),
       (1, 3), -- My Backyard Garden has Tomato, Cucumber, and Carrot
       (2, 2),
       (2, 4),
       (2, 5), -- Community Garden has Cucumber, Lettuce, and Radish
       (3, 1),
       (3, 4); -- Indoor Garden has Tomato and Lettuce