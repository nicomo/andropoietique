package com.nicolasmorin.andropoetique;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by nicolas morin on 27/08/2014
 * Copyright nicolas morin 2014
 * Licensed under the Apache Software Foundation License, version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class ChairCentreNotificationReceiver extends BroadcastReceiver {

    private static final String TAG = ChairCentreNotificationReceiver.class.getSimpleName();

    // Notification Action Elements
    private static final int MY_NOTIFICATION_ID = 1;
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    // Notification Text Elements
    private final CharSequence tickerText = "Livraison d'un poème";
    private final CharSequence contentTitle = "Andropoïétique";
    private final CharSequence contentText = "Tout est chair en mon propre sens";

    // get sharedpref ChairCentreNotif
    private SharedPreferences prefs;
    private Boolean chairCentreNotif;
    // TODO unregister the Receiver after first use (in activity's onPause()) rather than manage through a sharedpref

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v(TAG, "MyBroadcastReceiver loaded from activity");

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        chairCentreNotif = prefs.getBoolean("chairCentreNotif", true);

        if (chairCentreNotif == true) {

            // create intent
            mNotificationIntent = new Intent(context, ChairCentreActivity.class);
            // wrap intent in a pending intent
            mContentIntent = PendingIntent.getActivity(context, 0,
                    mNotificationIntent, PendingIntent.FLAG_ONE_SHOT);

            // Build the notification
            android.app.Notification.Builder notificationBuilder = new android.app.Notification.Builder(
                    context).setTicker(tickerText)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setAutoCancel(true).setContentTitle(contentTitle)
                    .setContentText(contentText).setContentIntent(mContentIntent);

            // Pass the Notification to the NotificationManager and notify
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(MY_NOTIFICATION_ID,
                    notificationBuilder.build());

            // set sharedpref ChairCentre to false;
            chairCentreNotif = false;
            SharedPreferences.Editor editorPref = prefs.edit();
            editorPref.putBoolean("chairCentreNotif", chairCentreNotif);
            editorPref.apply();

        }
    }
}