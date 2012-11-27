package com.mixd.grounds.tour;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener
{

	private TextView latitudeField;
	private TextView longitudeField;
	private String provider;
	private Location location;
	private LocationManager locationManager;
	public final static String LATITUDE = "lat";
	public final static String LONGITUDE = "lon";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		latitudeField = (TextView) findViewById(R.id.textView4);
		longitudeField = (TextView) findViewById(R.id.textView5);
		// Get the location manager sendMock(View view)
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the location provider -> use sendMock(View view)
	    
		// default
		Criteria criteria = new Criteria();
		// criteria.setAccuracy(Criteria.ACCURACY_FINE);
		provider = locationManager.getBestProvider(criteria, false);
		// String defaultAndroidSDK = "google_sdk";
		// if (android.os.Build.MODEL.equals(defaultAndroidSDK)) {
		// locationManager.setTestProviderEnabled("gps", true);
		// Location cville = new Location(provider);
		// cville.setLatitude(38.029028);
		// cville.setLongitude(-78.478088);
		// locationManager.setTestProviderLocation(provider, cville );
		// }
		Location location = locationManager.getLastKnownLocation(provider);
		// Initialize the location fields
		if (location != null)
		{
			this.location=location;
			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
		}
		else
		{
			latitudeField.setText("Location not available");
			longitudeField.setText("Location not available");
		}
	}

	/* Request updates at startup */
	@Override
	protected void onResume()
	{
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause()
	{
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location)
	{
		DecimalFormat df = new DecimalFormat("#.000000");
		double lat = (location.getLatitude());
		double lng = (location.getLongitude());
		latitudeField.setText(String.valueOf(df.format(lat)));
		longitudeField.setText(String.valueOf(df.format(lng)));
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
		mapIntent.putExtra(LATITUDE,location.getLatitude());
		mapIntent.putExtra(LONGITUDE,location.getLongitude());
		startActivity(mapIntent);
	}
}
