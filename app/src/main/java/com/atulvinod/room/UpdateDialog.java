package com.atulvinod.room;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateDialog extends Dialog {

    private EntityViewModel mViewModel;
    private Activity activity;
    private EntityData data;
    TextView amountView;
    EditText input;

    public UpdateDialog(EntityViewModel model, Activity activity,EntityData data){
        super(activity);
        mViewModel = model;
        this.activity = activity;
        this.data  = data;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        Button add = findViewById(R.id.AddButton);
        Button deduct = findViewById(R.id.deductButton);
        amountView = findViewById(R.id.amountView);
        amountView.setText(""+data.getAmount());
        input = findViewById(R.id.numberInput);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.update(new EntityData(data.getID(),data.getAmount()+Integer.parseInt(input.getText().toString())));
                dismiss();
            }
        });
        deduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getAmount()-Integer.parseInt(input.getText().toString())<=0){
                    Toast.makeText(getOwnerActivity().getApplicationContext(),"You cannot deduct more money than you've lent",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    mViewModel.update(new EntityData(data.getID(),data.getAmount()-Integer.parseInt(input.getText().toString())));
                    dismiss();
                }

            }
        });
    }
}
