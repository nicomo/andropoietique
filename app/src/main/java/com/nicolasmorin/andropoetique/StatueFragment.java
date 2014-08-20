package com.nicolasmorin.andropoetique;

/**
 * Created by nicolas morin on 17/08/2014
 */

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StatueFragment extends Fragment {

    private static final String TAG = StatueFragment.class.getName();
    private ArrayAdapter<String> mStatueAdapter;

    public StatueFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statue, container, false);

        // Statue poem lines to be displayed in list
        // defined in res/raw/statue_poem

        List<String> myStatueList = new ArrayList<String>();
        try {
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.statue_poem)));
            String line;
            while ((line = jsonReader.readLine()) != null) {
                myStatueList.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mStatueAdapter = new ArrayAdapter<String>(
                getActivity(), // context
                R.layout.list_item_statue, // layout
                R.id.list_item_statue_textview, // id of textview to populate
                myStatueList // data to populate the view
        );

        // bind the adapter we just created to the listview that should display it
        ListView listView = (ListView) rootView.findViewById(R.id.listview_statue);
        listView.setAdapter(mStatueAdapter);

        // setting up sound to be played later on Gesture Detection
        final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.whispering);






        // Gesture detector setup
        final GestureDetector gesture = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                Log.v(TAG, "onDown called");
                // play sound
                mediaPlayer.start();
                return true;
            }

        });

        // gesture detection, firing
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View listView, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        return rootView;
    }
}