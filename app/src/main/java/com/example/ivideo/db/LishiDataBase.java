package com.example.ivideo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {LishiEntity.class},version = 1,exportSchema = false)
public abstract class LishiDataBase extends RoomDatabase {
    public abstract LishiDao lishiDao();
}
