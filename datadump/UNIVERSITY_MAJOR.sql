-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: UNIVERSITY
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.17.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `MAJOR`
--

DROP TABLE IF EXISTS `MAJOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MAJOR` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` int(11) DEFAULT NULL,
  `name` text,
  PRIMARY KEY (`id`),
  KEY `department` (`department`),
  CONSTRAINT `MAJOR_ibfk_1` FOREIGN KEY (`department`) REFERENCES `DEPARTMENT` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAJOR`
--

LOCK TABLES `MAJOR` WRITE;
/*!40000 ALTER TABLE `MAJOR` DISABLE KEYS */;
INSERT INTO `MAJOR` VALUES (1,7,'Accounting'),(2,1,'Africana Studies'),(3,16,'Asian Studies'),(4,25,'Anthropology'),(5,26,'Art'),(6,26,'Art History'),(7,4,'Biblical Languages'),(8,2,'Biology'),(9,3,'Chemistry'),(10,16,'Chinese'),(11,4,'Classical Studies'),(12,4,'Classics'),(13,5,'Communication Studies'),(14,6,'Computer Science'),(15,26,'Dance'),(16,6,'Data Science'),(17,7,'Economics'),(18,8,'Education'),(19,9,'English'),(20,10,'Environmental Studies'),(21,16,'French'),(22,16,'German'),(23,11,'Health and Fitness Promotion'),(24,12,'History'),(25,7,'International Business'),(26,5,'Journalism'),(27,16,'Linguistics'),(28,7,'Management'),(29,15,'Mathematics'),(30,15,'Mathematics/Statistics'),(31,14,'Museum Studies'),(32,17,'Music'),(33,22,'Neuroscience'),(34,16,'Nordic Studies'),(35,18,'Paideia'),(36,19,'Philosophy'),(37,11,'Physical Education'),(38,20,'Physics'),(39,21,'Political Science'),(40,22,'Psychology'),(41,23,'Religion'),(42,16,'Russian Studies'),(43,25,'Social Welfare'),(44,25,'Social Work'),(45,25,'Sociology'),(46,16,'Spanish'),(47,26,'Theatre');
/*!40000 ALTER TABLE `MAJOR` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-22 12:57:33
