import json
import matplotlib.pyplot as plt
from datetime import datetime
import dateutil.parser

# Загрузка и фильтрация данных
timestamps = []
response_times = []

with open('results.json') as f:
    for line in f:
        try:
            data = json.loads(line)
            if data.get('metric') == 'http_req_duration' and 'data' in data:
                if isinstance(data['data'], dict) and 'value' in data['data']:
                    # Универсальный парсинг времени
                    time_str = data['data']['time']
                    if time_str.endswith('+03:0'):  # Исправление обрезанной строки
                        time_str += '0'  # Добавляем недостающий ноль
                    timestamp = dateutil.parser.isoparse(time_str)
                    timestamps.append(timestamp)
                    response_times.append(data['data']['value'])
        except (json.JSONDecodeError, ValueError, KeyError) as e:
            print(f"Skipping malformed data: {e}")
            continue

if not timestamps:
    print("No valid data points found!")
    exit()

# Построение графика
plt.figure(figsize=(14, 7))
plt.plot(timestamps, response_times, 'b.', alpha=0.6, label='Время ответа (мс)')
plt.axhline(y=500, color='r', linestyle='--', label='Порог (500 мс)')

# Добавляем статистику в заголовок
p95 = sorted(response_times)[int(len(response_times)*0.95)]
plt.title(f'Тестирование производительности\n'
          f'Всего запросов: {len(response_times)} | '
          f'Макс: {max(response_times):.0f} мс | '
          f'95-й перцентиль: {p95:.0f} мс',
          pad=20)

plt.xlabel('Время тестирования')
plt.ylabel('Время ответа (мс)')
plt.legend()
plt.grid(True, linestyle='--', alpha=0.7)
plt.xticks(rotation=45)
plt.tight_layout()

# Сохраняем и показываем график
plt.savefig('response_graph.png', dpi=300, bbox_inches='tight')
print("График сохранен в response_graph.png")
plt.show()

