package com.atulvinod.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class TransactionsRepository {
    private TransactionsDAO dao;
    private LiveData<List<Transactions>> allTransactions;
    private LiveData<List<Transactions>> specificTransactions;
    private int ID;
    public void setID(int ID){
        this.ID = ID;
    }


    TransactionsRepository(Application app){
        TransactionsDatabase db = TransactionsDatabase.getDatabase(app);
        dao = db.dao();
        allTransactions = dao.getTransactions();
        specificTransactions = dao.getTransationViaID(ID);

    }
    public LiveData<List<Transactions>> getAllTransactions(){
        return allTransactions;
    }

    public LiveData<List<Transactions>> getSpecificTransactions() {
        return specificTransactions;
    }

    public void insert(Transactions t){
        new insertTask(dao).execute(t);
    }
    public void delete(int ID){
        new deleteTask(dao).execute(ID);
    }
    public static class insertTask extends AsyncTask<Transactions,Void, Void>{
        private TransactionsDAO thisDao;
        public insertTask(TransactionsDAO d){
            thisDao = d;
        }
        @Override
        protected Void doInBackground(Transactions... transactions) {
            thisDao.insertTransaction(transactions[0]);
            return null;
        }
    }
    public static class deleteTask extends AsyncTask<Integer,Void,Void>{
        private TransactionsDAO thisDao;
        public deleteTask(TransactionsDAO dao){
            thisDao = dao;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            thisDao.deleteTransactions(integers[0]);
            return null;
        }
    }
}
