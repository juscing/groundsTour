package com.mixd.grounds.tour;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryButton extends ListActivity {

    public static final String VALUE = "value";
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_button);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        String keyword = l.getItemAtPosition(position).toString();
        System.out.println(keyword);
        Intent intent = new Intent(this, DisplayHistory.class);
        intent.putExtra(VALUE, keyword);
        startActivity(intent);
    }

    // public void openHistory(View view){
    // textView = (TextView) view;
    // Intent intent = new Intent(this,DisplayHistory.class);
    // intent.putExtra(VALUE,textView.getText().toString());
    // startActivity(intent);
    // }
}
