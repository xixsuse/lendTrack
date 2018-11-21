package com.atulvinod.room;

import java.util.LinkedList;

public class TransactionObject {
    int ID;
    LinkedList<Record> records ;
    public TransactionObject(int ID){
        this.ID = ID;
       records = new LinkedList<>();
    }
    public TransactionObject(){
       records = new LinkedList<>();
    }
    int getID(){
        return ID;
    }
    void insertRecord(Record r){
        records.add(r);
    }


}
