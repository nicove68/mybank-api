DROP TABLE IF EXISTS bank_account;

CREATE TABLE bank_account (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    alias VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    balance DECIMAL(13,2) NOT NULL DEFAULT 0
);

INSERT INTO bank_account (id, alias, type, balance) VALUES
    (1, 'messi', 'BLACK', 80999.45),
    (2, 'ronaldo', 'STANDARD', 0),
    (3, 'pogba', 'PLATINUM', 3007.77);



DROP TABLE IF EXISTS bank_transaction;

CREATE TABLE bank_transaction (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    account_id INT NOT NULL,
    date VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    amount DECIMAL(13,2) NOT NULL
);

INSERT INTO bank_transaction (account_id, date, type, amount) VALUES
    (1, '2010-01-01T16:00:00Z', 'DEPOSIT', 80000),
    (1, '2010-01-01T16:00:00Z', 'DEPOSIT', 990),
    (1, '2010-01-01T16:00:00Z', 'WITHDRAW', 90),
    (1, '2010-01-01T16:00:00Z', 'DEPOSIT', 99),
    (1, '2010-01-01T16:00:00Z', 'DEPOSIT', 0.45);

CREATE INDEX idx_account ON bank_transaction (account_id);