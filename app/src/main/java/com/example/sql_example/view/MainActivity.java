package com.example.sql_example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sql_example.R;
import com.example.sql_example.domain.User;
import com.example.sql_example.interactor.UsersInteractor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView inputLogin;
    EditText inputPassword;
    Button buttonLogin;
    Button buttonRegister;
    TextView test;
    UsersInteractor usersInteractor;

    ArrayList<User> lastFiveUsersList = new ArrayList<>();
    ArrayList<String> lastFiveNamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        test=findViewById(R.id.textView);
        init();
        initListeners();

    }


    private void initListeners() {
        inputLogin.setOnItemClickListener((parent, view, position, id) -> {
            inputPassword.setText(lastFiveUsersList.get(position).password);
        });

        buttonLogin.setOnClickListener((v) -> {
            final String login = inputLogin.getText().toString();
            final String password = inputPassword.getText().toString();
            User user = usersInteractor.getUser(login, password);
            if (user != null) {
                Toast.makeText(this, "Вход выполнен", Toast.LENGTH_SHORT).show();
                  SignUp(user.id);
            } else {
                Toast.makeText(this, "Логин или пароль неверны", Toast.LENGTH_SHORT).show();
            }
        });
        buttonRegister.setOnClickListener((v) -> {
            final String login = inputLogin.getText().toString();
            final String password = inputPassword.getText().toString();
            if ( usersInteractor.LoginCheck(login)==null && usersInteractor.insertUser(login, password)) {
                Toast.makeText(this, "Пользователь зарегестрирован", Toast.LENGTH_SHORT).show();
                fillInputLogin();
            } else {
                Toast.makeText(this, "Логин уже ипользуется", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillInputLogin() {
        lastFiveUsersList = usersInteractor.getUsers(5);

        if (lastFiveUsersList != null) {
            lastFiveNamesList.clear();
            for (User user : lastFiveUsersList) {
                lastFiveNamesList.add(user.name);
            }
            inputLogin.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line,
                    lastFiveNamesList));
        }
    }

    private void init() {
        inputLogin = findViewById(R.id.input_login);
        inputPassword = findViewById(R.id.input_pass);
        buttonLogin = findViewById(R.id.loginButton);
        buttonRegister = findViewById(R.id.registerButton);
        usersInteractor = new UsersInteractor(this);
        fillInputLogin();
    }
    private void SignUp(String id){
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

}
