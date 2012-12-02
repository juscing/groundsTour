package com.mixd.grounds.tour;

import java.lang.reflect.InvocationTargetException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NextCoor extends Activity
{
	public static double latitude;
	public static double longitude;
	public static boolean checker = false;
	private EditText editText3;
	private EditText editText4;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_coor);
		checker = true;
		
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		
		Intent intent = getIntent();
		String lat = intent.getStringExtra(MockActivity.LATITUDE).toString();
		String lon = intent.getStringExtra(MockActivity.LONGITUDE).toString();
		editText3.setText(lat);
		editText4.setText(lon);
	}

	public void sendMock(View view)
	{
		

		try
		{
			double lat = Double.parseDouble(editText3.getText().toString());
			double lon = Double.parseDouble(editText4.getText().toString());
						
			int iflags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
			Intent mockIntent = new Intent(this, MockActivity.class);
			mockIntent.setFlags(iflags);	
			latitude = lat;
			longitude = lon;
			
//			mockIntent.putExtra(LATITUDE, lat);
//			mockIntent.putExtra(LONGITUDE, lon);
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
	
	@Override
	public void onPause(){
		super.onPause();
		finish();
	}
}
