-- src/main/resources/db/migration/V1__Create_schedule_table.sql
CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    performance_number INT NOT NULL,
    date DATE NOT NULL,
    director VARCHAR(255) NOT NULL,
    stage VARCHAR(255) NOT NULL
);
