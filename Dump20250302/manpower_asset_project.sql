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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_project`
--

LOCK TABLES `asset_project` WRITE;
/*!40000 ALTER TABLE `asset_project` DISABLE KEYS */;
INSERT INTO `asset_project` VALUES (1,1,2,1,1,'AP-1',10.00,12.00,8.00,9.00,'2025-02-01','2025-03-15',1,1),(2,1,3,1,1,'DRILLING',10.00,12.00,8.00,9.00,'2025-03-01','2025-03-15',1,1),(3,1,2,2,1,NULL,20.00,25.00,15.00,21.00,'2025-03-01','2025-03-15',1,1),(4,1,2,1,1,'AP-2',10.00,12.00,8.00,9.00,'2025-02-01','2025-03-15',1,1);
/*!40000 ALTER TABLE `asset_project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-20 23:53:12
