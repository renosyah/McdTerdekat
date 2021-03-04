package com.ocha.mcdterdekat.ui.activity.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import com.ocha.mcdterdekat.R;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivity;
import com.ocha.mcdterdekat.util.Unit;

import static com.ocha.mcdterdekat.util.StaticVariabel.MY_PERMISSIONS_REQUEST_LOCATION;

// adalah aktivity yg menampilkan splash screen loading
// aplikasi menggukana delay selama 3 detik
public class SplashActivity extends AppCompatActivity {

    // deklarasi konteks
    private Context context;

    // fungsi yg dipanggil saat activity
    // dibuat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initWidget();
    }


    // fungsi kedua untuk menginisialisasi
    // seleurh variabel yg telah dideklarasi
    private void initWidget() {

        // inisialisasi kontekt
        this.context = this;

        // deklarasi dan inisialisasi
        // object handler yg nantinya digunakan
        // untuk menjalankan aksi
        Handler handler = new Handler();

        // sebelum menampilkan map
        // minta izin terlebih dahulu
        // di perangkat user
        requestLocationPermission(new Unit<Boolean>() {

            // jika izin telah disetujui
            @Override
            public void invoke(Boolean o) {

                // 3 detik akan dialihkan ke login activity
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // panggil aktivity menu utama
                        // dan selesai, hancurkan activity ini
                        startActivity(new Intent(context, LoginActivity.class));
                        finish();
                    }
                }, 3000);
            }
        });
    }

    // fungsi yg akan memberikan hasil
    // apakah izin diterima atau tidak
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // check kode permission
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION){

            // restart aktivity
            startActivity(new Intent(context,SplashActivity.class));

            // hancurkan aktivity
            finish();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    // fungsi untuk mengambil dialog
    // meminta izin menggunakan layanan
    // gps dari preangkat
    private void requestLocationPermission(Unit<Boolean> doIt){

        // check jika izin belum diberikan
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // tampilkan dialog
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

        } else {

            // lanjutkan
            doIt.invoke(true);
        }
    }
}