package com.ocha.mcdterdekat.ui.activity.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ocha.mcdterdekat.R;
import com.ocha.mcdterdekat.model.user.UserModel;
import com.ocha.mcdterdekat.ui.activity.home.HomeActivity;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivity;
import com.ocha.mcdterdekat.util.SerializableSave;

import static com.ocha.mcdterdekat.util.StaticVariabel.USER_DATA;

public class AboutActivity extends AppCompatActivity {

    // konteks yang dipakai
    private Context context;


    private Toolbar toolbar;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // panggil fungsi init widget
        initWidget();
    }

    // fungsi kedua untuk menginisialisasi
    // seleurh variabel yg telah dideklarasi
    private void initWidget() {
        this.context = this;

        // toolbar
        toolbar =  findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        home = findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HomeActivity.class));
                finish();
            }
        });

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
}