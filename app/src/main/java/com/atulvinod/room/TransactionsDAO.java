package com.atulvinod.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface TransactionsDAO {

    @Query("SELECT * FROM transactions_table ORDER BY sno ASC")
    LiveData<List<Transactions>> getTransactions();

    @Insert
    void insertTransaction(Transactions transactions);

    @Query("DELETE FROM transactions_table WHERE ID=:s")
    void deleteTransactions(int s);

    @Query("SELECT * FROM transactions_table WHERE ID=:s")
    LiveData<List<Transactions>> getTransationViaID(int s);
}
