package com.ocha.mcdterdekat.ui.activity.register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ocha.mcdterdekat.R;
import com.ocha.mcdterdekat.di.component.ActivityComponent;
import com.ocha.mcdterdekat.di.component.DaggerActivityComponent;
import com.ocha.mcdterdekat.di.module.ActivityModule;
import com.ocha.mcdterdekat.model.user.UserModel;
import com.ocha.mcdterdekat.ui.activity.home.HomeActivity;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivity;
import com.ocha.mcdterdekat.util.SerializableSave;

import javax.inject.Inject;

import static com.ocha.mcdterdekat.util.StaticVariabel.USER_DATA;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityContract.View {

    // presenter yang akan diinject
    @Inject
    public RegisterActivityContract.Presenter presenter;

    // konteks yang dipakai
    private Context context;

    // deklarasi edittext name email password
    private EditText name,email,password;

    // deklarasi button login
    private Button register;

    // deklarasi button signup
    private TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // panggil fungsi init widget
        initWidget();
    }


    // fungsi kedua untuk menginisialisasi
    // seleurh variabel yg telah dideklarasi
    private void initWidget() {
        this.context = this;

        // memanggil fungsi inject
        injectDependency();

        // ke activity ini
        presenter.attach(this);

        // subscribe presenter
        presenter.subscribe();


        name = findViewById(R.id.name_edittext);
        email = findViewById(R.id.email_edittext);
        password = findViewById(R.id.password_edittext);

        register = findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().isEmpty() || email.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()){
                    return;
                }
                presenter.add(new UserModel(0,name.getText().toString(),email.getText().toString() ,password.getText().toString()),true);
            }
        });

        signIn = findViewById(R.id.sign_in_textview);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LoginActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onAdd(@Nullable UserModel user) {
        if (new SerializableSave(context,USER_DATA).save(user)){
            startActivity(new Intent(context, HomeActivity.class));
            finish();
        }
    }

    @Override
    public void showProgressAdd(Boolean show) {

    }

    @Override
    public void showErrorAdd(String error) {
        Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
    }

    // fungsi yg akan dipanggil saat activity
    // dihancurkan
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
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