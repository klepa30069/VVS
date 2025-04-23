import http from 'k6/http';
import { check, sleep } from 'k6';

const insertRatio = __ENV.INSERT_RATIO || 50;
const readRatio = __ENV.READ_RATIO || 50;
const testDuration = __ENV.DURATION || '1m';

export const options = {
  scenarios: {
    mixed_workload: {
      executor: 'constant-arrival-rate',
      rate: 50,
      timeUnit: '1s',
      duration: testDuration,
      preAllocatedVUs: 10,
      maxVUs: 100,
    },
  },
};

export default function () {
  const isInsert = (Math.random() * 100) < insertRatio;

  if (isInsert) {
    const res = http.post('http://localhost:8080/visitors', JSON.stringify({
      fio: `Test User ${__VU}`,
      subscription: "premium",
      weight: 70,
      height: 175
    }), {
      headers: { 'Content-Type': 'application/json' },
    });
    check(res, { 'insert status was 201': (r) => r.status === 201 });
  } else {
    const res = http.get('http://localhost:8080/session-controller/getAverageDuration/3712890b-ff58-44c3-b15f-28b2d3f8961d');
    check(res, { 'read status was 200': (r) => r.status === 200 });
  }

  sleep(0.1);
}
