package com.mixd.grounds.tour;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

public class MainActivity extends Activity implements LocationListener
{

	private TextView latitudeField;
	private TextView longitudeField;
	private TextView stopField;
	private String provider;
	private Location location;
	private int firstStop;
	private LocationManager locationManager;
	private ArrayList<Object> nextStop;
	public final static String LATITUDE = "lat";
	public final static String LONGITUDE = "lon";
	private ImageView arrow;
	//private double prevBear;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		latitudeField = (TextView) findViewById(R.id.textView4);
		longitudeField = (TextView) findViewById(R.id.textView5);
		stopField = (TextView) findViewById(R.id.textView6);

		// Get the location manager sendMock(View view)
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the location provider -> use
		// sendMock(View view)

		// default
		Criteria criteria = new Criteria();

		provider = locationManager.getBestProvider(criteria, false);

		// }
		Location location = locationManager.getLastKnownLocation(provider);
		// Initialize the location fields
		if (location != null)
		{
			this.location = location;
			System.out.println("Provider " + provider + " has been selected.");
			

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
					double distanceC = Helper.latLngDist(
							location.getLatitude(), location.getLongitude(),
							(double) currentDest.getLatitudeE6() / 1000000,
							(double) currentDest.getLongitudeE6() / 1000000);
					double distanceF = Helper.latLngDist(
							location.getLatitude(), location.getLongitude(),
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

			firstStop = stopNum;
			
			nextStop = new ArrayList<Object>();
			nextStop.add(stopNum);
			nextStop.add((double)finalDest.getLatitudeE6()/1000000);
			nextStop.add((double)finalDest.getLongitudeE6()/1000000);
			nextStop.add(myStopName);
			//prevBear = 0;
			Matrix arrowMatrix = new Matrix();
	        arrow = (ImageView) findViewById(R.id.imageView1);
			
			onLocationChanged(location);
		}
		else
		{
			latitudeField.setText("Location not available");
			longitudeField.setText("Location not available");
			stopField.setText("Location not available");
		}
		
	}

	/* Request updates at startup */
	@Override
	protected void onResume()
	{
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause()
	{
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location)
	{
		DecimalFormat df = new DecimalFormat("#.000000");
		double lat = (location.getLatitude());
		double lon = (location.getLongitude());
		latitudeField.setText(String.valueOf(df.format(lat)));
		longitudeField.setText(String.valueOf(df.format(lon)));
		
		ArrayList<Object> newArray = Helper.getCurrentStop(lat, lon, nextStop, this);
		int newInt = 0;
		int thisInt = 0;
		if (newArray.get(0) instanceof Integer){
			newInt = (Integer) newArray.get(0);
		}
		
		if (nextStop.get(0) instanceof Integer){
			thisInt = (Integer) nextStop.get(0);
		}
			
		if (newInt != thisInt){
			
			nextStop = (ArrayList<Object>) newArray.clone();
			
			AlertDialog ad = new AlertDialog.Builder(this).create();  
			ad.setCancelable(false); // This blocks the 'BACK' button  
			ad.setMessage("You've arrived at the stop!");  
			ad.setButton("OK", new DialogInterface.OnClickListener() {  
			    @Override  
			    public void onClick(DialogInterface dialog, int which) {  
			        dialog.dismiss();                      
			    }
			});  
			ad.show();  
		}
		
		float bearingToStop = (float) Helper.latLngBearingDeg(location.getLatitude(), location.getLongitude(), (Double) nextStop.get(1), (Double) nextStop.get(2));
		float myBearing = location.getBearing();
		Matrix arrowMatrix = new Matrix();
		arrow.setScaleType(ScaleType.MATRIX);   //required
		arrowMatrix.postRotate((bearingToStop - myBearing), 37/2, 25);
		arrow.setImageMatrix(arrowMatrix);
		//Not needed prevBear = bearingToStop;
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		Toast.makeText(this, "Disabled provider " + provider, Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public void onProviderEnabled(String provider)
	{
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_LONG).show();

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		Toast.makeText(this, "Disabled provider " + provider, Toast.LENGTH_LONG)
				.show();

	}

	public void mapIntent(View view)
	{
		Intent mapIntent = new Intent(this, MapsActivity.class);
		mapIntent.putExtra(LATITUDE, location.getLatitude());
		mapIntent.putExtra(LONGITUDE, location.getLongitude());
		startActivity(mapIntent);
	}
}
