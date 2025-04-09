import argparse
import requests
from faker import Faker
import random
from datetime import datetime, timedelta
import time
import json

fake = Faker()

class DataGenerator:
    def __init__(self, base_url):
        self.base_url = base_url.rstrip('/')
        print(f"Using base URL: {self.base_url}")
        self.session = requests.Session()
        self.session.headers.update({'Content-Type': 'application/json'})
    
    def clear_data(self, endpoint):
        """Очистка данных через эндпоинт /clear"""
        url = f"{self.base_url}/{endpoint}/clear"
        try:
            response = self.session.post(url)
            return response.status_code == 204
        except requests.exceptions.RequestException as e:
            print(f"Clear error: {str(e)}")
            return False

    def is_success_response(self, response):
        """Проверяем успешность ответа"""
        if response.status_code in [200, 201]:
            return True
        try:
            data = response.json()
            return 'id' in data
        except:
            return False

    def generate_visitors(self, count):
        """Генерация тестовых посетителей"""
        if not self.clear_data("visitors"):
            raise Exception("Failed to clear visitors data")

        success_count = 0
        for i in range(count):
            visitor_data = {
                "fio": fake.name(),
                "subscription": random.choice(["monthly", "yearly", "none"]),
                "weight": round(random.uniform(50, 100), 2),
                "height": round(random.uniform(150, 200), 2)
            }
            try:
                response = self.session.post(
                    f"{self.base_url}/visitors",
                    json=visitor_data,
                    timeout=5
                )
                if self.is_success_response(response):
                    success_count += 1
                else:
                    print(f"Attempt {i+1}/{count}: Status {response.status_code}, Response: {response.text}")
            except Exception as e:
                print(f"Attempt {i+1}/{count}: Error: {str(e)}")

        print(f"Successfully created {success_count}/{count} visitors")
        return success_count > 0

    def generate_equipments(self, count):
        """Генерация тестового оборудования"""
        if not self.clear_data("equipments"):
            raise Exception("Failed to clear equipment data")

        types = ["TREADMILL", "BIKE", "CROSS_FIT", "DUMBBELLS", "HORIZONTAL_DEADLIFT"]
        success_count = 0
        
        for i in range(count):
            equipment_data = {
                "type": random.choice(types),
                "status": random.choice([True, False])
            }
            try:
                response = self.session.post(
                    f"{self.base_url}/equipments",
                    json=equipment_data,
                    timeout=5
                )
                if self.is_success_response(response):
                    success_count += 1
                else:
                    print(f"Attempt {i+1}/{count}: Status {response.status_code}, Response: {response.text}")
            except Exception as e:
                print(f"Attempt {i+1}/{count}: Error: {str(e)}")

        print(f"Successfully created {success_count}/{count} equipments")
        return success_count > 0

    def generate_sessions(self, count):
        """Генерация тестовых сессий с обработкой статуса 200"""
        if not self.clear_data("sessions"):
            raise Exception("Failed to clear sessions data")

        print("Fetching existing visitors and equipments...")
        visitors = self.session.get(f"{self.base_url}/visitors").json()
        equipments = self.session.get(f"{self.base_url}/equipments").json()
        print(f"Found {len(visitors)} visitors and {len(equipments)} equipments")

        if not visitors or not equipments:
            raise Exception("Need both visitors and equipments to create sessions")

        success_count = 0
        for i in range(count):
            try:
                equipment = random.choice(equipments)
                visitor = random.choice(visitors)
            
                session_data = {
                    "equipment": {
                        "id": equipment["id"],
                        "type": equipment["type"],
                        "status": equipment["status"]
                    },
                    "visitor": {
                        "id": visitor["id"],
                        "fio": visitor["fio"],
                        "subscription": visitor["subscription"],
                        "weight": float(visitor["weight"]),
                        "height": float(visitor["height"])
                    },
                    "date": datetime.utcnow().strftime("%Y-%m-%dT%H:%M:%SZ"),
                    "duration": random.randint(10, 120)
                }
            
                # print(f"\nAttempt {i+1}/{count}:")
                # print(f"Using visitor: {visitor['fio']} ({visitor['id']})")
                # print(f"Using equipment: {equipment['type']} ({equipment['id']})")
            
                response = self.session.post(
                    f"{self.base_url}/sessions",
                    json=session_data,
                    timeout=10
                )
            
                # Учитываем как 200, так и 201 как успешные статусы
                if response.status_code in [200, 201]:
                    success_count += 1
                    try:
                        created_session = response.json()
                        # print(f"Success! Created session ID: {created_session.get('id')}")
                    except:
                        print("Success! (no session ID in response)")
                else:
                    print(f"Error. Status: {response.status_code}")
                    print(f"Response: {response.text[:200]}...")
                
            except Exception as e:
                print(f"Exception: {str(e)}")
                if hasattr(e, 'response'):
                    print(f"Response content: {e.response.text[:500]}...")

        print(f"\nFinal result: Successfully created {success_count}/{count} sessions")
        return success_count > 0

def main():
    parser = argparse.ArgumentParser(description='Generate test data for Gym REST API')
    parser.add_argument('--count', type=int, default=500, help='Number of items to generate')
    parser.add_argument('--endpoint', required=True, 
                       choices=['visitors', 'equipments', 'sessions'],
                       help='Endpoint to populate')
    parser.add_argument('--base-url', default='http://10.82.27.167:8080',
                       help='Base URL of the REST service')
    
    args = parser.parse_args()
    generator = DataGenerator(args.base_url)

    try:
        start_time = time.time()
        
        if args.endpoint == 'visitors':
            print(f"Generating {args.count} visitors...")
            generator.generate_visitors(args.count)
        elif args.endpoint == 'equipments':
            print(f"Generating {args.count} equipments...")
            generator.generate_equipments(args.count)
        elif args.endpoint == 'sessions':
            print(f"Generating {args.count} sessions...")
            generator.generate_sessions(args.count)
        
        elapsed = time.time() - start_time
        print(f"Operation completed in {elapsed:.2f} seconds")
    except Exception as e:
        print(f"Fatal error: {str(e)}")

if __name__ == '__main__':
    main()

