-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: podsistem1
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
-- Table structure for table `filijala`
--

DROP TABLE IF EXISTS `filijala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filijala` (
  `id_filijala` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) NOT NULL,
  `mesto_fil` int NOT NULL,
  PRIMARY KEY (`id_filijala`),
  KEY `fil_mesto_idx` (`mesto_fil`),
  CONSTRAINT `fil_mesto` FOREIGN KEY (`mesto_fil`) REFERENCES `mesto` (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filijala`
--

LOCK TABLES `filijala` WRITE;
/*!40000 ALTER TABLE `filijala` DISABLE KEYS */;
INSERT INTO `filijala` VALUES (1,'Vozdovac','Vojvode Stepe 37',1),(2,'TC_Stadion','Zaplenjska trg 32',1),(3,'Trg slobode','Trg Slobode 7',3),(4,'Nis centar','Trg kralja Milana',2),(5,'Dom Kulture','Bulevar Slobode',4),(6,'Lakosnica 1','Lakosnica 1',11),(7,'Filijalica','Veselinova 2',3),(8,'naz','adr',1),(9,'Dusanova3','Adresa12',1),(10,'Filijala 12','Adresa 1',4);
/*!40000 ALTER TABLE `filijala` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id_komitent`),
  KEY `mesto_kom_idx` (`mesto`),
  CONSTRAINT `mesto_kom` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komitent`
--

LOCK TABLES `komitent` WRITE;
/*!40000 ALTER TABLE `komitent` DISABLE KEYS */;
INSERT INTO `komitent` VALUES (1,'Marko','Kneza Milosa 20',1),(2,'Nikola','Beogradska 23',1),(3,'Ana','Limska 5',3),(4,'Milica','Vojvode Stepe 23',4),(5,'Milica','Timocka 5',2),(6,'Marko','Tomina 2',5),(15,'Duki','dusanova 2',1),(16,'Duki','dusanova 2',1),(17,'Dusan','Dusanovac 3',6);
/*!40000 ALTER TABLE `komitent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesto`
--

DROP TABLE IF EXISTS `mesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesto` (
  `idMesto` int NOT NULL AUTO_INCREMENT,
  `postanski_broj` varchar(45) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesto`
--

LOCK TABLES `mesto` WRITE;
/*!40000 ALTER TABLE `mesto` DISABLE KEYS */;
INSERT INTO `mesto` VALUES (1,'11000','Beograd'),(2,'18101','Nis'),(3,'21101','Novi Sad'),(4,'16000','Leskovac'),(5,'16251','Pecenjevce'),(6,'16230','Prekopcelica'),(7,'24000','Subotica'),(8,'14560','Kraljevo'),(9,'12560','Valjevo'),(10,'11300','Manojlovce'),(11,'16700','Lakosnica'),(12,'11800','Beograd'),(13,'14000','Mesto14');
/*!40000 ALTER TABLE `mesto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-02 17:20:13
