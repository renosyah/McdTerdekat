package com.ocha.mcdterdekat.ui.activity.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ocha.mcdterdekat.R;
import com.ocha.mcdterdekat.model.user.UserModel;
import com.ocha.mcdterdekat.ui.activity.home.HomeActivity;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivity;
import com.ocha.mcdterdekat.util.SerializableSave;

import static com.ocha.mcdterdekat.util.StaticVariabel.USER_DATA;

public class ProfileActivity extends AppCompatActivity {

    // konteks yang dipakai
    private Context context;

    private UserModel user;

    private Toolbar toolbar;
    private TextView name,email;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // panggil fungsi init widget
        initWidget();
    }

    // fungsi kedua untuk menginisialisasi
    // seleurh variabel yg telah dideklarasi
    private void initWidget() {
        this.context = this;

        // toolbar
        toolbar =  findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        name = findViewById(R.id.name_textview);
        email = findViewById(R.id.email_textview);

        logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SerializableSave(context,USER_DATA).delete()){
                    startActivity(new Intent(context, LoginActivity.class));
                    finish();
                }
            }
        });

        if (new SerializableSave(context,USER_DATA).load() != null){
            user = (UserModel) new SerializableSave(context,USER_DATA).load();
            name.setText(user.Name);
            email.setText(user.Username);
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
}