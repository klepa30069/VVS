import psycopg2
import uuid
import random
import faker
from datetime import datetime, timedelta

# Подключение к базе данных PostgreSQL
def connect_db():
    return psycopg2.connect(
        dbname="gym",  # Имя вашей базы данных
        user="admin",  # Имя пользователя
        password="secret",  # Ваш пароль
        host="localhost",  # Адрес хоста
        port="5432"  # Порт PostgreSQL
    )

# Функция для генерации случайных данных для таблицы visitor
def generate_visitors(n):
    fake = faker.Faker()
    visitors = []
    for _ in range(n):
        visitor_id = str(uuid.uuid4())  # Приводим UUID к строке
        fio = fake.name()
        subscription = fake.word()
        weight = round(random.uniform(50, 100), 2)
        height = round(random.uniform(150, 190), 2)
        visitors.append((visitor_id, fio, subscription, weight, height))
    return visitors

# Функция для генерации случайных данных для таблицы equipment
def generate_equipment(n):
    equipment = []
    types = ["Treadmill", "Elliptical", "Rowing Machine", "Bike", "Leg Press"]
    for _ in range(n):
        equipment_id = str(uuid.uuid4())  # Приводим UUID к строке
        type_ = random.choice(types)
        status = random.choice([True, False])
        equipment.append((equipment_id, type_, status))
    return equipment

# Функция для генерации случайных данных для таблицы session
def generate_sessions(visitors, equipment, n):
    sessions = []
    for _ in range(n):
        visitor_id = random.choice(visitors)[0]
        equipment_id = random.choice(equipment)[0]
        date = datetime.now() - timedelta(days=random.randint(1, 30))
        duration = random.randint(10, 120)  # продолжительность в минутах
        sessions.append((str(uuid.uuid4()), equipment_id, visitor_id, date, duration))  # Приводим UUID к строке
    return sessions

# Основная функция для заполнения базы данных
def populate_db():
    conn = connect_db()
    cursor = conn.cursor()

    # Генерируем данные
    visitors = generate_visitors(10)
    equipment = generate_equipment(5)

    # Вставляем данные в таблицу visitor
    for visitor in visitors:
        cursor.execute("""
            INSERT INTO visitor (id, fio, subscription, weight, height)
            VALUES (%s, %s, %s, %s, %s)
        """, visitor)

    # Вставляем данные в таблицу equipment
    for equip in equipment:
        cursor.execute("""
            INSERT INTO equipment (id, type, status)
            VALUES (%s, %s, %s)
        """, equip)

    # Генерируем и вставляем данные в таблицу session
    sessions = generate_sessions(visitors, equipment, 15)
    for session in sessions:
        cursor.execute("""
            INSERT INTO session (id, equipment_id, visitor_id, date, duration)
            VALUES (%s, %s, %s, %s, %s)
        """, session)

    # Сохраняем изменения и закрываем соединение
    conn.commit()
    cursor.close()
    conn.close()

# Запуск функции
if __name__ == "__main__":
    populate_db()
