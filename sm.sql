-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sm
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `module` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `param_name` varchar(20) DEFAULT NULL,
  `param_value` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES ('SYSTEM','0','HOST_IP','192.168.29.109'),('SYSTEM','0','POINT_RATIO','10'),('SYSTEM','0','MAX_POINT','100'),('SYSTEM','0','SEND_SMS','FALSE');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_info`
--

DROP TABLE IF EXISTS `customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_info` (
  `mobile_no` varchar(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `REGISTRATION_DATE` varchar(20) NOT NULL,
  `LAST_VISIT_DATE` varchar(50) DEFAULT NULL,
  `TOTAL_POINTS` varchar(10) DEFAULT NULL,
  `state` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`mobile_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_info`
--

LOCK TABLES `customer_info` WRITE;
/*!40000 ALTER TABLE `customer_info` DISABLE KEYS */;
INSERT INTO `customer_info` VALUES ('7070096655','ujjwal sen','2021-04-07 15:33:55','2021-04-07 15:35:01','4',NULL),('8825329095','piyush','2021-04-07 19:11:24','2021-04-07 19:24:31','22',NULL),('9905663459','gaurav bhasker','2021-04-07 15:34:24','2021-04-07 16:01:47','75',NULL);
/*!40000 ALTER TABLE `customer_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `name` varchar(40) NOT NULL,
  `mobile_no` varchar(15) NOT NULL,
  `last_visit_date` varchar(30) DEFAULT NULL,
  `service` varchar(150) DEFAULT NULL,
  `total_bill` varchar(20) DEFAULT NULL,
  `points` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('ujjwal sen','7070096655','2021-04-07 15:33:55','haircut 245','245','24'),('gaurav bhasker','9905663459','2021-04-07 15:34:24','facial 450','450','45'),('ujjwal sen','7070096655','2021-04-07 15:35:01','waxing 240','220','-20'),('gaurav bhasker','9905663459','2021-04-07 15:59:22','haircut 300, facial 500','800','80'),('gaurav bhasker','9905663459','2021-04-07 16:01:47','waxing 200, manicure 250, facial 300','700','-50'),('piyush','8825329095','2021-04-07 19:11:24','haircut 250, haircolor 321','571','57'),('piyush','8825329095','2021-04-07 19:21:38','WAXING 137','81','-56'),('piyush','8825329095','2021-04-07 19:24:31','MANICURE 210','210','21');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense` (
  `name` varchar(40) DEFAULT NULL,
  `date` varchar(40) DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `total_amount` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES ('gaurav','2021-04-07 21:29:08','colddrink 15, chips 20, hairclip 20','55'),('shuvra','2021-04-07 21:34:28','hairoil 30, cream 50','80'),('gaurav','2021-04-07 22:03:51','food 200','200'),('gaurav','2021-04-07 22:05:04','colddrink 50','50'),('shuvra','2021-04-07 22:07:33','chips 20','20'),('shuvra','2021-04-07 22:10:37','cosmetics 500','500');
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `redeem_points`
--

DROP TABLE IF EXISTS `redeem_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `redeem_points` (
  `mobile_no` varchar(15) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `redeem_date` varchar(20) DEFAULT NULL,
  `points_redeem` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `redeem_points`
--

LOCK TABLES `redeem_points` WRITE;
/*!40000 ALTER TABLE `redeem_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `redeem_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `send_sms`
--

DROP TABLE IF EXISTS `send_sms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `send_sms` (
  `date` varchar(20) DEFAULT NULL,
  `mobile_no` varchar(15) DEFAULT NULL,
  `sms` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `send_sms`
--

LOCK TABLES `send_sms` WRITE;
/*!40000 ALTER TABLE `send_sms` DISABLE KEYS */;
INSERT INTO `send_sms` VALUES ('2021-04-07 15:59:22','9905663459','Thank you for opting for haircut, facialservices. Your total bill amount is 800. Thank you and please visit again. Regards Radiance beauty Parlour.'),('2021-04-07 16:01:47','9905663459','Thank you for opting for waxing, manicure, facial services. Your total bill amount is 700. Thank you and please visit again. Regards Radiance beauty Parlour.'),('2021-04-07 19:21:38','8825329095','Thank you for opting for WAXING services. Your total bill amount is 81. Thank you and please visit again. Regards Radiance beauty Parlour.');
/*!40000 ALTER TABLE `send_sms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_auth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(20) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
INSERT INTO `user_auth` VALUES (1,'2021-02-06 00:12:56','gaurav','grv123',3),(2,'2021-04-07 21:03:53','shuvra','shuv123',3);
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-07 22:16:13
