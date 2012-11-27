package com.mixd.grounds.tour;

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
    
    
    
    
}
