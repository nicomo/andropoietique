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

        // set image dynamically for each fragment in the pager
        View img = layoutView.findViewById(R.id.theatre_img);

        int img_id = getResources().getIdentifier("theatre_img_" + mNum, "drawable", "com.nicolasmorin.andropoetique");
        Log.v(TAG, "img_id is " + img_id);

        ((ImageView) img).setImageResource(img_id);

        // setText from array of strings for each verse
        View tv = layoutView.findViewById(R.id.theatre_text);

        String [] theatreVerses = {
                "la guerre est un cheval",
                "dire deux fois la guerre\n" +
                        "cheval cloué au tronc de l’homme",
                "la guerre est un cheval",
                "un tronc cloué à la mer\n" +
                        "du monde enveloppé de fibres\n\n" +
                        "l’aigle du monde plane chaque jour",
                "sur la hideur des animaux\n" +
                        "deux fois dénombrés, un homme, un tronc\n" +
                        "l’aigle du monde est un cheval coupant\n" +
                        "qui renverse l’homme",
                "par dessus les répétitions\n" +
                        "un homme, un tronc, son contenu\n" +
                        "à l’arbre du monde pendu\n" +
                        "arbre du monde répété poétique",
                "qui s’aligne devant lui-même\n" +
                        "au sommet de la guerre pendu\n" +
                        "avec ses étages ses degrés\n\n" +
                        "l’oubli n’est pas une image mais une barque",
                "où montent les nations\n" +
                        "dont chutent les chevaux\n\n" +
                        "ils broutent cinq siècles dans la mer\n" +
                        "où se déforment troncs et hommes",
                "qu’une guerre porte à eux-mêmes\n\n" +
                        "ils mangent la mort cinq fois cet hiver\n" +
                        "avec des fusillades comme des bouquets\n" +
                        "portés à la boutonnière",
                "la mort est un cheval mécanique\n" +
                        "comme une pantoufle qu’on enfile",
                "d’entrailles et de sang doublée",
                "regarde, regarde la forme\n" +
                        "de l’homme, de l’homme et de l’homme\n" +
                        "dans ses vêtements de jour",
                "regarde le cheval de chaque jour\n" +
                        "fondre sur l’épée comme la main\n " +
                        "au travers de sa classe\n\n" +
                        "la guerre est un tronc",
                "au travers des fleuves passé\n" +
                        "pour que les hommes flottent\n" +
                        "leur exaltante paresse\n" +
                        "en dépit de toute certitude",
                "la guerre est un animal de mes mains."
        };

        ((TextView) tv).setText(theatreVerses[mNum]);
        return layoutView;
    }
}
