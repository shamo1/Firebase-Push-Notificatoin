package com.example.firebasepushnotification.Notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=copy you oen key here"
            }
    )
    @POST("fcm/send")
    Call<REsponce> sendNotification(@Body Sender body);
}
