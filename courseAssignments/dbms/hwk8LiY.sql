-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: music1_db
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albums` (
  `alid` int NOT NULL AUTO_INCREMENT,
  `album_name` varchar(50) NOT NULL,
  `artist` varchar(50) NOT NULL,
  PRIMARY KEY (`alid`),
  KEY `albums_artists` (`artist`),
  CONSTRAINT `albums_artists` FOREIGN KEY (`artist`) REFERENCES `artists` (`artist_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,'Thrill of the Arts','Vulfpeck'),(2,'Nonfiction','Low Hum'),(3,'Habit','Still Woozy'),(4,'Origin','Vanilla'),(5,'Prologue','Life of Dillon'),(6,'New Light','John Mayer'),(7,'Overexposed','Maroon 5'),(8,'Television','99 Neighbors'),(9,'The Story of Us','Quinn XCII'),(10,'After Hours','The Weeknd'),(11,'Wait','Arlie'),(12,'Kauai','Childish Gambino'),(13,'Love Songs','Neil Diamond'),(14,'Synpathique','Pink Martini'),(15,'Happy Together','The Turtles'),(16,'Despacito Remix','Luis Fonsi');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_performs_song`
--

DROP TABLE IF EXISTS `artist_performs_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist_performs_song` (
  `sid` int NOT NULL,
  `aid` int NOT NULL,
  PRIMARY KEY (`sid`,`aid`),
  KEY `artist_performs_song_fk2` (`aid`),
  CONSTRAINT `artist_performs_song_fk1` FOREIGN KEY (`sid`) REFERENCES `songs` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `artist_performs_song_fk2` FOREIGN KEY (`aid`) REFERENCES `artists` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_performs_song`
--

