import uuid
import random
from datetime import datetime, timedelta
import os

def generate_sql(filename, num_visitors=10, num_equipments=5, num_sessions=8):
    equipment_type = ["TREADMILL", "BIKE", "CROSS_FIT", "DUMBBELLS", "HORIZONTAL_DEADLIFT"]

    os.makedirs(os.path.dirname(filename), exist_ok=True)

    with open(filename, 'w', encoding='utf-8') as f:
        f.write("""
        BEGIN TRANSACTION;

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

        COMMIT;
        """)

        f.write("\nBEGIN TRANSACTION;\n")
        equipment_id = uuid.uuid4()
        visitor_id = uuid.uuid4()

        for i in range(1, num_equipments + 1):
            equipment_id = uuid.uuid4()
            f.write(
                f"INSERT INTO equipment (id, type, status) VALUES ({equipment_id}, '{equipment_type}', false);\n")

        for i in range(1, num_visitors + 1):
            visitor_id = uuid.uuid4()
            f.write(
                f"INSERT INTO visitor (id, fio, subscription, weight, height) VALUES ({visitor_id}, 'Visitor_{i}', 'Subscription_{i % 3}', {random.uniform(50, 100):.2f}, {random.uniform(150, 200):.2f});\n")

        for i in range(1, num_sessions + 1):
            f.write(
                f"INSERT INTO session (ID, equipment, visitor, date, duration) VALUES ({i}, (SELECT id FROM equipment WHERE id = {equipment_id}), (SELECT id FROM visitor WHERE id = {visitor_id}), '{datetime.now() - timedelta(days=random.randint(1, 30))}', {random.randint(1, 120)});\n")

        f.write("COMMIT;\n")

generate_sql("scripts/generated_script.sql")