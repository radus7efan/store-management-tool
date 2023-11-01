INSERT INTO products (id, title, price, quantity, stock_status, discount, discounted_price, description)
VALUES (1, 'Brand Keyboard', 254.23, 34, 0, 0, 254.23, 'Versatile and ergonomic keyboard.');

INSERT INTO products (id, title, price, quantity, stock_status, discount, discounted_price, description)
VALUES (2, 'Brand Mouse', 212.00, 20, 0, 0, 212.00, 'A very lightweight and precise mouse.');

INSERT INTO products (id, title, price, quantity, stock_status, discount, discounted_price, description)
VALUES (3, 'Gaming Headset', 321.00, 4, 2, 0, 321.00, 'High fidelity sound & long lating battery.');

INSERT INTO products (id, title, price, quantity, stock_status, discount, discounted_price, description)
VALUES (4, 'Phone Charger', 90.00, 0, 1, 25, 67.50, 'Fast 50W charger.');

INSERT INTO products (id, title, price, quantity, stock_status, discount, discounted_price, description)
VALUES (5, 'Video Camera', 821.99, 5, 0, 15, 698.69, '50mp, 4k@120fps action camera.');




INSERT INTO users (id, username, password, roles)
VALUES (1, 'admin', '$2a$12$zLC9hNKDw245ZXlPZay/quM.O4DNP.AzYiVhiv1BZkoLEIoQxJYQi', 'ROLE_ADMIN');

INSERT INTO users (id, username, password, roles)
VALUES (2, 'user', '$2a$12$Bl4V9FwqJoxfKkLcA5xG7.OAmCoZSCwcfwooatM1RLNtIweuocAXe', 'ROLE_USER');