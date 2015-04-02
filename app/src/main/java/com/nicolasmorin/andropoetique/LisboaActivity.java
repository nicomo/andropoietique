package com.nicolasmorin.andropoetique;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class LisboaActivity extends Activity implements SensorEventListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LisboaActivity";

    GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float[] rotationMatrix;
    private float[] orientationValues;
    private int azimuth; // actual orientation of the phone in degrees
    private int bearing; // bearing between phone and lisbon, in degrees
    private int target = 999;

    // hardcoded lat-lng for martinique
    private double startLat = 14.610427;
    private double startLng = -61.097401;
    //private double startLat;
    //private double startLng;

    // lat-lng for Lisbon
    private double lisbonLat = 38.714299;
    private double lisbonLng = -9.138746;

    // location error message
    CharSequence locationError = "Impossible de vous localiser... Où est Lisbonne? Je ne sais pas.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisboa);

        // Toast to hint at how the poem works
        CharSequence toastTxt = "Il faut être tourné vers Lisbonne pour lire ce poème sur la ville.";
        Toast toast = Toast.makeText(this, toastTxt, Toast.LENGTH_LONG);
        toast.show();

        //TODO get user's lat lng
        // Create a GoogleApiClient instance for geolocation
        mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
        mGoogleApiClient.connect();


        // get sensor manager to retrieve the azimuth
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Connected to Google Play services!
        // The good stuff goes here.
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            startLat = mLastLocation.getLatitude();
            startLng = mLastLocation.getLongitude();
            Log.v(TAG, "startLat: " + startLat + " - startLng: " + startLng);
            // we have the phone's position, let's now
            // calculate bearing to lisbon
            getBearing(startLat, startLng, lisbonLat, lisbonLng);
        } else {
            Toast errToast = Toast.makeText(this, "onConnected error", Toast.LENGTH_LONG);
            errToast.show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast errToast = Toast.makeText(this, locationError, Toast.LENGTH_LONG);
        errToast.show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // This callback is important for handling errors that
        // may occur while attempting to connect with Google.
        //
        Toast errToast = Toast.makeText(this, locationError, Toast.LENGTH_LONG);
        errToast.show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
        // TODO Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    public void getBearing(double startLat, double startLng, double lisbonLat, double lisbonLng) {
        // calculate bearing between the phone's current position and lisbon
        double startLatitude = Math.toRadians(startLat);
        double starLongitude = Math.toRadians(startLng);
        double endLatitude = Math.toRadians(lisbonLat);
        double endLongitude =  Math.toRadians(lisbonLng);
        double dLon = endLongitude-starLongitude;
        double x = Math.toDegrees(
                Math.atan2(
                        Math.sin(dLon) * Math.cos(endLatitude),
                        Math.cos(startLatitude) * Math.sin(endLatitude) - Math.sin(startLatitude) * Math.cos(endLatitude) * Math.cos(dLon)
                ));
        bearing = (int) (Math.round(x +360) % 360);
        Log.v(TAG, "bearing: " + bearing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // reference the various textviews
        TextView tv1 = (TextView) findViewById(R.id.lisboa_1);
        TextView tv2 = (TextView) findViewById(R.id.lisboa_2);
        TextView tv3 = (TextView) findViewById(R.id.lisboa_3);
        TextView tv4 = (TextView) findViewById(R.id.lisboa_4);
        TextView tv5 = (TextView) findViewById(R.id.lisboa_5);
        TextView tv6 = (TextView) findViewById(R.id.lisboa_6);
        TextView tv7 = (TextView) findViewById(R.id.lisboa_7);
        TextView tv8 = (TextView) findViewById(R.id.lisboa_8);
        TextView tv9 = (TextView) findViewById(R.id.lisboa_9);
        TextView tv10 = (TextView) findViewById(R.id.lisboa_10);
        TextView tv11 = (TextView) findViewById(R.id.lisboa_11);
        TextView tv12 = (TextView) findViewById(R.id.lisboa_12);

        // calculate the azimuth from phone position in space using rotation vector
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            rotationMatrix = new float[9];
            orientationValues = new float[3];
            // calculate rotation matrix
            mSensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
            // get the azimuth value (orientation[0]) in degrees
            azimuth = (int) (Math.toDegrees(mSensorManager.getOrientation(rotationMatrix, orientationValues)[0])+360) % 360;
            target = Math.abs(azimuth - bearing);

            if(target < 15) {
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.VISIBLE);
                if(target<10){
                    tv5.setVisibility(View.VISIBLE);
                    tv6.setVisibility(View.VISIBLE);
                    tv7.setVisibility(View.VISIBLE);
                    tv8.setVisibility(View.VISIBLE);
                    if (target < 5) {
                        tv9.setVisibility(View.VISIBLE);
                        tv10.setVisibility(View.VISIBLE);
                        tv11.setVisibility(View.VISIBLE);
                        tv12.setVisibility(View.VISIBLE);
                    }
                }
            } else if(target > 15) {
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.INVISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                tv4.setVisibility(View.INVISIBLE);
                tv5.setVisibility(View.INVISIBLE);
                tv6.setVisibility(View.INVISIBLE);
                tv7.setVisibility(View.INVISIBLE);
                tv8.setVisibility(View.INVISIBLE);
                tv9.setVisibility(View.INVISIBLE);
                tv10.setVisibility(View.INVISIBLE);
                tv11.setVisibility(View.INVISIBLE);
                tv12.setVisibility(View.INVISIBLE);

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lisboa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
