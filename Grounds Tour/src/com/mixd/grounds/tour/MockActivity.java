package com.mixd.grounds.tour;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

		GeoPoint finalDest = null;
		Resources res = getResources();
		int stopNum = 0;
		String stopName = "";
		String myStopName = "";

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
					myStopName = stopName;
					stopField.setText(stopName);
				}
			}
		}

		ArrayList<Object> array = Helper.getMockStop(stopNum, latitude,
				longitude, (double) finalDest.getLatitudeE6() / 1000000,
				(double) finalDest.getLongitudeE6() / 1000000, this);

		if (array != null)
		{

			int nextNum = 0;
			if (array.get(0) instanceof Integer)
			{
				nextNum = (Integer) array.get(0);
			}
			if (nextNum != stopNum)
			{

				AlertDialog ad = new AlertDialog.Builder(this).create();
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

	@Override
	public void onBackPressed()
	{
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