LOCK TABLES `artist_performs_song` WRITE;
/*!40000 ALTER TABLE `artist_performs_song` DISABLE KEYS */;
INSERT INTO `artist_performs_song` VALUES (1,1),(17,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(18,15),(16,16),(16,17),(16,18);
/*!40000 ALTER TABLE `artist_performs_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(50) NOT NULL,
  `record_label_id` int NOT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `artist_name` (`artist_name`),
  KEY `artists_record_label` (`record_label_id`),
  CONSTRAINT `artists_record_label` FOREIGN KEY (`record_label_id`) REFERENCES `record_label` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Vulfpeck',1),(2,'Low Hum',2),(3,'Still Woozy',4),(4,'Vanilla',4),(5,'Life of Dillon',5),(6,'John Mayer',1),(7,'Maroon 5',3),(8,'99 Neighbors',3),(9,'Quinn XCII',2),(10,'The Weeknd',1),(11,'Arlie',1),(12,'Childish Gambino',5),(13,'Neil Diamond',1),(14,'Pink Martini',6),(15,'The Turtles',2),(16,'Justin Beiber',2),(17,'Luis Fonsi',3),(18,'Daddy Yankee',3);
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `gid` int NOT NULL,
  `genre_name` varchar(50) NOT NULL,
  `genre_description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'Rock','lots of electric instruments'),(2,'Pop','popular music'),(3,'Jazz','originated from blues and ragtime'),(4,'Hip Hop','includes rapping and utilizes synthesizers'),(5,'Country','originated from gosepl and church music'),(6,'Heavy Metal','characterized by loud distorted guitars and vocals'),(7,'Classical','written in a Western musical tradition'),(8,'Latin pop','A catchy pop style with Latin music flare');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moods`
--

DROP TABLE IF EXISTS `moods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moods` (
  `mid` int NOT NULL,
  `mood_name` varchar(50) NOT NULL,
  `mood_description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moods`
--

LOCK TABLES `moods` WRITE;
/*!40000 ALTER TABLE `moods` DISABLE KEYS */;
INSERT INTO `moods` VALUES (1,'Happy','cheerful music'),(2,'Exuberant','exciting, upbeat music'),(3,'Energetic','powerful music'),(4,'Frantic','fast-paced busy music'),(5,'Anxious/Sad','slow and melancholy tunes'),(6,'Depression','very sad, angsty songs'),(7,'Calm','good music to relax to'),(8,'Contentment','fulfilling and satisfying');
/*!40000 ALTER TABLE `moods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record_label`
--

DROP TABLE IF EXISTS `record_label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record_label` (
  `rid` int NOT NULL,
  `label_name` varchar(50) NOT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `label_name` (`label_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record_label`
--

LOCK TABLES `record_label` WRITE;
/*!40000 ALTER TABLE `record_label` DISABLE KEYS */;
INSERT INTO `record_label` VALUES (6,'Atlantic Records'),(1,'Capitol Music Group'),(2,'Def Jam Recordings'),(4,'Island Records'),(3,'Republic Records'),(5,'Warner Music Group');
/*!40000 ALTER TABLE `record_label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `song_name` varchar(50) NOT NULL,
  `album_id` int NOT NULL,
  `genre_id` int NOT NULL,
  `mood_id` int NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `songs_albums` (`album_id`),
  KEY `songs_genres` (`genre_id`),
  KEY `songs_mood` (`mood_id`),
  CONSTRAINT `songs_albums` FOREIGN KEY (`album_id`) REFERENCES `albums` (`alid`),
  CONSTRAINT `songs_genres` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`gid`),
  CONSTRAINT `songs_mood` FOREIGN KEY (`mood_id`) REFERENCES `moods` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES (1,'Funky Duck',1,2,4),(2,'Escape',2,2,8),(3,'Habit',3,2,1),(4,'Footsteps',4,2,8),(5,'Overload',5,1,3),(6,'New Light',6,2,7),(7,'One More Night',7,2,2),(8,'Lock N Key',8,4,7),(9,'Straightjacket',9,4,1),(10,'Blinding Lights',10,2,1),(11,'didya think',11,1,8),(12,'The Palisades',12,4,7),(13,'Suzanne',13,2,3),(14,'Amado Mio',14,3,5),(15,'Happy Together',15,1,7),(16,'Despacito',16,8,1),(17,'Walkies',1,2,4),(18,'Me about You',15,2,7);
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'User 1 ','user1@gmail.com'),(2,'User 2','user2.@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_follows_artist`
--

DROP TABLE IF EXISTS `user_follows_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_follows_artist` (
  `aid` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`aid`,`user_id`),
  KEY `user_follows_artist_fk2` (`user_id`),
  CONSTRAINT `user_follows_artist_fk1` FOREIGN KEY (`aid`) REFERENCES `artists` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_follows_artist_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_follows_artist`
--

LOCK TABLES `user_follows_artist` WRITE;
/*!40000 ALTER TABLE `user_follows_artist` DISABLE KEYS */;
INSERT INTO `user_follows_artist` VALUES (1,1),(4,1),(7,1),(9,1),(1,2),(7,2),(9,2);
/*!40000 ALTER TABLE `user_follows_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'music1_db'
--

--
-- Dumping routines for database 'music1_db'
--
/*!50003 DROP FUNCTION IF EXISTS `album_length` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `album_length`(length_p INT) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE number_of_albums INT;
    SELECT COUNT(*) INTO number_of_albums FROM(
    SELECT COUNT(album_id) 
    FROM songs
    GROUP BY album_id
    HAVING COUNT(sid) = length_p)AS sub;
    RETURN number_of_albums;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `more_followers` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `more_followers`(artist1 VARCHAR(50), artist2 VARCHAR(50)) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE followers_artist_1 INT;
    DECLARE followers_artist_2 INT;
    SELECT COUNT(*) INTO followers_artist_1
    FROM user_follows_artist LEFT JOIN artists USING(aid)
    WHERE artist_name = artist1;
    SELECT COUNT(*) INTO followers_artist_2
    FROM user_follows_artist LEFT JOIN artists USING(aid)
    WHERE artist_name = artist2;
    IF followers_artist_1 > followers_artist_2 THEN
        RETURN 1;
    ELSEIF followers_artist_1 = followers_artist_2 THEN
        RETURN 0;
    ELSE
        RETURN -1;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `num_songs_with_genre` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `num_songs_with_genre`(genre_P VARCHAR(50)) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN
DECLARE numbers INT;
SELECT COUNT(*) INTO numbers
FROM songs
LEFT JOIN genres ON songs.genre_id = genres.gid
WHERE genres.genre_name = genre_p;
RETURN numbers;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `create_song` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_song`(IN title_p VARCHAR(50), IN artist_p VARCHAR(50), IN record_label_p VARCHAR(50), IN mood_p VARCHAR(50), IN genre_p VARCHAR(50), IN album_title VARCHAR(50))
BEGIN
	DECLARE album_id INT;
	DECLARE genre_id INT;
    DECLARE mood_id INT;
	DECLARE artist_id INT;
	DECLARE record_label_id INT;
	DECLARE song_id INT;
	SELECT alid INTO album_id FROM albums WHERE album_name = album_title AND artist = artist_p;
    SELECT gid INTO genre_id FROM genres WHERE genre_name = genre_p;
    SELECT mid INTO mood_id FROM moods WHERE mood_name = mood_p;
	SELECT aid INTO artist_id FROM artists WHERE artist_name=artist_p;
    SELECT rid INTO record_label_id FROM record_label WHERE label_name = record_label_p;
	IF record_label_id IS NULL OR genre_id IS NULL OR mood_id IS NULL THEN
	SIGNAL SQLSTATE '45000'
	SET MESSAGE_TEXT = 'Record label, genre name or mood name have to be existed.';
    END IF;
    
    INSERT INTO songs (song_name, album_id, genre_id, mood_id) VALUES (title_p, album_id, genre_id, mood_id);
	SELECT sid INTO song_id FROM songs WHERE song_name =title_p;
    
    IF album_id IS NULL THEN INSERT INTO albums (album_name, artist) VALUES (album_title, artist_p);
	SELECT albums.alid INTO album_id WHERE artist = artist_p AND album_name = album_title;
    END IF;
    INSERT INTO artist_performs_song (sid, aid) VALUES (song_id, artist_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `get_artists_with_label` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_artists_with_label`(IN label_p VARCHAR(50))
BEGIN
SELECT artist_name, label_name
FROM record_label
	LEFT JOIN artists ON artists.record_label_id = record_label.rid
    WHERE record_label.label_name = label_p;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `get_songs_with_mood` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_songs_with_mood`(IN mood_name_argument VARCHAR(50))
BEGIN
    SELECT song_name, mood_name, mood_description, artist_name
    FROM moods
    LEFT JOIN songs ON moods.mid=songs.mood_id
    LEFT JOIN albums ON songs.album_id = albums.alid
    LEFT JOIN artists ON albums.artist = artists.artist_name
    WHERE mood_name = mood_name_argument;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `get_song_details` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_song_details`(IN song_name_argument VARCHAR(50))
BEGIN
SELECT song_name, sid, label_name, album_name, genre_name, mood_name FROM songs
LEFT JOIN genres ON songs.genre_id = genres.gid
LEFT JOIN moods ON songs.mood_id = moods.mid
LEFT JOIN albums ON songs.album_id = albums.alid
LEFT JOIN artists ON albums.artist = artists.artist_name
LEFT JOIN record_label ON artists.record_label_id = record_label.rid
WHERE song_name = song_name_argument;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `set_num_released_count` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_num_released_count`(artist_name_argument VARCHAR(50))
BEGIN
    DECLARE released_count INT;
    SELECT COUNT(*) INTO released_count
    FROM albums
    WHERE artist = artist_name_argument;
    UPDATE artists
    SET num_released = released_count
    WHERE artist_name = artist_name_argument;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `song_has_genre` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `song_has_genre`(IN genre_p VARCHAR(50))
BEGIN
    DECLARE genre_id INT;
    SELECT gid INTO genre_id
    FROM genres
    WHERE genre_name = genre_p;
    IF genre_id IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Genre is not valid';
    ELSE
        SELECT songs.sid, songs.song_name, albums.album_name
        FROM genres LEFT JOIN songs ON genres.gid = songs.genre_id LEFT JOIN albums ON songs.album_id = albums.alid
        WHERE songs.genre_id = genre_id;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_all_artists_num_releases` */;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_all_artists_num_releases`()
BEGIN
    DECLARE artist_arg VARCHAR(50);
    DECLARE row_not_found TINYINT DEFAULT 0;
    DECLARE artist_cursor CURSOR FOR SELECT artist_name FROM artists;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET row_not_found = 1;
    OPEN artist_cursor;
    WHILE row_not_found = 0 DO
	FETCH artist_cursor INTO artist_arg;
	CALL set_num_released_count(artist_arg);
    END WHILE;
    CLOSE artist_cursor;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `music1_db` CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-13 19:40:18
