package com.nicolasmorin.andropoetique;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/*
HORRIBLE HORRIBLE CODE - TODO CLEAN THAT MESS UP
 */
public class GuideVoyageActivity extends Activity {

    private static final String TAG = GuideVoyageActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_voyage);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guide_voyage, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_guide_voyage, container, false);

            final View myLayout = rootView.findViewById(R.id.guide_voyage_linearlayout);
            final TextView title = (TextView) rootView.findViewById(R.id.guide_voyage_title);
            final TextView verse1 = (TextView) rootView.findViewById(R.id.guide_voyage_1);
            final TextView verse2 = (TextView) rootView.findViewById(R.id.guide_voyage_2);
            final TextView verse3 = (TextView) rootView.findViewById(R.id.guide_voyage_3);
            final TextView verse4 = (TextView) rootView.findViewById(R.id.guide_voyage_4);
            final TextView verse5 = (TextView) rootView.findViewById(R.id.guide_voyage_5);
            final TextView verse6 = (TextView) rootView.findViewById(R.id.guide_voyage_6);
            final TextView verse7 = (TextView) rootView.findViewById(R.id.guide_voyage_7);
            final TextView verse8 = (TextView) rootView.findViewById(R.id.guide_voyage_8);



            // TODO detect onDown
            // Gesture detector setup
            final GestureDetector gesture = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onDown(MotionEvent e) {
                    Log.v(TAG, "onDown called");

                    Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                    Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);

                    if (verse1.getVisibility() == View.INVISIBLE) {
                        myLayout.setBackgroundColor(getResources().getColor(R.color.pink_1));
                        title.setTextColor(getResources().getColor(android.R.color.white));
                        verse1.startAnimation(in);
                        verse1.setVisibility(View.VISIBLE);
                        verse1.setBackgroundColor(getResources().getColor(R.color.blue_1));
                    } else if (verse2.getVisibility() == View.INVISIBLE) {
                        myLayout.setBackgroundColor(getResources().getColor(R.color.blue_2));
                        verse1.setBackgroundColor(getResources().getColor(R.color.pink_2));
                        verse2.startAnimation(in);
                        verse2.setVisibility(View.VISIBLE);
                        verse2.setBackgroundColor(getResources().getColor(R.color.pink_1));
                    } else if (verse3.getVisibility() == View.INVISIBLE) {
                        myLayout.setBackgroundColor(getResources().getColor(R.color.blue_3));
                        verse1.setBackgroundColor(getResources().getColor(R.color.blue_1));
                        verse2.setBackgroundColor(getResources().getColor(R.color.pink_3));
                        verse3.startAnimation(in);
                        verse3.setVisibility(View.VISIBLE);
                        verse3.setBackgroundColor(getResources().getColor(R.color.pink_4));
                    } else if (verse4.getVisibility() == View.INVISIBLE) {
                        verse1.setBackgroundColor(getResources().getColor(R.color.pink_4));
                        verse2.setBackgroundColor(getResources().getColor(R.color.blue_3));
                        verse3.setBackgroundColor(getResources().getColor(R.color.pink_2));
                        verse4.startAnimation(in);
                        verse4.setVisibility(View.VISIBLE);
                        verse4.setBackgroundColor(getResources().getColor(R.color.blue_1));
                    } else if (verse5.getVisibility() == View.INVISIBLE) {
                        myLayout.setBackgroundColor(getResources().getColor(R.color.blue_1));
                        verse1.setBackgroundColor(getResources().getColor(R.color.blue_2));
                        verse3.setBackgroundColor(getResources().getColor(R.color.blue_4));
                        verse5.startAnimation(in);
                        verse5.setVisibility(View.VISIBLE);
                        verse5.setBackgroundColor(getResources().getColor(R.color.blue_2));
                    } else if (verse6.getVisibility() == View.INVISIBLE) {
                        myLayout.setBackgroundColor(getResources().getColor(R.color.pink_1));
                        verse1.setBackgroundColor(getResources().getColor(R.color.blue_2));
                        verse2.setBackgroundColor(getResources().getColor(R.color.pink_2));
                        verse3.setBackgroundColor(getResources().getColor(R.color.pink_3));
                        verse4.setBackgroundColor(getResources().getColor(R.color.pink_1));
                        verse5.setBackgroundColor(getResources().getColor(R.color.pink_4));
                        verse6.startAnimation(in);
                        verse6.setVisibility(View.VISIBLE);
                        verse6.setBackgroundColor(getResources().getColor(R.color.pink_2));
                    } else if (verse7.getVisibility() == View.INVISIBLE) {
                        myLayout.setBackgroundColor(getResources().getColor(R.color.pink_4));
                        verse3.setBackgroundColor(getResources().getColor(R.color.blue_4));
                        verse4.setBackgroundColor(getResources().getColor(R.color.pink_2));
                        verse5.setBackgroundColor(getResources().getColor(R.color.blue_1));
                        verse6.setBackgroundColor(getResources().getColor(R.color.pink_3));
                        verse7.startAnimation(in);
                        verse7.setVisibility(View.VISIBLE);
                        verse7.setBackgroundColor(getResources().getColor(R.color.pink_2));
                    } else if (verse8.getVisibility() == View.INVISIBLE) {
                        myLayout.setBackgroundColor(getResources().getColor(R.color.blue_4));
                        verse1.setBackgroundColor(getResources().getColor(R.color.blue_2));
                        verse2.setBackgroundColor(getResources().getColor(R.color.pink_2));
                        verse3.setBackgroundColor(getResources().getColor(R.color.blue_4));
                        verse4.setBackgroundColor(getResources().getColor(R.color.pink_2));
                        verse5.setBackgroundColor(getResources().getColor(R.color.blue_1));
                        verse6.setBackgroundColor(getResources().getColor(R.color.pink_3));
                        verse7.setBackgroundColor(getResources().getColor(R.color.pink_2));
                        verse8.startAnimation(in);
                        verse8.setVisibility(View.VISIBLE);
                        verse8.setBackgroundColor(getResources().getColor(R.color.blue_1));
                    } else if (verse8.getVisibility() == View.VISIBLE) {
                        rootView.findViewById(R.id.guide_voyage_linearlayout).setBackgroundColor(getResources().getColor(android.R.color.white));
                        title.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                        verse1.startAnimation(out);
                        verse1.setVisibility(View.INVISIBLE);
                        verse2.startAnimation(out);
                        verse2.setVisibility(View.INVISIBLE);
                        verse3.startAnimation(out);
                        verse3.setVisibility(View.INVISIBLE);
                        verse4.startAnimation(out);
                        verse4.setVisibility(View.INVISIBLE);
                        verse5.startAnimation(out);
                        verse5.setVisibility(View.INVISIBLE);
                        verse6.startAnimation(out);
                        verse6.setVisibility(View.INVISIBLE);
                        verse7.startAnimation(out);
                        verse7.setVisibility(View.INVISIBLE);
                        verse8.startAnimation(out);
                        verse8.setVisibility(View.INVISIBLE);
                    }


                    // TODO on 1st onDown, set title to textColor white
                    // + background color to 1 of the color codes
                    // change visibility of 1st line

                    // TODO On every onDown after that,
                    // change background color + visibility of next line

                    // TODO after last line, restore initial state : title blue, background white, verses hidden

                    return true;
                }

            });

            // gesture detection, firing
            rootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    return gesture.onTouchEvent(event);
                }
            });

















            return rootView;

        }
    }
}
