package com.nicolasmorin.andropoetique;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by nicolas morin on 07/09/2014
 * Copyright nicolas morin 2014
 * Licensed under the Apache Software Foundation License, version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class TheatreListFragment extends ListFragment {
    int fragNum;
    String arr[] = { "This is", "a Truiton", "Demo", "App", "For", "Showing",
            "FragmentPagerAdapter", "and ViewPager", "Implementation" };

    static TheatreListFragment init(int val) {
        TheatreListFragment tList = new TheatreListFragment();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        tList.setArguments(args);

        return tList;
    }

    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_theatre_list,
                container, false);
        View tv = layoutView.findViewById(R.id.theatre_text);
        ((TextView) tv).setText("Theatre Fragment #");
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arr));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("Theatre FragmentList", "Item clicked: " + id);
    }
}
