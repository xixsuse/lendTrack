package com.atulvinod.room;

import android.arch.persistence.room.Transaction;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class TransationHistoryManager {

    Context C;
    SharedPreferences transactions;
    SharedPreferences.Editor editor;

    public TransationHistoryManager(Context C) {
        this.C = C;
        transactions = C.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = transactions.edit();
    }


    void createTransation(TransactionObject obj){
        Gson file = new Gson();
        String json = file.toJson(obj);
        int ID = obj.getID();
        editor.putString(Integer.toString(ID),json);
        editor.commit();

    }
    TransactionObject getTransaction(int ID){
        String fetched = transactions.getString(Integer.toString(ID),"No value");
        Gson gson = new Gson();
        TransactionObject obj = gson.fromJson(fetched,TransactionObject.class);
        return obj;
    }
 

}
