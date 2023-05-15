package com.example.ivideo.db;

import androidx.room.Room;

import com.example.ivideo.App;

public class DbInstance {
    public final static String TAB_NAME = "liShiDb";
    private static LishiDataBase dataBase;
    public static LishiDataBase getDataBase(){
        if (dataBase == null){
            synchronized (LishiDataBase.class){
                if (dataBase == null){
                    dataBase = Room.databaseBuilder(App.getContext(),LishiDataBase.class,TAB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return dataBase;
    }
}
