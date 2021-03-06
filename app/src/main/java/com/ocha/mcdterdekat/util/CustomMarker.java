package com.ocha.mcdterdekat.util;

import android.view.View;

import com.here.sdk.mapviewlite.MapMarker;
import com.here.sdk.mapviewlite.MapOverlay;

public class CustomMarker {
    public MapOverlay<View> markerOverlay;
    public  MapMarker marker;

    public CustomMarker(MapOverlay<View> markersOverlay, MapMarker markers) {
        this.markerOverlay = markersOverlay;
        this.marker = markers;
    }
}
