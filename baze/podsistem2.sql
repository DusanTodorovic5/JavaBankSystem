-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: podsistem2
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `komitent`
--

DROP TABLE IF EXISTS `komitent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `komitent` (
  `id_komitent` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) NOT NULL,
  `mesto` int NOT NULL,
  PRIMARY KEY (`id_komitent`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komitent`
--

LOCK TABLES `komitent` WRITE;
/*!40000 ALTER TABLE `komitent` DISABLE KEYS */;
INSERT INTO `komitent` VALUES (1,'Marko','Kneza Milosa 20',1),(2,'Nikola','Beogradska 23',1),(3,'Ana','Limska 5',3),(4,'Milica','Vojvode Stepe 23',4),(5,'Milica','Timocka 5',2),(6,'Marko','Jedina Ulica U Mestu',6),(7,'Duki','Ulicica 2',1),(8,'Duki','dusanova 2',1),(9,'Dusan','Veselinova',1);
/*!40000 ALTER TABLE `komitent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racun`
--

DROP TABLE IF EXISTS `racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `racun` (
  `broj_racuna` int NOT NULL AUTO_INCREMENT,
  `stanje` int DEFAULT NULL,
  `dozvoljen_minus` int DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'A',
  `datum` date DEFAULT NULL,
  `vreme` time DEFAULT NULL,
  `broj_transakcija` int DEFAULT NULL,
  `mesto` int DEFAULT NULL,
  `id_komitent` int DEFAULT NULL,
  PRIMARY KEY (`broj_racuna`),
  KEY `komiten_id_idx` (`id_komitent`),
  CONSTRAINT `komiten_id` FOREIGN KEY (`id_komitent`) REFERENCES `komitent` (`id_komitent`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racun`
--

LOCK TABLES `racun` WRITE;
/*!40000 ALTER TABLE `racun` DISABLE KEYS */;
INSERT INTO `racun` VALUES (1,4000,20000,'A','1999-12-07','11:28:09',4,1,1),(2,66657,0,'A','2012-02-04','12:04:00',7,1,2),(3,-55000,100000,'A','2016-09-22','12:20:00',1,1,2),(4,-55000,50000,'B','2007-08-16','10:00:00',1,3,3),(5,-9900,10000,'B','2009-07-01','12:00:00',2,4,4),(6,NULL,NULL,'U','2001-03-03','10:00:00',0,2,5),(7,NULL,NULL,'U','2009-12-07','11:00:00',2,2,5),(8,NULL,NULL,'U','2022-02-01','00:55:08',3,1,1),(9,-5000,2000,'B','2022-02-01','12:42:31',2,4,2),(10,NULL,NULL,'U','2022-02-02','15:05:39',0,11,3);
/*!40000 ALTER TABLE `racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transakcija`
--

DROP TABLE IF EXISTS `transakcija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transakcija` (
  `id_transakcije` int NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `vreme` time DEFAULT NULL,
  `iznos` int DEFAULT NULL,
  `rb_racuna` int DEFAULT NULL,
  `svrha` varchar(45) DEFAULT NULL,
  `id_racun` int DEFAULT NULL,
  `tipTrans` varchar(45) NOT NULL,
  `naRacun` int DEFAULT NULL,
  `idFil` int DEFAULT NULL,
  PRIMARY KEY (`id_transakcije`),
  KEY `racun_tr_idx` (`id_racun`),
  KEY `racun_na_idx` (`naRacun`),
  CONSTRAINT `racun_na` FOREIGN KEY (`naRacun`) REFERENCES `racun` (`broj_racuna`),
  CONSTRAINT `racun_tr` FOREIGN KEY (`id_racun`) REFERENCES `racun` (`broj_racuna`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transakcija`
--

LOCK TABLES `transakcija` WRITE;
/*!40000 ALTER TABLE `transakcija` DISABLE KEYS */;
INSERT INTO `transakcija` VALUES (1,'2020-11-01','12:00:00',1000,1,'uplata',1,'U',NULL,2),(2,'2020-11-02','11:20:00',500,2,'isplata',1,'U',NULL,3),(3,'2020-11-12','17:00:00',500,3,'uplata',1,'I',NULL,1),(4,'2020-12-01','13:00:00',50000,1,'uplata',2,'U',NULL,3),(5,'2021-11-01','09:23:00',28000,2,'uplata',2,'I',NULL,1),(6,'2021-10-11','08:25:26',55000,1,'isplata',3,'P',5,NULL),(7,'2021-06-06','18:19:19',55000,1,'isplata',4,'U',NULL,4),(8,'2020-11-01','23:59:59',12000,1,'isplata',5,'I',NULL,5),(9,'2020-11-01','12:09:54',12340,1,'uplata',7,'U',NULL,4),(10,'2020-11-01','10:10:11',12340,2,'isplata',7,'U',NULL,3),(11,'2019-11-09','19:19:19',8000,4,'prenos',1,'U',NULL,1),(12,'2022-02-01','00:56:59',1500,3,'prenos',2,'U',NULL,1),(13,'2022-02-01','00:58:24',500,1,'plata',8,'I',NULL,1),(14,'2022-02-01','00:59:35',3000,2,'racuni',8,'I',NULL,1),(15,'2022-02-01','01:00:36',5000,3,'pomoc',8,'P',2,NULL),(16,'2022-02-01','02:10:47',500,4,'plata',2,'I',NULL,1),(17,'2022-02-01','12:43:30',5000,1,'novac',9,'U',NULL,2),(18,'2022-02-01','12:44:17',10000,2,'porez',9,'I',NULL,2),(19,'2022-02-01','12:45:12',6000,5,'pomoc',2,'P',9,NULL),(20,'2022-02-02','15:19:04',3000,6,'Testiranje Prenosa',2,'P',1,NULL),(21,'2022-02-02','15:19:25',343,7,'Testiranje Isplate',2,'I',NULL,1),(22,'2022-02-02','15:19:50',2100,2,'Testiranje Uplate',5,'U',NULL,3);
/*!40000 ALTER TABLE `transakcija` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-02 17:20:25
