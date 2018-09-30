package com.atulvinod.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Repository {

    private EntityDAO dao;
    private LiveData<List<Entity>> allEntities;

    Repository(Application app){
        Database db = Database.getDatabase(app);
        dao = db.mEntityDAO();
        allEntities = dao.getEntity();
    }
    LiveData<List<Entity>> getAllEntities(){return allEntities;}

    public void insert(Entity e){
        new insertTask(dao).execute(e);
    }
    private static class insertTask extends AsyncTask<Entity,Void,Void>{
        private EntityDAO d;

        @Override
        protected Void doInBackground(Entity... entities) {
           d.insert(entities[0]);
            return null;
        }

        insertTask(EntityDAO edao){
            d = edao;
        }
    }
}
