package com.mixd.grounds.tour;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;


public class Helper
{

	public static double latLngDist(double lat1, double lng1, double lat2,
			double lng2)
	{
		final int R = 6371; // km

		double dlat = Math.toRadians(lat2 - lat1);
		double dlng = Math.toRadians(lng2 - lng1);

		double lat1rad = Math.toRadians(lat1);
		double lat2rad = Math.toRadians(lat2);

		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.sin(dlng / 2)
				* Math.sin(dlng / 2) * Math.cos(lat1rad) * Math.cos(lat2rad);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c;
	}
	/*
	public static double latLngDist(Location l1, Location l2)
	{
		double lat1 = l1.getLatitude();
		double lng1 = l1.getLongitude();
		dou branchesble lat2 = l2.getLatitude();
		double lng2 = l2.getLongitude();
		final int R = 6371; // km

		double dlat = Math.toRadians(lat2 - lat1);
		double dlng = Math.toRadians(lng2 - lng1);

		double lat1rad = Math.toRadians(lat1);
		double lat2rad = Math.toRadians(lat2);

		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.sin(dlng / 2)
				* Math.sin(dlng / 2) * Math.cos(lat1rad) * Math.cos(lat2rad);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c;
	}
    */
	public static double latLngBearingDeg(double lat1, double lng1,
			double lat2, double lng2)
	{
		// Returns bearing from latitude and longitude

		double dlng = Math.toRadians(lng2 - lng1);

		double lat1rad = Math.toRadians(lat1);
		double lat2rad = Math.toRadians(lat2);
		double y = Math.sin(dlng) * Math.cos(lat2rad);

		double x = Math.cos(lat1rad) * Math.sin(lat2rad) - Math.sin(lat1rad)
				* Math.cos(lat2rad) * Math.cos(dlng);

		return Math.toDegrees(Math.atan2(y, x));
	}

	public static double latLngBearingRad(double lat1, double lng1,
			double lat2, double lng2)
	{
		// Returns bearing from latitude and longitude

		double dlng = Math.toRadians(lng2 - lng1);

		double lat1rad = Math.toRadians(lat1);
		double lat2rad = Math.toRadians(lat2);
		double y = Math.sin(dlng) * Math.cos(lat2rad);

		double x = Math.cos(lat1rad) * Math.sin(lat2rad) - Math.sin(lat1rad)
				* Math.cos(lat2rad) * Math.cos(dlng);

		return Math.atan2(y, x);
	}

	public static ArrayList<Object> getCurrentStop(double lat, double lon,
			ArrayList<Object> array, Context context)
	{
		int stopNum = 0;
		
		double stopLat = 0;
		double stopLon = 0;

		double latTo = 0;
		double lonTo = 0;
		String nameTo = "";

		if (array.get(0) instanceof Integer)
		{
			stopNum = (Integer) array.get(0);
		}

		if (array.get(1) instanceof Double)
		{
			stopLat = (Double) array.get(1);
		}

		if (array.get(2) instanceof Double)
		{
			stopLon = (Double) array.get(2);
		}
		
		int nextNum = 0;
		if (stopNum != 8)
		{
			nextNum = stopNum + 1;
		}
		else
		{
			nextNum = 0;
		}

		Resources res = context.getResources();

		int[] intArray;

		switch (nextNum)
		{

		case 0:
			intArray = res.getIntArray(R.array.Rotunda);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Rotunda";
			break;
		case 1:
			intArray = res.getIntArray(R.array.Clark_Hall);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Clark Hall";
			break;
		case 2:
			intArray = res.getIntArray(R.array.Chemistry);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Chemistry Building";
			break;
		case 3:
			intArray = res.getIntArray(R.array.OHill);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Ohill";
			break;
		case 4:
			intArray = res.getIntArray(R.array.AFC_Clock);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "AFC Clock Tower";
			break;
		case 5:
			intArray = res.getIntArray(R.array.Rice_Hall);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Rice Hall";
			break;
		case 6:
			intArray = res.getIntArray(R.array.Nau_Hall);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Nau Hall";
			break;
		case 7:
			intArray = res.getIntArray(R.array.Homer);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Homer Statue";
			break;
		case 8:
			intArray = res.getIntArray(R.array.Chapel);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Chapel";
			break;
		}

		double distance = latLngDist(lat, lon, stopLat, stopLon);
		System.out.println(distance);

		if (distance < 0.009144)
		{
			array = new ArrayList<Object>();

			array.add(nextNum);
			array.add(latTo / 1000000);
			array.add(lonTo / 1000000);
			array.add(nameTo);
		}

		return array;

	}

	public static ArrayList<Object> getMockStop(int stopNum, double lat,
			double lon, double stopLat, double stopLon, Context context)
	{

		int nextNum = 0;
		if (stopNum != 8)
		{
			nextNum = stopNum + 1;
		}
		else
		{
			nextNum = 0;
		}
		
		int latTo = 0;
		int lonTo = 0;
		String nameTo = "";

		Resources res = context.getResources();

		int[] intArray;

		switch (nextNum)
		{

		case 0:
			intArray = res.getIntArray(R.array.Rotunda);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Rotunda";
			break;
		case 1:
			intArray = res.getIntArray(R.array.Clark_Hall);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Clark Hall";
			break;
		case 2:
			intArray = res.getIntArray(R.array.Chemistry);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Chemistry Building";
			break;
		case 3:
			intArray = res.getIntArray(R.array.OHill);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Ohill";
			break;
		case 4:
			intArray = res.getIntArray(R.array.AFC_Clock);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "AFC Clock Tower";
			break;
		case 5:
			intArray = res.getIntArray(R.array.Rice_Hall);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Rice Hall";
			break;
		case 6:
			intArray = res.getIntArray(R.array.Nau_Hall);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Nau Hall";
			break;
		case 7:
			intArray = res.getIntArray(R.array.Homer);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Homer Statue";
			break;
		case 8:
			intArray = res.getIntArray(R.array.Chapel);
			latTo = intArray[0];
			lonTo = intArray[1];
			nameTo = "Chapel";
			break;
		}

		double distance = latLngDist(lat, lon, stopLat, stopLon);
		
		System.out.println(distance);

		if (distance < 0.009144)
		{
			ArrayList<Object> array = new ArrayList<Object>();
			array.add(nextNum);
			array.add(latTo);
			array.add(lonTo);
			array.add(nameTo);
			return array;
		}
		else
		{
			return null;
		}

	}
}
