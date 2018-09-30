package com.atulvinod.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@android.arch.persistence.room.Entity(tableName = "entity_table")
public class Entity {

    @PrimaryKey
    @NonNull
    

    @ColumnInfo(name = "name")
    private String mName;


    public Entity(@NonNull String name){
        this.mName = name;
    }
    public String getName(){
        return this.mName;
    }
}
