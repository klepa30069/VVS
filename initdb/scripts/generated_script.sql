
        BEGIN TRANSACTION;

        -- Удаляем старые последовательности, если они существуют
        DROP SEQUENCE IF EXISTS seq_equipment;
        DROP SEQUENCE IF EXISTS seq_session;
        DROP SEQUENCE IF EXISTS seq_visitor;

        -- Создаем новые последовательности
        CREATE SEQUENCE seq_equipment START WITH 1 INCREMENT BY 1;
        CREATE SEQUENCE seq_session START WITH 1 INCREMENT BY 1;
        CREATE SEQUENCE seq_visitor START WITH 1 INCREMENT BY 1;

        -- Создаем таблицу equipment
        CREATE TABLE equipment (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            type VARCHAR(255) NOT NULL,
            status BOOLEAN DEFAULT FALSE
        );

        -- Создаем таблицу visitor
        CREATE TABLE visitor (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            fio VARCHAR(255) NOT NULL,
            subscription VARCHAR(255),
            weight DOUBLE PRECISION,
            height DOUBLE PRECISION
        );

        -- Создаем таблицу session
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
INSERT INTO equipment (id, type, status) VALUES (48960c73-ac59-40d5-95a1-2b56de7d2456, '['TREADMILL', 'BIKE', 'CROSS_FIT', 'DUMBBELLS', 'HORIZONTAL_DEADLIFT']', false);
INSERT INTO equipment (id, type, status) VALUES (bf6640e3-52a2-49f8-9a70-9d4e3de6814d, '['TREADMILL', 'BIKE', 'CROSS_FIT', 'DUMBBELLS', 'HORIZONTAL_DEADLIFT']', false);
INSERT INTO equipment (id, type, status) VALUES (b11c7c0d-a454-4661-8fd4-3e98953d11a4, '['TREADMILL', 'BIKE', 'CROSS_FIT', 'DUMBBELLS', 'HORIZONTAL_DEADLIFT']', false);
INSERT INTO equipment (id, type, status) VALUES (667c75ad-8684-4702-aa0c-fb4e0983199e, '['TREADMILL', 'BIKE', 'CROSS_FIT', 'DUMBBELLS', 'HORIZONTAL_DEADLIFT']', false);
INSERT INTO equipment (id, type, status) VALUES (fd8929ec-7f7d-4581-b9a2-6af33fdc33c9, '['TREADMILL', 'BIKE', 'CROSS_FIT', 'DUMBBELLS', 'HORIZONTAL_DEADLIFT']', false);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (5564010c-853d-4ce5-9212-9b8f133e66ad, 'Visitor_1', 'Subscription_1', 79.17, 160.91);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (f5267cf9-6574-4707-9723-f3ce93f2cb8d, 'Visitor_2', 'Subscription_2', 93.45, 186.55);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (513c7fe3-02c0-4427-9782-783fba6271a5, 'Visitor_3', 'Subscription_0', 77.76, 184.34);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (4fa13c91-f1d6-43c4-94a9-73fbf68235da, 'Visitor_4', 'Subscription_1', 67.08, 194.51);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (4285e32b-544c-4e98-9527-b03733d20629, 'Visitor_5', 'Subscription_2', 88.94, 188.80);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (024abc6c-b5f5-4537-8f62-4253623d9b07, 'Visitor_6', 'Subscription_0', 66.76, 176.46);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (629c7487-3bca-4329-94bd-201a5ded2e8a, 'Visitor_7', 'Subscription_1', 94.37, 171.35);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (45228f9c-f34a-4ee3-a773-f9224234d566, 'Visitor_8', 'Subscription_2', 86.47, 155.56);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (4b1fc54b-243a-488a-ade5-764895698aea, 'Visitor_9', 'Subscription_0', 92.72, 162.38);
INSERT INTO visitor (id, fio, subscription, weight, height) VALUES (e3aa4916-7053-4463-bdd1-4854963fea1b, 'Visitor_10', 'Subscription_1', 96.48, 151.21);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (1, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-03-09 09:02:36.568053', 25);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (2, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-03-07 09:02:36.568075', 68);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (3, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-03-06 09:02:36.568082', 70);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (4, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-03-01 09:02:36.568089', 9);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (5, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-03-07 09:02:36.568099', 51);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (6, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-02-27 09:02:36.568106', 47);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (7, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-03-07 09:02:36.568113', 104);
INSERT INTO session (ID, equipment, visitor, date, duration) VALUES (8, (SELECT id FROM equipment WHERE id = fd8929ec-7f7d-4581-b9a2-6af33fdc33c9), (SELECT id FROM visitor WHERE id = e3aa4916-7053-4463-bdd1-4854963fea1b), '2025-02-26 09:02:36.568119', 85);
COMMIT;
