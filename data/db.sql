-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: May 22, 2025 at 12:38 PM
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
(2, 1, 'Seemab', 'CASH', NULL, '', 48.00, 4563534535.00, '', 1, 1, NULL, NULL);

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
  `id_number` varchar(15) CHARACTER SET utf32 COLLATE utf32_general_ci DEFAULT NULL,
  `iqama_expiry` date DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `designation_id` int DEFAULT NULL,
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

INSERT INTO `asset` (`id`, `company_id`, `sponsored_by`, `sponsorship_value`, `sponsorship_type`, `name`, `id_number`, `iqama_expiry`, `phone`, `designation_id`, `passport`, `passport_expiry`, `joining_date`, `asset_type`, `asset_number`, `asset_ownership`) VALUES
(2, 1, 1, 10.20, 'FIXED', 'Adeel', '12321', '2025-03-20', '', NULL, 'AB1234', '2025-03-01', '2025-03-01', 1, 123, 1),
(3, 1, 1, 5.20, 'PERCENTAGE', 'Hamza', '123', '2025-03-20', NULL, NULL, NULL, NULL, '2025-03-01', 1, 321, 2),
(4, 1, NULL, NULL, NULL, 'Adil', 'string', '2025-03-18', 'string', NULL, 'string', '2025-03-18', '2025-03-18', 1, 0, 1),
(6, 1, NULL, NULL, NULL, 'stringAli', 'string2', '2025-03-18', 'string', NULL, 'string', '2025-03-18', '2025-03-18', 1, 0, 1),
(7, 1, NULL, NULL, NULL, 'Test Asset', '324234', '2025-04-29', '3453535', NULL, '3453gh', '2025-04-29', '2025-04-10', 2, 22424, 2),
(10, 1, NULL, NULL, NULL, 'Hamza 1', '1', NULL, '', 2, '', NULL, '2025-05-06', 1, NULL, 1),
(11, 1, NULL, NULL, NULL, '17MayAsset', '2', '2025-05-17', '', 1, 'ABCD', '2025-05-17', '2025-05-17', 1, 123, 1),
(12, 1, NULL, NULL, NULL, '17MayAsset1', '3', '2025-05-17', '', 1, 'aBCD', '2025-05-17', '2025-05-17', 1, 123, 1);

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
  `payment_status` varchar(25) CHARACTER SET utf32 COLLATE utf32_general_ci DEFAULT NULL,
  `status` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `asset_payable`
--

