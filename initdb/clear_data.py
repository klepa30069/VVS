import psycopg2
from psycopg2 import sql

# Параметры подключения к базе данных
DB_HOST = "localhost"  # Хост
DB_NAME = "gym"     # Имя базы данных
DB_USER = "admin"      # Имя пользователя
DB_PASSWORD = "secret" # Пароль

# Создаем подключение к базе данных
def connect_db():
    return psycopg2.connect(
        dbname="gym",  # Имя вашей базы данных
        user="admin",  # Имя пользователя
        password="secret",  # Ваш пароль
        host="localhost",  # Адрес хоста
        port="5432"  # Порт PostgreSQL
    )

# Функция для очистки базы данных
def clean_db():
    conn = connect_db()
    cursor = conn.cursor()

    # Очистка таблиц session, equipment, visitor
    cursor.execute("TRUNCATE TABLE session, equipment, visitor RESTART IDENTITY CASCADE;")
    print("Таблицы session, equipment, visitor очищены")

    # Очистка таблицы flyway_schema_history
    cursor.execute("TRUNCATE TABLE flyway_schema_history RESTART IDENTITY CASCADE;")
    print("Таблица flyway_schema_history очищена")

    # Сохраняем изменения и закрываем соединение
    conn.commit()
    cursor.close()
    conn.close()

# Запуск очистки
if __name__ == "__main__":
    clean_db()
