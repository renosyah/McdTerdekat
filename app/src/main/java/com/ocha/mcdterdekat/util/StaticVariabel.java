package com.ocha.mcdterdekat.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Metadata;
import com.here.sdk.mapviewlite.MapImage;
import com.here.sdk.mapviewlite.MapImageFactory;
import com.here.sdk.mapviewlite.MapMarker;
import com.here.sdk.mapviewlite.MapMarkerImageStyle;
import com.here.sdk.mapviewlite.MapOverlay;
import com.ocha.mcdterdekat.BuildConfig;
import com.ocha.mcdterdekat.model.location.LocationModel;

import com.ocha.mcdterdekat.R;

// ini adalah class
// yg memiliki variabel dengan nilais statik
// yg akan digunakan berkali-kali
public class StaticVariabel {

    // flag id untuk request lokasi
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 122;

    // waktu refresh untuk update lokasi
    public static final long LOCATION_REFRESH_TIME = 5000;

    // jarak antar lokasi untuk update
    public static final float LOCATION_REFRESH_DISTANCE = 0;

    // tinggakatan standar zoom di map
    public static final int ZOOM_LEVEL = 16;

    // nama cache untuk data keyword percarian terakhir
    public static final String USER_DATA = BuildConfig.APPLICATION_ID + "_USER_DATA.CASES";


    public static CustomMarker createCustomMarker(Context c, LocationModel location){
        return new CustomMarker(createLocationMarkerWithText(c, location),createLocationMarker(c, location));
    }

    // fungsi untuk membuat marker
    // untuk wisata kuliner dengan 3 parameter
    public static MapOverlay<View> createLocationMarkerWithText(Context c, LocationModel location){

        View v = ((Activity) c).getLayoutInflater().inflate(R.layout.location_marker,null);
        TextView textView = v.findViewById(R.id.location_name_textview);
        textView.setText(location.Name);

        GeoCoordinates geoCoordinates = new GeoCoordinates(location.Latitude,location.Longitude);
        MapOverlay<View> mapOverlay = new MapOverlay<>(v, geoCoordinates);

        return mapOverlay;
    }

    // fungsi untuk membuat marker
    // untuk wisata kuliner dengan 3 parameter
    public static MapMarker createLocationMarker(Context c, LocationModel location){

        // membuat instance marker
        MapMarker defaultMarker = new MapMarker(new GeoCoordinates(location.Latitude,location.Longitude));

        // membuat image yg akan digunakan
        MapImage mapImage = MapImageFactory.fromResource(c.getResources(), R.drawable.marker_small);

        // deklarasi style
        MapMarkerImageStyle style = new MapMarkerImageStyle();

        // set skala ke 1
        style.setScale(1.0f);

        // tempelkan image dan style
        // ke marker
        defaultMarker.addImage(mapImage, style);

        // membuat instance metadata
        Metadata metadata = new Metadata();

        // set variabel id di metadata
        metadata.setInteger("id", location.Id);

        // set name id di metadata
        metadata.setString("name",location.Name);

        // set address di metadata
        metadata.setString("address", location.Address);

        // set distance di metadata
        metadata.setDouble("distance", location.Distance);

        // tambahkan metadata ke marker
        defaultMarker.setMetadata(metadata);

        // balikan marker sebagai nilai balik
        // dari fungsi
        return defaultMarker;
    }

    // fungsi untuk membuat marker
    // untuk posisi user dengan 3 parameter
    public static MapMarker createUserMarker(Context c,GeoCoordinates coordinates){

        // membuat instance marker
        MapMarker defaultMarker = new MapMarker(coordinates);

        // membuat image yg akan digunakan
        MapImage mapImage = MapImageFactory.fromResource(c.getResources(),R.drawable.user_current_marker);

        // deklarasi style
        MapMarkerImageStyle style = new MapMarkerImageStyle();

        // set skala ke 1
        style.setScale(1.0f);

        // tempelkan image dan style
        // ke marker
        defaultMarker.addImage(mapImage, style);

        // membuat instance metadata
        Metadata metadata = new Metadata();

        // set variabel id di metadata
        metadata.setInteger("id", -1);

        // set variabel name di metadata
        metadata.setString("name", "User");

        // set distance di metadata
        metadata.setString("message", "Your Current Location");

        // tambahkan metadata ke marker
        defaultMarker.setMetadata(metadata);

        // balikan marker sebagai nilai balik
        // dari fungsi
        return defaultMarker;
    }
}
