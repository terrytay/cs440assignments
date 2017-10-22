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
-- Temporary view structure for view `CLASS_LOCATIONS`
--

DROP TABLE IF EXISTS `CLASS_LOCATIONS`;
/*!50001 DROP VIEW IF EXISTS `CLASS_LOCATIONS`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `CLASS_LOCATIONS` AS SELECT 
 1 AS `abbreviation`,
 1 AS `number`,
 1 AS `building`,
 1 AS `room`,
 1 AS `purpose`,
 1 AS `startHour`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `FAILED_CURRENT_SPRING`
--

DROP TABLE IF EXISTS `FAILED_CURRENT_SPRING`;
/*!50001 DROP VIEW IF EXISTS `FAILED_CURRENT_SPRING`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `FAILED_CURRENT_SPRING` AS SELECT 
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `SEMESTER_GRADES`
--

DROP TABLE IF EXISTS `SEMESTER_GRADES`;
/*!50001 DROP VIEW IF EXISTS `SEMESTER_GRADES`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `SEMESTER_GRADES` AS SELECT 
 1 AS `name`,
 1 AS `title`,
 1 AS `grade`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `GRAD_FALL`
--

DROP TABLE IF EXISTS `GRAD_FALL`;
/*!50001 DROP VIEW IF EXISTS `GRAD_FALL`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `GRAD_FALL` AS SELECT 
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `CURRENT_COURSES`
--

DROP TABLE IF EXISTS `CURRENT_COURSES`;
/*!50001 DROP VIEW IF EXISTS `CURRENT_COURSES`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `CURRENT_COURSES` AS SELECT 
 1 AS `abbreviation`,
 1 AS `number`,
 1 AS `title`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `CLASS_LOCATIONS`
--

/*!50001 DROP VIEW IF EXISTS `CLASS_LOCATIONS`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `CLASS_LOCATIONS` AS select `c`.`abbreviation` AS `abbreviation`,`c`.`number` AS `number`,`loc`.`building` AS `building`,`loc`.`room` AS `room`,`loc`.`purpose` AS `purpose`,`sec`.`startHour` AS `startHour` from ((`SECTION` `sec` join `LOCATION` `loc`) join `COURSE` `c`) where ((`sec`.`location` = `loc`.`id`) and (`c`.`id` = `sec`.`course`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `FAILED_CURRENT_SPRING`
--

/*!50001 DROP VIEW IF EXISTS `FAILED_CURRENT_SPRING`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `FAILED_CURRENT_SPRING` AS select distinct `stu`.`name` AS `name` from (((`STUDENT` `stu` join `ENROLLMENT` `en`) join `SEMESTER` `sem`) join `SECTION` `sec`) where ((`stu`.`id` = `en`.`student`) and (`sec`.`id` = `en`.`section`) and (`en`.`grade` = 'F') and (`sem`.`id` = `sec`.`offered`) and (`sem`.`year` = 2017) and (`sem`.`season` = 'SPRING')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `SEMESTER_GRADES`
--

/*!50001 DROP VIEW IF EXISTS `SEMESTER_GRADES`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `SEMESTER_GRADES` AS select `stu`.`name` AS `name`,`c`.`title` AS `title`,`en`.`grade` AS `grade` from ((((`SECTION` `sec` join `SEMESTER` `sem`) join `COURSE` `c`) join `STUDENT` `stu`) join `ENROLLMENT` `en`) where ((`sec`.`offered` = `sem`.`id`) and (`c`.`id` = `sec`.`course`) and (`stu`.`id` = `en`.`student`) and (`sec`.`id` = `en`.`section`) and (`sem`.`year` = 2017) and (`sem`.`season` = 'FALL')) order by `sec`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `GRAD_FALL`
--

/*!50001 DROP VIEW IF EXISTS `GRAD_FALL`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `GRAD_FALL` AS select `st`.`name` AS `name` from (`STUDENT` `st` join `SEMESTER` `se`) where ((`st`.`graduationDate` = `se`.`id`) and (`se`.`year` = 2018) and (`se`.`season` = 'FALL')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `CURRENT_COURSES`
--

/*!50001 DROP VIEW IF EXISTS `CURRENT_COURSES`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `CURRENT_COURSES` AS select `c`.`abbreviation` AS `abbreviation`,`c`.`number` AS `number`,`c`.`title` AS `title` from ((`SECTION` `sec` join `SEMESTER` `sem`) join `COURSE` `c`) where ((`sec`.`offered` = `sem`.`id`) and (`c`.`id` = `sec`.`course`) and (`sem`.`year` = 2017) and (`sem`.`season` = 'FALL')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-22 12:57:33
