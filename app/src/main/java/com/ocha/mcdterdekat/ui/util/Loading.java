package com.ocha.mcdterdekat.ui.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ocha.mcdterdekat.R;


// class yang digunakan untuk menampilkan
// layout loading
public class Loading {

    // kontenks yang dipakai
    private Context c;

    // view yang diapakai
    private View v;

    // text yang dipakai
    private TextView message;

    // konstruksi
    public Loading(Context c, View v, String message) {
        this.c = c;
        this.v = v;
        this.message = v.findViewById(R.id.loading_message);

        // set pesan loading
        this.message.setText(message);
    }

    // fungsi set pesan
    public void setMessage(String m) {
        message.setText(m);
    }

    // fungsi untuk set apakah layout
    // akan tampil atau tidak
    public void  setVisibility(Boolean b) {
        v.setVisibility((b ? View.VISIBLE : View.GONE));
    }
}
