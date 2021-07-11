package com.example.accountapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class UserOperator {
    private UserDBHelper userDBHelper;
    private SQLiteDatabase db;

    public UserOperator(Context context) {
        userDBHelper = new UserDBHelper(context, "db_user", null, 1);
        db = userDBHelper.getWritableDatabase();
    }

    public void addUser(User bean) {
        db.execSQL("insert into User(username, password) values(?, ?)", new Object[]{bean.getUsername(), bean.getPassword()});
    }

    public void updateUser(User bean) {
        db.execSQL("update User set password = ? where username = ?", new Object[]{bean.getPassword(), bean.getUsername()});
    }

    public void deleteUser(User bean) {
        db.execSQL("delete from User where username=?", new Object[]{bean.getUsername()});
    }

    public User isExit(String name) {
        User bean = null;
        Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{name});
        while (cursor.moveToNext()) {
            bean = new User();
            bean.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            bean.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        cursor.close();
        return bean;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from User", null);
        while (cursor.moveToNext()) {
            User bean = new User();
            bean.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            bean.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            list.add(bean);
        }
        cursor.close();
        return list;
    }
}
