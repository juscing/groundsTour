package com.mixd.grounds.tour;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MockActivity extends Activity implements LocationListener 
{

	private TextView latitudeField;
	private TextView longitudeField;
	public final static String LATITUDE = "lat";
	public final static String LONGITUDE = "lon";
	public final static String ACTIVITY = "lol";
	public static Activity activity;
	private double latitude;
	private double longitude;
	private LocationManager locationManager;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		activity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mock);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation("gps");
		latitudeField = (TextView) findViewById(R.id.textView4);
		longitudeField = (TextView) findViewById(R.id.textView5);
		//latitude = location.getLatitude();
		//longitude = location.getLongitude();
		Intent intent = getIntent();
		latitude = intent.getDoubleExtra(MockCoor.LATITUDE, 0);
		longitude = intent.getDoubleExtra(MockCoor.LONGITUDE, 0);
		
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		
		locationManager.addTestProvider("gps", "requiresNetwork" == "",
                "requiresSatellite" == "", "requiresCell" == "",
                "hasMonetaryCost" == "", "supportsAltitude" == "",
                "supportsSpeed" == "", "supportsBearing" == "",
                android.location.Criteria.POWER_LOW,
                android.location.Criteria.ACCURACY_FINE);
        locationManager.setTestProviderEnabled("gps", true);
        locationManager.setTestProviderLocation("gps", location);
		
		DecimalFormat df = new DecimalFormat("#.000000");
		latitudeField.setText(String.valueOf(df.format(latitude)));
		longitudeField.setText(String.valueOf(df.format(longitude)));
	}
	
	@Override
	public void onBackPressed(){
		activity = null;
		super.onBackPressed();
	}
	
	protected void onResume()
    {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                400, 1, this);
    }

	public void mapIntent(View view)
	{
		Intent mapIntent = new Intent(this, MapsActivity.class);
		mapIntent.putExtra(LATITUDE, latitude);
		mapIntent.putExtra(LONGITUDE, longitude);
		startActivity(mapIntent);
	}

    @Override
    public void onLocationChanged(Location location) {
        DecimalFormat df = new DecimalFormat("#.000000");
        double lat = (location.getLatitude());
        double lng = (location.getLongitude());
        latitudeField.setText(String.valueOf(df.format(lat)));
        longitudeField.setText(String.valueOf(df.format(lng)));
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        locationManager.removeUpdates(this);
    }
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
        
    }
}