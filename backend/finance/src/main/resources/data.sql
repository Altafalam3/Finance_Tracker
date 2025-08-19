INSERT INTO users (name, email, password) VALUES
('Arun Kumar', 'arun@example.com', 'password123'),
('Demo User', 'demo@example.com', 'demo123');

INSERT INTO accounts (user_id, name, type, balance) VALUES
(1, 'HDFC Savings', 'BANK', 50000.00),
(1, 'GPay Wallet', 'WALLET', 1200.00),
(2, 'ICICI Credit', 'CREDIT', -3000.00);

INSERT INTO transactions (user_id, account_id, amount, income, category, tags, description, created_at) VALUES
(1, 1, 75000.00, TRUE, 'Salary', 'payroll,aug', 'August Salary', '2025-08-01 10:00:00'),
(1, 1, 2000.00, FALSE, 'Food', 'swiggy', 'Dinner', '2025-08-02 20:10:00'),
(1, 2, 500.00, FALSE, 'Transport', 'uber', 'Airport cab', '2025-08-03 09:00:00'),
(2, 3, 1000.00, FALSE, 'Shopping', 'amazon', 'Headphones', '2025-08-04 15:30:00');
