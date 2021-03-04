package com.ocha.mcdterdekat.model.location;

import com.google.gson.annotations.SerializedName;
import com.ocha.mcdterdekat.BuildConfig;

public class RequestLocation {

    @SerializedName("search_by")
    public String SearchBy = "name";

    @SerializedName("search_value")
    public String SearchValue = "";

    @SerializedName("current_latitude")
    public double CurrentLatitude = 0.0;

    @SerializedName("current_longitude")
    public double CurrentLongitude = 0.0;

    @SerializedName("range")
    public int Range = BuildConfig.MIN_RADIUS;

    @SerializedName("order_by")
    public String OrderBy = "name";

    @SerializedName("order_dir")
    public String OrderDir= "ASC";

    @SerializedName("offset")
    public int Offset = 0;

    @SerializedName("limit")
    public int Limit = 100;

    public RequestLocation() {
        super();
    }

    public RequestLocation(String searchBy, String searchValue, double currentLatitude, double currentLongitude, int range, String orderBy, String orderDir, int offset, int limit) {
        SearchBy = searchBy;
        SearchValue = searchValue;
        CurrentLatitude = currentLatitude;
        CurrentLongitude = currentLongitude;
        Range = range;
        OrderBy = orderBy;
        OrderDir = orderDir;
        Offset = offset;
        Limit = limit;
    }

    public RequestLocation clone(){
        return new RequestLocation(
            this.SearchBy,
            this.SearchValue,
            this.CurrentLatitude,
            this.CurrentLongitude,
            this.Range,
            this.OrderBy,
            this.OrderDir,
            this.Offset,
            this.Limit
        );
    }
}
