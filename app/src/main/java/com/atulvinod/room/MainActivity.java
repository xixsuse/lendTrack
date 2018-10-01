package com.atulvinod.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_ENTITY_ACTIVITY_REQUEST_CODE = 1;

    public EntityViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.list);
        model = ViewModelProviders.of(this).get(EntityViewModel.class);
        final ListAdapter adapter = new ListAdapter(this,model);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        model.getAllWords().observe(this, new Observer<List<Entity>>() {
            @Override
            public void onChanged(@Nullable List<Entity> entities) {
                adapter.setElements(entities);
            }
        });
        Button button = (Button)findViewById(R.id.New);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NewWordActivity.class);
                startActivityForResult(i,NEW_ENTITY_ACTIVITY_REQUEST_CODE);
            }
        });


    }
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==NEW_ENTITY_ACTIVITY_REQUEST_CODE&&resultCode==RESULT_OK){

            Entity entity = new Entity(Integer.parseInt(data.getStringExtra(NewWordActivity.AMOUNT_REPLY)),data.getStringExtra(NewWordActivity.EXTRA_REPLY),0);
            model.insert(entity);

        }else{
            Toast.makeText(this,"Nothing is entered",Toast.LENGTH_SHORT).show();
        }
    }
}
