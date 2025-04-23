BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS equipment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(255) NOT NULL,
    status BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS visitor (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fio VARCHAR(255) NOT NULL,
    subscription VARCHAR(255),
    weight DOUBLE PRECISION,
    height DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS session (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    equipment_id UUID NOT NULL,
    visitor_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    duration INT DEFAULT 0,
    FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON DELETE CASCADE,
    FOREIGN KEY (visitor_id) REFERENCES visitor (id) ON DELETE CASCADE
);

COMMIT;

