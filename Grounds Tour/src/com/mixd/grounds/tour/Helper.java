package com.mixd.grounds.tour;

import android.location.Location;

public class Helper {
    
    public static double latLngDist(double lat1, double lng1, double lat2, double lng2){
        final int R = 6371; // km
        
        double dlat = Math.toRadians(lat2 - lat1);
        double dlng = Math.toRadians(lng2 - lng1);
        
        double lat1rad = Math.toRadians(lat1);
        double lat2rad = Math.toRadians(lat2);
        
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) +
                Math.sin(dlng/2) * Math.sin(dlng/2) * Math.cos(lat1rad) * Math.cos(lat2rad);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        return R * c;
    }
    public static double latLngDist(Location l1, Location l2){
        double lat1 = l1.getLatitude();
        double lng1 = l1.getLongitude();
        double lat2 = l2.getLatitude();
        double lng2 = l2.getLongitude();
        final int R = 6371; // km
        
        double dlat = Math.toRadians(lat2 - lat1);
        double dlng = Math.toRadians(lng2 - lng1);
        
        double lat1rad = Math.toRadians(lat1);
        double lat2rad = Math.toRadians(lat2);
        
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) +
                Math.sin(dlng/2) * Math.sin(dlng/2) * Math.cos(lat1rad) * Math.cos(lat2rad);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        return R * c;
    }
    
    public static double latLngBearingDeg(double lat1, double lng1, double lat2, double lng2){
        //Returns bearing from latitude and longitude

        double dlng = Math.toRadians(lng2 - lng1);
        
        double lat1rad = Math.toRadians(lat1);
        double lat2rad = Math.toRadians(lat2);
        double y = Math.sin(dlng) * Math.cos(lat2rad);
        
        double x = Math.cos(lat1rad)*Math.sin(lat2rad) -
                Math.sin(lat1rad)*Math.cos(lat2rad)*Math.cos(dlng);
        
        return Math.toDegrees(Math.atan2(y, x));
    }
    
    public static double latLngBearingRad(double lat1, double lng1, double lat2, double lng2){
        //Returns bearing from latitude and longitude

        double dlng = Math.toRadians(lng2 - lng1);
        
        double lat1rad = Math.toRadians(lat1);
        double lat2rad = Math.toRadians(lat2);
        double y = Math.sin(dlng) * Math.cos(lat2rad);
        
        double x = Math.cos(lat1rad)*Math.sin(lat2rad) -
                Math.sin(lat1rad)*Math.cos(lat2rad)*Math.cos(dlng);
        
        return Math.atan2(y, x);
    }
    
}
