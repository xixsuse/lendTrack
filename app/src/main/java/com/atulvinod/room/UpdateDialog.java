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
    FingerprintHelper fingerprint;
    static Button add,deduct;
    static TextView fingerprintStatus;

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
         add = findViewById(R.id.AddButton);
        deduct = findViewById(R.id.deductButton);
        fingerprintStatus = findViewById(R.id.fingerprintStatus);
        fingerprint = new FingerprintHelper(getContext());
        add.setEnabled(false);
        deduct.setEnabled(false);
        amountView = findViewById(R.id.amountView);
        amountView.setText(""+data.getAmount());
        input = findViewById(R.id.numberInput);
        fingerprint.startAuth(MainActivity.fingerprintManager,MainActivity.cryptoObject);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fingerprint.getAUTHENTICATION_STATUS()==false){
                    Toast.makeText(activity.getApplicationContext(),"Touch the fingerprint sensor to authenticate transaction",Toast.LENGTH_LONG).show();
                }else {
                    mViewModel.update(new EntityData(data.getID(), data.getAmount() + Integer.parseInt(input.getText().toString())));
                    fingerprint.setAUTHENTICATION_STATUS(false);
                    dismiss();

                }
            }
        });
        deduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fingerprint.getAUTHENTICATION_STATUS()==false){
                    Toast.makeText(activity.getApplicationContext(),"Touch the fingerprint sensor to authenticate transaction",Toast.LENGTH_LONG).show();

                }else {
                    if (data.getAmount() - Integer.parseInt(input.getText().toString()) <= 0) {
                        Toast.makeText(activity.getApplicationContext(), "You cannot deduct more money than you've lent", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        mViewModel.update(new EntityData(data.getID(), data.getAmount() - Integer.parseInt(input.getText().toString())));
                        fingerprint.setAUTHENTICATION_STATUS(false);
                        dismiss();
                    }
                }
            }
        });
    }
}
