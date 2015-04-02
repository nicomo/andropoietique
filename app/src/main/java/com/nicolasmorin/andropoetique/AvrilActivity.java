package com.nicolasmorin.andropoetique;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class AvrilActivity extends Activity {
    private static final String TAG = AvrilActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avril);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_avril, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_avril, container, false);

            final TextView avril_txt = (TextView) rootView.findViewById(R.id.avril_txt);

            rootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                    Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
                    int action = MotionEventCompat.getActionMasked(event);
                    switch (action){
                        case (MotionEvent.ACTION_DOWN):
                            Log.v(TAG, "Action was DOWN");
                            avril_txt.startAnimation(in);
                            avril_txt.setVisibility(View.VISIBLE);
                            return true;
                        case (MotionEvent.ACTION_UP):
                            Log.v(TAG, "Event was UP");
                            avril_txt.startAnimation(out);
                            avril_txt.setVisibility(View.INVISIBLE);
                            return true;
                    }
                    return false;
                }
            });
            return rootView;
        }
    }

}
