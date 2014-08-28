package com.nicolasmorin.andropoetique;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Random;


public class ChairCentreAlarm extends Activity {


    private Intent mNotificationReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent;
    // AlarmManager and intents to be used in the alarm
    private AlarmManager mAlarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair_centre_alarm);

        // set time in random number in next 24 hours
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        int rand = random.nextInt(24 - 1 + 1) + 1;
        calendar.add(Calendar.HOUR_OF_DAY, rand);

        // Create PendingIntent to start the AlarmNotificationReceiver
        mNotificationReceiverIntent = new Intent(this, ChairCentreNotificationReceiver.class);
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(this, 1, mNotificationReceiverIntent, 0);

        // Get the AlarmManager Service
        mAlarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // Set the alarm
        mAlarmManager.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                mNotificationReceiverPendingIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chair_centre_alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
