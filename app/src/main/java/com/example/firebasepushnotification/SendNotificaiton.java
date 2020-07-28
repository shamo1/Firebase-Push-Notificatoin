package com.example.firebasepushnotification;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasepushnotification.Notifications.APIService;
import com.example.firebasepushnotification.Notifications.Client;
import com.example.firebasepushnotification.Notifications.Data;
import com.example.firebasepushnotification.Notifications.REsponce;
import com.example.firebasepushnotification.Notifications.Sender;
import com.example.firebasepushnotification.Notifications.Tokens;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendNotificaiton extends AppCompatActivity {
    EditText edtTitle, edtMessage;
    String key;
    APIService apiService;
    Button btnsent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notificaiton);
        edtTitle = findViewById(R.id.edtTitle);
        edtMessage = findViewById(R.id.edtMesage);
        btnsent = findViewById(R.id.btnSend);
        key = getIntent().getStringExtra("key");
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        btnsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Tokens").child(key).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String usertoken = dataSnapshot.getValue(String.class);
                        sendNotifications(usertoken, edtTitle.getText().toString().trim(), edtMessage.getText().toString().trim());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        updateToken();
    }


    private void updateToken() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Tokens token = new Tokens(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    private void sendNotifications(String usertoken, String title, String mesage) {
        Data data = new Data(title, mesage);
        Sender sender = new Sender(data, usertoken);
        apiService.sendNotification(sender).enqueue(new Callback<REsponce>() {
            @Override
            public void onResponse(Call<REsponce> call, Response<REsponce> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(SendNotificaiton.this, "Notificaiton Not send", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<REsponce> call, Throwable t) {

            }
        });
    }


}