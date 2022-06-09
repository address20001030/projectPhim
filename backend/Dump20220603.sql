-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: phimonline
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id_category` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Hành động'),(2,'Kinh dị'),(3,'Tình cảm'),(4,'Hài hước'),(5,'Cổ trang'),(6,'Tâm lý'),(7,'Hình sự'),(8,'Chiến tranh'),(9,'Thể thao'),(10,'Võ thuật'),(11,'Hoạt hình'),(12,'Viễn tưởng'),(13,'Phiêu lưu'),(14,'Khoa học'),(15,'Thần thoại'),(16,'Truyền hình'),(17,'Anime');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_section`
--

DROP TABLE IF EXISTS `comment_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_section` (
  `cm_id` bigint NOT NULL AUTO_INCREMENT,
  `cm_content` text,
  `create_date` datetime(6) NOT NULL,
  `id_movie` bigint DEFAULT NULL,
  `id` bigint DEFAULT NULL,
  PRIMARY KEY (`cm_id`),
  KEY `FKtqktf96dcpkde55i0p3q0n1o5` (`id_movie`),
  KEY `FKs899ycr4e3ip6md6vdny8xgpl` (`id`),
  CONSTRAINT `FKs899ycr4e3ip6md6vdny8xgpl` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKtqktf96dcpkde55i0p3q0n1o5` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_section`
--

LOCK TABLES `comment_section` WRITE;
/*!40000 ALTER TABLE `comment_section` DISABLE KEYS */;
INSERT INTO `comment_section` VALUES (1,'haha, test','2022-05-26 23:20:21.687084',2,7),(2,'ahah','2022-06-02 18:42:43.107980',2,7),(3,'asdasd','2022-06-02 20:50:59.016325',8,7),(4,'haha','2022-06-02 20:54:20.734692',8,7),(5,'test','2022-06-02 20:58:25.930941',8,7),(6,'đẹp trai','2022-06-02 20:59:35.140215',5,7),(7,'hay','2022-06-03 04:16:05.352635',6,7);
/*!40000 ALTER TABLE `comment_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id_country` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Trung Quốc'),(2,'Ấn Độ'),(3,'Hoa Kỳ'),(4,'Indonesia'),(5,'Pakistan'),(6,'Brasil'),(7,'Nigeria'),(8,'Bangladesh'),(9,'Nga'),(10,'México'),(11,'Nhật Bản'),(12,'Ethiopia'),(13,'Philippines'),(14,'Ai Cập'),(15,'Việt Nam'),(16,'CHDC Congo'),(17,'Thổ Nhĩ Kỳ'),(18,'Iran'),(19,'Đức'),(20,'Thái Lan'),(21,'Vương quốc Anh'),(22,'Pháp'),(23,'Tanzania'),(24,'Nam Phi'),(25,'Ý'),(26,'Kenya'),(27,'Myanmar'),(28,'Colombia'),(29,'Hàn Quốc'),(30,'Uganda'),(31,'Tây Ban Nha'),(32,'Argentina'),(33,'Sudan'),(34,'Algérie'),(35,'Ukraina'),(36,'Iraq'),(37,'Afghanistan'),(38,'Canada'),(39,'Ba Lan'),(40,'Maroc'),(41,'Ả Rập Saudi'),(42,'Angola'),(43,'Uzbekistan'),(44,'Peru'),(45,'Malaysia'),(46,'Mozambique'),(47,'Ghana'),(48,'Yemen'),(49,'Nepal'),(50,'Venezuela'),(51,'Madagascar'),(52,'Cameroon'),(53,'Bờ Biển Ngà'),(54,'CHDCND Triều Tiên'),(55,'Úc'),(56,'Niger'),(57,'Đài Loan'),(58,'Burkina Faso'),(59,'Sri Lanka'),(60,'Mali'),(61,'Malawi'),(62,'Chile'),(63,'Zambia'),(64,'Kazakhstan'),(65,'România'),(66,'Syria'),(67,'Guatemala'),(68,'Ecuador'),(69,'Sénégal'),(70,'Hà Lan'),(71,'Tchad'),(72,'Campuchia'),(73,'Somalia'),(74,'Zimbabwe'),(75,'Guinée'),(76,'Rwanda'),(77,'Bénin'),(78,'Burundi'),(79,'Tunisia'),(80,'Bolivia'),(81,'Bỉ'),(82,'Haiti'),(83,'Nam Sudan'),(84,'Cuba'),(85,'Cộng hòa Dominica'),(86,'Cộng hòa Séc'),(87,'Hy Lạp'),(88,'Jordan'),(89,'Azerbaijan'),(90,'Bồ Đào Nha'),(91,'Thụy Điển'),(92,'Honduras'),(93,'UAE'),(94,'Tajikistan'),(95,'Hungary'),(96,'Belarus'),(97,'Papua New Guinea'),(98,'Áo'),(99,'Israel'),(100,'Thụy Sĩ'),(101,'Serbia'),(102,'Togo'),(103,'Sierra Leone'),(104,'Hồng Kông'),(105,'Lào'),(106,'Paraguay'),(107,'Libya'),(108,'Bulgaria'),(109,'Nicaragua'),(110,'Liban'),(111,'Kyrgyzstan'),(112,'El Salvador'),(113,'Turkmenistan'),(114,'Singapore'),(115,'Đan Mạch'),(116,'Cộng hòa Congo'),(117,'Phần Lan'),(118,'Na Uy'),(119,'Slovakia'),(120,'Palestine'),(121,'Oman'),(122,'Liberia'),(123,'Costa Rica'),(124,'Ireland'),(125,'Cộng hòa Trung Phi'),(126,'New Zealand'),(127,'Mauritanie'),(128,'Panama'),(129,'Kuwait'),(130,'Croatia'),(131,'Moldova'),(132,'Gruzia'),(133,'Eritrea'),(134,'Uruguay'),(135,'Mông Cổ'),(136,'Bosna và Hercegovina'),(137,'Jamaica'),(138,'Armenia'),(139,'Qatar'),(140,'Albania'),(141,'Puerto Rico'),(142,'Litva'),(143,'Namibia'),(144,'Gambia'),(145,'Botswana'),(146,'Gabon'),(147,'Lesotho'),(148,'Bắc Macedonia'),(149,'Slovenia'),(150,'Guiné-Bissau'),(151,'Latvia'),(152,'Bahrain'),(153,'Guinea Xích Đạo'),(154,'Trinidad và Tobago'),(155,'Đông Timor'),(156,'Estonia'),(157,'Mauritius'),(158,'Síp'),(159,'Eswatini'),(160,'Djibouti'),(161,'Fiji'),(162,'Réunion'),(163,'Comoros'),(164,'Guyana'),(165,'Bhutan'),(166,'Quần đảo Solomon'),(167,'Ma Cao'),(168,'Luxembourg'),(169,'Montenegro'),(170,'Tây Sahara'),(171,'Suriname'),(172,'Cabo Verde'),(173,'Maldives'),(174,'Brunei'),(175,'Malta'),(176,'Belize'),(177,'Guadeloupe'),(178,'Bahamas'),(179,'Martinique'),(180,'Iceland'),(181,'Vanuatu'),(182,'Guyane thuộc Pháp'),(183,'Nouvelle-Calédonie'),(184,'Barbados'),(185,'Polynésie thuộc Pháp'),(186,'Mayotte'),(187,'São Tomé và Príncipe'),(188,'Samoa'),(189,'Saint Lucia'),(190,'Quần đảo Eo Biển'),(191,'Guam'),(192,'Curaçao'),(193,'Kiribati'),(194,'Liên bang Micronesia'),(195,'Grenada'),(196,'Saint Vincent và Grenadines'),(197,'Aruba'),(198,'Tonga'),(199,'Quần đảo Virgin thuộc Mỹ'),(200,'Seychelles'),(201,'Antigua và Barbuda'),(202,'Đảo Man'),(203,'Andorra'),(204,'Dominica'),(205,'Quần đảo Cayman'),(206,'Bermuda'),(207,'Quần đảo Marshall'),(208,'Quần đảo Bắc Mariana'),(209,'Greenland'),(210,'Samoa thuộc Mỹ'),(211,'Saint Kitts và Nevis'),(212,'Quần đảo Faroe'),(213,'Sint Maarten'),(214,'Monaco'),(215,'Quần đảo Turks và Caicos'),(216,'Liechtenstein'),(217,'Gibraltar'),(218,'San Marino'),(219,'Quần đảo Virgin thuộc Anh'),(220,'Palau'),(221,'Quần đảo Cook'),(222,'Anguilla'),(223,'Tuvalu'),(224,'Wallis và Futuna'),(225,'Nauru'),(226,'Saint Helena, Ascension và Tristan da Cunha'),(227,'Saint Pierre và Miquelon'),(228,'Montserrat'),(229,'Quần đảo Falkland'),(230,'Niue'),(231,'Tokelau'),(232,'Thành Vatican');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_connective`
--

DROP TABLE IF EXISTS `country_connective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_connective` (
  `cnmv_id` bigint NOT NULL,
  `cnct_id` bigint NOT NULL,
  PRIMARY KEY (`cnmv_id`,`cnct_id`),
  KEY `FK9lknemj470df2djjxepskixlj` (`cnct_id`),
  CONSTRAINT `FK9lknemj470df2djjxepskixlj` FOREIGN KEY (`cnct_id`) REFERENCES `country` (`id_country`),
  CONSTRAINT `FKfkssv2ysv5y43w0lnomnr0eg1` FOREIGN KEY (`cnmv_id`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_connective`
--

LOCK TABLES `country_connective` WRITE;
/*!40000 ALTER TABLE `country_connective` DISABLE KEYS */;
INSERT INTO `country_connective` VALUES (2,1),(3,1),(3,2),(5,197),(8,197),(6,222),(7,222);
/*!40000 ALTER TABLE `country_connective` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (16);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_movie`
--

DROP TABLE IF EXISTS `link_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_movie` (
  `id_link` bigint NOT NULL AUTO_INCREMENT,
  `url` text,
  `id_quality` bigint DEFAULT NULL,
  `id_detail` bigint DEFAULT NULL,
  PRIMARY KEY (`id_link`),
  KEY `FK7on26tarta3uxfg34ul4hwnd` (`id_quality`),
  KEY `FK8s7opf1puf7iu0qkhgspyb3qq` (`id_detail`),
  CONSTRAINT `FK7on26tarta3uxfg34ul4hwnd` FOREIGN KEY (`id_quality`) REFERENCES `quality` (`id_quality`),
  CONSTRAINT `FK8s7opf1puf7iu0qkhgspyb3qq` FOREIGN KEY (`id_detail`) REFERENCES `movie_detail` (`id_detail`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_movie`
--

LOCK TABLES `link_movie` WRITE;
/*!40000 ALTER TABLE `link_movie` DISABLE KEYS */;
INSERT INTO `link_movie` VALUES (1,'https://res.cloudinary.com/phimonline/video/upload/v1654188650/r7rxif9xlafk1fyarmio.mp4',1,2),(2,'https://res.cloudinary.com/phimonline/video/upload/v1654190299/ahdqoqo4druw0o0pu0ky.mp4',2,5),(3,'https://res.cloudinary.com/phimonline/video/upload/v1654202150/jlf8rgy182kxtdcmx6qr.mp4',1,1);
/*!40000 ALTER TABLE `link_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id_movie` bigint NOT NULL AUTO_INCREMENT,
  `actor` text,
  `content` text,
  `director` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `img_quality` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `time` int DEFAULT NULL,
  `year` int DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  `create_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id_movie`),
  KEY `FKsrv3pey3fxycjkpsaj0jxa2r9` (`id_category`),
  CONSTRAINT `FKsrv3pey3fxycjkpsaj0jxa2r9` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (2,'Không nhớ','đang soạn','Không biết','https://res.cloudinary.com/phimonline/image/upload/v1653044371/poster_eq9tbf.jpg','HD','Đại thánh trở về',1,120,2018,1,'2022-05-16 03:48:28.996608'),(3,'Không nhớ','đang soạn','Không biết','https://res.cloudinary.com/phimonline/image/upload/v1653044371/poster_eq9tbf.jpg','HD','Doremon',2,120,2019,11,'2022-05-18 00:13:33.608384'),(4,'chịu','đang soạn','không biết','https://res.cloudinary.com/phimonline/image/upload/v1653044371/poster_eq9tbf.jpg','HD','Test',1,120,2020,12,'2022-05-18 00:13:33.608384'),(5,'Z','Zx','asa','https://res.cloudinary.com/phimonline/image/upload/v1654098080/bjm8aunxr8bp7a1y8g6d.png','HD','Huynguyenmau93@gmail.com',23,120,2022,3,'2022-06-01 22:41:33.796647'),(6,'asdad','asdasdf','asdas','https://res.cloudinary.com/phimonline/image/upload/v1654098776/afxczblgdh08i8ukaod7.png','HD','truongphamnhu@gmail.com',23,45,2022,9,'2022-06-01 22:52:58.599574'),(7,'asdad','asdasdf','asdas','https://res.cloudinary.com/phimonline/image/upload/v1654098776/afxczblgdh08i8ukaod7.png','HD','truongphamnhu@gmail.com',23,45,2022,9,'2022-06-01 22:54:04.915653'),(8,'kasja','asdkljajdska','asda','https://res.cloudinary.com/phimonline/image/upload/v1654098917/t61fmw4sgmqy8z449nzi.png','HD','scjkahdhjkasjkkj',23,200,2022,3,'2022-06-01 22:55:20.195946');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_detail`
--

DROP TABLE IF EXISTS `movie_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_detail` (
  `id_detail` bigint NOT NULL AUTO_INCREMENT,
  `episode` int DEFAULT NULL,
  `id_movie` bigint DEFAULT NULL,
  PRIMARY KEY (`id_detail`),
  KEY `FKopy44ofb1d1o1ga7kowjhaqvk` (`id_movie`),
  CONSTRAINT `FKopy44ofb1d1o1ga7kowjhaqvk` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_detail`
--

LOCK TABLES `movie_detail` WRITE;
/*!40000 ALTER TABLE `movie_detail` DISABLE KEYS */;
INSERT INTO `movie_detail` VALUES (1,1,6),(2,2,6),(3,3,6),(4,4,6),(5,1,7);
/*!40000 ALTER TABLE `movie_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKrvhjnns4bvlh4m1n97vb7vbar` (`role_id`),
  CONSTRAINT `FKc2tpu6eawwwnyn45lp9mciyn7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrvhjnns4bvlh4m1n97vb7vbar` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (7,1),(13,1),(14,1),(4,2);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality`
--

DROP TABLE IF EXISTS `quality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quality` (
  `id_quality` bigint NOT NULL AUTO_INCREMENT,
  `name_quality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_quality`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quality`
--

LOCK TABLES `quality` WRITE;
/*!40000 ALTER TABLE `quality` DISABLE KEYS */;
INSERT INTO `quality` VALUES (1,'144'),(2,'240'),(3,'360'),(4,'480'),(5,'720'),(6,'1080'),(7,'1440'),(8,'2160');
/*!40000 ALTER TABLE `quality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_section`
--

DROP TABLE IF EXISTS `review_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_section` (
  `id_rv` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) NOT NULL,
  `rate` bigint DEFAULT NULL,
  `id_movie` bigint DEFAULT NULL,
  `id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_rv`),
  KEY `FKjek8yypm1h9pgnyq5mld7tuhe` (`id_movie`),
  KEY `FKb0masp8m6nywkfpwhcmhkiyc0` (`id`),
  CONSTRAINT `FKb0masp8m6nywkfpwhcmhkiyc0` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjek8yypm1h9pgnyq5mld7tuhe` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_section`
--

LOCK TABLES `review_section` WRITE;
/*!40000 ALTER TABLE `review_section` DISABLE KEYS */;
INSERT INTO `review_section` VALUES (1,'2022-05-27 02:20:56.833993',8,2,7),(2,'2022-06-02 20:50:16.372469',12,8,7),(3,'2022-06-02 20:58:33.371509',20,8,7),(4,'2022-06-02 20:59:40.536626',10,5,7),(5,'2022-06-03 04:16:10.962063',10,6,7);
/*!40000 ALTER TABLE `review_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secure_token`
--

DROP TABLE IF EXISTS `secure_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secure_token` (
  `token_id` bigint NOT NULL,
  `expire_at` datetime(6) NOT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `id` bigint DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  UNIQUE KEY `UK_1vnojtqwxyii8kinnsohdknhw` (`token`),
  KEY `FKe8vx7phaxju710pxt7kd09ff7` (`id`),
  CONSTRAINT `FKe8vx7phaxju710pxt7kd09ff7` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secure_token`
--

LOCK TABLES `secure_token` WRITE;
/*!40000 ALTER TABLE `secure_token` DISABLE KEYS */;
INSERT INTO `secure_token` VALUES (14,'2022-06-03 08:38:27.228182','2022-06-03 04:38:27.536000','HmZuL8fhsV2u83HOEnxw',13),(15,'2022-06-03 08:52:00.788766','2022-06-03 04:52:00.847000','fgRRnZrB2CiOA56gSyXs',14);
/*!40000 ALTER TABLE `secure_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_verified` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `login_disabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,_binary '','Huynguyenmau93@gmail.com','81DC9BDB52D04DC20036DBD8313ED055','admin',_binary '\0'),(7,_binary '','jzb82398@zcrcd.com','81DC9BDB52D04DC20036DBD8313ED055','huy',_binary '\0'),(13,_binary '\0','wfm26706@jiooq.com','81DC9BDB52D04DC20036DBD8313ED055','huyaaa',_binary '\0'),(14,_binary '\0','ihe65151@xcoxc.com','81DC9BDB52D04DC20036DBD8313ED055','huybbb',_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-03  5:45:33
