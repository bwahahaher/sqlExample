package com.example.sql_example.domain;

public class Friend {
    public final int id;
    public final int firstUserId;
    public final int secondUserId;
    public final boolean isConfirm;


    public Friend(int id, int firstUserId, int secondUserId, boolean isConfirm) {
        this.id = id;
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.isConfirm = isConfirm;
    }
}
