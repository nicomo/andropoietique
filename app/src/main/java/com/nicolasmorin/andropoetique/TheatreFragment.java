package com.nicolasmorin.andropoetique;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nicolas morin on 07/09/2014
 * Copyright nicolas morin 2014
 * Licensed under the Apache Software Foundation License, version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class TheatreFragment extends Fragment {
    int mNum;

    static TheatreFragment init(int num) {
        TheatreFragment theatreFragment = new TheatreFragment();
        // supply val as an arg
        Bundle args = new Bundle();
        args.putInt("num", num);
        theatreFragment.setArguments(args);
        return theatreFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_theatre, container, false);
        View tv = layoutView.findViewById(R.id.theatre_text);

        // TODO setText from array of strings for each verse
        ((TextView) tv).setText("text fragment #" + mNum);
        return layoutView;
    }
}
