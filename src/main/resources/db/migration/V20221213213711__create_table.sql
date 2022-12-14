
    CREATE TABLE IF NOT EXISTS `account_summary_daily_test` (
    `monthly_id` int(11) NOT NULL AUTO_INCREMENT,
    `as_of_date` datetime DEFAULT NULL,
    `customer_id` varchar(255) DEFAULT NULL,
    `deposit_amt` decimal(20,6) DEFAULT NULL,
    `insurance_amt` decimal(20,6) DEFAULT NULL,
    `offshore_bond_amt` decimal(20,6) DEFAULT NULL,
    PRIMARY KEY (`monthly_id`)
    );

    INSERT INTO `report_generator`.`account_summary_daily_test`
        (`as_of_date`, `customer_id`, `deposit_amt`, `insurance_amt`, `offshore_bond_amt`)
    VALUES ('2022-12-13 21:41:21', 'ghd', '4564', '456456', '54645');

