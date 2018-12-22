import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import predic.com.authentic.MainActivity;
import predic.com.authentic.R;

public class firebasemsghandler extends FirebaseMessagingService {

    private static final String TAG = "firebasemsghandler";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            showNotification(remoteMessage.getNotification().getBody());
        }


    }

    public void showNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent=PendingIntent.getActivity(this,(int)System.currentTimeMillis(),intent,0);
        Notification.Builder builder=new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(message)
                .setSmallIcon(R.drawable.cric)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            builder.setChannelId("43002");
        NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel= new NotificationChannel("43002","ChannelName",NotificationManager.IMPORTANCE_HIGH);
            if (notificationManager!= null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Notification n = builder.build();
        if(notificationManager != null){
            notificationManager.notify(0,n);
        }

    }
}

