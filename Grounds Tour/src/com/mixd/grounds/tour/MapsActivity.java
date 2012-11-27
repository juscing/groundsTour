package com.mixd.grounds.tour;

import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity
{

	GeoPoint cville = new GeoPoint(38029028, -78478088);

	@Override
	protected boolean isRouteDisplayed()
	{
		// TODO Auto-generated method stub
		// Doesn't need to do anything until we actually start displaying a
		// route, in our case this is always false
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		Intent intent = getIntent();

		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		MapController mc = mapView.getController();
		MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this,
				mapView);
		List<Overlay> mapOverlays = mapView.getOverlays();

		if (MockActivity.activity != null)
		{
			int lat = (int) (intent.getDoubleExtra(MockActivity.LATITUDE, 0) * 1000000);
			int lon = (int) (intent.getDoubleExtra(MockActivity.LONGITUDE, 0) * 1000000);
			GeoPoint location = new GeoPoint(lat, lon);
			mc.setCenter(location);
			Drawable draw = this.getResources().getDrawable(
					R.drawable.androidmarker);
			MyItemizedOverlay overlay = new MyItemizedOverlay(draw, this);
			OverlayItem point = new OverlayItem(location, "Mock Location",
					"This is your mock location.");
			overlay.addOverlay(point);
			mapOverlays.add(overlay);

		}
		else
		{
			int lat = (int) (intent.getDoubleExtra(MainActivity.LATITUDE, 0) * 1000000);
			int lon = (int) (intent.getDoubleExtra(MainActivity.LONGITUDE, 0) * 1000000);
			GeoPoint location = new GeoPoint(lat, lon);
			mc.setCenter(location);
			myLocationOverlay.enableMyLocation();
		}

		mc.setZoom(17);

		myLocationOverlay.enableCompass();
		mapView.getOverlays().add(myLocationOverlay);

		Drawable drawable = this.getResources().getDrawable(
				R.drawable.uva_rotunda);
		MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(drawable,
				this);

		Resources res = getResources();

		int[] array1 = res.getIntArray(R.array.Rotunda);
		GeoPoint point1 = new GeoPoint(array1[0], array1[1]);
		OverlayItem overlayitem1 = new OverlayItem(point1, "The Rotunda",
				res.getString(R.string.Rotunda));

		int[] array2 = res.getIntArray(R.array.Chemistry);
		GeoPoint point2 = new GeoPoint(array2[0], array2[1]);
		OverlayItem overlayitem2 = new OverlayItem(point2,
				"Chemistry Building", res.getString(R.string.Chemistry));

		int[] array3 = res.getIntArray(R.array.Clark_Hall);
		GeoPoint point3 = new GeoPoint(array3[0], array3[1]);
		OverlayItem overlayitem3 = new OverlayItem(point3, "Clark Hall",
				res.getString(R.string.Clark_Hall));

		int[] array4 = res.getIntArray(R.array.OHill);
		GeoPoint point4 = new GeoPoint(array4[0], array4[1]);
		OverlayItem overlayitem4 = new OverlayItem(point4,
				"Observatory Hill Dining", res.getString(R.string.OHill));

		int[] array5 = res.getIntArray(R.array.AFC_Clock);
		GeoPoint point5 = new GeoPoint(array5[0], array5[1]);
		OverlayItem overlayitem5 = new OverlayItem(point5, "Clock Tower",
				res.getString(R.string.AFC_Clock));

		int[] array6 = res.getIntArray(R.array.Rice_Hall);
		GeoPoint point6 = new GeoPoint(array6[0], array6[1]);
		OverlayItem overlayitem6 = new OverlayItem(point6, "Rice Hall",
				res.getString(R.string.Rice_Hall));

		int[] array7 = res.getIntArray(R.array.Nau_Hall);
		GeoPoint point7 = new GeoPoint(array7[0], array7[1]);
		OverlayItem overlayitem7 = new OverlayItem(point7, "Nau Hall",
				res.getString(R.string.Nau_Hall));

		int[] array8 = res.getIntArray(R.array.Homer);
		GeoPoint point8 = new GeoPoint(array8[0], array8[1]);
		OverlayItem overlayitem8 = new OverlayItem(point8, "Homer Statue",
				res.getString(R.string.Homer));

		int[] array9 = res.getIntArray(R.array.Chapel);
		GeoPoint point9 = new GeoPoint(array9[0], array9[1]);
		OverlayItem overlayitem9 = new OverlayItem(point9, "The Chapel",
				res.getString(R.string.Chapel));

		itemizedoverlay.addOverlay(overlayitem1);
		itemizedoverlay.addOverlay(overlayitem2);
		itemizedoverlay.addOverlay(overlayitem3);
		itemizedoverlay.addOverlay(overlayitem4);
		itemizedoverlay.addOverlay(overlayitem5);
		itemizedoverlay.addOverlay(overlayitem6);
		itemizedoverlay.addOverlay(overlayitem7);
		itemizedoverlay.addOverlay(overlayitem8);
		itemizedoverlay.addOverlay(overlayitem9);

		mapOverlays.add(itemizedoverlay);

	}

}
