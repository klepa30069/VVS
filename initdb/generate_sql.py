import uuid
import random
from datetime import datetime, timedelta

def generate_sql(filename, num_visitor=10, num_equipment=5, num_session=18):
    equipment_types = ["TREADMILL", "BIKE", "CROSS_FIT", "DUMBBELLS", "HORIZONTAL_DEADLIFT"]

    with open(filename, 'w', encoding='utf-8') as f:

        f.write("""
        BEGIN TRANSACTION;

        CREATE TABLE equipment (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            type VARCHAR(255) NOT NULL,
            status BOOLEAN DEFAULT FALSE
        );

        CREATE TABLE visitor (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            fio VARCHAR(255) NOT NULL,
            subscription VARCHAR(255),
            weight DOUBLE PRECISION,
            height DOUBLE PRECISION
        );

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
        visitors = []
        equipment = []


        for i in range(num_visitor):
            visitor_id = str(uuid.uuid4())  # Приводим UUID к строке
            visitors.append(visitor_id)
            weight = round(random.uniform(50, 100), 2)
            height = round(random.uniform(150, 190), 2)
            f.write(
                f"INSERT INTO visitor (id, fio, subscription, weight, height) VALUES ({visitor_id}, 'FIO_{i}', 'subscription_{i}', {weight}, {height});\n"
            )


        for i in range(num_equipment):
            equipment_id = str(uuid.uuid4())  # Приводим UUID к строке
            equipment.append(equipment_id)
            type_ = random.choice(equipment_types)
            status = random.choice([True, False])
            f.write(
                f"INSERT INTO equipment (id, type, status) VALUES ({equipment_id}, {type_}, {status});\n"
            )


        for i in range(num_session):
            visitor_id = random.choice(visitors)[0]
            equipment_id = random.choice(equipment)[0]
            date = datetime.now() - timedelta(days=random.randint(1, 30))
            duration = random.randint(10, 120)  # продолжительность в минутах
            f.write(
                f"INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES ({uuid.uuid4()}, {equipment_id}, {visitor_id}, {date}, {duration});\n"
            )

        f.write("COMMIT;\n")


generate_sql("./scripts_python/generate_.sql")