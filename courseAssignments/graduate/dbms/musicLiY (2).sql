-- 1.
SELECT artist_name, label_name
FROM artists 
LEFT JOIN record_label
ON  artists.record_label_id = record_label.rid;

-- 2.
SELECT label_name, COUNT(aid) AS signed_artists
FROM record_label 
LEFT JOIN artists ON record_label.rid = artists.record_label_id
GROUP BY label_name
ORDER BY signed_artists DESC;

-- 3.
SELECT artist_name, COUNT(user_id) AS num_followers
FROM user_follows_artist 
LEFT JOIN artists USING (aid)
GROUP BY user_follows_artist.aid;

-- 4.
CREATE TABLE rock_songs(
SELECT *
FROM songs 
WHERE genre_id = ( SELECT gid FROM genres WHERE genre_name = 'Rock')
);

-- 5.
SELECT sid, song_name, album_name, label_name, artist_name
FROM songs
LEFT JOIN albums ON songs.album_id = albums.alid
LEFT JOIN artists ON albums.artist =artists.artist_name
LEFT JOIN record_label ON artists.record_label_id = record_label.rid;

-- 6.
SELECT song_name, album_name, label_name, GROUP_CONCAT(artists.artist_name) FROM songs
LEFT JOIN artist_performs_song USING(sid)
LEFT JOIN albums ON songs.album_id = albums.alid
LEFT JOIN artists USING(aid)
LEFT JOIN record_label ON artists.record_label_id = record_label.rid 
GROUP BY song_name,  album_name, label_name
ORDER BY song_name ASC;

-- 7.
WITH sub_count AS (SELECT song_name, COUNT(aid) as count_aid FROM artist_performs_song
LEFT JOIN songs ON artist_performs_song.sid=songs.sid
GROUP BY artist_performs_song.sid
ORDER BY COUNT(aid) DESC)
SELECT song_name  FROM sub_count
WHERE count_aid = (SELECT MAX(count_aid)FROM sub_count);

-- 8.
SELECT artist_name, COUNT(user_id) AS num_followers
FROM  artists LEFT JOIN user_follows_artist USING( aid)
GROUP BY artist_name
ORDER BY num_followers DESC;

-- 9.
SELECT label_name, COUNT(alid) as num_albums 
FROM record_label 
LEFT JOIN artists on record_label.rid =artists.record_label_id
INNER JOIN albums on artist_name=albums.artist
GROUP BY rid
ORDER BY num_albums DESC;

-- 10.
SELECT genre_name, COUNT(sid) AS num_songs  FROM genres LEFT JOIN songs on genres.gid = songs.genre_id
GROUP BY genre_name
ORDER BY num_songs DESC;

-- 11.
SELECT DISTINCT song_name, album_name, artist, genre_name, mood_name, (SELECT COUNT(user_id) FROM user_follows_artist WHERE user_follows_artist.aid = artists.aid) AS num_followers  FROM songs
LEFT JOIN moods ON songs.mood_id = moods.mid
LEFT JOIN genres ON songs.genre_id = genres.gid
LEFT JOIN albums ON songs.album_id = albums.alid
LEFT JOIN artists ON albums.artist = artists.artist_name
LEFT JOIN user_follows_artist ON artists.aid = user_follows_artist.aid;

-- 12.
SELECT artist_name
FROM artists
LEFT JOIN user_follows_artist USING(aid)
GROUP BY artist_name
HAVING COUNT(user_id) = (SELECT  COUNT(DISTINCT user_id) FROM user_follows_artist);

-- 13.
SELECT user_name, user_email
FROM user LEFT JOIN  user_follows_artist USING(user_id)
GROUP BY user_name, user_email
HAVING COUNT(aid) = (SELECT COUNT(DISTINCT aid) FROM artists);

-- 14.
SELECT * FROM albums
LEFT JOIN artists ON albums.artist = artists.artist_name
LEFT JOIN record_label ON artists.record_label_id = record_label.rid
WHERE label_name LIKE '%MUSIC%';

-- 15.
SELECT DISTINCT sub.user_name, user.user_name,  artist_name FROM user 
INNER JOIN user_follows_artist using(user_id)
INNER JOIN artists USING(aid)
INNER JOIN (SELECT * FROM user INNER JOIN user_follows_artist using(user_id)) AS sub ON user_follows_artist.aid = sub.aid AND user_follows_artist.user_id <> sub.user_id
WHERE user.user_name > sub.user_name
ORDER BY artist_name ASC;

-- 16.
SELECT artist_name, COUNT(alid) as num_albums FROM artists
LEFT JOIN albums ON artists.artist_name = albums.artist
GROUP BY aid
ORDER BY num_albums DESC;

-- 17.
WITH perform AS(SELECT * FROM artists
LEFT JOIN artist_performs_song USING(aid)
LEFT JOIN songs USING(sid)),
produce AS (SELECT * FROM artists
LEFT JOIN albums ON artists.artist_name = albums.artist
LEFT JOIN songs ON albums.alid = songs.album_id),
result AS(
SELECT artist_name, songscount FROM(
SELECT DISTINCT artist_name, COUNT(song_name) AS songscount FROM( SELECT artist_name, song_name FROM perform UNION ALL SELECT artist_name, song_name FROM produce) AS alltable
GROUP BY artist_name) AS countsong)
SELECT artist_name, songscount AS most_songs FROM result WHERE songscount = (SELECT MAX(songscount)FROM result);

-- 18.
WITH perform AS(SELECT * FROM artists
LEFT JOIN artist_performs_song USING(aid)
LEFT JOIN songs USING(sid)),
result AS(
SELECT artist_name, songscount FROM(
SELECT DISTINCT artist_name, COUNT(song_name) AS songscount FROM( SELECT artist_name, song_name FROM perform ) AS alltable
GROUP BY artist_name) AS countsong)
SELECT artist_name, songscount AS most_songs FROM result WHERE songscount = (SELECT MAX(songscount)FROM result);

-- 19.
WITH alltables AS (SELECT * FROM record_label 
LEFT JOIN artists ON record_label.rid = artists.record_label_id
LEFT JOIN albums ON artists.artist_name = albums.artist
LEFT JOIN songs ON albums.alid = songs.album_id)
SELECT label_name, COUNT(sid) FROM alltables GROUP BY label_name;