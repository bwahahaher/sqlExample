package com.example.sql_example.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sql_example.domain.User;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseRepository {
    DatabaseHelper databaseHelper;
    public DatabaseRepository(Context context) {
        initDb(context);
    }

    private void initDb(Context context) {
        databaseHelper = new DatabaseHelper(context, "UserDb", null, 1);
    }

    public boolean insertUser(String name, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if (getUser(name, password) != null) {
            return false;
        } else {
            db.execSQL(SQLScripts.insertUserScript(name, password));
            return true;
        }
    }
    public boolean insertFriend(int firstId, int secondId, String request){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL(SQLScripts.insertFriendScript(firstId, secondId, request));
        return true;
    }

    public User getUser(String name, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor userCursor = db.rawQuery(SQLScripts.getUserScript(name, password), null);
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");

            do {
                // получаем значения по номерам столбцов
                User user = new User(userCursor.getString(idColIndex),
                        userCursor.getString(nameColIndex),
                        userCursor.getString(passwordColIndex));
                userCursor.close();
                return user;
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
        } else {
            userCursor.close();
            return null;
        }
    }
    public User getName(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.SignUp(id), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");

            do {
                // получаем значения по номерам столбцов
                User user = new User(userCursor.getString(idColIndex),
                        userCursor.getString(nameColIndex),
                        userCursor.getString(passwordColIndex));
                userCursor.close();
                return user;
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
        } else {
            userCursor.close();
            return null;
        }
    }


    public String LoginCheck(String name) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.TestOutput(name), null);
        if (userCursor.moveToFirst()) {
            int nameColIndex = userCursor.getColumnIndex("name");
            do {
                String userLog;
                userLog=userCursor.getString(nameColIndex);
                userCursor.close();
                return userLog;
            } while (userCursor.moveToNext());
        } else {
            userCursor.close();
            return null;
        }
    }

    public ArrayList<User> getUsers(int limit) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.getAllUsersScript(limit), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");

            ArrayList<User> userList = new ArrayList();
            do {
                // получаем значения по номерам столбцов
                User user = new User(userCursor.getString(idColIndex),
                        userCursor.getString(nameColIndex),
                        userCursor.getString(passwordColIndex));
                userList.add(user);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
            userCursor.close();
            return userList;
        } else {
            userCursor.close();
            return null;
        }
    }
    public HashMap<Integer, String> getFriends(int my_id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.getMyFriends(my_id), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int userId = userCursor.getColumnIndex("secondUserId");
            int Confirm = userCursor.getColumnIndex("isConfirm");

            HashMap<Integer, String> MyFriends = new HashMap<>();
            do {
                int friendId = Integer.parseInt(userCursor.getString(userId));
                String isConfirm = userCursor.getString(Confirm);
                MyFriends.put(friendId, isConfirm);
            } while (userCursor.moveToNext());
            userCursor.close();
            return MyFriends;
        } else {
            userCursor.close();
            return null;
        }
    }

    public ArrayList<User> getAllUsers() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.allUsers(), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");

            ArrayList<User> allUserList = new ArrayList();
            do {
                // получаем значения по номерам столбцов
                User user = new User(userCursor.getString(idColIndex),
                        userCursor.getString(nameColIndex),
                        userCursor.getString(passwordColIndex));
                allUserList.add(user);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
            userCursor.close();
            return allUserList;
        } else {
            userCursor.close();
            return null;
        }
    }
}
