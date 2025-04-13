-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: Apr 13, 2025 at 01:01 AM
-- Server version: 9.2.0
-- PHP Version: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `manpower`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
                           `id` int NOT NULL,
                           `company_id` int NOT NULL,
                           `name` varchar(100) NOT NULL,
                           `type` varchar(50) DEFAULT NULL,
                           `bank_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                           `description` text,
                           `balance` decimal(15,2) DEFAULT '0.00',
                           `account_number` decimal(15,2) DEFAULT NULL,
                           `iban` varchar(50) DEFAULT NULL,
                           `is_default` tinyint DEFAULT NULL,
                           `status` tinyint NOT NULL,
                           `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `company_id`, `name`, `type`, `bank_name`, `description`, `balance`, `account_number`, `iban`, `is_default`, `status`, `created_at`, `updated_at`) VALUES
    (2, 1, 'Seemab', 'CASH', NULL, '', 200.00, 4563534535.00, '', 1, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `asset`
--

CREATE TABLE `asset` (
                         `id` int NOT NULL,
                         `company_id` int NOT NULL,
                         `sponsored_by` int DEFAULT NULL,
                         `sponsorship_value` decimal(5,2) DEFAULT NULL,
                         `sponsorship_type` varchar(15) DEFAULT NULL,
                         `name` varchar(50) NOT NULL,
                         `id_number` varchar(15) NOT NULL,
                         `iqama_expiry` date DEFAULT NULL,
                         `phone` varchar(25) DEFAULT NULL,
                         `designation` varchar(25) DEFAULT NULL,
                         `passport` varchar(25) DEFAULT NULL,
                         `passport_expiry` date DEFAULT NULL,
                         `joining_date` date NOT NULL,
                         `asset_type` tinyint NOT NULL,
                         `asset_number` int DEFAULT NULL,
                         `asset_ownership` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `asset`
--

INSERT INTO `asset` (`id`, `company_id`, `sponsored_by`, `sponsorship_value`, `sponsorship_type`, `name`, `id_number`, `iqama_expiry`, `phone`, `designation`, `passport`, `passport_expiry`, `joining_date`, `asset_type`, `asset_number`, `asset_ownership`) VALUES
                                                                                                                                                                                                                                                                   (2, 1, 1, 10.20, 'FIXED', 'Adeel', '12321', '2025-03-20', '', '', 'AB1234', '2025-03-01', '2025-03-01', 1, 123, 1),
                                                                                                                                                                                                                                                                   (3, 1, 1, 5.20, 'PERCENTAGE', 'Hamza', '123', '2025-03-20', NULL, NULL, NULL, NULL, '2025-03-01', 1, 321, 2),
                                                                                                                                                                                                                                                                   (4, 1, NULL, NULL, NULL, 'Adil', 'string', '2025-03-18', 'string', 'string', 'string', '2025-03-18', '2025-03-18', 1, 0, 1),
                                                                                                                                                                                                                                                                   (6, 1, NULL, NULL, NULL, 'stringAli', 'string2', '2025-03-18', 'string', 'string', 'string', '2025-03-18', '2025-03-18', 1, 0, 1),
                                                                                                                                                                                                                                                                   (7, 1, NULL, NULL, NULL, 'Test Asset', '324234', '2025-04-29', '3453535', '', '3453gh', '2025-04-29', '2025-04-10', 2, 22424, 2);

-- --------------------------------------------------------

--
-- Table structure for table `asset_designation`
--

CREATE TABLE `asset_designation` (
                                     `id` int NOT NULL,
                                     `company_id` int NOT NULL,
                                     `asset_id` int NOT NULL,
                                     `designation_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

-- --------------------------------------------------------

--
-- Table structure for table `asset_payable`
--

CREATE TABLE `asset_payable` (
                                 `id` int NOT NULL,
                                 `company_id` int NOT NULL,
                                 `asset_project_id` int DEFAULT NULL,
                                 `asset_id` int NOT NULL,
                                 `invoice_id` int NOT NULL,
                                 `asset_payable` decimal(10,2) DEFAULT NULL,
                                 `paid_amount` decimal(10,2) NOT NULL,
                                 `payment_status` varchar(10) DEFAULT NULL,
                                 `status` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `asset_payable`
--

INSERT INTO `asset_payable` (`id`, `company_id`, `asset_project_id`, `asset_id`, `invoice_id`, `asset_payable`, `paid_amount`, `payment_status`, `status`) VALUES
                                                                                                                                                               (1, 1, 1, 2, 9, 29.23, 9.00, 'UNPAID', NULL),
                                                                                                                                                               (7, 1, 5, 7, 17, 400.00, 0.00, 'UNPAID', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `asset_project`
--

CREATE TABLE `asset_project` (
                                 `id` int NOT NULL,
                                 `company_id` int NOT NULL,
                                 `asset_id` int NOT NULL,
                                 `project_id` int NOT NULL,
                                 `designation` int NOT NULL,
                                 `asset_project_name` varchar(45) DEFAULT NULL,
                                 `regular_rate` decimal(10,2) NOT NULL,
                                 `overtime_rate` decimal(10,2) NOT NULL,
                                 `regular_rate_paid` decimal(10,2) NOT NULL,
                                 `overtime_rate_paid` decimal(10,2) NOT NULL,
                                 `start_date` date NOT NULL,
                                 `end_date` date DEFAULT NULL,
                                 `is_active` tinyint NOT NULL DEFAULT '1',
                                 `status` tinyint NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `asset_project`
--

INSERT INTO `asset_project` (`id`, `company_id`, `asset_id`, `project_id`, `designation`, `asset_project_name`, `regular_rate`, `overtime_rate`, `regular_rate_paid`, `overtime_rate_paid`, `start_date`, `end_date`, `is_active`, `status`) VALUES
                                                                                                                                                                                                                                                 (1, 1, 2, 1, 1, 'AP-1', 10.00, 12.00, 8.00, 9.00, '2025-02-01', '2025-03-15', 1, 1),
                                                                                                                                                                                                                                                 (2, 1, 3, 1, 1, 'DRILLING', 10.00, 12.00, 8.00, 9.00, '2025-03-01', '2025-03-15', 1, 1),
                                                                                                                                                                                                                                                 (3, 1, 2, 2, 1, NULL, 20.00, 25.00, 15.00, 21.00, '2025-03-01', '2025-03-15', 1, 1),
                                                                                                                                                                                                                                                 (4, 1, 2, 1, 1, 'AP-2', 10.00, 12.00, 8.00, 9.00, '2025-02-01', '2025-03-15', 1, 1),
                                                                                                                                                                                                                                                 (5, 1, 7, 4, 1, NULL, 10.00, 20.00, 5.00, 10.00, '2025-04-10', '2025-04-28', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
                          `id` int NOT NULL,
                          `client_id` varchar(50) NOT NULL,
                          `name` varchar(50) NOT NULL,
                          `address` varchar(100) NOT NULL,
                          `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `client_id`, `name`, `address`, `status`) VALUES
                                                                          (1, 'ARM-123', 'ARAMCO', 'DHAHRAN', NULL),
                                                                          (2, 'TEST-CLIENT-1', 'TEST-CLIENT-1', 'ISLAMABAD', 1);

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
                           `id` int NOT NULL,
                           `name` varchar(50) NOT NULL,
                           `address` varchar(100) NOT NULL,
                           `max_asset_count` int NOT NULL,
                           `header_image_url` varchar(100) DEFAULT NULL,
                           `footer_image_url` varchar(100) DEFAULT NULL,
                           `bank_account_title` varchar(45) DEFAULT NULL,
                           `bank_account_number` varchar(45) DEFAULT NULL,
                           `bank_iban` varchar(45) DEFAULT NULL,
                           `bank_name` varchar(45) DEFAULT NULL,
                           `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `name`, `address`, `max_asset_count`, `header_image_url`, `footer_image_url`, `bank_account_title`, `bank_account_number`, `bank_iban`, `bank_name`, `status`) VALUES
                                                                                                                                                                                                (1, 'SAUDI TECH', 'DAMMAN', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                (2, 'SAUDI TECH2', 'RIYADH', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `designation`
--

CREATE TABLE `designation` (
                               `id` int NOT NULL,
                               `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `designation`
--

INSERT INTO `designation` (`id`, `name`) VALUES
    (1, 'QC Engineer');

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

CREATE TABLE `expense` (
                           `id` int NOT NULL,
                           `company_id` int NOT NULL,
                           `asset_id` int NOT NULL,
                           `expense_category` int NOT NULL,
                           `expense_type` varchar(10) NOT NULL COMMENT '''project/self''',
                           `comments` varchar(100) NOT NULL,
                           `amount` int NOT NULL,
                           `expense_project` int DEFAULT NULL,
                           `expense_metric` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`id`, `company_id`, `asset_id`, `expense_category`, `expense_type`, `comments`, `amount`, `expense_project`, `expense_metric`) VALUES
                                                                                                                                                          (1, 1, 2, 1, 'Self', '', 100, 1, NULL),
                                                                                                                                                          (2, 1, 2, 2, 'Project', '', 100, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `expense_category`
--

CREATE TABLE `expense_category` (
                                    `expense_category_id` int NOT NULL,
                                    `category_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `expense_category`
--

INSERT INTO `expense_category` (`expense_category_id`, `category_name`) VALUES
                                                                            (1, 'Food'),
                                                                            (2, 'Accommodation'),
                                                                            (3, 'Iqama');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
                           `id` int NOT NULL,
                           `company_id` int NOT NULL,
                           `client_id` int NOT NULL,
                           `number` varchar(50) DEFAULT NULL,
                           `status` tinyint DEFAULT '1',
                           `cleared_date` date DEFAULT NULL,
                           `start_date` date DEFAULT NULL,
                           `end_date` date DEFAULT NULL,
                           `create_date` date DEFAULT NULL,
                           `total_before_tax` decimal(10,2) DEFAULT NULL,
                           `tax_amount` decimal(10,2) DEFAULT NULL,
                           `total_amount_with_tax` decimal(10,2) DEFAULT NULL,
                           `assets_payable` decimal(10,2) DEFAULT NULL,
                           `sponsor_payable` decimal(10,2) DEFAULT NULL,
                           `profit` decimal(10,2) DEFAULT NULL,
                           `creator_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `company_id`, `client_id`, `number`, `status`, `cleared_date`, `start_date`, `end_date`, `create_date`, `total_before_tax`, `tax_amount`, `total_amount_with_tax`, `assets_payable`, `sponsor_payable`, `profit`, `creator_id`) VALUES
                                                                                                                                                                                                                                                                 (9, 1, 1, 'INV-123', 1, NULL, '2025-03-06', '2025-03-06', '2025-03-11', 5000.00, 750.00, 5750.00, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                 (17, 1, 2, 'INV-20', 1, NULL, '2025-04-03', '2025-04-29', '2025-04-03', 640.00, 96.00, 736.00, 400.00, 128.00, 112.00, 5);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_asset`
--

CREATE TABLE `invoice_asset` (
                                 `id` int NOT NULL,
                                 `invoice_id` int NOT NULL,
                                 `asset_id` int NOT NULL,
                                 `asset_project_id` int DEFAULT NULL,
                                 `standard_hours` decimal(5,2) DEFAULT NULL,
                                 `standard_rate` decimal(5,2) DEFAULT NULL,
                                 `ot_rate` decimal(5,2) DEFAULT NULL,
                                 `ot_hours` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `invoice_asset`
--

INSERT INTO `invoice_asset` (`id`, `invoice_id`, `asset_id`, `asset_project_id`, `standard_hours`, `standard_rate`, `ot_rate`, `ot_hours`) VALUES
                                                                                                                                               (13, 9, 2, 1, 0.00, 0.00, 12.00, 9.00),
                                                                                                                                               (14, 9, 3, 2, 0.00, 0.00, 0.00, 0.00),
                                                                                                                                               (15, 9, 2, 3, 0.00, 0.00, 12.00, 5.00),
                                                                                                                                               (23, 17, 7, 5, 40.00, 10.00, 12.00, 20.00);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_sponsor_payable`
--

CREATE TABLE `invoice_sponsor_payable` (
                                           `id` int NOT NULL,
                                           `company_id` int NOT NULL,
                                           `project_sponsorship_id` int DEFAULT NULL,
                                           `sponsor_id` int NOT NULL,
                                           `invoice_id` int NOT NULL,
                                           `sponsorship_payable` decimal(10,2) DEFAULT NULL,
                                           `paid_amount` decimal(10,2) NOT NULL,
                                           `sponsorship_asset` int DEFAULT NULL,
                                           `payment_status` varchar(7) DEFAULT NULL,
                                           `sponsorship_determinant` varchar(11) DEFAULT NULL,
                                           `status` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `invoice_sponsor_payable`
--

INSERT INTO `invoice_sponsor_payable` (`id`, `company_id`, `project_sponsorship_id`, `sponsor_id`, `invoice_id`, `sponsorship_payable`, `paid_amount`, `sponsorship_asset`, `payment_status`, `sponsorship_determinant`, `status`) VALUES
                                                                                                                                                                                                                                       (1, 1, 2, 1, 9, 19.29, 0.00, 4, 'UNPAID', 'REVENUE', NULL),
                                                                                                                                                                                                                                       (2, 1, NULL, 1, 9, 19.29, 0.00, 4, 'UNPAID', 'REVENUE', NULL),
                                                                                                                                                                                                                                       (8, 1, 6, 5, 17, 64.00, 0.00, 7, 'UNPAID', 'REVENUE', 1),
                                                                                                                                                                                                                                       (9, 1, 7, 1, 17, 64.00, 0.00, 7, 'UNPAID', 'REVENUE', 1);

-- --------------------------------------------------------

--
-- Table structure for table `main_account`
--

CREATE TABLE `main_account` (
                                `id` int NOT NULL,
                                `company_id` int NOT NULL,
                                `name` varchar(100) NOT NULL,
                                `type` varchar(50) DEFAULT NULL,
                                `description` text,
                                `balance` decimal(15,2) DEFAULT '0.00',
                                `account_number` decimal(15,2) DEFAULT NULL,
                                `iban` varchar(50) DEFAULT NULL,
                                `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
                           `id` int NOT NULL,
                           `main_account_id` int DEFAULT NULL,
                           `amount` decimal(15,2) NOT NULL,
                           `payment_date` date DEFAULT NULL,
                           `payment_method` varchar(50) DEFAULT NULL,
                           `reference` varchar(100) DEFAULT NULL,
                           `paid_to_type` enum('ASSET','SPONSOR','EXPENSE','INVOICE','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                           `paid_to_id` int DEFAULT NULL,
                           `invoice_id` int DEFAULT NULL,
                           `remarks` text,
                           `status` enum('COMPLETED','PENDING','FAILED') DEFAULT 'COMPLETED',
                           `payment_type` enum('INITIAL','ADJUSTMENT','FULL','REFUND','ADVANCE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                           `payment_direction` enum('INCOMING','OUTGOING') NOT NULL DEFAULT 'OUTGOING' COMMENT '1. incoming, 2.outgoing',
                           `payment_timestamp` timestamp NOT NULL,
                           `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `main_account_id`, `amount`, `payment_date`, `payment_method`, `reference`, `paid_to_type`, `paid_to_id`, `invoice_id`, `remarks`, `status`, `payment_type`, `payment_direction`, `payment_timestamp`, `created_at`, `updated_at`) VALUES
                                                                                                                                                                                                                                                                    (2, 2, 100.00, '2025-04-10', 'BANK_TRANSFER', 'test', 'ASSET', 7, NULL, 'testing payment', 'COMPLETED', 'INITIAL', 'OUTGOING', '2025-04-11 14:40:32', NULL, '2025-04-11 14:46:11'),
                                                                                                                                                                                                                                                                    (3, 2, 100.00, '2025-04-10', 'BANK_TRANSFER', 'test', 'ASSET', 7, NULL, 'testing payment', 'COMPLETED', 'INITIAL', 'OUTGOING', '2025-04-11 14:41:05', NULL, '2025-04-11 14:46:08'),
                                                                                                                                                                                                                                                                    (4, 2, 100.00, '2025-04-10', 'BANK_TRANSFER', 'test', 'ASSET', 7, NULL, 'testing payment', 'COMPLETED', 'INITIAL', 'OUTGOING', '2025-04-11 14:41:54', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `preferences`
--

CREATE TABLE `preferences` (
                               `id` int NOT NULL,
                               `company_id` int NOT NULL,
                               `tax_amount` decimal(10,2) DEFAULT NULL,
                               `invoice_seq` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `preferences`
--

INSERT INTO `preferences` (`id`, `company_id`, `tax_amount`, `invoice_seq`) VALUES
    (1, 1, 15.00, 21);

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
                           `id` int NOT NULL,
                           `company_id` int NOT NULL,
                           `client_id` int NOT NULL,
                           `project_id` varchar(50) NOT NULL,
                           `name` varchar(50) NOT NULL,
                           `location` varchar(100) NOT NULL,
                           `start_date` datetime NOT NULL,
                           `end_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`id`, `company_id`, `client_id`, `project_id`, `name`, `location`, `start_date`, `end_date`) VALUES
                                                                                                                        (1, 1, 1, 'PROJ-1', 'ARAMCO HALL', 'DAMMAM', '2025-03-01 00:00:00', '2025-04-22 00:00:00'),
                                                                                                                        (2, 1, 1, 'PROJ-2', 'Halliburton', 'RIYADH', '2025-03-01 00:00:00', '2025-04-22 00:00:00'),
                                                                                                                        (4, 1, 2, 'PR-TEST-01', 'STELLA AI', 'ISLAMABAD', '2025-04-13 00:00:00', '2025-04-29 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `project_asset_sponsorship`
--

CREATE TABLE `project_asset_sponsorship` (
                                             `id` int NOT NULL,
                                             `sponsor_id` int NOT NULL,
                                             `asset_id` int NOT NULL,
                                             `asset_project_id` int DEFAULT NULL,
                                             `sponsorship_type` varchar(11) DEFAULT NULL COMMENT 'FIXED/PERCENTAGE',
                                             `sponsorship_value` decimal(10,2) DEFAULT NULL,
                                             `sponsorship_determinant` varchar(11) DEFAULT NULL COMMENT 'REVENUE/PROFIT',
                                             `sponsorship_basis` varchar(20) CHARACTER SET utf32 COLLATE utf32_general_ci NOT NULL COMMENT 'ASSETBASED/PROJECTBASED'
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `project_asset_sponsorship`
--

INSERT INTO `project_asset_sponsorship` (`id`, `sponsor_id`, `asset_id`, `asset_project_id`, `sponsorship_type`, `sponsorship_value`, `sponsorship_determinant`, `sponsorship_basis`) VALUES
                                                                                                                                                                                          (2, 1, 2, 1, 'PERCENTAGE', 0.50, 'REVENUE', 'ASSET_BASED'),
                                                                                                                                                                                          (3, 1, 2, 1, 'PERCENTAGE', 0.50, 'REVENUE', 'ASSET_BASED'),
                                                                                                                                                                                          (4, 1, 2, NULL, 'PERCENTAGE', 0.50, 'REVENUE', 'ASSET_BASED'),
                                                                                                                                                                                          (6, 5, 7, NULL, 'PERCENTAGE', 10.00, 'REVENUE', 'ASSET_BASED'),
                                                                                                                                                                                          (7, 1, 7, NULL, 'FIXED', 10.00, 'REVENUE', 'ASSET_BASED');

-- --------------------------------------------------------

--
-- Table structure for table `sponsor`
--

CREATE TABLE `sponsor` (
                           `id` int NOT NULL,
                           `company_id` int NOT NULL,
                           `sponsor_id` int DEFAULT NULL,
                           `name` varchar(50) NOT NULL,
                           `phone` varchar(25) CHARACTER SET utf32 COLLATE utf32_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `sponsor`
--

INSERT INTO `sponsor` (`id`, `company_id`, `sponsor_id`, `name`, `phone`) VALUES
                                                                              (1, 1, 1, 'HAMZA_SPONSOR', '+921234'),
                                                                              (2, 1, 2, 'HAMZA_SPONSOR2', '+921234'),
                                                                              (3, 1, NULL, 'Seemab Sponsor profit', ''),
                                                                              (5, 1, NULL, 'Seemab revenue', '');

-- --------------------------------------------------------

--
-- Table structure for table `timesheet`
--

CREATE TABLE `timesheet` (
                             `id` int NOT NULL,
                             `asset_id` int NOT NULL,
                             `asset_project_id` int NOT NULL,
                             `timesheet_date` date NOT NULL,
                             `hours` decimal(5,2) NOT NULL,
                             `rate_type` tinyint NOT NULL,
                             `rate` decimal(5,2) DEFAULT NULL,
                             `rate_paid` decimal(5,2) DEFAULT NULL,
                             `invoice_number` int DEFAULT NULL,
                             `row_sr_no` int DEFAULT NULL,
                             `week_index` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `timesheet`
--

INSERT INTO `timesheet` (`id`, `asset_id`, `asset_project_id`, `timesheet_date`, `hours`, `rate_type`, `rate`, `rate_paid`, `invoice_number`, `row_sr_no`, `week_index`) VALUES
                                                                                                                                                                             (4, 2, 1, '2025-03-01', 5.00, 1, 10.00, 8.00, 0, NULL, NULL),
                                                                                                                                                                             (5, 2, 1, '2025-03-01', 5.51, 2, 12.00, 9.00, 0, NULL, NULL),
                                                                                                                                                                             (6, 2, 1, '2025-03-02', 6.00, 1, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (7, 2, 1, '2025-03-02', 7.00, 2, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (8, 2, 1, '2025-03-05', 8.00, 1, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (9, 2, 1, '2025-03-06', 9.00, 2, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (10, 2, 2, '2025-03-02', 5.00, 1, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (11, 2, 2, '2025-03-02', 5.00, 2, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (12, 2, 2, '2025-03-05', 5.00, 1, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (13, 2, 3, '2025-03-06', 5.00, 2, 12.00, 9.00, NULL, NULL, NULL),
                                                                                                                                                                             (14, 7, 5, '2025-04-14', 8.00, 1, 10.00, 8.00, 0, 3, 3),
                                                                                                                                                                             (15, 7, 5, '2025-04-15', 8.00, 1, 10.00, 8.00, 0, 3, 3),
                                                                                                                                                                             (16, 7, 5, '2025-04-16', 8.00, 1, 10.00, 8.00, 0, 3, 3),
                                                                                                                                                                             (17, 7, 5, '2025-04-17', 8.00, 1, 10.00, 8.00, 0, 3, 3),
                                                                                                                                                                             (18, 7, 5, '2025-04-18', 8.00, 1, 10.00, 8.00, 0, 3, 3),
                                                                                                                                                                             (19, 7, 5, '2025-04-14', 4.00, 2, 12.00, 9.00, 0, 3, 3),
                                                                                                                                                                             (20, 7, 5, '2025-04-15', 4.00, 2, 12.00, 9.00, 0, 3, 3),
                                                                                                                                                                             (21, 7, 5, '2025-04-16', 4.00, 2, 12.00, 9.00, 0, 3, 3),
                                                                                                                                                                             (22, 7, 5, '2025-04-17', 4.00, 2, 12.00, 9.00, 0, 3, 3),
                                                                                                                                                                             (23, 7, 5, '2025-04-18', 4.00, 2, 12.00, 9.00, 0, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
                        `id` int NOT NULL,
                        `username` varchar(45) NOT NULL,
                        `password` varchar(45) NOT NULL,
                        `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `company_id` int NOT NULL,
                        `role` int DEFAULT '2' COMMENT '1. admin\n2. employee',
                        `status` int DEFAULT '1' COMMENT '1. active\\n2. inactive'
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `create_date`, `update_date`, `company_id`, `role`, `status`) VALUES
    (5, 'admin@admin.com', 'Admin123', '2025-03-01 12:29:45', '2025-03-19 00:20:28', 1, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `asset`
--
ALTER TABLE `asset`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_number` (`id_number`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `sponsored_by` (`sponsored_by`);

--
-- Indexes for table `asset_designation`
--
ALTER TABLE `asset_designation`
    ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `asset_id` (`asset_id`),
  ADD KEY `designation_id` (`designation_id`);

--
-- Indexes for table `asset_payable`
--
ALTER TABLE `asset_payable`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_asset_project_idx` (`asset_project_id`);

--
-- Indexes for table `asset_project`
--
ALTER TABLE `asset_project`
    ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `asset_id` (`asset_id`),
  ADD KEY `project_id` (`project_id`),
  ADD KEY `designation` (`designation`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `client_id` (`client_id`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `designation`
--
ALTER TABLE `designation`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
    ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `asset_id` (`asset_id`),
  ADD KEY `expense_project` (`expense_project`),
  ADD KEY `expense_ibfk_4_idx` (`expense_category`);

--
-- Indexes for table `expense_category`
--
ALTER TABLE `expense_category`
    ADD PRIMARY KEY (`expense_category_id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `number` (`number`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `invoice_asset`
--
ALTER TABLE `invoice_asset`
    ADD PRIMARY KEY (`id`),
  ADD KEY `invoice_id` (`invoice_id`),
  ADD KEY `asset_id` (`asset_id`),
  ADD KEY `invoice_asset_ibfk_3_idx` (`asset_project_id`);

--
-- Indexes for table `invoice_sponsor_payable`
--
ALTER TABLE `invoice_sponsor_payable`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_inv_sponsor_idx` (`project_sponsorship_id`),
  ADD KEY `fk_inv_sponsor_asset_idx` (`sponsorship_asset`),
  ADD KEY `fk_inv_sponsor` (`sponsor_id`);

--
-- Indexes for table `main_account`
--
ALTER TABLE `main_account`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
    ADD PRIMARY KEY (`id`),
  ADD KEY `main_account_id` (`main_account_id`);

--
-- Indexes for table `preferences`
--
ALTER TABLE `preferences`
    ADD PRIMARY KEY (`id`),
  ADD KEY `company_idx` (`company_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `project_id` (`project_id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `project_asset_sponsorship`
--
ALTER TABLE `project_asset_sponsorship`
    ADD PRIMARY KEY (`id`),
  ADD KEY `asset_project_fk_idx` (`asset_project_id`),
  ADD KEY `fk_asset_idx` (`asset_id`),
  ADD KEY `fk_sponsor_idx` (`sponsor_id`);

--
-- Indexes for table `sponsor`
--
ALTER TABLE `sponsor`
    ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `timesheet`
--
ALTER TABLE `timesheet`
    ADD PRIMARY KEY (`id`),
  ADD KEY `asset_id` (`asset_id`),
  ADD KEY `timesheet_asset_proj_fk_1_idx` (`asset_project_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`),
  ADD KEY `user_company_idx` (`company_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `asset`
--
ALTER TABLE `asset`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `asset_designation`
--
ALTER TABLE `asset_designation`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `asset_payable`
--
ALTER TABLE `asset_payable`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `asset_project`
--
ALTER TABLE `asset_project`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `designation`
--
ALTER TABLE `designation`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `expense_category`
--
ALTER TABLE `expense_category`
    MODIFY `expense_category_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `invoice_asset`
--
ALTER TABLE `invoice_asset`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `invoice_sponsor_payable`
--
ALTER TABLE `invoice_sponsor_payable`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `main_account`
--
ALTER TABLE `main_account`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `preferences`
--
ALTER TABLE `preferences`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `project_asset_sponsorship`
--
ALTER TABLE `project_asset_sponsorship`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `sponsor`
--
ALTER TABLE `sponsor`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `timesheet`
--
ALTER TABLE `timesheet`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `asset`
--
ALTER TABLE `asset`
    ADD CONSTRAINT `asset_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `asset_ibfk_2` FOREIGN KEY (`sponsored_by`) REFERENCES `sponsor` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `asset_designation`
--
ALTER TABLE `asset_designation`
    ADD CONSTRAINT `asset_designation_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `asset_designation_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `asset_designation_ibfk_3` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `asset_payable`
--
ALTER TABLE `asset_payable`
    ADD CONSTRAINT `fk_asset_project` FOREIGN KEY (`asset_project_id`) REFERENCES `asset_project` (`id`);

--
-- Constraints for table `asset_project`
--
ALTER TABLE `asset_project`
    ADD CONSTRAINT `asset_project_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `asset_project_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `asset_project_ibfk_3` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `asset_project_ibfk_4` FOREIGN KEY (`designation`) REFERENCES `designation` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `expense`
--
ALTER TABLE `expense`
    ADD CONSTRAINT `expense_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `expense_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `expense_ibfk_3` FOREIGN KEY (`expense_project`) REFERENCES `project` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `expense_ibfk_4` FOREIGN KEY (`expense_category`) REFERENCES `expense_category` (`expense_category_id`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
    ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `invoice_asset`
--
ALTER TABLE `invoice_asset`
    ADD CONSTRAINT `invoice_asset_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `invoice_asset_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `invoice_asset_ibfk_3` FOREIGN KEY (`asset_project_id`) REFERENCES `asset_project` (`id`);

--
-- Constraints for table `invoice_sponsor_payable`
--
ALTER TABLE `invoice_sponsor_payable`
    ADD CONSTRAINT `fk_inv_sponsor` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_inv_sponsor_asset` FOREIGN KEY (`sponsorship_asset`) REFERENCES `asset` (`id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
    ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`main_account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `preferences`
--
ALTER TABLE `preferences`
    ADD CONSTRAINT `company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `project`
--
ALTER TABLE `project`
    ADD CONSTRAINT `project_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `project_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `project_asset_sponsorship`
--
ALTER TABLE `project_asset_sponsorship`
    ADD CONSTRAINT `asset_project_fk` FOREIGN KEY (`asset_project_id`) REFERENCES `asset_project` (`id`),
  ADD CONSTRAINT `fk_asset` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`),
  ADD CONSTRAINT `fk_sponsor` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`);

--
-- Constraints for table `sponsor`
--
ALTER TABLE `sponsor`
    ADD CONSTRAINT `sponsor_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `timesheet`
--
ALTER TABLE `timesheet`
    ADD CONSTRAINT `timesheet_asset_proj_fk_1` FOREIGN KEY (`asset_project_id`) REFERENCES `asset_project` (`id`),
  ADD CONSTRAINT `timesheet_ibfk_1` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
    ADD CONSTRAINT `user_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
