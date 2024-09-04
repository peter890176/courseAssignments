use music1_db;
-- 1. Write a function num_songs_with_genre(genre_p) that accepts a genre name and returns the number of songs with the genre. (5 points)
DELIMITER $$
CREATE FUNCTION num_songs_with_genre(genre_P VARCHAR(50)) RETURNS INT DETERMINISTIC READS SQL DATA
BEGIN
	DECLARE numbers INT;
	SELECT COUNT(*) INTO numbers
	FROM songs
	LEFT JOIN genres ON songs.genre_id = genres.gid
	WHERE genres.genre_name = genre_p;
	RETURN numbers;
END$$
DELIMITER ;
SELECT num_songs_with_genre('Rock');
SELECT num_songs_with_genre('Pop');
SELECT num_songs_with_genre('Jazz');

-- 2. Write a procedure get_artists_with_label(label_p) that accepts a record label name and returns a result set of all artist names and the corresponding label name. (5 points)
DELIMITER $$
CREATE PROCEDURE get_artists_with_label(IN label_p VARCHAR(50))
BEGIN
	SELECT artist_name, label_name
	FROM record_label
	LEFT JOIN artists ON artists.record_label_id = record_label.rid
	WHERE record_label.label_name = label_p;
END$$
DELIMITER ;
CALL get_artists_with_label('Capitol Music Group');
CALL get_artists_with_label('Def Jam Recordings');
CALL get_artists_with_label('Republic Records');

-- 3. Write a procedure named song_has_genre(genre_p) that accepts a genre name and returns a result set of the songs with that genre. The result should contain the song id , the song name, and the album name. If a genre is provided that is not found in the genre table, generate an error from the procedure stating that the passed genre is not valid and use SIGNAL to throw error ‘45000’. (10 points)
DELIMITER $$
CREATE PROCEDURE song_has_genre(IN genre_p VARCHAR(50))
BEGIN
    DECLARE genre_id INT;
    SELECT gid INTO genre_id
    FROM genres
    WHERE genre_name = genre_p;
    IF genre_id IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Genre is not valid.';
    ELSE
        SELECT songs.sid, songs.song_name, albums.album_name
        FROM genres LEFT JOIN songs ON genres.gid = songs.genre_id LEFT JOIN albums ON songs.album_id = albums.alid
        WHERE songs.genre_id = genre_id;
    END IF;
END $$
DELIMITER ;
CALL song_has_genre('Rock');
CALL song_has_genre('Pop');
CALL song_has_genre('ABC');

-- 4. Write a function named album_length(length_p) that accepts one parameter, a count of songs and returns the number of albums with that length (5 points)
DELIMITER $$
CREATE FUNCTION album_length(length_p INT) RETURNS INT DETERMINISTIC READS SQL DATA
BEGIN
    DECLARE number_of_albums INT;
    SELECT COUNT(*) INTO number_of_albums FROM(
    SELECT COUNT(album_id) 
    FROM songs
    GROUP BY album_id
    HAVING COUNT(sid) = length_p)AS sub;
    RETURN number_of_albums;
END $$
DELIMITER ;
SELECT album_length(1);
SELECT album_length(2);
SELECT album_length(3);

-- 5. Write a procedure named get_song_details() that accepts a song name as an argument and returns the song name, the song id, the recording label, the album name, the genre name and the mood name. (10 points)
DELIMITER $$
CREATE PROCEDURE  get_song_details(IN song_name_argument VARCHAR(50))
BEGIN
	SELECT song_name, sid, label_name, album_name, genre_name, mood_name FROM songs
	LEFT JOIN genres ON songs.genre_id = genres.gid
	LEFT JOIN moods ON songs.mood_id = moods.mid
	LEFT JOIN albums ON songs.album_id = albums.alid
	LEFT JOIN artists ON albums.artist = artists.artist_name
	LEFT JOIN record_label ON artists.record_label_id = record_label.rid
	WHERE song_name = song_name_argument;
