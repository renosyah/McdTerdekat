package com.ocha.mcdterdekat.ui.activity.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.gestures.GestureType;
import com.here.sdk.gestures.TapListener;
import com.here.sdk.mapviewlite.CameraObserver;
import com.here.sdk.mapviewlite.CameraUpdate;
import com.here.sdk.mapviewlite.MapMarker;
import com.here.sdk.mapviewlite.MapScene;
import com.here.sdk.mapviewlite.MapStyle;
import com.here.sdk.mapviewlite.MapViewLite;
import com.ocha.mcdterdekat.R;
import com.ocha.mcdterdekat.di.component.ActivityComponent;
import com.ocha.mcdterdekat.di.component.DaggerActivityComponent;
import com.ocha.mcdterdekat.di.module.ActivityModule;
import com.ocha.mcdterdekat.model.location.LocationModel;
import com.ocha.mcdterdekat.model.location.RequestLocation;
import com.ocha.mcdterdekat.model.user.UserModel;
import com.ocha.mcdterdekat.ui.activity.home.HomeActivity;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivity;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivityContract;
import com.ocha.mcdterdekat.util.SerializableSave;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.ocha.mcdterdekat.util.StaticVariabel.USER_DATA;
import static com.ocha.mcdterdekat.util.StaticVariabel.ZOOM_LEVEL;
import static com.ocha.mcdterdekat.util.StaticVariabel.createLocationMarker;
import static com.ocha.mcdterdekat.util.StaticVariabel.createUserMarker;

public class MapActivity extends AppCompatActivity implements MapActivityContract.View {

    // presenter yang akan diinject
    @Inject
    public MapActivityContract.Presenter presenter;

    // konteks yang dipakai
    private Context context;

    private Toolbar toolbar;

    private ArrayList<LocationModel> locationModels = new ArrayList<>();

    private MapViewLite mapView;
    private ArrayList<MapMarker> markers = new ArrayList<>();

    // deklarasi service lokasi manager
    private LocationManager locationManager;

    // deklarasi user lokasi
    private GeoCoordinates userCoordinate;

    // deklarasi user marker
    // yg akan dipakai di map
    private MapMarker userMarker;

