package com.example.ivideo.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lishi")
public class LishiEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String liShi;

    public LishiEntity(String liShi) {
        this.liShi = liShi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLiShi() {
        return liShi;
    }

    public void setLiShi(String liShi) {
        this.liShi = liShi;
    }

    @Override
    public String toString() {
        return "LishiEntity{" +
                "id=" + id +
                ", liShi='" + liShi + '\'' +
                '}';
    }
}