END$$
DELIMITER ;
CALL get_song_details('Funky Duck');
CALL get_song_details('Escape');
CALL get_song_details('Habit');

-- 6. Write a function named more_followers(artist1,artist2). It accepts 2 artist names and returns 1 if artist1 has more followers than artist2, 0 if they have the same number of followers , and -1 if artist2 has more followers that artist1. (5 points)
DELIMITER $$
CREATE FUNCTION more_followers(artist1 VARCHAR(50), artist2 VARCHAR(50)) RETURNS INT DETERMINISTIC READS SQL DATA
BEGIN
    DECLARE followers_artist_1 INT;
    DECLARE followers_artist_2 INT;
    SELECT COUNT(*) INTO followers_artist_1
    FROM user_follows_artist LEFT JOIN artists USING(aid)
    WHERE artist_name = artist1;
    SELECT COUNT(*) INTO followers_artist_2
    FROM user_follows_artist LEFT JOIN artists USING(aid)
    WHERE artist_name = artist2;
    IF 
		followers_artist_1 > followers_artist_2 THEN RETURN 1;
    ELSEIF 
		followers_artist_1 = followers_artist_2 THEN RETURN 0;
    ELSE
		RETURN -1;
    END IF;
END$$
DELIMITER ;
SELECT more_followers('Vanilla','Vulfpeck');
SELECT more_followers('Vulfpeck','Vanilla');
SELECT more_followers('Vulfpeck','Maroon 5');

-- 7. Create a procedure named create_song( title_p, artist_p, record_label_p, mood_p, genre_p, album_title) that inserts a song into the database. Make sure you create the appropriate tuples in the album and other required tables before attempting to insert the song. Also, ensure that the specified record label, genre name and mood name already exist in the database. If they do not exist, use SIGNAL with error number 450000. When adding a song, it can be associated with a known artist’s current existing album or the song could belong to a new album for the artist. Also, assume the producer of the song performs on the song.
DELIMITER $$
CREATE PROCEDURE create_song(IN title_p VARCHAR(50), IN artist_p VARCHAR(50), IN record_label_p VARCHAR(50), IN mood_p VARCHAR(50), IN genre_p VARCHAR(50), IN album_title VARCHAR(50))
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
END$$
DELIMITER ;
CALL create_song('Me about You', 'The Turtles', 'Def Jam Recordings', 'Calm', 'Pop', 'Happy Together');
SELECT * FROM songs;
SELECT * FROM artist_performs_song;
SELECT * FROM albums;
SELECT * FROM artists;
SELECT * FROM genres;
SELECT * FROM moods;
SELECT * FROM record_label;

-- 8. Write a procedure named get_songs_with_mood() that accepts a mood name and returns the song name, the mood name, mood description and the artist who released the song. (5 points)
DELIMITER $$
CREATE PROCEDURE get_songs_with_mood(IN mood_name_argument VARCHAR(50))
BEGIN
    SELECT song_name, mood_name, mood_description, artist_name
    FROM moods
    LEFT JOIN songs ON moods.mid=songs.mood_id
    LEFT JOIN albums ON songs.album_id = albums.alid
    LEFT JOIN artists ON albums.artist = artists.artist_name
    WHERE mood_name = mood_name_argument;
END$$
DELIMITER ;
CALL get_songs_with_mood('Happy');
CALL get_songs_with_mood('Exuberant');
CALL get_songs_with_mood('Energetic');

-- 9. Modify the artists table to contain a field called num_released of type INTEGER and write a procedure called set_num_released_count(artist) that accepts an artist name and initializes the num_released field to the number of albums the artist has released. The artist table modification can occur outside or inside of the procedure but must be executed only once. (10 points)
ALTER TABLE artists ADD num_released INT;
DELIMITER $$
CREATE PROCEDURE set_num_released_count(artist_name_argument VARCHAR(50))
BEGIN
    DECLARE released_count INT;
    SELECT COUNT(*) INTO released_count
    FROM albums
    WHERE artist = artist_name_argument;
    UPDATE artists
    SET num_released = released_count
    WHERE artist_name = artist_name_argument;
