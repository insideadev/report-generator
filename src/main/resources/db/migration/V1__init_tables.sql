CREATE TABLE account_summary_daily
(
    customer_id       VARCHAR(10) NOT NULL,
    as_of_date        DATE        NOT NULL,
    deposit_amt       DECIMAL(20, 2),
    insurance_amt     DECIMAL(20, 2),
    offshore_bond_amt DECIMAL(20, 2),
    PRIMARY KEY (customer_id, as_of_date)
)
    PARTITION BY RANGE COLUMNS (as_of_date) (
        PARTITION pass VALUES LESS THAN ('2022-12-20'),
        PARTITION P20221220 VALUES LESS THAN ('2022-12-21'),
        PARTITION P20221221 VALUES LESS THAN ('2022-12-22')
        );

CREATE TABLE account_summary_monthly
(
    customer_id       VARCHAR(10) NOT NULL,
    as_of_date        DATE        NOT NULL,
    deposit_amt       DECIMAL(20, 2),
    insurance_amt     DECIMAL(20, 2),
    offshore_bond_amt DECIMAL(20, 2),
    PRIMARY KEY (customer_id, as_of_date)
)
    PARTITION BY RANGE COLUMNS (as_of_date) (
        PARTITION pass VALUES LESS THAN ('2022-12-01'),
        PARTITION P202212 VALUES LESS THAN ('2023-01-01'),
        PARTITION P202301 VALUES LESS THAN ('2023-02-01')
        );
