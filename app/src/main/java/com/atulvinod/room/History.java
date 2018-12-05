package com.atulvinod.room;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class History extends AppCompatActivity {
    public RecordViewModel viewModel;

    public RecyclerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        int ID = i.getExtras().getInt("TRANSACTION_ID");

       getRepo r = new getRepo(ID);
       r.execute(this.getApplication());
        RecordRepository rp = null;
        try {
            rp = r.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<Record> rc = rp.getRecordList();
        view = findViewById(R.id.transationView);
        ViewAdapter adapter = new ViewAdapter(rc,this);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);



    }
    class getRepo extends AsyncTask<Application,Void,RecordRepository>{
       int ID = 0;
        public RecordRepository repo;
        public getRepo(int Id){
            this.ID = Id;
        }

        @Override
        protected RecordRepository doInBackground(Application... contexts) {
            repo = new RecordRepository(contexts[0],ID);
            return repo;
        }
    }
    class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.holder>{

        private final LayoutInflater inflater;
        private List<Record> records;
        private Context c;
        public ViewAdapter(List<Record> r,Context c){
            records  =r;
            this.c = c;
            inflater = LayoutInflater.from(c);

        }
        @NonNull
        @Override
        public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = inflater.inflate(R.layout.transactioncard,parent,false);

            return new holder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull holder holder, int position) {
            Record r = records.get(position);
            String desc = r.getDescription();
            if (desc==""||desc == " "||desc.equals("")||desc.equals(" ")){
                desc = "No description available";
            }

            holder.discription.setText(desc);
            holder.dateTime.setText(r.getDate());
            holder.amount.setText(r.getAmount());
            if(r.getAmount().toString().contains("+")){
                holder.card.setBackgroundColor(Color.parseColor("#4e9525"));
            }else{
                holder.card.setBackgroundColor(Color.parseColor("#c1224f"));

            }
            holder.card.setRadius(20);

        }

        @Override
        public int getItemCount() {
            if(records==null) {
                return 0;
            }else{
                return records.size();
            }
        }

        class holder extends RecyclerView.ViewHolder{
            TextView amount,dateTime,discription;
            CardView card;
            public holder(View V){
                super(V);
                amount = V.findViewById(R.id.amountView);
                dateTime = V.findViewById(R.id.dateTimeView);
                discription = V.findViewById(R.id.discriptionView);
                card = V.findViewById(R.id.card);
            }
        }
    }

}
