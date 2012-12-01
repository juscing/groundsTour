package com.mixd.grounds.tour;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayHistory extends Activity
{
	private TextView textView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_history);

		textView = (TextView) findViewById(R.id.textView16);
		
		Intent intent = getIntent();
		String place = intent.getStringExtra(HistoryButton.VALUE);
		
		Resources res = getResources();
		
		if(place == "Rotunda"){
			textView.setText(res.getString(R.string.Rotunda));
		}
		if(place == "Clark Hall"){
			textView.setText(res.getString(R.string.Clark_Hall));
		}
		if(place == "Chemistry Building"){
			textView.setText(res.getString(R.string.Chemistry));
		}
		if(place == "OHill"){
			textView.setText(res.getString(R.string.OHill));
		}
		if(place == "AFC Clock Tower"){
			textView.setText(res.getString(R.string.AFC_Clock));
		}
		if(place == "Rice Hall"){
			textView.setText(res.getString(R.string.Rice_Hall));
		}
		if(place == "Nau Hall"){
			textView.setText(res.getString(R.string.Nau_Hall));
		}
		if(place == "Homer Statue"){
			textView.setText(res.getString(R.string.Homer));
		}
		if(place == "Chapel"){
			textView.setText(res.getString(R.string.Chapel));
		}
		
	}

}
