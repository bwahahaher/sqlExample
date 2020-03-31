package com.example.sql_example.interactor;

import android.content.Context;

import com.example.sql_example.domain.User;
import com.example.sql_example.repository.DatabaseRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersInteractor {
    DatabaseRepository repository;

    public UsersInteractor(Context context) {
        repository = new DatabaseRepository(context);
    }

    public boolean insertUser(String name, String password) {
        return repository.insertUser(name, password);
    }
    public boolean insertFriend(int i1, int id2, String request) {
        return repository.insertFriend(i1, id2, request);
    }

    public User getUser(String name, String password) {
        return repository.getUser(name, password);
    }
    public User getUserName(int id) {
        return repository.getName(id);
    }
    public String LoginCheck(String login) {
        return repository.LoginCheck(login);
    }

    public ArrayList<User> getUsers(int limit) {
        return repository.getUsers(limit);
    }
    public HashMap<Integer, String> getFriends(int myId){
        return repository.getFriends(myId);
    }
    public ArrayList<User> getAllUsers() {
        return repository.getAllUsers();
    }
}
