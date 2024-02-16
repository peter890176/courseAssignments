#!/usr/bin/env python
# coding: utf-8

# In[ ]:


#if the pymysql not installed yet, it should be installed
#pip install pymysql


# In[1]:


import pymysql
genre_list = []
genre_name = ""
# 1) (10 points) Prompt the user for the MySQL username and password.
username = input("Enter username: ")
password = input("Enter password: ")

# 2) (10 points) Use the user provided username and password values to connect to the Music database
#                you modified in homework seven.
try:
    connection = pymysql.connect(
        host='localhost',
        user=username,
        password=password,
        db='music1_db', 
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
    )
    cursor = connection.cursor()
# MYSQL operations
    try:
        cursor.execute("SELECT genre_name FROM genres")
        result = cursor.fetchall()
        print("")
        print("Available genre_name:")
# 4) (20 points) Have your application code do data validation on the input provided by the user. 
        for genre in result:
            genre_list.append(genre['genre_name'])
            print(genre['genre_name'])
        while True:
            print("")
# 3) (10 points) Prompt the user to enter a particular music genre. Store the result in a host language variable.
            genre_name = input("Please enter one genre or enter 'exit' to exit: ")
            if genre_name == 'exit':
                break
# 4) continued, verification section
# 5) (10 points) Generate an error message to standard output and re-prompt the user for input, 
#                if the user provides invalid input.
            if (genre_name not in genre_list):
                print("Invalid genre name, please retry.")
# 6) (20 points) Use the genre as an argument to the song_has_genre(genre_p) . Call the procedure.
            if genre_name != "" and genre_name in genre_list:
                cursor.callproc("song_has_genre", (genre_name,))
                result_set = cursor.fetchall()
                print("")
                print("Result set of song_has_genre:")
# 7) (15 points) Print the result set of song_has_genre() to standard output.
                for genre in result_set:
                    print(genre)
    except pymysql.Error as e:
        print("MYSQL operation error")
    finally:cursor.close()

# 8) (5 points) Once the records are written to standard output, close the connection to the database
#               and end the application program.

except pymysql.Error as e:
    print("Connection fail")
    
finally:
    connection.close()


# In[ ]:




