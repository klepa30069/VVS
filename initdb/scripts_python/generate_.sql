
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
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (8d8898c9-462a-4eee-9924-004b6c23a8b2, 'FIO_0', 'subscription_0', 72.29, 171.55);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (9d31ed9d-f3c5-49a4-990d-bd5d3e1dc86e, 'FIO_1', 'subscription_1', 72.74, 186.57);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (1a39da00-5dfe-48d4-b91c-d9399edabd98, 'FIO_2', 'subscription_2', 93.23, 186.81);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (773121b7-6960-420c-ad87-76a1623fa030, 'FIO_3', 'subscription_3', 98.32, 176.66);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (8818d2bd-9594-43de-b40f-4e6e6af0c014, 'FIO_4', 'subscription_4', 70.87, 174.53);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (6e59c1d4-892e-49a3-8297-c0597f8e89db, 'FIO_5', 'subscription_5', 77.91, 189.72);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (7ab2071a-653b-41e4-b710-4a9db7bb82de, 'FIO_6', 'subscription_6', 81.39, 170.59);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (843ff50c-7ac4-4b38-8f16-dc1e2ef9754c, 'FIO_7', 'subscription_7', 59.28, 173.23);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (276e2836-4323-4415-a612-f94d4ebb9a00, 'FIO_8', 'subscription_8', 91.93, 166.98);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (3a980516-b9d8-4129-a71a-81aaad207c79, 'FIO_9', 'subscription_9', 56.62, 156.9);
INSERT INTO equipment (id, type, status) VALUES (01f8de87-8f60-4dc5-a2f2-0b5991f1054d, BIKE, False);
INSERT INTO equipment (id, type, status) VALUES (a98700a0-613e-49ae-9379-dd6de5d0265f, TREADMILL, True);
INSERT INTO equipment (id, type, status) VALUES (d879e07d-df59-44fa-9360-63b8dac197a6, TREADMILL, False);
INSERT INTO equipment (id, type, status) VALUES (761d470b-a91b-46d6-9d16-b1a1c10dc86c, DUMBBELLS, False);
INSERT INTO equipment (id, type, status) VALUES (4a9ca2ba-a326-440f-800a-f029900b2f81, TREADMILL, False);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (d130e450-98f2-4ae0-ba4d-b7b4fb840acb, 7, 9, 2025-02-18 08:38:29.459428, 26);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (3b51522c-9f26-451b-bdb4-055dea11b89e, 0, 7, 2025-03-01 08:38:29.459461, 42);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (8017fbbf-639e-4224-83c1-af05b3ef227f, 0, 8, 2025-02-16 08:38:29.459470, 75);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (854b58cc-13ac-43ad-83df-aaa3d2b5aeaa, 0, 7, 2025-02-25 08:38:29.459478, 56);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (c2d67190-ba42-4b43-8ce9-e7a4330ffb6d, a, 9, 2025-02-18 08:38:29.459488, 118);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (8e080f49-a07d-42f6-a6ca-2e5596831aaf, 7, 9, 2025-02-14 08:38:29.459496, 75);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (e42c6ca0-49cb-4ae3-be12-def8da4cf2ec, a, 6, 2025-02-22 08:38:29.459504, 13);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (c8f26b14-5b80-4423-98b6-4ca7fa90b030, 0, 8, 2025-03-01 08:38:29.459511, 44);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (fc720f35-f9dd-4aa9-a990-18d191f746e1, a, 7, 2025-02-23 08:38:29.459518, 72);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (9fcf2571-e740-4b8d-9298-874e494bdcaf, 4, 8, 2025-02-13 08:38:29.459525, 28);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (5b76e65b-2edf-45a9-bae9-85f6b19ae43d, 4, 3, 2025-03-03 08:38:29.459533, 61);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (afe39b46-f081-496b-ab7f-0a0d33d0e10a, 7, 3, 2025-03-03 08:38:29.459540, 46);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (dcada9c7-ab72-4e5e-a54f-e1f3191465da, 4, 2, 2025-02-16 08:38:29.459547, 117);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (0f7ea887-ab01-41f3-ac36-4d13414e7752, d, 6, 2025-03-02 08:38:29.459554, 104);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (b4c0eec2-ba34-4773-846c-73e7c5d8395a, 0, 7, 2025-02-20 08:38:29.459569, 50);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (10182dbc-6315-42c0-adbe-b0214da1a9ab, 7, 7, 2025-02-14 08:38:29.459576, 95);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (aa3bce26-183f-4329-ad54-e5c666306d28, 4, 8, 2025-03-14 08:38:29.459583, 10);
INSERT INTO t_delivery (id, equipment_id, visitor_id, date, duration) VALUES (b9575030-1575-4855-b7f5-8719d3900618, 0, 6, 2025-03-07 08:38:29.459590, 119);
COMMIT;
