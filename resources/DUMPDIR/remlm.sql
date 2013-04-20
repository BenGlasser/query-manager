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
-- Table structure for table `remlm`
--

DROP TABLE IF EXISTS `remlm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remlm` (
  `exitVal` int(11) NOT NULL,
  `runId` bigint(20) NOT NULL,
  `msg` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `err` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `nodeId` bigint(20) NOT NULL,
  `versionTestId` bigint(20) NOT NULL,
  `policyTestId` bigint(20) NOT NULL,
  `oid` bigint(20) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `remLm_1_ASC` (`runId`),
  KEY `remLm_4_ASC` (`nodeId`),
  KEY `remLm_5_ASC` (`versionTestId`),
  KEY `remLm_8_ASC` (`exitVal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-20 14:27:42
