package com.mixd.grounds.tour;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MockCoor extends Activity
{
	public final static String LATITUDE = "lat";
	public final static String LONGITUDE = "lon";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mock_coor);
	}

	public void sendMock(View view)
	{

		EditText editText1 = (EditText) findViewById(R.id.editText1);
		EditText editText2 = (EditText) findViewById(R.id.editText2);

		try
		{
			double lat = Double.parseDouble(editText1.getText().toString());
			double lon = Double.parseDouble(editText2.getText().toString());
						
			
			Intent mockIntent = new Intent(this, MockActivity.class);
			mockIntent.putExtra(LATITUDE, lat);
			mockIntent.putExtra(LONGITUDE, lon);
			startActivity(mockIntent);
		}
		catch (NumberFormatException e)
		{
			AlertDialog ad = new AlertDialog.Builder(this).create();  
			ad.setCancelable(false); // This blocks the 'BACK' button  
			ad.setMessage("That is not a valid location.");  
			ad.setButton("OK", new DialogInterface.OnClickListener() {  
			    @Override  
			    public void onClick(DialogInterface dialog, int which) {  
			        dialog.dismiss();                      
			    }  
			});  
			ad.show();  
		}

	}

}
