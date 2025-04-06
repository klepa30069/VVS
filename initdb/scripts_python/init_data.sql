BEGIN TRANSACTION;

-- Вставка посетителей (сохраняем UUID для ссылок)
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES
('969b7d00-6a49-4ea1-9148-c1ca149c073b', 'FIO_0', 'subscription_0', 54.11, 181.29),
('304b0f29-b3e8-42da-aa6c-be39f01f380d', 'FIO_1', 'subscription_1', 70.6, 180.3),
('ac208797-01cd-4113-b5b0-25faa4fdf51b', 'FIO_2', 'subscription_2', 96.43, 162.72),
('0ab54c64-8194-48af-86db-9563b5feca8f', 'FIO_3', 'subscription_3', 59.36, 155.19),
('0c7fc60a-6b75-434a-b070-5b9dd274d7dc', 'FIO_4', 'subscription_4', 76.75, 181.1),
('3cdd7158-0d46-4d41-a511-5083997996f9', 'FIO_5', 'subscription_5', 99.39, 152.61),
('3f81d20f-ca05-4a6b-9ccc-8b224fc4feae', 'FIO_6', 'subscription_6', 63.77, 172.96),
('c5adf25e-93e6-47c5-a2b3-3384ad3e34d8', 'FIO_7', 'subscription_7', 76.5, 157.14),
('3392e2c3-f49d-4e54-9cd3-0d067ba74d87', 'FIO_8', 'subscription_8', 61.94, 168.59),
('e3f5427b-1086-40b7-9701-b75ff59daac4', 'FIO_9', 'subscription_9', 73.31, 185.09);

-- Вставка оборудования
INSERT INTO equipment (id, type, status) VALUES
('cd60a817-6be8-4966-81e4-abf8de03b310', 'HORIZONTAL_DEADLIFT', True),
('68111516-3f3e-4b05-8c57-c18feddd2401', 'CROSS_FIT', True),
('93eff5a4-bd46-4992-839a-a766e8172d69', 'HORIZONTAL_DEADLIFT', False),
('4ae97714-d1e6-499c-b3ee-630eae054fe6', 'HORIZONTAL_DEADLIFT', False),
('c7adcaed-5fbe-420e-bf7d-fd4dc68051cb', 'DUMBBELLS', True);

-- Вставка сессий (исправлены ссылки на существующие ID)
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES
('0b899320-eba4-497b-9954-4b593f395a07', 'cd60a817-6be8-4966-81e4-abf8de03b310', '969b7d00-6a49-4ea1-9148-c1ca149c073b', '2025-02-23 06:57:22.184039', 64),
('57fa9b74-317d-4deb-8912-92a4c23907dc', '68111516-3f3e-4b05-8c57-c18feddd2401', '969b7d00-6a49-4ea1-9148-c1ca149c073b', '2025-02-17 06:57:22.184067', 51),
('51f8528c-b001-48e1-80d6-e29ed0952359', '68111516-3f3e-4b05-8c57-c18feddd2401', 'e3f5427b-1086-40b7-9701-b75ff59daac4', '2025-03-02 06:57:22.184078', 56);

COMMIT;