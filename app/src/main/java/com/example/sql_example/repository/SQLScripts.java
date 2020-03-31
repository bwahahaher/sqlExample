package com.example.sql_example.repository;

public class SQLScripts {
    public static String initDbScript() {
        return "create table user(" +
                "id integer primary key autoincrement not null," +
                "name text primary key not null," +
                "password text not null" +
                ");";
    }
    public static String initFriendship(){
        return "create table friendship(" +
                "id integer primary key autoincrement" +
                "firstUserId integer not null," +
                "secondUserId integer not null," +
                "isConfirm boolean not null);";

    }
    public static String insertFriendScript(int id1, int id2) {
        int firstId = id1;
        int secondId = id2;

        return "insert into friendship" +
                "(firstUserId, secondUserId, isConfirm)" +
                "values" +
                "(" + firstId + "," + secondId + "," + true +
                ");";

    }
    public static String insertUserScript(String name, String password) {
        String _name = "\"" + name + "\"";
        String _password = "\"" + password + "\"";

        return "insert into user" +
                "(name, password)" +
                "values" +
                "(" + _name + "," + _password +
                ");";

    }
    public static String TestOutput(String name) {
        String _name = "\"" + name + "\"";


        return "select * from user" +
                " where name = " + _name +

                ";";
    }

    public static String getUserScript(String name, String password) {
        String _name = "\"" + name + "\"";
        String _password = "\"" + password + "\"";

        return "select * from user" +
            " where name = " + _name +
            " and password = " + _password +
            ";";
}



    public static String getAllUsersScript(int limit) {
        return "select * from user" +
                " limit " + limit +
                ";";
    }
    public static String SignUp(int id) {

        return "select * from user" +
                " where id = " + id +
                ";";
    }
    public static String allUsers(){
        return "select * from user;";
    }

    public static String getMyFriends(int my_id) {
        return "select * from friendship where firstUserId = " + my_id + ";";
    }
}