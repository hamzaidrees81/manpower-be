-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: May 22, 2025 at 12:53 PM
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
-- Table structure for table `stock_movement`
--

CREATE TABLE `stock_movement` (
  `id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` decimal(15,2) DEFAULT NULL,
  `remaining_qty` decimal(15,2) DEFAULT NULL,
  `retail_price` decimal(15,2) DEFAULT NULL,
  `min_price` decimal(15,2) DEFAULT NULL,
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
(7, 1, 10.00, 5.00, 650000.00, 600000.00, NULL, 500000.00, 'IN', 'PURCHASE', '2025-05-22 12:33:14', 'Rack123', 'Batch-001', 'first purchase', 15, 'PURCHASE', 1, 1, NULL, 200.00, 'ACTIVE'),
(8, 1, 5.00, NULL, 60.00, NULL, 55.00, 500000.00, 'OUT', 'SALE', '2025-05-22 12:51:32', 'Rack123', 'Batch-001', 'Sold from stock movement ID: 7', 3, 'SALE', 1, 1, NULL, 150.00, 'ACTIVE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stock_movement`
--
ALTER TABLE `stock_movement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `company_id` (`company_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stock_movement`
--
ALTER TABLE `stock_movement`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `stock_movement`
--
ALTER TABLE `stock_movement`
  ADD CONSTRAINT `stock_movement_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `stock_movement_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
