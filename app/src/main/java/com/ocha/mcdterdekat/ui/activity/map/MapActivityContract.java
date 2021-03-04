package com.ocha.mcdterdekat.ui.activity.map;

import androidx.annotation.Nullable;

import com.ocha.mcdterdekat.base.BaseContract;
import com.ocha.mcdterdekat.model.location.LocationModel;
import com.ocha.mcdterdekat.model.location.RequestLocation;

import java.util.ArrayList;

public class MapActivityContract {

    // inteface view yg akan diimplement oleh
    // view seperti aktivity atau fragment
    public interface View extends BaseContract.View {

        // fungsi response saat mendapatkan data
        public void onGetListLocation(@Nullable ArrayList<LocationModel> locations);

        // fungsi response saat progress atau loading
        public void showProgressGetListLocation(Boolean show);

        // fungsi response saat mendapatkan error
        public void showErrorGetListLocation(String error);
    }

    // inteface presenter yg akan diimplement oleh
    // presenter seperti aktivity presenter atau fragment presenter
    public interface Presenter extends BaseContract.Presenter<MapActivityContract.View> {

        // fungsi untyk mendapatkan data
        // wisata kuliner terdekat
        public void getListLocation(RequestLocation requestLocation, boolean enableLoading);
    }
}
