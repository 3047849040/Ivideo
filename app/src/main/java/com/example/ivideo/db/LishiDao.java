package com.example.ivideo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LishiDao {
    @Insert
    void insertLs(LishiEntity entity);
    @Delete
    void delete(LishiEntity entity);
    @Query("select * from lishi")
    List<LishiEntity> queryAll();
    @Query("delete from lishi")
    void deleteAll();
}
