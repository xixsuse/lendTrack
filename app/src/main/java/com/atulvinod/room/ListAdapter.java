package com.atulvinod.room;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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

    class EntityViewHolder extends RecyclerView.ViewHolder{
        private final TextView view;
        private final TextView IDview;
        private EntityViewHolder(View V){
            super(V);
            view = V.findViewById(R.id.textView);
            IDview = V.findViewById(R.id.idview);
            Button b  = V.findViewById(R.id.showIDButton);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c,""+IDview.getText(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

   private final LayoutInflater inflater;
    private List<Entity> mEntities;
    private EntityViewModel model;
    Context c;
   ListAdapter(Context c,EntityViewModel model){
       this.c = c;
       inflater = LayoutInflater.from(c);
       this.model = model;
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
        holder.IDview.setText(""+current.getID());
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


}