END$$
DELIMITER ;
CALL set_num_released_count('Vulfpeck');
CALL set_num_released_count('Low Hum');
CALL set_num_released_count('Still Woozy');
SELECT * FROM artists ;

-- 10. Create a procedure named update_all_artists_num_releases( ) that assigns the artist.num_releases to the correct value. The correct value is determined by the number of albums the artist has released. Use the procedure from problem 9 to complete this procedure. You will need a cursor and a handler to complete this procedure (5 points)
DELIMITER $$
CREATE PROCEDURE update_all_artists_num_releases()
BEGIN
    DECLARE artist_argument VARCHAR(50);
    DECLARE row_not_found TINYINT DEFAULT 0;
    DECLARE artist_cursor CURSOR FOR SELECT artist_name FROM artists;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET row_not_found = 1;
    OPEN artist_cursor;
    WHILE row_not_found = 0 DO
	FETCH artist_cursor INTO artist_argument;
	CALL set_num_released_count(artist_argument);
    END WHILE;
    CLOSE artist_cursor;
END$$
DELIMITER ;
CALL update_all_artists_num_releases();
SELECT * FROM artists ;

-- 11. Write a trigger that updates the artist table when an album tuple is inserted into the database. The trigger will need to assign the correct value of albums released for the artist. Name the trigger artist_update_after_insert_album. Insert an album into the album table to verify your trigger is working; The album name = “Justice”, Artist = “Justin Beiber”. (10 points)
DELIMITER $$
CREATE TRIGGER artist_update_after_insert_album
AFTER INSERT ON albums
FOR EACH ROW
BEGIN
	UPDATE artists
	SET num_released = num_released+1
	WHERE artist_name = NEW.artist;
END $$
DELIMITER ;
INSERT INTO albums(album_name, artist)
VALUES('Justice', 'Justin Beiber');
INSERT INTO albums(album_name, artist)
VALUES('Justic2', 'Justin Beiber');
SELECT * FROM artists;

-- 12. Write a trigger that updates the artist table when an album is deleted from the album table. The trigger will need to assign the correct value to the artist.num_released field for the corresponding artist. Name the trigger artist_update_after_delete_artist. Delete an album from the album table to verify your trigger is working; The album name = “Justice”, Artist = “Justin Beiber”. (5 points)
DELIMITER $$
CREATE TRIGGER artist_update_after_delete_album
AFTER DELETE ON albums
FOR EACH ROW
BEGIN
	UPDATE artists
	SET num_released = num_released-1
	WHERE artist_name = OLD.artist;
END $$
DELIMITER ;
DELETE FROM albums WHERE album_name ='Justice' AND artist = 'Justin Beiber';
DELETE FROM albums WHERE album_name ='Justic2' AND artist = 'Justin Beiber';
SELECT * FROM artists;

-- 13.Create and execute a prepared statement from the SQL workbench that calls the function more_followers(artist1,artist2). Use 2 user session variables to pass the two arguments to the function. Pass the values “Vanilla” and “The Turtles” as the author values. (5 points)
PREPARE stmt1 FROM 'SELECT more_followers(?, ?) AS more_followers';
SET @a = 'Vanilla'; 
SET @b = 'The Turtles';
EXECUTE stmt1 USING @a, @b;

-- 14. Create and execute a prepared statement from the SQL workbench that calls the function num_songs_with_genre(genre_p) . Use a user session variable to pass the genre name to the function. Pass the value “Rock” as the length (5 points)
PREPARE stmt2 FROM 'SELECT num_songs_with_genre(?) AS get_genre_song_numbers';
SET @c = 'Rock';
EXECUTE stmt2 USING @c;


