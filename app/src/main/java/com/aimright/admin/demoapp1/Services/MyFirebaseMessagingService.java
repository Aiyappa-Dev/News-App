package com.aimright.admin.demoapp1.Services;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.adimoro.business.adimorosdk.provider.InfomoNotificationHandler;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.aimright.admin.demoapp1.R;
/*import com.thedascapital.www.newsapp.R;*/

import java.util.Objects;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static String TAG = "FCM";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //remoteMessage.getNotification().getTitle();
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.e("meesage",remoteMessage.getNotification().getBody());
        showNotification(Objects.requireNonNull(remoteMessage.getNotification()).getTitle(), remoteMessage.getNotification().getBody());

        InfomoNotificationHandler handler = InfomoNotificationHandler.getInstance(this.getApplicationContext());
        handler.handlePushNotification(remoteMessage);
    }

    public void showNotification(String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotification")
                                                .setContentTitle(title)
                                                .setSmallIcon(R.drawable.infomo)
                                                .setAutoCancel(true)
                                                .setContentText(message);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(999, builder.build());
    }


}
