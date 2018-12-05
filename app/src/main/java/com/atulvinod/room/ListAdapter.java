package com.atulvinod.room;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.EntityViewHolder> {


    private final LayoutInflater inflater;
    private List<Entity> mEntities;
    private EntityViewModel model;
    private IndiaCurrencyFormatter formatter;
    Context c;
    Activity mActivity;
    FingerprintHelper  fingerprint;
    class EntityViewHolder extends RecyclerView.ViewHolder{
        private final TextView view;

        private int ID;
        private Context c;
        public void setID(int i){
            ID = i;
        }
        public int getID() {
            return ID;
        }
        private  String internalString;

        public String getInternalString() {
            return internalString;
        }

        public void setInternalString(String internalString) {
            this.internalString = internalString;
        }

        private final TextView amount;
        private EntityViewHolder(View V){
            super(V);
            c = V.getContext();
            view = V.findViewById(R.id.textView);
            amount = V.findViewById(R.id.idview);
            setInternalString(amount.getText().toString());
            Button delete  = V.findViewById(R.id.deleteButton);
            Button update = V.findViewById(R.id.updateButton);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UpdateDialog dialog = new UpdateDialog(model,mActivity,new EntityData(getID(),Integer.parseInt(getInternalString())));
                    dialog.show();

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog deleteDialog = new DeleteDialog(mActivity,model,ID);
                    deleteDialog.show();
                }
            });
            V.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { Intent i = new Intent(c,History.class);
                    i.putExtra("TRANSACTION_ID",getID());
                    c.startActivity(i);

                }
            });
        }

    }


   ListAdapter(Context c, EntityViewModel model, Activity activity){
       this.c = c;
       inflater = LayoutInflater.from(c);
       this.model = model;
       mActivity = activity;
       fingerprint = new FingerprintHelper(this.c);
       formatter = new IndiaCurrencyFormatter();

   }


    @NonNull
    @Override
    public EntityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View item = inflater.inflate(R.layout.entity,parent,false);
       return new EntityViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityViewHolder holder, int position) {
        Entity current = mEntities.get(position);
        holder.view.setText(current.getName());
        holder.amount.setText(formatter.formatAmount(current.getAmount()));
        holder.setInternalString(""+current.getAmount());
        holder.setID(current.getID());
    }

    void setElements(List<Entity> elements){
       mEntities = elements;
       notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        if(mEntities != null){
            return mEntities.size();
        }else{
            return 0;
        }
    }
    public void confirm(final int ID){
        fingerprint.startAuth(MainActivity.fingerprintManager,MainActivity.cryptoObject);
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(c, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(c);
        }
        builder.setTitle("Delete Entry").setMessage("Are you sure you want to delete this entry ? \n Touch the fingerprint sensor to authenticate delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
        }).show();
    }


}
