-- Удаляем старые таблицы, если они существуют (в правильном порядке, чтобы избежать ошибок внешних ключей)
DROP TABLE IF EXISTS session;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS visitor;

-- Удаляем старые последовательности, если они существуют
DROP SEQUENCE IF EXISTS seq_equipment;
DROP SEQUENCE IF EXISTS seq_session;
DROP SEQUENCE IF EXISTS seq_visitor;

-- Создаем новые последовательности
CREATE SEQUENCE seq_equipment START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_session START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_visitor START WITH 1 INCREMENT BY 1;

-- Создаем таблицу equipment
CREATE TABLE equipment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(255) NOT NULL,
    status BOOLEAN DEFAULT FALSE
);

-- Создаем таблицу visitor
CREATE TABLE visitor (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fio VARCHAR(255) NOT NULL,
    subscription VARCHAR(255),
    weight DOUBLE PRECISION,
    height DOUBLE PRECISION
);

-- Создаем таблицу session
CREATE TABLE session (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    equipment_id UUID NOT NULL,
    visitor_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    duration INT DEFAULT 0,
    FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON DELETE CASCADE,
    FOREIGN KEY (visitor_id) REFERENCES visitor (id) ON DELETE CASCADE
);