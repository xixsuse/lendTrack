package com.atulvinod.room;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder> {

    private Context context;
    TransactionsViewModel tvm;
    Activity activity;
    LayoutInflater inflater;
    private List<Transactions> allTransactions = null;
    private int ID;

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.transaction,parent,false);


        return new TransactionViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transactions current = allTransactions.get(position);
        if(current.getID()==ID) {
            holder.datetime.setText(current.getDatetime());
            holder.desc.setText(current.getDescription());
            holder.setID(current.getID());
            holder.amount.setText(current.getAmount().toString());
        }

    }

    @Override
    public int getItemCount() {
        int temp = 0;
        if(allTransactions != null){
            for(int i=0;i<allTransactions.size();i++){
                if(allTransactions.get(i).getID()==ID){
                    temp++;
                }
            }
            return temp;
        }else{
            return 0;
        }

    }
    void setElements(List<Transactions> elements){
       List<Transactions> temp = new List<Transactions>() {
           @Override
           public int size() {
               return 0;
           }

           @Override
           public boolean isEmpty() {
               return false;
           }

           @Override
           public boolean contains(Object o) {
               return false;
           }

           @NonNull
           @Override
           public Iterator<Transactions> iterator() {
               return null;
           }

           @NonNull
           @Override
           public Object[] toArray() {
               return new Object[0];
           }

           @NonNull
           @Override
           public <T> T[] toArray(@NonNull T[] a) {
               return null;
           }

           @Override
           public boolean add(Transactions transactions) {
               return false;
           }

           @Override
           public boolean remove(Object o) {
               return false;
           }

           @Override
           public boolean containsAll(@NonNull Collection<?> c) {
               return false;
           }

           @Override
           public boolean addAll(@NonNull Collection<? extends Transactions> c) {
               return false;
           }

           @Override
           public boolean addAll(int index, @NonNull Collection<? extends Transactions> c) {
               return false;
           }

           @Override
           public boolean removeAll(@NonNull Collection<?> c) {
               return false;
           }

           @Override
           public boolean retainAll(@NonNull Collection<?> c) {
               return false;
           }

           @Override
           public void clear() {

           }

           @Override
           public Transactions get(int index) {
               return null;
           }

           @Override
           public Transactions set(int index, Transactions element) {
               return null;
           }

           @Override
           public void add(int index, Transactions element) {

           }

           @Override
           public Transactions remove(int index) {
               return null;
           }

           @Override
           public int indexOf(Object o) {
               return 0;
           }

           @Override
           public int lastIndexOf(Object o) {
               return 0;
           }

           @NonNull
           @Override
           public ListIterator<Transactions> listIterator() {
               return null;
           }

           @NonNull
           @Override
           public ListIterator<Transactions> listIterator(int index) {
               return null;
           }

           @NonNull
           @Override
           public List<Transactions> subList(int fromIndex, int toIndex) {
               return null;
           }
       };


        allTransactions = elements;
        notifyDataSetChanged();

    }


    public TransactionsListAdapter(Context c, TransactionsViewModel vm, Activity a,int ID){
        context = c;
        tvm = vm;
        activity = a;
        this.ID = ID;
        inflater = LayoutInflater.from(c);
    }
    class TransactionViewHolder extends RecyclerView.ViewHolder{
        TextView datetime;

        TextView desc;
        TextView amount;
        private int ID;
        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }



        public TransactionViewHolder(View v){
            super(v);
            datetime = v.findViewById(R.id.dateTimeView);
            desc = v.findViewById(R.id.discriptionView);
            amount = v.findViewById(R.id.amountView);

        }
    }
}
