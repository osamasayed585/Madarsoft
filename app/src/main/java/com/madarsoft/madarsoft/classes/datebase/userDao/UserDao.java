package com.madarsoft.madarsoft.classes.datebase.userDao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.madarsoft.madarsoft.classes.datebase.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> fetchUsers();

    @Insert
    long saveUser(User user);


    /*
      if you want to update any user I can make this nia annotation @Update
      Or write a sentence using annotation @Query
      I can make anything for database like (Update, Delete)
      but you ask me  just to "save user" and "fetch all users"
    * */
}