INSERT INTO `asset_payable` (`id`, `company_id`, `asset_project_id`, `asset_id`, `invoice_id`, `asset_payable`, `paid_amount`, `payment_status`, `status`) VALUES
(1, 1, 1, 2, 9, 29.23, 9.00, 'UNPAID', NULL),
(7, 1, 5, 7, 17, 400.00, 0.00, 'UNPAID', NULL),
(8, 1, 6, 10, 19, 240.00, 0.00, 'INVOICE_PENDING', NULL),
(10, 1, 6, 10, 21, 240.00, 0.00, 'INVOICE_PENDING', NULL),
(11, 1, 6, 10, 22, 240.00, 0.00, 'INVOICE_PENDING', NULL),
(12, 1, 6, 10, 23, 240.00, 0.00, 'INVOICE_PENDING', NULL),
(13, 1, 6, 10, 24, 240.00, 0.00, 'INVOICE_PENDING', NULL),
(14, 1, 6, 10, 25, 240.00, 0.00, 'INVOICE_PENDING', NULL),
(15, 1, 10, 11, 26, 400.00, 0.00, 'INVOICE_PENDING', NULL),
(16, 1, 10, 11, 27, 400.00, 0.00, 'INVOICE_PENDING', NULL),
(17, 1, 10, 11, 28, 400.00, 0.00, 'INVOICE_PENDING', NULL),
(18, 1, 10, 11, 29, 400.00, 0.00, 'INVOICE_PENDING', NULL),
(19, 1, 10, 11, 30, 400.00, 0.00, 'INVOICE_PENDING', NULL),
(20, 1, 10, 11, 31, 400.00, 0.00, 'INVOICE_PENDING', NULL),
(21, 1, 11, 12, 32, 400.00, 0.00, 'INVOICE_PENDING', NULL);

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
(5, 1, 7, 4, 1, NULL, 10.00, 20.00, 5.00, 10.00, '2025-04-10', '2025-04-28', 1, 1),
(6, 1, 10, 5, 1, 'PRJ', 10.00, 20.00, 5.00, 10.00, '2025-05-05', '2025-05-05', 1, 1),
(7, 1, 10, 4, 1, 'PRJ2', 10.00, 20.00, 5.00, 10.00, '2025-05-05', '2025-05-05', 1, 1),
(8, 1, 2, 5, 1, NULL, 10.00, 20.00, 15.00, 17.00, '2025-05-14', '2025-05-14', 1, 1),
(9, 1, 2, 5, 1, NULL, 10.00, 20.00, 7.00, 15.00, '2025-05-14', '2025-05-14', 1, 1),
(10, 1, 11, 6, 1, NULL, 10.00, 20.00, 5.00, 10.00, '2025-05-17', '2025-05-18', 1, 1),
(11, 1, 12, 7, 1, NULL, 10.00, 20.00, 5.00, 10.00, '2025-05-17', '2025-05-17', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id` int NOT NULL,
  `brand_name` varchar(100) NOT NULL,
  `company_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `brand_name`, `company_id`) VALUES
(1, 'APPLE', 1);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int NOT NULL,
  `client_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `status` int DEFAULT NULL,
  `company_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `client_id`, `name`, `address`, `status`, `company_id`) VALUES
(1, 'ARM-123', 'ARAMCO', 'DHAHRAN', NULL, 1),
(2, 'TEST-CLIENT-1', 'TEST-CLIENT-1', 'ISLAMABAD', 1, 1),
(3, 'CL123', 'Aramco1', 'Khobar', 1, 1),
(4, '17MayClient', '17MayClient', '17MayClient', 1, 1),
(5, '17MayClient1', '17MayClient1', '17MayClient1', 1, 1);

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
  `vat` varchar(20) NOT NULL,
  `status` int DEFAULT NULL,
  `allow_erp` tinyint(1) DEFAULT NULL,
  `allow_pos` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `name`, `address`, `max_asset_count`, `header_image_url`, `footer_image_url`, `bank_account_title`, `bank_account_number`, `bank_iban`, `bank_name`, `vat`, `status`, `allow_erp`, `allow_pos`) VALUES
(1, 'SAUDI TECH', 'DAMMAN', 5, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, 1, 1),
(2, 'SAUDI TECH2', 'RIYADH', 5, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL);

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
(1, 'QC Engineer'),
(2, 'Safety');

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
(2, 1, 2, 2, 'Project', '', 100, NULL, NULL),
(3, 1, 2, 1, 'PROJECT', 'dfdfdf', 400, 1, NULL),
(4, 1, 2, 1, 'PROJECT', 'dfdfdf', 400, 1, NULL);

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
  `due_date` date DEFAULT NULL,
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

