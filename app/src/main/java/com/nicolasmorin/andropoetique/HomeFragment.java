package com.nicolasmorin.andropoetique;

/**
 * Created by nicolas on 16/08/2014
 */

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A poems fragment containing a simple view, i.e. title of the poem
 * to be displayed as list on home activity
 */
public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getName();
    private ArrayAdapter<String> mPoemsAdapter;
    private SharedPreferences prefs;
    private Boolean ChairCentreNotif;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // hide the action bar on home page
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.hide();
        // poem titles to be displayed in list
        // defined in values/strings.xml
        Resources res = getResources();
        String [] poemsArray = res.getStringArray(R.array.titles_array);

        List<String> poemsList = new ArrayList<String>(
                Arrays.asList(poemsArray)
        );

        mPoemsAdapter = new ArrayAdapter<String>(
            getActivity(), // context
            R.layout.list_item_poems, // layout
            R.id.list_item_poems_textview, // id of textview to populate
            poemsList // data to populate the view
        );

        // bind the adapter we just created to the listview that should display it
        ListView listView = (ListView) rootView.findViewById(R.id.listview_poems);
        listView.setAdapter(mPoemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Intent intent = new Intent();
            switch (position){
                case 0:
                    intent.setClass(getActivity(), StatueActivity.class);
                    break;
                case 1:
                    intent.setClass(getActivity(), ChevalGuerreActivity.class);
                    break;
                case 2:
                    // TODO test
                    // special case : for poem 2 we point to an activity if 1st time
                    // and another otherwise
                    // get preferences
                    prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    ChairCentreNotif = prefs.getBoolean("ChairCentreNotif", true);
                    if (ChairCentreNotif) {
                        intent.setClass(getActivity(), ChairCentreAlarm.class);
                        // update ChairCentreNotif
                        SharedPreferences.Editor editorPref = prefs.edit();
                        editorPref.putBoolean("ChairCentreNotif", false);
                        editorPref.commit();
                    } else {
                        intent.setClass(getActivity(), ChairCentreActivity.class);
                    }
                    break;
            }
            startActivity(intent);
            }
        });

        return rootView;
    }
}