package com.hector.mycryptotracker.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hector.mycryptotracker.SQLiteHelpers.TransactionSQLiteHelper;
import com.hector.mycryptotracker.entities.Activity;

import java.util.List;
import java.util.Set;


public class TransactionController {

    private SQLiteDatabase db;
    private static TransactionSQLiteHelper helper;

    public SQLiteDatabase getDb() {
        return helper.getWritableDatabase();
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public static TransactionSQLiteHelper getHelper() {
        return helper;
    }

    public static void setHelper(TransactionSQLiteHelper helper) {
        TransactionController.helper = helper;
    }

    public TransactionController(Context context) {
        setHelper(new TransactionSQLiteHelper(context, "Transaction", null, 1));
        setDb(db);
    }

    public void createTransaction(String type, String name, float value, String date, float amount) {
        getHelper().createTransaction(getDb(), type, name, value, date, amount);
    }

    public Set<Activity> getTransactions(){
        return getHelper().getTransactions(getDb());
    }

    public void deleteTransaction(Activity activity) {
        getHelper().deleteTransaction( getDb(), activity.getType(), activity.getName(),
                activity.getValue(), activity.getDate(), activity.getAmount());
    }

    public Activity createEmptyActivity(){
        return new Activity(null,null,0,null, 0);
    }
}
