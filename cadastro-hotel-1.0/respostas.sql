-- 1. Quantos clientes temos na base?
SELECT COUNT(*) AS total_customers FROM customers;

-- 2. Quantos quartos temos cadastrados?
SELECT COUNT(*) AS total_rooms FROM rooms;

-- 3. Quantas reservas em aberto o hotel possui no momento?
SELECT COUNT(*) AS open_reservations
FROM reservations
WHERE status = 'OPEN';

-- 4. Quantos quartos temos vagos no momento?
SELECT COUNT(*) AS rooms_available
FROM rooms
WHERE occupied = FALSE;

-- 5. Quantos quartos temos ocupados no momento?
SELECT COUNT(*) AS rooms_occupied
FROM rooms
WHERE occupied = TRUE;

-- 6. Quantas reservas futuras o hotel possui?
SELECT COUNT(*) AS future_reservations
FROM reservations
WHERE checkin > CURRENT_DATE;

-- 7. Quarto mais caro:
SELECT *
FROM rooms
ORDER BY price DESC
LIMIT 1;

-- 8. Quarto com maior histórico de cancelamentos:
SELECT room_id, COUNT(*) AS total_cancellations
FROM reservations
WHERE status = 'CANCELED'
GROUP BY room_id
ORDER BY total_cancellations DESC
LIMIT 1;

-- 9. Todos os quartos e quantidade de clientes que já ocuparam cada um:
SELECT r.id AS room_id, COUNT(DISTINCT res.customer_id) AS total_customers
FROM rooms r
LEFT JOIN reservations res ON r.id = res.room_id
GROUP BY r.id
ORDER BY total_customers DESC;

-- 10. Top 3 quartos com mais ocupações:
SELECT room_id, COUNT(*) AS total_occupations
FROM reservations
WHERE status IN ('FINISHED', 'IN_USE')
GROUP BY room_id
ORDER BY total_occupations DESC
LIMIT 3;

-- 11. Top 10 clientes com maior número de reservas:
SELECT customer_id, COUNT(*) AS total_reservations
FROM reservations
GROUP BY customer_id
ORDER BY total_reservations DESC
LIMIT 10;

-- 12. Receita média total gerada (FINISHED + IN_USE):
SELECT AVG(total) AS avg_revenue
FROM (
    SELECT SUM(rm.price) AS total
    FROM reservations res
    JOIN rooms rm ON rm.id = res.room_id
    WHERE res.status IN ('FINISHED', 'IN_USE')
    GROUP BY res.id
) AS revenues;
