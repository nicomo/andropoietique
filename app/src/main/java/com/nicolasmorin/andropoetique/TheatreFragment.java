package com.nicolasmorin.andropoetique;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nicolas morin on 07/09/2014
 * Copyright nicolas morin 2014
 * Licensed under the Apache Software Foundation License, version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class TheatreFragment extends Fragment {

    private static final String TAG = TheatreFragment.class.getName();
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

        // set both top and bottom images dynamically for each fragment in the pager
        View img_top = layoutView.findViewById(R.id.theatre_img_top);
        View img_bottom = layoutView.findViewById(R.id.theatre_img_bottom);

        int img_top_id = getResources().getIdentifier("theatre_img_top_" + mNum, "drawable", "com.nicolasmorin.andropoetique");
        int img_bottom_id = getResources().getIdentifier("theatre_img_bottom_" + mNum, "drawable", "com.nicolasmorin.andropoetique");
        Log.v(TAG, "img_top_id is " + img_top_id);
        Log.v(TAG, "img_bottom_id is " + img_bottom_id);

        ((ImageView) img_top).setImageResource(img_top_id);
        ((ImageView) img_bottom).setImageResource(img_bottom_id);

        // setText from array of strings for each verse
        View tv = layoutView.findViewById(R.id.theatre_text);

        String [] theatreVerses = {
                "La guerre est un cheval",
                "dire deux fois la guerre\n" +
                        "cheval cloué au tronc de l’homme",
                "La guerre est un cheval",
                "un tronc cloué à la mer\n" +
                        "du monde enveloppé de fibres",
                "l’aigle du monde plane chaque jour",
                "sur la hideur des animaux\n" +
                        "deux fois dénombrés, un homme, un tronc",
                "l’aigle du monde est un cheval coupant",
                "qui renverse l’homme\n" +
                        "par dessus les répétitions",
                "un homme, un tronc, son contenu",
                "à l’arbre du monde pendu\n" +
                        "arbre du monde répété poétique",
                "qui s’aligne devant lui-même",
                "au sommet de la guerre pendu\n" +
                        "avec ses étages ses degrés",
                "l’oubli n’est pas une image mais une barque",
                "où montent les nations\n" +
                        "dont chutent les chevaux",
                "ils broutent cinq siècles dans la mer",
                "où se déforment troncs et hommes\n" +
                        "qu’une guerre porte à eux-mêmes",
                "ils mangent la mort cinq fois cet hiver",
                "avec des fusillades comme des bouquets\n" +
                        "portés à la boutonnière",
                "la mort est un cheval mécanique",
                "comme une pantoufle qu’on enfile\n" +
                        "d’entrailles et de sang doublée",
                "regarde, regarde la forme",
                "de l’homme, de l’homme et de l’homme\n" +
                        "dans ses vêtements de jour",
                "regarde le cheval de chaque jour",
                "fondre sur l’épée comme la main\n" +
                        "au travers de sa classe",
                "la guerre est un tronc",
                "au travers des fleuves passé\n" +
                        "pour que les hommes flottent",
                "leur exaltante paresse",
                "en dépit de toute certitude\n" +
                        "la guerre est un animal de mes mains."
        };

        ((TextView) tv).setText(theatreVerses[mNum]);
        return layoutView;
    }
}