    private ImageView userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // panggil fungsi init widget
        initWidget(savedInstanceState);
    }

    // fungsi kedua untuk menginisialisasi
    // seleurh variabel yg telah dideklarasi
    private void initWidget(Bundle savedInstanceState) {
        this.context = this;

        // memanggil fungsi inject
        injectDependency();

        // ke activity ini
        presenter.attach(this);

        // subscribe presenter
        presenter.subscribe();

        // toolbar
        toolbar =  findViewById(R.id.map_toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // inisialisasi map view
        mapView = findViewById(R.id.map_view);

        // panggil fungsi on create mapview
        mapView.onCreate(savedInstanceState);

        userLocation = findViewById(R.id.user_location_imageview);
        userLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapView.getCamera().setTarget(userCoordinate);
                mapView.getCamera().setZoomLevel(ZOOM_LEVEL);
            }
        });

        loadMapScene();
    }

    // fungsi untuk menampilkan map view
    private void loadMapScene() {

        // check kondisi pada saat map view berhasil diload
        mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(@Nullable MapScene.ErrorCode errorCode) {

                // jika tidak ada error
                if (errorCode == null) {

                    // gunakan lokasi akakom
                    // sebagai target default
                    mapView.getCamera().setTarget(new GeoCoordinates(-7.792810, 110.408499));
                    mapView.getCamera().setZoomLevel(ZOOM_LEVEL);
                }

                // panggil fungsi lokasi manajer
                setLocationManager();

                // panggil fungsi set gestur
                // dimapview
                setTapGestureHandler();
            }
        });
    }

    // fungsi untuk inisialisasi
    // lokasi manajer agar dapat menggunakan
    // service GPS di perangkat user
    @SuppressLint("MissingPermission")
    private void setLocationManager(){

        // inisilisasi service untuk lokasi manajer
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // jika tidak kosong
        // panggil service
        // untuk mendapatkan lokasi user
        if (locationManager != null)
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {

                // pada saat mendapatkan lokasi user
                @Override
                public void onLocationChanged(Location location) {


                    // jika koordinat user belun diisi
                    if (userCoordinate == null){

                        // panggil fungsi untuk mendaptkan
                        // lokasi wisata kuliner terdekat
                        getAllNearestLocation(new GeoCoordinates(location.getLatitude(),location.getLongitude()),true);
                    }

                    // inisialisasi kordinat user
                    userCoordinate = new GeoCoordinates(location.getLatitude(),location.getLongitude());

                    // coba
                    try {

                        // jika marker user tidak null
                        if (userMarker != null){

                            // hapus marker user
                            mapView.getMapScene().removeMapMarker(userMarker);

                            // kosongkan marker user
                            userMarker = null;
                        }

                        // inisialisasi marker user
                        userMarker = createUserMarker(context,userCoordinate);

                        // tambahkan marker user
                        // ke mapview
                        mapView.getMapScene().addMapMarker(userMarker);

                        // jika terjadi exception
                        // di hiraukan aja
                    }catch (NullPointerException ignore){}
                }

                // untuk fungsi lokasi berubah
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                // untuk fungsi jika provider di aktifkan
                @Override
                public void onProviderEnabled(String provider) {

                }

                // untuk fungsi jika provider di non-aktifkan
                @Override
                public void onProviderDisabled(String provider) {

                }
            },null);
    }

    // fungsi untuk mengatur gestur interaksi user di map
    private void setTapGestureHandler() {

        // atur gestur pada map view
        // untuk interaksi tap
        mapView.getGestures().setTapListener(new TapListener() {
            @Override
            public void onTap(@NotNull Point2D touchPoint) {

            }
        });

        // non aktifkan gestur double tap karna tidak dipakai
        mapView.getGestures().disableDefaultAction(GestureType.DOUBLE_TAP);

        // non aktifkan gestur double finger tap karna tidak dipakai
        mapView.getGestures().disableDefaultAction(GestureType.TWO_FINGER_TAP);

        // non aktifkan gestur double fingger pan karna tidak dipakai
        mapView.getGestures().disableDefaultAction(GestureType.TWO_FINGER_PAN);

        // fungsi untuk menon aktifkan rotasi kamera
        mapView.getCamera().addObserver(new CameraObserver() {

            // pada saat status kamera berubah
            @Override
            public void onCameraUpdated(@NonNull CameraUpdate cameraUpdate) {

                // check, jika bearingnya bukan 0
                // atau menghadap keutara maka
                if (cameraUpdate.bearing != 0) {

                    // paksakan menghadap keutara
                    mapView.getCamera().setBearing(0);
                }
            }
        });
    }

    @Override
    public void onGetListLocation(@Nullable ArrayList<LocationModel> locations) {

        // untuk setiap data array di response
        for (LocationModel r : locations){

            // akan dipanggil fungsi untuk
            // menunjukan jarak
            r.Distance = r.calculateDistance(userCoordinate);

            // memanggil fungsi untuk membuat marker lokasi
            MapMarker m = createLocationMarker(context,r);

            // tambahkan ke array marker
            markers.add(m);

            // tampilkan marker dimap
            mapView.getMapScene().addMapMarker(m);
        }

        // tambahkan semua data response ke array
        // data wisata kuliner
        locationModels.addAll(locations);


        // jika array tidak kosong
        if (locationModels.size() > 0) {

            // arahkan kamera ke marker
            // data lokasi wisata kuliner
            // posisi pertama
            mapView.getCamera().setTarget(new GeoCoordinates(locationModels.get(0).Latitude,locationModels.get(0).Longitude));

            // setting tingkatan zoom kamera
            mapView.getCamera().setZoomLevel(ZOOM_LEVEL);
        }

    }

    @Override
    public void showProgressGetListLocation(Boolean show) {

    }

    @Override
    public void showErrorGetListLocation(String error) {
        Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
    }

    // fungsi untuk mendapatkan data
    // lokasi wisata kuliner terdekat
    private void getAllNearestLocation(GeoCoordinates userCoordinate, boolean loading){

        RequestLocation location = new RequestLocation();
        location.CurrentLatitude = userCoordinate.latitude;
        location.CurrentLongitude = userCoordinate.longitude;

        // panggil fungsi presenter
        presenter.getListLocation(location,loading );
    }

    // fungsi untuk menghilangkan marker
    private void removeRestaurantMarker(){

        // untuk setiap marker di array
        for (MapMarker m : markers){

            // hilangkan marker pada map
            mapView.getMapScene().removeMapMarker(m);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(context, HomeActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(context, HomeActivity.class));
        finish();
    }


    // fungsi yg akan dipanggil saat
    // activity dihancurkan
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // memanggil fungsi destroy di map view
        mapView.onDestroy();

        // memanggil fungsi unsubscribe
        presenter.unsubscribe();
    }

    // fungsi yg akan dipanggil saat
    // activity di pause
    @Override
    protected void onPause() {
        super.onPause();

        // memanggil fungsi pause di map view
        mapView.onPause();
    }

    // fungsi yg akan dipanggil saat
    // activity dilanjutkan
    @Override
    protected void onResume() {
        super.onResume();

        // memanggil fungsi dilanjutkan di map view
        mapView.onResume();
    }


    // pemanggilan register
    // dependensi injeksi untuk aktivity ini
    private void injectDependency(){
        ActivityComponent listcomponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();

        listcomponent.inject(this);
    }
}