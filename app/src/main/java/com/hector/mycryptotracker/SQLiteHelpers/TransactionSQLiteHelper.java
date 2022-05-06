package com.hector.mycryptotracker.SQLiteHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hector.mycryptotracker.entities.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TransactionSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE IF NOT EXISTS Transaccion  (type TEXT,  name TEXT, value REAL, date DATE, amount REAL)";
    String sqlDrop = "DROP TABLE IF EXISTS Transaccion";

    public TransactionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(sqlDrop);
        db.execSQL(sqlCreate);
    }

    public void createTransaction(SQLiteDatabase db, String type, String name, float value, String date, float amount) {
        String query = "INSERT INTO Transaccion (type, name, value, date, amount)" +
                " VALUES ('" + type + "','" + name + "'," + value + ",'" + date + "'," + amount + ")";
        db.execSQL(query);
    }

    public void deleteTransaction(SQLiteDatabase db, String type, String name, float value, String date, float amount) {
        String query = "DELETE FROM Transaccion WHERE " +
                " type like '" + type + "' and name like '" + name + "' and value like '" + value +
                "' and date like '" + date + "' and amount = " + amount ;
        db.execSQL(query);
    }

    public Set<Activity> getTransactions(SQLiteDatabase db) {
        String query = "SELECT * FROM Transaccion";
        String[] cols = new String[]{"type", "name", "value", "date", "amount"};
        Cursor rows = db.query(true,"Transaccion", cols, null, null, null, null, null, null);
        Set<Activity> transactions = new HashSet();

        if(rows.moveToFirst()) {
            do {

                int typeIndex = rows.getColumnIndex("type");
                String type = rows.getString(typeIndex);

                int nameIndex = rows.getColumnIndex("name");
                String name = rows.getString(nameIndex);

                int valueIndex = rows.getColumnIndex("value");
                float value = rows.getFloat(valueIndex);

                int dateIndex = rows.getColumnIndex("date");
                String date = rows.getString(dateIndex);

                int amountIndex = rows.getColumnIndex("amount");
                float amount = rows.getFloat(amountIndex);

                Activity activity = new Activity(type, name, value, date, amount);

                transactions.add(activity);
            } while (rows.moveToNext());
        }

        return transactions;
    }
}
