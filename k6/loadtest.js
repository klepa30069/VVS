import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Rate } from 'k6/metrics';

// Конфигурация теста
const BASE_URL = 'http://10.82.27.177:8080';
const ENDPOINTS = [
  '/visitors',
  '/equipments',
  '/sessions',
  '/visitors/304b0f29-b3e8-42da-aa6c-be39f01f380d'
];

// Метрики
const responseTime = new Trend('response_time');
const errorRate = new Rate('errors');

// Параметры нагрузки по умолчанию
const defaultStages = [
  { duration: '30s', target: 20 },  // Плавный рост
  { duration: '1m', target: 50 },   // Пиковая нагрузка
  { duration: '30s', target: 0 }    // Завершение
];

export const options = {
  stages: JSON.parse(__ENV.STAGES || JSON.stringify(defaultStages)),
  thresholds: {
    'errors': ['rate<0.05'],
    'http_req_duration{endpoint:/visitors}': ['p(95)<500'],
    'http_req_duration{endpoint:/equipments}': ['p(95)<300'],
    'http_req_duration{endpoint:/sessions}': ['p(95)<700']
  },
  rps: parseInt(__ENV.RPS || 10)  // Контроль RPS
};

// Генератор тестовых данных
function generateVisitor() {
  return {
    fio: `User-${Math.floor(Math.random() * 1000)}`,
    subscription: ['basic', 'premium', 'vip'][Math.floor(Math.random() * 3)],
    weight: 50 + Math.random() * 50,
    height: 150 + Math.random() * 40
  };
}

export default function () {
  const endpoint = ENDPOINTS[Math.floor(Math.random() * ENDPOINTS.length)];
  let res;
  let payload;
  let params = { tags: { endpoint } };

  // Логика запросов
  switch(endpoint) {
    case '/visitors':
      if (Math.random() > 0.7) {
        payload = JSON.stringify(generateVisitor());
        params.headers = { 'Content-Type': 'application/json' };
        res = http.post(`${BASE_URL}${endpoint}`, payload, params);
      } else {
        res = http.get(`${BASE_URL}${endpoint}`, params);
      }
      break;
      
    default:
      res = http.get(`${BASE_URL}${endpoint}`, params);
  }

  // Проверка результатов
  const isOK = check(res, {
    'Status 2xx': (r) => r.status >= 200 && r.status < 300,
    'Response time OK': (r) => r.timings.duration < 1000
  }) || false;

  if (!isOK) errorRate.add(1);
  
  responseTime.add(res.timings.duration);
  sleep(1 / (__ENV.RPS || 10));  // Регулировка интервала между запросами
}

