package com.osfg.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.osfg.fcmregistrationsample.FCMMainActivity;
import com.osfg.fcmregistrationsample.R;

/**
 * Created by athakur on 12/3/16.
 */
public class CommonUtils {

    private static final String TAG = CommonUtils.class.getSimpleName();

    public static void showNotification(String messageBody, Context context) {
        showNotification("Notification received from Server", messageBody, context);
    }

    public static void showNotification(String messageTitle, String messageBody,  Context context) {

        Log.d(TAG,"Showing notiifcation");

        Intent intent = new Intent(context, FCMMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new Notification();

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setSound(defaultSoundUri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public static void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
        showToast(context,"Copied FCM Reg Id to clipboard",true);
    }

    public static void showToast(Context context, String toastMessage, boolean isShort) {
        Toast.makeText(context,toastMessage,isShort?Toast.LENGTH_SHORT:Toast.LENGTH_LONG).show();
    }
}
