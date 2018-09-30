package com.atulvinod.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class EntityViewModel extends AndroidViewModel {

    private Repository repo;
    private LiveData<List<Entity>> entities;

    public EntityViewModel(Application app){
        super(app);
        repo = new Repository(app);
        entities = repo.getAllEntities();
    }
    LiveData<List<Entity>> getAllWords(){
        return entities;
    }
    public void insert(Entity e){
        repo.insert(e);
    }
}
