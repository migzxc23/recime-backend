CREATE TABLE RECIPE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recipe VARCHAR(255) NOT NULL,
    description TEXT,
    difficulty VARCHAR(50),
    image_url VARCHAR(255),
    positions INT
);
