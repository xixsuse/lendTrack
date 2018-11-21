package com.atulvinod.room;

public class Record {
    String am;
    String desc;
    String dateTime;
    public Record(String amount,String d,String dt){
        am = amount;
        desc = d;
        dt = dateTime;
    }

    public String getAmount() {
        return am;
    }

    public void setAmount(String am) {
        this.am = am;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
