package com.atulvinod.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(exportSchema = false,entities = Transactions.class,version = 1)
public abstract class TransactionsDatabase extends RoomDatabase {
    public abstract TransactionsDAO dao();

    private static TransactionsDatabase INSTANCE;

    static TransactionsDatabase getDatabase(final Context c) {
        if (INSTANCE == null) {
            synchronized (TransactionsDatabase.class) {
                INSTANCE = Room.databaseBuilder(c.getApplicationContext(), TransactionsDatabase.class, "transactions_database").fallbackToDestructiveMigration().addCallback(callback).build();

            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }


      };
}

