package com.example.firebasepushnotification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasepushnotification.Addaptor.UserAdaptor;
import com.example.firebasepushnotification.Models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllUsers extends AppCompatActivity {
    DatabaseReference dbref;
    RecyclerView recyclerView;
    UserAdaptor userAdaptor;
    ArrayList<UserModel> userModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbref = FirebaseDatabase.getInstance().getReference("All User");
        userModels = new ArrayList<>();
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        UserModel userModel = ds.getValue(UserModel.class);
                        userModels.add(userModel);
                    }
                    userAdaptor = new UserAdaptor(AllUsers.this, userModels);
                    recyclerView.setAdapter(userAdaptor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}