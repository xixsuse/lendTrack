package com.atulvinod.room;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteDialog extends Dialog {
    EntityViewModel model;
    private Activity mActivity;
    static Button yes,no;
    static TextView fingerprintStatus;
    FingerprintHelper fingerprint;
   int ID;
    public DeleteDialog(Activity e,EntityViewModel model,int id){
        super(e);
        mActivity = e;
        this.model = model;
        fingerprint = new FingerprintHelper(e);
        this.ID = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        yes = findViewById(R.id.yesButton);
        yes.setEnabled(false);
        no = findViewById(R.id.noButton);
        fingerprint.startAuth(MainActivity.fingerprintManager,MainActivity.cryptoObject);
        fingerprintStatus = findViewById(R.id.fingerprint);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity.getApplicationContext(),"Changes were saved",Toast.LENGTH_SHORT).show();
                model.delete(ID);
                dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
