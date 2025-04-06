
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
        
BEGIN TRANSACTION;
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (969b7d00-6a49-4ea1-9148-c1ca149c073b, 'FIO_0', 'subscription_0', 54.11, 181.29);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (304b0f29-b3e8-42da-aa6c-be39f01f380d, 'FIO_1', 'subscription_1', 70.6, 180.3);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (ac208797-01cd-4113-b5b0-25faa4fdf51b, 'FIO_2', 'subscription_2', 96.43, 162.72);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (0ab54c64-8194-48af-86db-9563b5feca8f, 'FIO_3', 'subscription_3', 59.36, 155.19);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (0c7fc60a-6b75-434a-b070-5b9dd274d7dc, 'FIO_4', 'subscription_4', 76.75, 181.1);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (3cdd7158-0d46-4d41-a511-5083997996f9, 'FIO_5', 'subscription_5', 99.39, 152.61);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (3f81d20f-ca05-4a6b-9ccc-8b224fc4feae, 'FIO_6', 'subscription_6', 63.77, 172.96);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (c5adf25e-93e6-47c5-a2b3-3384ad3e34d8, 'FIO_7', 'subscription_7', 76.5, 157.14);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (3392e2c3-f49d-4e54-9cd3-0d067ba74d87, 'FIO_8', 'subscription_8', 61.94, 168.59);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (e3f5427b-1086-40b7-9701-b75ff59daac4, 'FIO_9', 'subscription_9', 73.31, 185.09);
INSERT INTO equipment (id, type, status) VALUES (cd60a817-6be8-4966-81e4-abf8de03b310, HORIZONTAL_DEADLIFT, True);
INSERT INTO equipment (id, type, status) VALUES (68111516-3f3e-4b05-8c57-c18feddd2401, CROSS_FIT, True);
INSERT INTO equipment (id, type, status) VALUES (93eff5a4-bd46-4992-839a-a766e8172d69, HORIZONTAL_DEADLIFT, False);
INSERT INTO equipment (id, type, status) VALUES (4ae97714-d1e6-499c-b3ee-630eae054fe6, HORIZONTAL_DEADLIFT, False);
INSERT INTO equipment (id, type, status) VALUES (c7adcaed-5fbe-420e-bf7d-fd4dc68051cb, DUMBBELLS, True);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (0b899320-eba4-497b-9954-4b593f395a07, c, c, 2025-02-23 06:57:22.184039, 64);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (57fa9b74-317d-4deb-8912-92a4c23907dc, 6, c, 2025-02-17 06:57:22.184067, 51);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (51f8528c-b001-48e1-80d6-e29ed0952359, 6, 9, 2025-03-02 06:57:22.184078, 56);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (6b52d577-4c03-4ec2-b651-3154e6bb98c6, 6, e, 2025-02-27 06:57:22.184088, 119);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (22bfc1df-d3ff-45c4-a612-cd51f2dc7d3e, 6, 3, 2025-02-28 06:57:22.184101, 18);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (09a04f5c-286f-4830-8cfe-808876bda5ce, 6, 3, 2025-03-11 06:57:22.184111, 34);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (5e442560-41c9-4ee9-969c-685f9760d126, 6, 3, 2025-03-14 06:57:22.184120, 119);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (1375c0fb-d455-4709-84da-0c0564cef625, 6, c, 2025-02-21 06:57:22.184129, 64);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (d7ab21e7-6189-458a-aebe-6465b80e05cd, 6, 3, 2025-02-23 06:57:22.184138, 14);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (570e6c10-6d1d-4d39-9d45-b826dce17e09, c, 3, 2025-02-25 06:57:22.184147, 64);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (44bb4fd4-6577-4078-81ce-e4c53c18050f, 6, 9, 2025-02-16 06:57:22.184156, 50);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (aec01d37-3dbc-471e-b9ee-5d3bf1202b4e, c, 0, 2025-03-12 06:57:22.184165, 28);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (d061c52e-25ee-477a-91ab-015505af8dff, 4, 3, 2025-03-08 06:57:22.184173, 50);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (2c62c722-1b87-4d8d-98fc-9778b59bb3d9, 4, 3, 2025-03-04 06:57:22.184182, 48);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (af5e0df9-e058-4069-994b-8ab58d52a2f4, 6, 3, 2025-03-14 06:57:22.184191, 26);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (a5ce219d-a4e0-473b-9eed-8fe34b7c4c9c, 4, 9, 2025-02-25 06:57:22.184200, 79);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (af5d761f-ddad-4791-b563-879b6e2250ea, 4, 3, 2025-03-07 06:57:22.184210, 100);
INSERT INTO session (id, equipment_id, visitor_id, date, duration) VALUES (57f698f2-bade-48bb-bb93-15899dca9434, c, a, 2025-03-01 06:57:22.184218, 30);
COMMIT;
