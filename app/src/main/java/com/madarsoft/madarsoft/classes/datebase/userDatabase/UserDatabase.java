package com.madarsoft.madarsoft.classes.datebase.userDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.classes.datebase.userDao.UserDao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDoa();
}
