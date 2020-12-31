package com.arik.AcademicActivityManager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        shownot(remoteMessage.getData().get("message"));
      super.onMessageReceived(remoteMessage);
      sendnot(remoteMessage.getNotification().getBody());
    }
  private void sendnot(String msg)
  {
      Intent intent = new Intent(this,LoginActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      PendingIntent penin = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
      Uri ring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
      NotificationCompat.Builder noti = new NotificationCompat.Builder(this);
      noti.setSmallIcon(R.drawable.ic_stat_name);
      noti.setContentTitle("New Academic Update");
      noti.setContentText(msg);
      noti.setAutoCancel(true);
      noti.setSound(ring);
      noti.setContentIntent(penin);

      NotificationManager notiman = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
      notiman.notify(0,noti.build());
  }
  private void shownot(String message)
  {
      Intent in = new Intent(this,LoginActivity.class);
      in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      PendingIntent pen = PendingIntent.getActivity(this,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
      NotificationCompat.Builder bil = new NotificationCompat.Builder(this)
              .setAutoCancel(true)
              .setContentTitle("test")
              .setContentText(message)
              .setSmallIcon(R.drawable.ic_stat_name)
              .setContentIntent(pen);

      NotificationManager man = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
      man.notify(0,bil.build());
  }
}
