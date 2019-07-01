DROP TABLE IF EXISTS bank_account;

CREATE TABLE bank_account (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    alias VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    balance DECIMAL(13,2) NOT NULL DEFAULT 0
);

INSERT INTO bank_account (id, alias, type, balance) VALUES
    (1, 'axl_rose', 'GOLD', 100.00),
    (2, 'paul_mccartney', 'GOLD', 100.00),
    (3, 'tarja_turunen', 'PLATINUM', 0),
    (4, 'bruce_dickinson', 'STANDARD', -350.00),
    (5, 'brian_johnson', 'PLATINUM', 100.00),
    (6, 'freddie_mercury', 'BLACK', 100.00);



DROP TABLE IF EXISTS bank_transaction;

CREATE TABLE bank_transaction (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    account_id INT NOT NULL,
    date VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    amount DECIMAL(13,2) NOT NULL
);

CREATE INDEX idx_account ON bank_transaction (account_id);