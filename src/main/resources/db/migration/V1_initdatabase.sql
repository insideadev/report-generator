


-- Dumping database structure for report_generator
DROP DATABASE IF EXISTS `report_generator`;
CREATE DATABASE IF NOT EXISTS `report_generator` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `report_generator`;

-- Dumping structure for table report_generator.account_summary_daily
DROP TABLE IF EXISTS `account_summary_daily`;
CREATE TABLE IF NOT EXISTS `account_summary_daily` (
  `monthly_id` int(11) NOT NULL AUTO_INCREMENT,
  `as_of_date` datetime DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `deposit_amt` decimal(20,6) DEFAULT NULL,
  `insurance_amt` decimal(20,6) DEFAULT NULL,
  `offshore_bond_amt` decimal(20,6) DEFAULT NULL,
  PRIMARY KEY (`monthly_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table report_generator.account_summary_monthly
DROP TABLE IF EXISTS `account_summary_monthly`;
CREATE TABLE IF NOT EXISTS `account_summary_monthly` (
  `monthly_id` int(11) NOT NULL AUTO_INCREMENT,
  `as_of_date` datetime DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `deposit_amt` decimal(20,6) DEFAULT NULL,
  `insurance_amt` decimal(20,6) DEFAULT NULL,
  `offshore_bond_amt` decimal(20,6) DEFAULT NULL,
  PRIMARY KEY (`monthly_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


