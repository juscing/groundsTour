package com.mixd.grounds.tour;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayHistory extends Activity {
    private TextView textView;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_history);

        textView = (TextView) findViewById(R.id.textView16);
        imageView = (ImageView) findViewById(R.id.imageView1);
        Intent intent = getIntent();
        String place = intent.getStringExtra(HistoryButton.VALUE);

        Resources res = getResources();

        if (place.equals("Rotunda")) {
            textView.setText(res.getString(R.string.Rotunda));
            imageView.setImageResource(R.drawable.jefferson_statue);
        }
        if (place.equals("Clark Hall")) {
            textView.setText(res.getString(R.string.Clark_Hall));
            imageView.setImageResource(R.drawable.clark_hall);
        }
        if (place.equals("Chemistry Building")) {
            textView.setText(res.getString(R.string.Chemistry));
            imageView.setImageResource(R.drawable.chem_bldg);
        }
        if (place.equals("OHill")) {
            textView.setText(res.getString(R.string.OHill));
            imageView.setImageResource(R.drawable.ohill);
        }
        if (place.equals("AFC Clock Tower")) {
            textView.setText(res.getString(R.string.AFC_Clock));
            imageView.setImageResource(R.drawable.afc_clocktower);
        }
        if (place.equals("Rice Hall")) {
            textView.setText(res.getString(R.string.Rice_Hall));
            imageView.setImageResource(R.drawable.rice_hall);
        }
        if (place.equals("Nau Hall")) {
            textView.setText(res.getString(R.string.Nau_Hall));
            imageView.setImageResource(R.drawable.nau_hall);
        }
        if (place.equals("Homer Statue")) {
            textView.setText(res.getString(R.string.Homer));
            imageView.setImageResource(R.drawable.homer_statue);
        }
        if (place.equals("Chapel")) {
            textView.setText(res.getString(R.string.Chapel));
            imageView.setImageResource(R.drawable.uva_chapel);
        }

    }

}
