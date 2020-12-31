package com.arik.AcademicActivityManager;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Arik on 02/15/18.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("fire","my token"+token);
         regtok(token);

        // If you want to tsend messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
    }
    private void regtok(String token)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token",token)
                .build();
        Request req = new Request.Builder()
                .url("http://rococo-leaf.000webhostapp.com/Reg.php")
                .post(body)
                .build();
        try {
            client.newCall(req).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
