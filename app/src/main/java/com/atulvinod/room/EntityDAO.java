package com.atulvinod.room;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EntityDAO {

    @Query("SELECT * FROM entity_table ORDER BY name ASC")
    LiveData<List<Entity>> getEntity();

    @Insert
    void insert(Entity entity);

    @Query("DELETE FROM ENTITY_TABLE")
    void deleteAll();
}
