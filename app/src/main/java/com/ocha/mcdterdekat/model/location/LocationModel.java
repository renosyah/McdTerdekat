package com.ocha.mcdterdekat.model.location;

import com.google.gson.annotations.SerializedName;
import com.here.sdk.core.GeoCoordinates;
import com.ocha.mcdterdekat.model.BaseModel;

public class LocationModel extends BaseModel {

    // variabel id
    // dengan nama untuk serialisasi
    @SerializedName("id")
    public int Id;

    // variabel name
    // dengan nama untuk serialisasi
    @SerializedName("name")
    public String Name;

    // variabel address
    // dengan nama untuk serialisasi
    @SerializedName("address")
    public String Address;

    // variabel description
    // dengan nama untuk serialisasi
    @SerializedName("description")
    public String Description;

    // variabel id
    // dengan nama untuk serialisasi
    @SerializedName("latitude")
    public double Latitude;

    // variabel id
    // dengan nama untuk serialisasi
    @SerializedName("longitude")
    public double Longitude;

    // variabel id
    // dengan nama untuk serialisasi
    @SerializedName("url_image")
    public String UrlImage;

    // variabel id
    // dengan nama untuk serialisasi
    @SerializedName("distance")
    public double Distance = 1.0;

    // konstruktor standar
    public LocationModel() {
        super();
    }

    // konstruktor dengan
    // 1 parameter
    public LocationModel(int id) {
        Id = id;
    }

    // konstruktor dengan
    // 3 parameter
    public LocationModel(int id, String name, String address, double distance) {
        this.Id = id;
        this.Name = name;
        this.Address = address;
        this.Distance = distance;
    }

    // konstruktor dengan
    // banyak parameter
    // sesuai dengan jumlah
    // variabel di class ini
    public LocationModel(int id, String name, String address, String description, double latitude, double longitude, String urlImage, double distance) {
        this.Id = id;
        this.Name = name;
        this.Address = address;
        this.Description = description;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.UrlImage = urlImage;
        this.Distance = distance;
    }


    // ini adalah fungsi yg digunakan
    // untuk mengkalkulasi jarak
    // antar lokasi user dan data dalam instance
    public double calculateDistance(GeoCoordinates userLocation) {

        // jika lokasi user null
        if (userLocation == null) {

            // balikan nilai 1.0
            return 1.0;
        }

        // menentukan jarak dalam meter
        double dist = Math.sin(Math.toRadians(userLocation.latitude)) * Math.sin(Math.toRadians(this.Latitude)) + Math.cos(Math.toRadians(userLocation.latitude)) * Math.cos(Math.toRadians(this.Latitude)) * Math.cos(Math.toRadians(userLocation.longitude - this.Longitude));

        // dengan menggunakan rumus matimtika acos
        dist = Math.acos(dist);

        // lalu dicari derajatnya
        dist = Math.toDegrees(dist);

        // kalikan dengan 60 dan diameter bumi
        dist = dist * 60 * 1.1515;

        // lalu ubah ke km
        dist = dist * 1.609344; // for KM

        // balikan hasil sebagai nilai
        return dist;
    }
}