package com.atulvinod.room;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class TransactionHistory extends AppCompatActivity {

    private int ID;
    private TransactionsListAdapter adapter;
    private RecyclerView list;
    private TransactionsViewModel tvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        ID = i.getExtras().getInt("ID",0);
        tvm = new TransactionsViewModel(this.getApplication());
        adapter = new TransactionsListAdapter(this,tvm,this,ID);
        list = findViewById(R.id.transationView);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        tvm.getAllTransactions().observe(this, new Observer<List<Transactions>>() {
            @Override
            public void onChanged(@Nullable List<Transactions> transactions) {


                adapter.setElements(transactions);
            }
        });
}

}
