package com.osfg.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.osfg.utils.CommonUtils;
import com.osfg.utils.StringUtils;

/**
 * Created by athakur on 12/3/16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String loggerName = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(loggerName,"From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData() != null) {
            Log.d(loggerName, "Message data payload: " + remoteMessage.getData());

            String title = remoteMessage.getData().get("messageTitle");
            String body = remoteMessage.getData().get("messageBody");

            if(StringUtils.hasText(title) && StringUtils.hasText(body))
            {
                Log.d(loggerName, "Showing notification for message");
                CommonUtils.showNotification(title, body,this);
            }

        }

        // Check if message contains a notification payload. Notification should automatically come up
        if (remoteMessage.getNotification() != null) {
            Log.d(loggerName, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}