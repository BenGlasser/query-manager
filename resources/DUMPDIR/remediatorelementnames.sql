-- MySQL dump 10.13  Distrib 5.1.49, for Win64 (unknown)
--
-- Host: localhost    Database: te
-- ------------------------------------------------------
-- Server version	5.1.49-enterprise-commercial-pro

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `remediatorelementnames`
--

DROP TABLE IF EXISTS `remediatorelementnames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remediatorelementnames` (
  `oid` bigint(20) NOT NULL,
  `ref_pmId` bigint(20) NOT NULL,
  `elementNamePattern` varchar(1536) COLLATE utf8_bin NOT NULL,
  `remediatorId` bigint(20) NOT NULL,
  `pos` bigint(20) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `RemediatorElementNames_5_ASC` (`remediatorId`,`pos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-20 14:27:42
