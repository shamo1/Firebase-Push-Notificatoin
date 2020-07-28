package com.example.firebasepushnotification.Notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA0tzPb5Q:APA91bGFoWfZuwe-Zp9KviJChSDZhdd7j5TxSrODM9qbUCfaSu9NYa3cEaNKCD9gM-c2xzKPd0-cbsAVFhyxmGgz1PlQswSraGsyUTLL54G9122RuFgUzOo2UWTqLtywSWrO5KDodXWv"
            }
    )
    @POST("fcm/send")
    Call<REsponce> sendNotification(@Body Sender body);
}
