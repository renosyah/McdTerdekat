package com.ocha.mcdterdekat.ui.activity.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ocha.mcdterdekat.R;
import com.ocha.mcdterdekat.ui.activity.about.AboutActivity;
import com.ocha.mcdterdekat.ui.activity.map.MapActivity;
import com.ocha.mcdterdekat.ui.activity.profile.ProfileActivity;

public class HomeActivity extends AppCompatActivity {

    // konteks yang dipakai
    private Context context;

    private Toolbar toolbar;

    private CardView menuMap,menuFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // panggil fungsi init widget
        initWidget();
    }

    // fungsi kedua untuk menginisialisasi
    // seleurh variabel yg telah dideklarasi
    private void initWidget() {
        this.context = this;

        // inisialisasi toolbar
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        menuMap = findViewById(R.id.menu_map_cardview);
        menuMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MapActivity.class));
                finish();
            }
        });

        menuFood = findViewById(R.id.menu_food_cardview);
        menuFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // membuat intent untuk browsing
                // menggunakan browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mcdonalds.co.id/menu"));

                // tampilkan activity
                startActivity(browserIntent);
            }
        });

    }

    // pada saat menu toolbar dibuat
    // dan timapilkan
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    // check menu apa saja yang diklik oleh user
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:

                startActivity(new Intent(context, ProfileActivity.class));
                finish();

                return true;
            case R.id.action_about:

                startActivity(new Intent(context, AboutActivity.class));
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}