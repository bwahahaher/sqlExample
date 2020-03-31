package com.example.sql_example.view;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sql_example.R;
import com.example.sql_example.domain.Friend;
import com.example.sql_example.domain.User;
import com.example.sql_example.interactor.UsersInteractor;

import java.util.ArrayList;


import java.util.ArrayList;
import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    UsersInteractor usersInteractor;
    Button backButton;
    Button button;
    TextView HiMyNameIs;
    public int usId;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            ArrayList<User> allUsersList;
            Intent getIntent=getIntent();
            usId = Integer.parseInt(getIntent.getStringExtra("id"));
            button = findViewById(R.id.button);
            HiMyNameIs=findViewById(R.id.userNameId);
            usersInteractor = new UsersInteractor(this);
            allUsersList = usersInteractor.getAllUsers();
            HashMap<Integer, String> myFriends = new HashMap<>();
//            usersInteractor.insertFriend(usId,1);
            backButton=findViewById(R.id.backToLogin);

//            myFriends=usersInteractor.getFriends(usId);
            User me = usersInteractor.getUserName(usId);
            HiMyNameIs.setText(me.name);



            com.example.sql_example.view.RecyclerAdapter userAdapter = new com.example.sql_example.view.RecyclerAdapter(allUsersList);
            RecyclerView contactList = findViewById(R.id.contactRecyclerView);

//        contactList.setLayoutManager(new LinearLayoutManager(this));
            contactList.setLayoutManager(new GridLayoutManager(this, 1));
            contactList.setAdapter(userAdapter);

        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<com.example.sql_example.view.ContactVH> {
        final ArrayList<User> userList;


        public RecyclerAdapter(ArrayList<User> userList) {
            this.userList = userList;
        }



        @NonNull
        @Override
        public com.example.sql_example.view.ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
            return new com.example.sql_example.view.ContactVH(contactView);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.sql_example.view.ContactVH holder, int position) {
            User user = userList.get(position);
            holder.bind(user);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }

    class ContactVH extends RecyclerView.ViewHolder {
        TextView name;
        Button button;

        public ContactVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            button = itemView.findViewById(R.id.button);


        }

        public void bind(User user) {
            name.setText(user.name);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    button.setText("Заявка отправлена!");
                }
            });


        }
}