INSERT INTO `invoice` (`id`, `company_id`, `client_id`, `number`, `status`, `cleared_date`, `start_date`, `end_date`, `create_date`, `due_date`, `total_before_tax`, `tax_amount`, `total_amount_with_tax`, `assets_payable`, `sponsor_payable`, `profit`, `creator_id`) VALUES
(9, 1, 1, 'INV-123', 1, NULL, '2025-03-06', '2025-03-06', '2025-03-11', NULL, 5000.00, 750.00, 5750.00, NULL, NULL, NULL, NULL),
(17, 1, 2, 'INV-20', 1, NULL, '2025-04-03', '2025-04-29', '2025-04-03', NULL, 640.00, 96.00, 736.00, 400.00, 128.00, 112.00, 5),
(19, 1, 3, 'INV-21', 1, NULL, '2025-04-30', '2025-05-09', '2025-05-05', NULL, 480.00, 72.00, 552.00, 240.00, 0.00, 240.00, 5),
(21, 1, 3, 'INV-22', 1, NULL, '2025-04-30', '2025-05-06', '2025-05-05', NULL, 480.00, 72.00, 552.00, 240.00, 48.00, 192.00, 5),
(22, 1, 3, 'INV-23', 3, NULL, '2025-04-30', '2025-05-05', '2025-05-05', NULL, 480.00, 72.00, 552.00, 240.00, 48.00, 192.00, 5),
(23, 1, 3, 'INV-24', 3, NULL, '2025-04-30', '2025-05-05', '2025-05-05', NULL, 480.00, 72.00, 552.00, 240.00, 21.50, 218.50, 5),
(24, 1, 3, 'INV-25', 3, NULL, '2025-04-30', '2025-05-05', '2025-05-05', NULL, 480.00, 72.00, 552.00, 240.00, 21.50, 218.50, 5),
(25, 1, 3, 'INV-26', 3, NULL, '2025-04-30', '2025-05-05', '2025-05-05', NULL, 480.00, 72.00, 552.00, 240.00, 21.50, 218.50, 5),
(26, 1, 4, 'INV-27', 3, NULL, '2025-04-30', '2025-05-30', '2025-05-17', NULL, 800.00, 120.00, 920.00, 400.00, 0.00, 400.00, 5),
(27, 1, 4, 'INV-28', 3, NULL, '2025-04-30', '2025-05-30', '2025-05-17', NULL, 800.00, 120.00, 920.00, 400.00, 0.00, 400.00, 5),
(28, 1, 4, 'INV-29', 3, NULL, '2025-04-30', '2025-05-30', '2025-05-17', NULL, 800.00, 120.00, 920.00, 400.00, 0.00, 400.00, 5),
(29, 1, 4, 'INV-30', 3, NULL, '2025-04-30', '2025-05-30', '2025-05-17', NULL, 800.00, 120.00, 920.00, 400.00, 0.00, 400.00, 5),
(30, 1, 4, 'INV-31', 3, NULL, '2025-04-30', '2025-05-30', '2025-05-17', NULL, 800.00, 120.00, 920.00, 400.00, 0.00, 400.00, 5),
(31, 1, 4, 'INV-32', 3, NULL, '2025-05-15', '2025-05-30', '2025-05-17', NULL, 800.00, 120.00, 920.00, 400.00, 0.00, 400.00, 5),
(32, 1, 5, 'INV-33', 3, NULL, '2025-04-30', '2025-05-30', '2025-05-17', '2025-05-17', 800.00, 120.00, 920.00, 400.00, 0.00, 400.00, 5);

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
(23, 17, 7, 5, 40.00, 10.00, 12.00, 20.00),
(25, 19, 10, 6, 32.00, 10.00, 20.00, 8.00),
(27, 21, 10, 6, 32.00, 10.00, 20.00, 8.00),
(28, 22, 10, 6, 32.00, 10.00, 20.00, 8.00),
(29, 23, 10, 6, 32.00, 10.00, 20.00, 8.00),
(30, 24, 10, 6, 32.00, 10.00, 20.00, 8.00),
(31, 25, 10, 6, 32.00, 10.00, 20.00, 8.00),
(32, 26, 11, 10, 40.00, 10.00, 20.00, 20.00),
(33, 27, 11, 10, 40.00, 10.00, 20.00, 20.00),
(34, 28, 11, 10, 40.00, 10.00, 20.00, 20.00),
(35, 29, 11, 10, 40.00, 10.00, 20.00, 20.00),
(36, 30, 11, 10, 40.00, 10.00, 20.00, 20.00),
(37, 31, 11, 10, 40.00, 10.00, 20.00, 20.00),
(38, 32, 12, 11, 40.00, 10.00, 20.00, 20.00);

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
  `payment_status` varchar(25) CHARACTER SET utf32 COLLATE utf32_general_ci DEFAULT NULL,
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
(9, 1, 7, 1, 17, 64.00, 0.00, 7, 'UNPAID', 'REVENUE', 1),
(10, 1, 8, 6, 21, 48.00, 0.00, 10, 'INVOICE_PENDING', 'REVENUE', 1),
(11, 1, 8, 6, 22, 48.00, 0.00, 10, 'INVOICE_PENDING', 'REVENUE', 1),
(12, 1, 8, 6, 23, 10.00, 0.00, 10, 'INVOICE_PENDING', 'REVENUE', 1),
(13, 1, 9, 7, 23, 11.50, 0.00, 10, 'INVOICE_PENDING', 'PROFIT', 1),
(14, 1, 8, 6, 24, 10.00, 0.00, 10, 'INVOICE_PENDING', 'REVENUE', 1),
(15, 1, 9, 7, 24, 11.50, 0.00, 10, 'INVOICE_PENDING', 'PROFIT', 1),
(16, 1, 8, 6, 25, 10.00, 0.00, 10, 'INVOICE_PENDING', 'REVENUE', 1),
(17, 1, 9, 7, 25, 11.50, 0.00, 10, 'INVOICE_PENDING', 'PROFIT', 1);

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
  `paid_to_type` enum('ASSET','SPONSOR','EXPENSE','INVOICE','OTHER','INVOICES') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
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
(4, 2, 100.00, '2025-04-10', 'BANK_TRANSFER', 'test', 'ASSET', 7, NULL, 'testing payment', 'COMPLETED', 'INITIAL', 'OUTGOING', '2025-04-11 14:41:54', NULL, NULL),
(5, 2, 40.00, '2025-05-05', 'CASH', 'adv', 'ASSET', 10, NULL, '', 'COMPLETED', 'INITIAL', 'OUTGOING', '2025-05-05 22:38:55', NULL, NULL),
(6, 2, 8.00, '2025-05-05', 'CASH', '', 'SPONSOR', 6, NULL, '', 'COMPLETED', 'INITIAL', 'OUTGOING', '2025-05-05 22:53:00', NULL, NULL),
(7, 2, 4.00, '2025-05-05', 'CASH', '', 'INVOICE', 3, 21, '', 'COMPLETED', 'ADJUSTMENT', 'INCOMING', '2025-05-05 22:56:20', NULL, NULL),
(8, 2, 100.00, '2025-05-17', 'CASH', '', 'ASSET', 12, NULL, '', 'COMPLETED', 'ADVANCE', 'OUTGOING', '2025-05-17 15:01:30', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `preferences`
--

CREATE TABLE `preferences` (
  `id` int NOT NULL,
  `company_id` int NOT NULL,
  `tax_amount` decimal(10,2) DEFAULT NULL,
  `invoice_seq` int DEFAULT NULL,
  `asset_id_sequence` int NOT NULL,
  `user_id_sequence` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `preferences`
--

INSERT INTO `preferences` (`id`, `company_id`, `tax_amount`, `invoice_seq`, `asset_id_sequence`, `user_id_sequence`) VALUES
(1, 1, 15.00, 34, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_print` varchar(250) NOT NULL,
  `name_print_ar` varchar(255) NOT NULL,
  `cat_id` int DEFAULT NULL,
  `subcat_id` decimal(60,0) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `packaging_id` int DEFAULT NULL,
  `comments` text,
  `company_id` int DEFAULT NULL,
  `selling_price` decimal(15,2) DEFAULT '0.00',
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `name_print`, `name_print_ar`, `cat_id`, `subcat_id`, `code`, `product_code`, `product_type`, `group_id`, `brand_id`, `packaging_id`, `comments`, `company_id`, `selling_price`, `status`) VALUES
(1, 'Apple iPhone 15 Pro Max', 'Apple iPhone 15 Pro Max', 'Apple iPhone 15 Pro Max', 1, NULL, 'IP-15-PX', 'IP-15-PX', NULL, NULL, 1, NULL, 'A good phone', 1, NULL, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `id` int NOT NULL,
  `category_name` varchar(50) NOT NULL,
  `company_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id`, `category_name`, `company_id`) VALUES
(1, 'Mobiles', 1),
(2, 'Mobiles1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `product_units`
--

CREATE TABLE `product_units` (
  `id` int NOT NULL,
  `product_id` int NOT NULL,
  `unit_id` int DEFAULT NULL,
  `conversion` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(4, 1, 2, 'PR-TEST-01', 'STELLA AI', 'ISLAMABAD', '2025-04-13 00:00:00', '2025-04-29 00:00:00'),
(5, 1, 3, 'PR-1234', 'Aramco-6-May', 'Khobar', '2025-05-05 00:00:00', '2025-05-13 00:00:00'),
(6, 1, 4, '17MayProjectId', '17MayProjectName', '17MayProject', '2025-05-17 00:00:00', '2025-05-24 00:00:00'),
(7, 1, 5, '17MayProject1', '17MayProject1', '17MayProject1', '2025-05-17 00:00:00', '2025-05-17 00:00:00');

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
(7, 1, 7, NULL, 'FIXED', 10.00, 'REVENUE', 'ASSET_BASED'),
(8, 6, 10, NULL, 'FIXED', 10.00, 'REVENUE', 'ASSET_BASED'),
(9, 7, 10, NULL, 'PERCENTAGE', 5.00, 'PROFIT', 'ASSET_BASED'),
(10, 8, 10, 5, 'FIXED', 10.00, 'PROFIT', 'PROJECT_BASED'),
(11, 9, 10, 6, 'PERCENTAGE', 10.00, 'PROFIT', 'PROJECT_BASED');

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `id` int NOT NULL,
  `supplier_id` int DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `total_amount` decimal(15,2) NOT NULL,
  `supplier_invoice_number` varchar(50) NOT NULL,
  `paid_amount` decimal(15,2) NOT NULL,
  `vat_amount` decimal(15,2) NOT NULL,
  `company_id` int DEFAULT NULL,
  `shop_id` int NOT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`id`, `supplier_id`, `date`, `total_amount`, `supplier_invoice_number`, `paid_amount`, `vat_amount`, `company_id`, `shop_id`, `status`) VALUES
(6, 1, '2025-05-20 00:06:06', 5000000.00, 'INV123', 0.00, 0.00, 1, 1, 'ACTIVE'),
(7, 1, '2025-05-20 00:09:32', 5000000.00, 'INV125', 0.00, 0.00, 1, 1, 'ACTIVE'),
(8, 1, '2025-05-20 00:13:30', 6000000.00, 'INV123', 0.00, 0.00, 1, 1, 'ACTIVE'),
(9, 1, '2025-05-20 00:14:34', 7000000.00, 'INV123', 0.00, 0.00, 1, 1, 'ACTIVE'),
(10, 1, '2025-05-20 00:15:04', 7000000.00, 'INV123', 0.00, 0.00, 1, 4, 'ACTIVE'),
(11, 1, '2025-05-20 08:24:36', 5000000.00, 'INV126', 0.00, 0.00, 1, 4, 'ACTIVE'),
(15, 1, '2025-05-22 12:33:12', 5000000.00, 'INV123', 2000.00, 0.00, 1, 1, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `sale`
--

CREATE TABLE `sale` (
  `id` int NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `total_amount` decimal(15,2) NOT NULL,
  `company_id` int DEFAULT NULL,
  `sale_date` datetime DEFAULT NULL,
  `shop_id` int DEFAULT NULL,
  `client_id` int NOT NULL,
  `po_number` varchar(50) DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `paid_amount` decimal(15,2) NOT NULL,
  `vat_amount` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sale_item`
--

CREATE TABLE `sale_item` (
  `id` int NOT NULL,
  `sale_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` decimal(15,2) NOT NULL,
  `unit_price` decimal(15,2) NOT NULL,
  `purchase_price` decimal(15,2) DEFAULT NULL,
  `discount` decimal(15,2) DEFAULT '0.00',
  `vat_amount` decimal(15,2) DEFAULT '0.00',
  `company_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE `shop` (
  `id` int NOT NULL,
  `shop_name` varchar(255) NOT NULL,
  `shop_address` text,
  `shop_phone1` varchar(20) DEFAULT NULL,
  `shop_phone2` varchar(20) DEFAULT NULL,
  `comments` text,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `company_id` int DEFAULT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `shop`
--

INSERT INTO `shop` (`id`, `shop_name`, `shop_address`, `shop_phone1`, `shop_phone2`, `comments`, `date_created`, `company_id`, `status`) VALUES
(1, 'Hamza Shop 1', NULL, NULL, NULL, NULL, NULL, 1, 'ACTIVE'),
(4, 'Hamza Shop 2', NULL, NULL, NULL, NULL, NULL, 1, 'ACTIVE');

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
(5, 1, NULL, 'Seemab revenue', ''),
(6, 1, NULL, 'Ham Sponsor3', '1234'),
(7, 1, NULL, 'Ham Sponsor3 Profit', '1234'),
(8, 1, NULL, 'Hamza Sponsor3 Project Based', '10'),
(9, 1, NULL, 'Hamza Sponsor3 Project 2 Based', '123');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` decimal(15,2) DEFAULT '0.00',
  `retail_price` decimal(15,2) DEFAULT NULL,
  `min_sale_price` decimal(15,2) NOT NULL,
  `storage_rack` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `shop_id` int NOT NULL,
  `company_id` int DEFAULT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `product_id`, `quantity`, `retail_price`, `min_sale_price`, `storage_rack`, `shop_id`, `company_id`, `status`) VALUES
(3, 1, 60.00, 850000.00, 800000.00, NULL, 1, 1, 'ACTIVE'),
(4, 1, 10.00, 650000.00, 600000.00, NULL, 4, 1, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `stock_movement`
--

CREATE TABLE `stock_movement` (
  `id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` decimal(15,2) DEFAULT NULL,
  `remaining_qty` decimal(15,2) NOT NULL,
  `retail_price` decimal(15,2) DEFAULT NULL,
  `min_price` decimal(15,2) NOT NULL,
  `sold_price` decimal(15,2) DEFAULT NULL,
  `buy_price` decimal(15,2) DEFAULT NULL,
  `movement_type` enum('IN','OUT') NOT NULL,
  `reason` enum('PURCHASE','SALE','RETURN') NOT NULL,
  `movement_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `storage_rack` varchar(100) NOT NULL,
  `batch` varchar(50) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `related_entity_id` int DEFAULT NULL,
  `related_entity_type` enum('SALE','PURCHASE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_id` int DEFAULT NULL,
  `shop_id` int NOT NULL,
  `pricing_strategy` enum('MAXIMUM_PRICE','LATEST_PRICE','MIN_PRICE') DEFAULT 'LATEST_PRICE',
  `vat_amount` decimal(15,2) NOT NULL,
  `status` enum('ACTIVE','DELETED','INACTIVE') DEFAULT 'ACTIVE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stock_movement`
--

INSERT INTO `stock_movement` (`id`, `product_id`, `quantity`, `remaining_qty`, `retail_price`, `min_price`, `sold_price`, `buy_price`, `movement_type`, `reason`, `movement_date`, `storage_rack`, `batch`, `comments`, `related_entity_id`, `related_entity_type`, `company_id`, `shop_id`, `pricing_strategy`, `vat_amount`, `status`) VALUES
(1, 1, 10.00, 0.00, 650000.00, 600000.00, 0.00, 500000.00, 'IN', 'PURCHASE', '2025-05-20 00:06:06', 'Rack123', 'Batch-001', 'first purchase', 6, 'PURCHASE', 1, 1, NULL, 0.00, 'ACTIVE'),
(2, 1, 10.00, 0.00, 650000.00, 600000.00, 0.00, 500000.00, 'IN', 'PURCHASE', '2025-05-20 00:09:32', 'Rack123', 'Batch-001', 'first purchase', 7, 'PURCHASE', 1, 1, NULL, 0.00, 'ACTIVE'),
(3, 1, 10.00, 0.00, 750000.00, 700000.00, 0.00, 600000.00, 'IN', 'PURCHASE', '2025-05-20 00:13:30', 'Rack123', 'Batch-001', 'first purchase', 8, 'PURCHASE', 1, 1, NULL, 0.00, 'ACTIVE'),
(4, 1, 10.00, 0.00, 850000.00, 800000.00, 0.00, 700000.00, 'IN', 'PURCHASE', '2025-05-20 00:14:34', 'Rack123', 'Batch-001', 'first purchase', 9, 'PURCHASE', 1, 1, NULL, 0.00, 'ACTIVE'),
(5, 1, 10.00, 0.00, 850000.00, 800000.00, 0.00, 700000.00, 'IN', 'PURCHASE', '2025-05-20 00:15:04', 'Rack123', 'Batch-001', 'first purchase', 10, 'PURCHASE', 1, 4, NULL, 0.00, 'ACTIVE'),
(6, 1, 10.00, 0.00, 650000.00, 600000.00, 0.00, 500000.00, 'IN', 'PURCHASE', '2025-05-20 08:24:38', 'Rack123', 'Batch-001', 'first purchase', 11, 'PURCHASE', 1, 4, NULL, 0.00, 'ACTIVE'),
(7, 1, 10.00, 10.00, 650000.00, 600000.00, NULL, 500000.00, 'IN', 'PURCHASE', '2025-05-22 12:33:14', 'Rack123', 'Batch-001', 'first purchase', 15, 'PURCHASE', 1, 1, NULL, 200.00, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` text,
  `contact` varchar(255) DEFAULT NULL,
  `comments` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `name`, `address`, `contact`, `comments`) VALUES
(1, 'Hamza Supplier 1', 'Riyadh', NULL, NULL);

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
(23, 7, 5, '2025-04-18', 4.00, 2, 12.00, 9.00, 0, 3, 3),
(24, 10, 6, '2025-05-01', 8.00, 1, 10.00, 5.00, 0, 1, 1),
(25, 10, 6, '2025-05-02', 8.00, 1, 10.00, 5.00, 0, 1, 1),
(26, 10, 6, '2025-05-03', 8.00, 1, 10.00, 5.00, 0, 1, 1),
(27, 10, 6, '2025-05-04', 8.00, 1, 10.00, 5.00, 0, 1, 1),
(28, 10, 6, '2025-05-01', 2.00, 2, 20.00, 10.00, 0, 1, 1),
(29, 10, 6, '2025-05-02', 2.00, 2, 20.00, 10.00, 0, 1, 1),
(30, 10, 6, '2025-05-03', 2.00, 2, 20.00, 10.00, 0, 1, 1),
(31, 10, 6, '2025-05-04', 2.00, 2, 20.00, 10.00, 0, 1, 1),
(32, 11, 10, '2025-05-26', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(33, 11, 10, '2025-05-27', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(34, 11, 10, '2025-05-28', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(35, 11, 10, '2025-05-29', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(36, 11, 10, '2025-05-30', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(37, 11, 10, '2025-05-26', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(38, 11, 10, '2025-05-27', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(39, 11, 10, '2025-05-28', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(40, 11, 10, '2025-05-29', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(41, 11, 10, '2025-05-30', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(42, 12, 11, '2025-05-26', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(43, 12, 11, '2025-05-27', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(44, 12, 11, '2025-05-28', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(45, 12, 11, '2025-05-29', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(46, 12, 11, '2025-05-30', 8.00, 1, 10.00, 5.00, 0, 5, 5),
(47, 12, 11, '2025-05-26', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(48, 12, 11, '2025-05-27', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(49, 12, 11, '2025-05-28', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(50, 12, 11, '2025-05-29', 4.00, 2, 20.00, 10.00, 0, 5, 5),
(51, 12, 11, '2025-05-30', 4.00, 2, 20.00, 10.00, 0, 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `unit_name`
--

CREATE TABLE `unit_name` (
  `id` int NOT NULL,
  `unit_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `role` varchar(20) DEFAULT NULL COMMENT '1. admin\r\n2. employee',
  `status` int DEFAULT '1' COMMENT '1. active\\n2. inactive'
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `create_date`, `update_date`, `company_id`, `role`, `status`) VALUES
(5, 'admin@admin.com', 'Admin123', '2025-03-01 12:29:45', '2025-05-01 14:21:45', 1, 'ADMIN', 1);

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
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `category_fk` (`cat_id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_company` (`company_id`);

--
-- Indexes for table `product_units`
--
ALTER TABLE `product_units`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `unit_id` (`unit_id`);

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
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `fk_sale_shop` (`shop_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `sale_item`
--
ALTER TABLE `sale_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sale_id` (`sale_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `stock_movement`
--
ALTER TABLE `stock_movement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `timesheet`
--
ALTER TABLE `timesheet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `asset_id` (`asset_id`),
  ADD KEY `timesheet_asset_proj_fk_1_idx` (`asset_project_id`);

--
-- Indexes for table `unit_name`
--
ALTER TABLE `unit_name`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `asset_designation`
--
ALTER TABLE `asset_designation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `asset_payable`
--
ALTER TABLE `asset_payable`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `asset_project`
--
ALTER TABLE `asset_project`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `designation`
--
ALTER TABLE `designation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `expense_category`
--
ALTER TABLE `expense_category`
  MODIFY `expense_category_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `invoice_asset`
--
ALTER TABLE `invoice_asset`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `invoice_sponsor_payable`
--
ALTER TABLE `invoice_sponsor_payable`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `main_account`
--
ALTER TABLE `main_account`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `preferences`
--
ALTER TABLE `preferences`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product_units`
--
ALTER TABLE `product_units`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `project_asset_sponsorship`
--
ALTER TABLE `project_asset_sponsorship`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `sale`
--
ALTER TABLE `sale`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sale_item`
--
ALTER TABLE `sale_item`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sponsor`
--
ALTER TABLE `sponsor`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `stock_movement`
--
ALTER TABLE `stock_movement`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `timesheet`
--
ALTER TABLE `timesheet`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `unit_name`
--
ALTER TABLE `unit_name`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `expense_ibfk_3` FOREIGN KEY (`expense_project`) REFERENCES `asset_project` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
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
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `category_fk` FOREIGN KEY (`cat_id`) REFERENCES `product_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `fk_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `product_units`
--
ALTER TABLE `product_units`
  ADD CONSTRAINT `product_units_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `product_units_ibfk_2` FOREIGN KEY (`unit_id`) REFERENCES `unit_name` (`id`);

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
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  ADD CONSTRAINT `purchase_ibfk_3` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `fk_sale_shop` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  ADD CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  ADD CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `sale_item`
--
ALTER TABLE `sale_item`
  ADD CONSTRAINT `sale_item_ibfk_1` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`),
  ADD CONSTRAINT `sale_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `sale_item_ibfk_3` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD CONSTRAINT `sponsor_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `stock_ibfk_3` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  ADD CONSTRAINT `stock_ibfk_4` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `stock_movement`
--
ALTER TABLE `stock_movement`
  ADD CONSTRAINT `stock_movement_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `stock_movement_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

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
