package com.atulvinod.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TransactionsViewModel extends AndroidViewModel{
    private TransactionsRepository repo;
    private LiveData<List<Transactions>> transactions;
    private int id;
    public TransactionsViewModel(Application app){
        super(app);

        repo = new TransactionsRepository(app);
        transactions = repo.getAllTransactions();

    }
    public LiveData<List<Transactions>> getAllTransactions(){
        return transactions;
    }
    public LiveData<List<Transactions>> getSpecificTransactions(int ID){
        return repo.getSpecificTransactions();
    }
    public void createTransaction(Transactions t){
        repo.insert(t);
    }
    public void deleteTransaction(int ID){
        repo.delete(ID);

    }
}
