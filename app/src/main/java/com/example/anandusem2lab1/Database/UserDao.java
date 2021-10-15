package com.example.anandusem2lab1.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from user")
    List<User> getAllUsers();

    @Insert
    void insertUser(User... user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
