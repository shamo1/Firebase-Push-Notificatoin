package com.example.firebasepushnotification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText edt_email, edt_pass;
    Button btn_login;
    ProgressBar progbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkCurrentUser();
        edt_email = findViewById(R.id.edtEmail);
        edt_pass = findViewById(R.id.edtpass);
        btn_login = findViewById(R.id.btnLogin);
        progbar = findViewById(R.id.progbar);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progbar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signInWithEmailAndPassword(edt_email.getText().toString(), edt_pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("All User");
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("key", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(map)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    progbar.setVisibility(View.GONE);
                                                    startActivity(new Intent(MainActivity.this, AllUsers.class));
                                                }
                                            });
                                } else {
                                    progbar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void checkCurrentUser() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, AllUsers.class));
        }
    }
}