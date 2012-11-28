package com.mixd.grounds.tour;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;

public class MockActivity extends Activity
{

	private TextView latitudeField;
	private TextView longitudeField;
	private TextView stopField;
	public final static String LATITUDE = "lat";
	public final static String LONGITUDE = "lon";
	public final static String ACTIVITY = "lol";
	public static Activity activity;
	private double latitude;
	private double longitude;

	private GeoPoint finalDest;
	private int stopNum;
	
	TimerTask task;
	final Handler handler = new Handler();
	Timer timer;
	
	
	
	public void check()
	{
		task = new TimerTask()
		{
			public void run()
			{
				handler.post(new Runnable()
				{
					public void run()
					{
						ArrayList<Object> array = Helper.getMockStop(stopNum, latitude,
								longitude, (double) finalDest.getLatitudeE6() / 1000000,
								(double) finalDest.getLongitudeE6() / 1000000, MockActivity.activity);

						if (array != null)
						{
							int nextNum = 0;
							if (array.get(0) instanceof Integer)
							{
								nextNum = (Integer) array.get(0);
								System.out.println("Numyay!");
							}
							if (nextNum != stopNum)
							{
								stopNum = nextNum;
								stopField.setText(String.valueOf(array.get(3)));
								int latTo = 0;
								int lonTo = 0;
								System.out.println(array.get(1));
								if (array.get(1) instanceof Integer)
								{									
									latTo = (Integer) array.get(1);
									
									
									System.out.println("latYay!"); 
											
								}
								if(array.get(2) instanceof Integer){
									lonTo = (Integer) array.get(2);
								}
								
								finalDest = new GeoPoint(latTo, lonTo);
								
								
								AlertDialog ad = new AlertDialog.Builder(MockActivity.activity).create();
								ad.setCancelable(false); // This blocks the 'BACK' button
								ad.setMessage("You've arrived at the stop!");
								ad.setButton("OK", new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										dialog.dismiss();
									}
								});
								ad.show();
							}
						}
					}
				});
			}
		};

		timer = new Timer();

		timer.schedule(task, 300, 1000);

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		activity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mock);

		latitudeField = (TextView) findViewById(R.id.textView4);
		longitudeField = (TextView) findViewById(R.id.textView5);
		stopField = (TextView) findViewById(R.id.textView6);

		Intent intent = getIntent();
		latitude = intent.getDoubleExtra(MockCoor.LATITUDE, 0);
		longitude = intent.getDoubleExtra(MockCoor.LONGITUDE, 0);

		DecimalFormat df = new DecimalFormat("#.000000");
		latitudeField.setText(String.valueOf(df.format(latitude)));
		longitudeField.setText(String.valueOf(df.format(longitude)));

		finalDest = null;
		Resources res = getResources();
		String stopName = "";

		for (int i = 0; i < 9; i++)
		{
			GeoPoint currentDest = new GeoPoint(0, 0);
			int[] array;

			switch (i)
			{

			case 0:
				array = res.getIntArray(R.array.Rotunda);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "Rotunda";
				break;
			case 1:
				array = res.getIntArray(R.array.Clark_Hall);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "Clark Hall";

				break;
			case 2:
				array = res.getIntArray(R.array.Chemistry);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "Chemistry Building";
				break;
			case 3:
				array = res.getIntArray(R.array.OHill);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "OHill";
				break;
			case 4:
				array = res.getIntArray(R.array.AFC_Clock);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "AFC Clock Tower";
				break;
			case 5:
				array = res.getIntArray(R.array.Rice_Hall);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "Rice Hall";
				break;
			case 6:
				array = res.getIntArray(R.array.Nau_Hall);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "Nau Hall";
				break;
			case 7:
				array = res.getIntArray(R.array.Homer);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "Homer Statue";
				break;
			case 8:
				array = res.getIntArray(R.array.Chapel);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "Chapel";
				break;
			}

			if (finalDest == null)
			{
				finalDest = new GeoPoint(currentDest.getLatitudeE6(),
						currentDest.getLongitudeE6());
				stopNum = i;
				stopField.setText(stopName);

			}
			else
			{
				double distanceC = Helper.latLngDist(latitude, longitude,
						(double) currentDest.getLatitudeE6() / 1000000,
						(double) currentDest.getLongitudeE6() / 1000000);
				double distanceF = Helper.latLngDist(latitude, longitude,
						(double) finalDest.getLatitudeE6() / 1000000,
						(double) finalDest.getLongitudeE6() / 1000000);

				if (distanceC < distanceF)
				{
					finalDest = new GeoPoint(currentDest.getLatitudeE6(),
							currentDest.getLongitudeE6());
					stopNum = i;
					stopField.setText(stopName);
				}
			}
		}
	}

	@Override
	public void onBackPressed()
	{
		activity = null;
		timer.cancel();
		super.onBackPressed();
	}

	public void mapIntent(View view)
	{
		Intent mapIntent = new Intent(this, MapsActivity.class);
		mapIntent.putExtra(LATITUDE, latitude);
		mapIntent.putExtra(LONGITUDE, longitude);
		startActivity(mapIntent);
	}
	
	public void nextCoor(View view){
		onBackPressed();
	}
	
	@Override
	public void onResume(){
		check();
		super.onResume();
	}
	
	public void onPause(){
		timer.cancel();
		super.onResume();
	}
}
