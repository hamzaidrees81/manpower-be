CREATE DATABASE  IF NOT EXISTS `manpower` /*!40100 DEFAULT CHARACTER SET utf32 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `manpower`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: manpower
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `sponsored_by` int DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `id_number` varchar(15) NOT NULL,
  `iqama_expiry` date DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `designation` varchar(25) DEFAULT NULL,
  `passport` varchar(25) DEFAULT NULL,
  `passport_expiry` date DEFAULT NULL,
  `joining_date` date NOT NULL,
  `asset_type` tinyint NOT NULL,
  `asset_number` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_number` (`id_number`),
  KEY `company_id` (`company_id`),
  KEY `sponsored_by` (`sponsored_by`),
  CONSTRAINT `asset_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  CONSTRAINT `asset_ibfk_2` FOREIGN KEY (`sponsored_by`) REFERENCES `sponsor` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
INSERT INTO `asset` VALUES (2,1,1,'Adeel','12321','2025-03-20','','','AB1234','2025-03-01','2025-03-01',1,123),(3,1,1,'Hamza','123','2025-03-20',NULL,NULL,NULL,NULL,'2025-03-01',1,321);
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_designation`
--

DROP TABLE IF EXISTS `asset_designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset_designation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `asset_id` int NOT NULL,
  `designation_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `asset_id` (`asset_id`),
  KEY `designation_id` (`designation_id`),
  CONSTRAINT `asset_designation_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  CONSTRAINT `asset_designation_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  CONSTRAINT `asset_designation_ibfk_3` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_designation`
--

LOCK TABLES `asset_designation` WRITE;
/*!40000 ALTER TABLE `asset_designation` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_project`
--

DROP TABLE IF EXISTS `asset_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset_project` (
  `id` int NOT NULL AUTO_INCREMENT,
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
  `status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `asset_id` (`asset_id`),
  KEY `project_id` (`project_id`),
  KEY `designation` (`designation`),
  CONSTRAINT `asset_project_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  CONSTRAINT `asset_project_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  CONSTRAINT `asset_project_ibfk_3` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE,
  CONSTRAINT `asset_project_ibfk_4` FOREIGN KEY (`designation`) REFERENCES `designation` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_project`
--

LOCK TABLES `asset_project` WRITE;
/*!40000 ALTER TABLE `asset_project` DISABLE KEYS */;
INSERT INTO `asset_project` VALUES (1,1,2,1,1,NULL,10.00,12.00,8.00,9.00,'2025-02-01','2025-03-15',1,1),(2,1,3,1,1,'DRILLING',10.00,12.00,8.00,9.00,'2025-03-01','2025-03-15',1,1),(3,1,2,2,1,NULL,20.00,25.00,15.00,21.00,'2025-03-01','2025-03-15',1,1);
/*!40000 ALTER TABLE `asset_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'ARM-123','ARAMCO','DHAHRAN');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `max_asset_count` int NOT NULL,
  `header_image_url` varchar(100) DEFAULT NULL,
  `footer_image_url` varchar(100) DEFAULT NULL,
  `bank_account_title` varchar(45) DEFAULT NULL,
  `bank_account_number` varchar(45) DEFAULT NULL,
  `bank_iban` varchar(45) DEFAULT NULL,
  `bank_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'SAUDI TECH','DAMMAN',5,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `designation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation`
--

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
INSERT INTO `designation` VALUES (1,'QC Engineer');
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `asset_id` int NOT NULL,
  `expense_type` tinyint NOT NULL,
  `amount` int NOT NULL,
  `expense_project` int DEFAULT NULL,
  `expense_metric` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `asset_id` (`asset_id`),
  KEY `expense_project` (`expense_project`),
  CONSTRAINT `expense_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  CONSTRAINT `expense_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  CONSTRAINT `expense_ibfk_3` FOREIGN KEY (`expense_project`) REFERENCES `project` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `client_id` int NOT NULL,
  `number` varchar(50) DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  `total_amount` decimal(10,2) DEFAULT NULL,
  `cleared_date` date DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  KEY `company_id` (`company_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (2,1,1,NULL,0,NULL,NULL,'2025-03-01','2025-03-05',NULL),(3,1,1,NULL,1,NULL,NULL,'2025-03-01','2025-03-05',NULL),(4,1,1,NULL,1,NULL,NULL,'2025-03-01','2025-03-05',NULL),(9,1,1,NULL,1,NULL,NULL,'2025-03-06','2025-03-06','2025-03-11');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_asset`
--

DROP TABLE IF EXISTS `invoice_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_asset` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `asset_id` int NOT NULL,
  `asset_project_id` int DEFAULT NULL,
  `standard_hours` decimal(5,2) DEFAULT NULL,
  `standard_rate` decimal(5,2) DEFAULT NULL,
  `ot_rate` decimal(5,2) DEFAULT NULL,
  `ot_hours` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `invoice_id` (`invoice_id`),
  KEY `asset_id` (`asset_id`),
  KEY `invoice_asset_ibfk_3_idx` (`asset_project_id`),
  CONSTRAINT `invoice_asset_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE CASCADE,
  CONSTRAINT `invoice_asset_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE,
  CONSTRAINT `invoice_asset_ibfk_3` FOREIGN KEY (`asset_project_id`) REFERENCES `asset_project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_asset`
--

LOCK TABLES `invoice_asset` WRITE;
/*!40000 ALTER TABLE `invoice_asset` DISABLE KEYS */;
INSERT INTO `invoice_asset` VALUES (13,9,2,1,0.00,0.00,12.00,9.00),(14,9,3,2,0.00,0.00,0.00,0.00),(15,9,2,3,0.00,0.00,12.00,5.00);
/*!40000 ALTER TABLE `invoice_asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `client_id` int NOT NULL,
  `project_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `location` varchar(100) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_id` (`project_id`),
  KEY `company_id` (`company_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  CONSTRAINT `project_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,1,1,'PROJ-1','ARAMCO HALL','DAMMAM','2025-03-01 00:00:00','2025-04-22 00:00:00'),(2,1,1,'PROJ-2','Halliburton','RIYADH','2025-03-01 00:00:00','2025-04-22 00:00:00');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `sponsor_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `sponsor_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (1,1,1,'HAMZA_SPONSOR','+921234'),(2,1,2,'HAMZA_SPONSOR','+921234');
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timesheet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `asset_id` int NOT NULL,
  `asset_project_id` int NOT NULL,
  `timesheet_date` date NOT NULL,
  `hours` decimal(5,2) NOT NULL,
  `rate_type` tinyint NOT NULL,
  `rate` decimal(5,2) DEFAULT NULL,
  `rate_paid` decimal(5,2) DEFAULT NULL,
  `invoice_number` int DEFAULT NULL,
  `row_sr_no` int DEFAULT NULL,
  `week_index` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `asset_id` (`asset_id`),
  KEY `timesheet_asset_proj_fk_1_idx` (`asset_project_id`),
  CONSTRAINT `timesheet_asset_proj_fk_1` FOREIGN KEY (`asset_project_id`) REFERENCES `asset_project` (`id`),
  CONSTRAINT `timesheet_ibfk_1` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet`
--

LOCK TABLES `timesheet` WRITE;
/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` VALUES (4,2,1,'2025-03-01',5.00,1,10.00,8.00,0,NULL,NULL),(5,2,1,'2025-03-01',5.51,2,12.00,9.00,0,NULL,NULL),(6,2,1,'2025-03-02',6.00,1,12.00,9.00,NULL,NULL,NULL),(7,2,1,'2025-03-02',7.00,2,12.00,9.00,NULL,NULL,NULL),(8,2,1,'2025-03-05',8.00,1,12.00,9.00,NULL,NULL,NULL),(9,2,1,'2025-03-06',9.00,2,12.00,9.00,NULL,NULL,NULL),(10,2,2,'2025-03-02',5.00,1,12.00,9.00,NULL,NULL,NULL),(11,2,2,'2025-03-02',5.00,2,12.00,9.00,NULL,NULL,NULL),(12,2,2,'2025-03-05',5.00,1,12.00,9.00,NULL,NULL,NULL),(13,2,3,'2025-03-06',5.00,2,12.00,9.00,NULL,NULL,NULL);
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `company_id` int NOT NULL,
  `role` int DEFAULT '2' COMMENT '1. admin\n2. employee',
  `status` int DEFAULT '1' COMMENT '1. active\\n2. inactive',
  PRIMARY KEY (`id`),
  KEY `user_company_idx` (`company_id`),
  CONSTRAINT `user_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'Admin','Admin123','2025-03-01 12:29:45','2025-03-01 12:29:45',1,1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-12 22:37:38
