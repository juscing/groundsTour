package com.mixd.grounds.tour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Choice extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }

    public void useNormal(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void useMock(View view) {
        Intent intent = new Intent(this, MockCoor.class);
        startActivity(intent);

    }
}
