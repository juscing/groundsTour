package com.mixd.grounds.tour;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MockActivity extends Activity 
{

	private TextView latitudeField;
	private TextView longitudeField;
	public final static String LATITUDE = "lat";
	public final static String LONGITUDE = "lon";
	public final static String ACTIVITY = "lol";
	public static Activity activity;
	private double latitude;
	private double longitude;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		activity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mock);		

		latitudeField = (TextView) findViewById(R.id.textView4);
		longitudeField = (TextView) findViewById(R.id.textView5);

		Intent intent = getIntent();
		latitude = intent.getDoubleExtra(MockCoor.LATITUDE, 0);
		longitude = intent.getDoubleExtra(MockCoor.LONGITUDE, 0);
		
		DecimalFormat df = new DecimalFormat("#.000000");
		latitudeField.setText(String.valueOf(df.format(latitude)));
		longitudeField.setText(String.valueOf(df.format(longitude)));
	}
	
	@Override
	public void onBackPressed(){
		activity = null;
		super.onBackPressed();
	}


	public void mapIntent(View view)
	{		
		Intent mapIntent = new Intent(this, MapsActivity.class);
		mapIntent.putExtra(LATITUDE, latitude);
		mapIntent.putExtra(LONGITUDE, longitude);
		startActivity(mapIntent);
	}
}
