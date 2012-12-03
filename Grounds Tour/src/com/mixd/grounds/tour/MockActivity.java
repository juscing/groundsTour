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
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;

public class MockActivity extends Activity
{

	private TextView latitudeField;
	private TextView longitudeField;
	private TextView stopField;
	private TextView stopLatField;
	private TextView stopLonField;
	public final static String LATITUDE = "lat";
	public final static String LONGITUDE = "lon";
	public final static String ACTIVITY = "lol";
	public static Activity activity;
	private double latitude;
	private double longitude;
	private TextView temp;
	private GeoPoint finalDest;
	private int stopNum;
	private int firstNum;

	TimerTask task;
	final Handler handler = new Handler();
	Timer timer;
	
	private ImageView arrow;

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
						ArrayList<Object> array = Helper.getMockStop(stopNum,
								latitude, longitude,
								(double) finalDest.getLatitudeE6() / 1000000,
								(double) finalDest.getLongitudeE6() / 1000000,
								MockActivity.activity);
						
						float bearingToStop = (float) Helper.latLngBearingDeg(latitude, longitude,
				                (double) finalDest.getLatitudeE6() / 1000000,
				                (double) finalDest.getLongitudeE6() / 1000000);
						
				        arrow = (ImageView) findViewById(R.id.imageView1);
				        Matrix arrowMatrix = new Matrix();
				        arrowMatrix.postRotate(bearingToStop, 37 / 2, 25);
				        arrow.setImageMatrix(arrowMatrix);

						if (array != null)
						{
							int nextNum = 0;
							int stopLat = 0;
							int stopLon = 0;
							if (array.get(0) instanceof Integer)
							{
								nextNum = (Integer) array.get(0);
							}
							if (nextNum != stopNum)
							{								
								if (nextNum != firstNum)
								{
									if (array.get(1) instanceof Integer)
									{
										stopLat = (Integer) array.get(1);
									}
									if (array.get(2) instanceof Integer)
									{
										stopLon = (Integer) array.get(2);
									}
									
									stopNum = nextNum;
									stopField.setText(String.valueOf(array
											.get(3)));
									stopLatField.setText(String.valueOf((double)stopLat/1000000));
									stopLonField.setText(String.valueOf((double)stopLon/1000000));
									int latTo = 0;
									int lonTo = 0;
									System.out.println(array.get(1));
									if (array.get(1) instanceof Integer)
									{
										latTo = (Integer) array.get(1);

									}
									if (array.get(2) instanceof Integer)
									{
										lonTo = (Integer) array.get(2);
									}

									finalDest = new GeoPoint(latTo, lonTo);

									AlertDialog ad = new AlertDialog.Builder(
											MockActivity.activity).create();
									ad.setCancelable(false); // This blocks the
																// 'BACK' button
									ad.setMessage("You've arrived at the stop!");
									ad.setButton(
											"OK",
											new DialogInterface.OnClickListener()
											{
												@Override
												public void onClick(
														DialogInterface dialog,
														int which)
												{
													dialog.dismiss();
												}
											});
									ad.show();
								} else {
									AlertDialog ad = new AlertDialog.Builder(
											MockActivity.activity).create();
									ad.setCancelable(false); // This blocks the
																// 'BACK' button
									ad.setMessage("You've finished the tour!");
									ad.setButton("OK",
											new DialogInterface.OnClickListener()
											{
												@Override
												public void onClick(
														DialogInterface dialog,
														int which)
												{
													dialog.dismiss();
												}
											});
									ad.show();
									timer.cancel();
									stopField.setText("You're done!");
									stopLatField.setText("");
									stopLonField.setText("");
								}
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
		arrow = (ImageView) findViewById(R.id.imageView1);
		latitudeField = (TextView) findViewById(R.id.textView4);
		longitudeField = (TextView) findViewById(R.id.textView5);
		stopField = (TextView) findViewById(R.id.textView6);
		temp = (TextView) findViewById(R.id.textView9);
		stopLatField = (TextView) findViewById(R.id.textView14);
		stopLonField = (TextView) findViewById(R.id.textView15);

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
				stopName = "Chem Bldg";
				break;
			case 3:
				array = res.getIntArray(R.array.OHill);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "OHill";
				break;
			case 4:
				array = res.getIntArray(R.array.AFC_Clock);
				currentDest = new GeoPoint(array[0], array[1]);
				stopName = "AFC Clock";
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
				stopName = "Homer";
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
			        
			        if (distanceC < 0.018288)
		            {
		                temp.setText("HOT");
		                temp.setTextColor(getResources().getColor(R.color.hot));
		            }
		            else if (distanceC < 0.03048)
		            {
		                temp.setText("Warm");
		                temp.setTextColor(getResources().getColor(R.color.warm));
		            }
		            else
		            {
		                temp.setText("Cold");
		                temp.setTextColor(getResources().getColor(R.color.blue));
		            }
				}
			}
		}
		stopLatField.setText(String.valueOf((double)finalDest.getLatitudeE6()/1000000));
		stopLonField.setText(String.valueOf((double)finalDest.getLongitudeE6()/1000000));
		firstNum = stopNum;
		
	}

	@Override
	public void onBackPressed()
	{
		AlertDialog ad = new AlertDialog.Builder(
				this).create();
		ad.setCancelable(false); // This blocks the
									// 'BACK' button
		ad.setMessage("Are you sure you want to exit the tour?");
		ad.setButton(DialogInterface.BUTTON_NEGATIVE,"No",
				new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(
							DialogInterface dialog,
							int which)
					{
						dialog.dismiss();
					}
				});
		ad.setButton(DialogInterface.BUTTON_POSITIVE,"Yes",
				new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(
							DialogInterface dialog,
							int which)
					{
						finish();
						activity = null;
						timer.cancel();
						NextCoor.checker = false;
						
					}
				});
		ad.show();
		
	}

	public void mapIntent(View view)
	{
		Intent mapIntent = new Intent(this, MapsActivity.class);
		mapIntent.putExtra(LATITUDE, latitude);
		mapIntent.putExtra(LONGITUDE, longitude);
		startActivity(mapIntent);
	}

	public void nextCoor(View view)
	{
		Intent intent = new Intent(this, NextCoor.class);
		intent.putExtra(LATITUDE, latitudeField.getText().toString());
		intent.putExtra(LONGITUDE, longitudeField.getText().toString());
		startActivity(intent);
	}

	@Override
	public void onResume()
	{
		super.onResume();

		check();
		if (NextCoor.checker)
		{
			double lat = NextCoor.latitude;
			double lon = NextCoor.longitude;
			latitude = lat;
			longitude = lon;

			DecimalFormat df = new DecimalFormat("#.000000");
			latitudeField.setText(df.format(latitude) + "");
			longitudeField.setText(df.format(longitude) + "");
		}

	}

	public void onPause()
	{
		timer.cancel();
		super.onResume();
	}
	
	public void goToHistory(View view){
		Intent intent = new Intent(this,HistoryButton.class);
		startActivity(intent);
	}
}
