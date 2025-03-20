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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-20 23:53:11
