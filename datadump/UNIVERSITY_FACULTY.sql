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
-- Table structure for table `FACULTY`
--

DROP TABLE IF EXISTS `FACULTY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FACULTY` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `department` int(11) DEFAULT NULL,
  `startDate` int(11) DEFAULT NULL,
  `endDate` int(11) DEFAULT NULL,
  `office` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department` (`department`),
  KEY `startDate` (`startDate`),
  KEY `endDate` (`endDate`),
  KEY `office` (`office`),
  CONSTRAINT `FACULTY_ibfk_1` FOREIGN KEY (`department`) REFERENCES `DEPARTMENT` (`id`),
  CONSTRAINT `FACULTY_ibfk_2` FOREIGN KEY (`startDate`) REFERENCES `SEMESTER` (`id`),
  CONSTRAINT `FACULTY_ibfk_3` FOREIGN KEY (`endDate`) REFERENCES `SEMESTER` (`id`),
  CONSTRAINT `FACULTY_ibfk_4` FOREIGN KEY (`office`) REFERENCES `LOCATION` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FACULTY`
--

LOCK TABLES `FACULTY` WRITE;
/*!40000 ALTER TABLE `FACULTY` DISABLE KEYS */;
INSERT INTO `FACULTY` VALUES (1,'Whitsitt, Novian',1,1,NULL,51),(2,'Anderson, Lauren',1,5,1,51),(3,'Mtisi, Richard',1,2,NULL,51),(4,'Eichinger, Mark',2,3,1,151),(5,'Baack, Eric',2,6,NULL,151),(6,'Carlson, Scott',2,5,11,151),(7,'Enos-Berlage, Jodi',2,4,NULL,151),(8,'Fretham, Stephanie',2,7,NULL,151),(9,'Kaehler, Marian',2,1,NULL,151),(10,'Kraus, Kevin',2,5,NULL,151),(11,'Larsen, Kirk',2,2,NULL,151),(12,'Lynch, Elizabeth',2,1,NULL,151),(13,'McNicoll, Molly',2,5,NULL,151),(14,'Mefford, Melissa',2,4,NULL,151),(15,'Reding, Dawn',2,6,NULL,151),(16,'Strand, Gwen',2,1,NULL,151),(17,'Talsness, Dana',2,2,NULL,151),(18,'Chamberlain, Bradley',3,3,NULL,151),(19,'Hedstrom, Jack',3,6,NULL,151),(20,'Jefferson, John',3,4,NULL,151),(21,'Mertzenich, Claude',3,7,NULL,151),(22,'Michels, Olga',3,1,NULL,151),(23,'Tokheim, Benjamin',3,2,NULL,151),(24,'Wilker, Molly',3,7,2,151),(25,'Winsauer, Alisa',3,7,NULL,151),(26,'Freeman, Philip',4,2,NULL,51),(27,'Bulliung, Anne',4,3,NULL,51),(28,'Davis, Dan',4,7,3,51),(29,'Powell, Kim',5,1,NULL,251),(30,'Sweet, Derek',5,3,NULL,251),(31,'Johns, Mark',5,2,NULL,251),(32,'Johnson, Thomas',5,7,NULL,251),(33,'Wilder, Sarah',5,7,NULL,251),(34,'Miller, Brad',6,7,NULL,351),(35,'Hardy, Carolyn',6,3,NULL,351),(36,'Lee, Kent',6,3,NULL,351),(37,'Mattson, Paul',6,1,NULL,351),(38,'Ranum, David',6,6,NULL,351),(39,'Yasinovskyy, Roman',6,7,NULL,351),(40,'Holland, Steven',7,4,NULL,351),(41,'Cord, Brittany',7,7,NULL,351),(42,'Gomersall, Nicholas',7,2,NULL,351),(43,'Jones, Don',7,1,NULL,351),(44,'Larson, Rob',7,7,NULL,351),(45,'Mutsune, Tony',7,2,NULL,351),(46,'Nelson, Ramona',7,6,NULL,351),(47,'Patton, Greg',7,2,NULL,351),(48,'Schweizer, Tim',7,3,NULL,351),(49,'Shilts, Wade',7,7,NULL,351),(50,'Torkelson, Ryan',7,2,NULL,351),(51,'White, Alexandra',7,5,NULL,351),(52,'Wrightsman, Amy',7,2,NULL,351),(53,'Bohach, Barbara',8,3,NULL,451),(54,'Huinker, Amanda',8,2,NULL,451),(55,'Leet-Otley, Jill',8,4,NULL,451),(56,'Meade, Birgitta',8,3,NULL,451),(57,'Meyer-Mork, Jodi',8,1,NULL,451),(58,'Norland, Deborah',8,5,NULL,451),(59,'Pillsbury, Jeannette',8,6,NULL,451),(60,'Sheppard, Tom',8,7,NULL,451),(61,'Snell-Anderson, Heidi',8,4,NULL,451),(62,'Sovereign, Shirley',8,2,NULL,451),(63,'Stockton, William',8,6,NULL,451),(64,'Vesperman, Dean',8,2,NULL,451),(65,'Wilson, Jill',8,7,NULL,451),(66,'Klammer, Martin',9,1,NULL,51),(67,'Barry, Nancy',9,1,9,51),(68,'Drews, Marie',9,1,NULL,51),(69,'Faldet, David',9,7,NULL,51),(70,'Faldet, Rachel',9,3,9,51),(71,'Garcia, Mike',9,7,NULL,51),(72,'Hageman, Andrew',9,7,9,51),(73,'Kildegaard, Lise',9,5,NULL,51),(74,'Narveson, Kate',9,7,NULL,51),(75,'Row-Heyveld, Lindsey',9,4,NULL,51),(76,'Weldon, Amy',9,1,NULL,51),(77,'Whitsitt, Novian',9,6,3,51),(78,'Baack, Eric',10,3,NULL,551),(79,'Brummel, Rachel',10,5,NULL,551),(80,'Jensen, Jon',10,6,NULL,551),(81,'Peterson, Laura',10,2,NULL,551),(82,'Solberg, Brian',11,4,NULL,651),(83,'Boeke, Jeff',11,3,NULL,651),(84,'Drewes-Stoen, Ellen',11,6,NULL,651),(85,'Hall, James',11,2,9,651),(86,'Hornbacher, Steven',11,5,NULL,651),(87,'Jaeger, Roger',11,5,NULL,651),(88,'O’Gara, Jeff',11,4,4,651),(89,'Wettach, Jeff',11,1,NULL,651),(90,'Wright, Jacquelyn',11,4,NULL,651),(91,'Caton, Brian',12,5,NULL,451),(92,'Anderson, Lauren',12,6,NULL,451),(93,'Christman, Robert',12,7,NULL,451),(94,'Christman, Victoria',12,4,NULL,451),(95,'Mtisi, Richard',12,3,NULL,451),(96,'Peterson, Anna',12,2,NULL,451),(97,'Tebbenhoff, Edward',12,2,NULL,451),(98,'Wilkie, Jacqueline',12,7,NULL,451),(99,'Westlund, Eric',15,3,NULL,351),(100,'Becker, Joyce',15,5,NULL,351),(101,'Berger, Ruth',15,2,NULL,351),(102,'Bernatz, Richard',15,6,NULL,351),(103,'Fey, Kyle',15,7,NULL,351),(104,'Johnson, Michael',15,2,NULL,351),(105,'Mitchell, David',15,7,NULL,351),(106,'Occhipinti, Thomas',15,3,NULL,351),(107,'Savariappan, Paul',15,5,NULL,351),(108,'Wangsness, Cheryl',15,6,NULL,351),(109,'Thompson, David',16,2,NULL,51),(110,'Estenoz, Alfredo',16,4,NULL,51),(111,'Bowman, Melanie',16,6,NULL,51),(112,'Santis, Francesca',16,2,NULL,51),(113,'Feat, Anne',16,4,NULL,51),(114,'Madsen, Nancy',16,7,NULL,51),(115,'Grønningsæter, Kari',16,3,NULL,51),(116,'Iudin-Nelson, Laurie',16,7,NULL,51),(117,'Johnson, Maren',16,2,NULL,51),(118,'Kath, Ruth',16,6,NULL,51),(119,'Såkvitne, Tonje',16,4,NULL,51),(120,'Steding, Elizabeth',16,7,NULL,51),(121,'Steding, Sören',16,1,NULL,51),(122,'Strom, Megan',16,7,NULL,51),(123,'Tejada, Rita',16,7,NULL,51),(124,'Yu, Hongmei',16,7,NULL,51),(125,'Zaring, Laurie',16,6,NULL,51),(126,'Peterson, Gregory',17,3,NULL,851),(127,'Ailabouni, Jonathan',17,4,NULL,851),(128,'Andereck, Edwin',17,3,NULL,851),(129,'Armstrong, Heather',17,2,NULL,851),(130,'Baldwin, Daniel',17,2,NULL,851),(131,'Batoff, Melanie',17,7,NULL,851),(132,'Beane-Hanson, Katherine',17,1,NULL,851),(133,'Borter, Philip',17,2,NULL,851),(134,'Brandwein, Rachel',17,5,NULL,851),(135,'Britton, Jason',17,1,NULL,851),(136,'Britton, Margaret',17,7,NULL,851),(137,'Chesher, Michael',17,2,NULL,851),(138,'Cord, John',17,7,NULL,851),(139,'deAlbuquerque, Joan',17,6,NULL,851),(140,'Douma, Jeffrey',17,5,NULL,851),(141,'Engelsdorfer, Amy',17,7,NULL,851),(142,'Fulton, William',17,4,NULL,851),(143,'Geary, Michael',17,2,NULL,851),(144,'Gover, Deborah',17,1,NULL,851),(145,'Guzman, Juan',17,4,NULL,851),(146,'Hanson, Carla',17,1,NULL,851),(147,'Hart, Lynne',17,7,NULL,851),(148,'Hart, Peter',17,5,NULL,851),(149,'Hester, Carol',17,6,NULL,851),(150,'Jones, Ann',17,1,NULL,851),(151,'Hu, Xiao',17,3,5,851),(152,'Huang, Du',17,7,9,851),(153,'Joyce, Brooke',17,7,NULL,851),(154,'Kalnin, Igor',17,4,8,851),(155,'Kominami, Miko',17,2,NULL,851),(156,'Kreuscher, Carol',17,2,NULL,851),(157,'Last, Andrew',17,2,10,851),(158,'Lingen, Peter',17,2,NULL,851),(159,'Martin, Linda',17,6,NULL,851),(160,'Martin, Spencer',17,4,NULL,851),(161,'Meade, Tara',17,6,NULL,851),(162,'Morton, Gregory',17,3,NULL,851),(163,'Moss, Gary',17,4,NULL,851),(164,'Phillips, Jill',17,1,NULL,851),(165,'Westlund, Beth',17,5,NULL,851),(166,'Reed, Kathy',17,5,NULL,851),(167,'Robison, Jennaya',17,3,NULL,851),(168,'Schultz, Brad',17,5,NULL,851),(169,'Shaffer, Rebecca',17,6,NULL,851),(170,'Shaneyfelt, Nicholas',17,5,8,851),(171,'Smith, Michael',17,5,NULL,851),(172,'Sonka, Chad',17,1,NULL,851),(173,'Strauss, John',17,3,7,851),(174,'Strauss, Virginia',17,5,NULL,851),(175,'Struve, Jonathan',17,2,NULL,851),(176,'Ryn, Michael',17,6,NULL,851),(177,'Whitfield, Andrew',17,5,NULL,851),(178,'Yates, Benjamin',17,5,NULL,851),(179,'Sullivan, Rebecca',18,6,NULL,51),(180,'Bailey, Storm',19,7,NULL,951),(181,'Moore, Holly',19,3,NULL,951),(182,'Jensen, Jon',19,3,NULL,951),(183,'Perez, James',20,3,NULL,1051),(184,'Flater, Erin',20,2,NULL,1051),(185,'Pedlar, Todd',20,2,NULL,1051),(186,'Wilkerson, Jeffrey',20,6,NULL,1051),(187,'Gardner, Paul',21,4,NULL,451),(188,'Santos, Pedro',21,5,NULL,451),(189,'Engelhardt, Michael',21,5,NULL,451),(190,'Foster, Carly',21,2,1,451),(191,'Moeller, John',21,2,NULL,451),(192,'Njus, David',22,6,NULL,1051),(193,'Bishop, David',22,1,5,1051),(194,'Bossard, Elaine',22,5,NULL,1051),(195,'Breitenstein, Joseph',22,2,NULL,1051),(196,'Dykstra, Rita',22,3,NULL,1051),(197,'Gould, Kristy',22,2,NULL,1051),(198,'Sprung, Justin',22,4,NULL,1051),(199,'Toussaint, Loren',22,7,11,1051),(200,'Travers, Stephanie',22,5,4,1051),(201,'Burke, Sean',23,2,NULL,51),(202,'Deifelt, Wanda',23,5,NULL,51),(203,'Green, Todd',23,2,NULL,51),(204,'Henning, Alyssa',23,7,NULL,51),(205,'Hurley, Scott',23,1,NULL,51),(206,'Kopf, Gereon',23,3,NULL,51),(207,'Martin-Schramm, James',23,2,NULL,51),(208,'Nave, Guy',23,4,NULL,51),(209,'Shedinger, Robert',23,5,NULL,51),(210,'Sparkes, Terry',23,2,NULL,51),(211,'Swanson, Kristin',23,5,NULL,51),(212,'Wafula, Robert',23,2,NULL,51),(213,'Williams, Natalie',23,6,NULL,51),(214,'Wright, Laura',23,1,NULL,51),(215,'Rhodes, Britt',25,3,NULL,451),(216,'Bazylevych, Maryna',25,7,10,451),(217,'Betts, Colin',25,1,NULL,451),(218,'Carrasco, Anita',25,5,NULL,451),(219,'Ferguson, Ronald',25,4,NULL,451),(220,'Kane, Elizabeth',25,3,NULL,451),(221,'Kremer, Joseph',25,1,NULL,451),(222,'Kunkel, Charlotte',25,1,6,451),(223,'Schmidt, Susan',25,5,NULL,451),(224,'Stanley, Lori',25,2,NULL,451),(225,'Tenneson, Karen',25,3,NULL,451),(226,'Moore, Benjamin',26,5,NULL,1251),(227,'Dintaman, Jeff',26,7,NULL,1251),(228,'Elliott, Kate',26,3,NULL,1251),(229,'Hawley, Jane',26,3,NULL,1251),(230,'Kamm, David',26,6,NULL,1251),(231,'Lantz, Lisa',26,2,4,1251),(232,'Lovelace, Lea',26,6,NULL,1251),(233,'Madrigal, Joseph',26,6,NULL,1251),(234,'Merritt, Richard',26,6,NULL,1251),(235,'Aguirre, Andrea',26,6,NULL,1251),(236,'Vrtis, Robert',26,5,1,1251);
/*!40000 ALTER TABLE `FACULTY` ENABLE KEYS */;
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
