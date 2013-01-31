-- MySQL dump 10.13  Distrib 5.1.34, for apple-darwin9.5.0 (i386)
--
-- Host: localhost    Database: MVCVideoPlayer
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Current Database: `MVCVideoPlayer`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `MVCVideoPlayer` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `MVCVideoPlayer`;

--
-- Table structure for table `canal`
--

DROP TABLE IF EXISTS `canal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `canal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `texto_tooltip` varchar(140) DEFAULT NULL,
  `publicado` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canal`
--

LOCK TABLES `canal` WRITE;
/*!40000 ALTER TABLE `canal` DISABLE KEYS */;
INSERT INTO `canal` VALUES (1,'Sala 0111','http://www.google.com.br','Google',1),(2,'Sala 0222','http://www.uol.com.br','UOL',1),(3,'Sala 03','http://www.bing.com.br','BING',1),(4,'Sala 04','http://localhost:8080/MVCVideoPlayer/resources/playerAoVivo/s1.html','S1',1),(5,'Estúdio 01',NULL,'eee',1),(6,'Estúdio 02','http://www.asaweb.com.br/player/verytasfilmes/','Teste do teste, sendo testado, e ainda sendo testado...',1);
/*!40000 ALTER TABLE `canal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `ativo` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'adm','1234abcd','Admin',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `extensao` varchar(5) DEFAULT NULL,
  `tamanho_arquivo` bigint(20) DEFAULT NULL,
  `duracao` varchar(15) DEFAULT NULL,
  `data_criacao` varchar(25) DEFAULT NULL COMMENT '22/22/2222',
  `nome_antigo_arquivo` varchar(255) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `framerate` int(11) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `publicado` int(11) DEFAULT '0',
  `chamada` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (79,'79.flv',NULL,7613396,'00:05:00.10','21/05/2012 - 20:54:44','56.flv',320,240,15,'jdsjdajskldja daksjdklasjdndaksjdklasjd dkasjdklajsd aklsjdklasjd...',1,'jdsjdajskldja daksjdklasjdndaksjdklasjd dkasjdklajsd aklsjdklasjd...'),(80,'80.flv',NULL,7613396,'00:05:00.10','21/05/2012 - 20:54:44','77.flv',320,240,15,'ventura teste do teste...ventura teste do teste...ventura teste do teste...ventura teste do teste...ventura teste do teste...ventura teste do teste...ventura teste do teste...',1,'ventura teste do teste...ventura teste do teste...ventura teste do teste...ventura teste do teste...ventura teste do teste...ventura teste d'),(76,'jadkljas','fsdfs',111,'fsdfsdf','sdfdsf','fsdfsdf',1,1,1,'fsdf fksdfksdfl;sdk fls;dkfls;dkf l;sdkf;lk',1,'fsdf fksdfksdfl;sdk fls;dkfls;dkf l;sdkf;lk'),(77,'77.flv',NULL,7613396,'00:05:00.10','21/05/2012 - 20:54:44','73.flv',320,240,15,'mfklsdmfklsm fksmdfklsdmf fklmsdflkm wwwwwwwww wwwwwww wwwwwww w w w w w w w w w w w w w w w w w w w w w w w w w w w',1,'mfklsdmfklsm fksmdfklsdmf fklmsdflkm wwwwwwwww wwwwwww wwwwwww w w w w w w w w w w w w w w w w w w w w w w w w w w w'),(78,'78.flv',NULL,6405749,'00:03:03.09','07/06/2012 - 14:38:03','Skeletal Family - Promised Land.flv',320,240,30,'jhfshfhkdsf fsjdkfdsfhsd jfjdskhfjksdhfkjh fsdjkfjksdhfs fhjsdbfhdsfbsdbfd fsdhbfjkdsfjbdsbdsjfkbsdjkb fjkshdfhdsjkhf fjsdhfjksdfh fjsdfjkdshfjkhsd jksdfjdshfjk',1,'jhfshfhkdsf fsjdkfdsfhsd jfjdskhfjksdhfkjh fsdjkfjksdhfs fhjsdbfhdsfbsdbfd fsdhbfjkdsfjbdsbdsjfkbsdjkb fjkshdfhdsjkhf fjsdhfjksdfh fjsdfjkds'),(81,'81.flv',NULL,0,'01:45:55.64','07/06/2012 - 15:09:12','julio_2498.flv',406,304,30,'teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste...',1,'teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste...'),(82,'82.flv',NULL,7613396,'00:05:00.10','21/05/2012 - 20:54:44','56.flv',320,240,15,'teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, ',1,'teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do teste, teste do tes'),(83,'83.f4v',NULL,0,'00:00:31.75','11/06/2012 - 18:24:55','teste.f4v',0,0,0,'teste do f4v...',1,'teste do f4v...teste do f4v...teste do f4v...teste do f4v...teste do f4v...teste do f4v...teste do f4v...teste do f4v...');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-30 18:53:27
