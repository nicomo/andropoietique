package com.nicolasmorin.andropoetique;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MentonActivity extends Activity {

    /**
     * Container view with layout changes animations turned on
     */
    private ViewGroup mContainerView;
    private int verseNb = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menton);

        mContainerView = (ViewGroup) findViewById(R.id.menton_container);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menton_add_item) {
            addItem();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addItem(){

        // Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.menton_item, mContainerView, false);


        if(verseNb < MENTON_POEM.length) {
            ((TextView) newView.findViewById(android.R.id.text1)).setText(
                    MENTON_POEM[verseNb]);
            ++verseNb;
        } else {
            // Set the text in the new row to a random verse.
            ((TextView) newView.findViewById(android.R.id.text1)).setText(
                    MENTON_POEM[(int) (Math.random() * MENTON_POEM.length)]);
        }



        // Set a click listener for the "X" button in the row that will remove the row.
        newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the row from its parent (the container view).
                mContainerView.removeView(newView);
            }
        });

        // Because mContainerView has android:animateLayoutChanges set to true,
        // adding this view is automatically animated.
        mContainerView.addView(newView, 0);
    }

    /**
     * A static list of strings containing the poem
     */
    private final String[] MENTON_POEM = new String[] {
            "Le menton sur la poitrine,",
            "rasé d’hier, allongé,",
            "la nuque prise dans l’oreiller",
            "comme un sarcophage, le regard bien droit,",
            "je passe la butte enneigée, je passe l’arbre,",
            "allongé sur les journaux, les cahiers,",
            "Victorien dans l’aventure, immobile,",
            "avec aux lèvres une cloque de sang comme",
            "un reste de bière, le regard",
            "pris dans l’épaisseur des madras,",
            "des indiennes, perdu dans les clichés,",
            "l’un où tu es déguisée, en bottes",
            "dans la neige sale, cape à l’épaule,",
            "la chapka à poils ras",
            "soudée sur l’arrière de la tête,",
            "l’autre où le soleil d’hiver",
            "découpe des carrés de lumière",
            "sur le parquet en pente douce,",
            "où l’ombre du rideau trace",
            "un fleuve gris qu’en souvenir",
            "je sais vert bouteille - je suis pesant,",
            "allongé sur des coussins",
            "à passepoils de velours, les pieds",
            "dressés vers le ciel froid",
            "comme une pierre au-dessus de mon divan.",
    };
}
