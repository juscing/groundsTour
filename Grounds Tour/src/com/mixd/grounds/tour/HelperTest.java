package com.mixd.grounds.tour;

import static org.junit.Assert.*;
import org.junit.Test;
import com.mixd.grounds.tour.Helper;

public class HelperTest {
    double lat1 = 20;
    double lng1 = 30;
    double lat2 = 30;
    double lng2 = 40;

    @Test
    public void testLatLngDistDoubleDoubleDoubleDouble() {
        double dist = Helper.latLngDist(lat1, lng1, lat2, lng2);
        assertEquals(dist, 1498, 4);
    }

    @Test
    public void testLatLngBearingDeg() {
        fail("Not yet implemented");
    }

    @Test
    public void testLatLngBearingRad() {
        fail("Not yet implemented");
    }

}
