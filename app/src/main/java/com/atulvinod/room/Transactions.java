package com.atulvinod.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

@android.arch.persistence.room.Entity(tableName = "transactions_table")
public class Transactions {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="sno")
    private int sno;

    @ColumnInfo(name ="datetime")
    private String datetime;

    @ColumnInfo(name = "ID")
    private int ID;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="amount")
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }




    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transactions(int sno, String datetime, int ID, String description,String amount) {
        this.sno = sno;
        this.datetime = datetime;
        this.ID = ID;
        this.description = description;
        this.amount = amount;
    }
}
