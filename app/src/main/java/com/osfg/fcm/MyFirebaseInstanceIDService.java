package com.osfg.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.osfg.fcmregistrationsample.FCMMainActivity;

/**
 * Created by athakur on 12/3/16.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String loggerName = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(loggerName, "FCM token refreshed: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        sendFcmTokenToServer(refreshedToken);

    }

    private void sendFcmTokenToServer(String token) {
        Log.d(loggerName, "Sending FCM refresh token to server");
    }
}
