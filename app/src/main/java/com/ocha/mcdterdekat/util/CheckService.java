package com.ocha.mcdterdekat.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Objects;

// ini adalah class check service
// yg memiliki fungsi-fungsi yg
// dapat digunakan untuk
// mengecheck status service pada device
public class CheckService {

    // melakukan cek status koneksi
    // dengan parameter yg dibutuhkan adalah
    // context yg didapat dari activity
    public static Boolean isInternetConnected(Context c) {

        // membuat instance konsi manajer
        ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        // check lagi berhasil dibuat
        // dan hasilnya bukan null maka
        if (connectivityManager !=null)

            // jika koneksi internet yg digunakan adalah
            // data atau wifi aktif maka
            // balikkan nilai true
            if (Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
                return true;
        }

        // jika tidak
        // maka false
        return false;
    }

    // melakukan cek service lokasi
    // dengan parameter yg dibutuhkan adalah
    // context yg didapat dari activity
    public static Boolean isGpsIson(Context c) {

        // membuat instance lokasi manajer
        LocationManager lm = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);

        // deklarasi variabel untuk status
        // gps aktif
        boolean gps_enabled = false;

        // internet aktif
        boolean network_enabled = false;

        // coba
        try {

            // memanggil provider
            // dan jika berhasil
            // akan mengembalikan nilai true
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // jika terjadi exception
            // hiraukan
        } catch(Exception ignore) {}

        // coba
        try {

            // memanggil provider
            // dan jika berhasil
            // akan mengembalikan nilai true
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

          // jika terjadi exception
          // hiraukan
        } catch(Exception ignore) {}

        // balikkan nilai balik
        // gps dan internet
        return gps_enabled && network_enabled;
    }
}
