package com.ocha.mcdterdekat.di.module;

import android.app.Activity;

import com.ocha.mcdterdekat.ui.activity.login.LoginActivityContract;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivityPresenter;
import com.ocha.mcdterdekat.ui.activity.map.MapActivityContract;
import com.ocha.mcdterdekat.ui.activity.map.MapActivityPresenter;
import com.ocha.mcdterdekat.ui.activity.register.RegisterActivityContract;
import com.ocha.mcdterdekat.ui.activity.register.RegisterActivityPresenter;

import dagger.Module;
import dagger.Provides;


// ini adalah class dimana
// setiap melakukan injecksi
// presenter ke activity
// maka akan di provide presenter
// untuk aktivity yg bersangkutan
@Module
public class ActivityModule {

    // dekalrasi variabel activity
    private Activity activity;

    // konstruktor class
    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    // fungsi untuk provide activity
    // dengan nilai balik adalah variabel activity
    // yg telah diinisialisasi
    @Provides
    public Activity provideActivity()  {
        return activity;
    }

    // fungsi untuk provide presenter pada activity login
    @Provides
    public LoginActivityContract.Presenter provideLoginActivityPresenter() {
        return new LoginActivityPresenter();
    }

    // fungsi untuk provide presenter pada activity login
    @Provides
    public RegisterActivityContract.Presenter provideRegisterActivityPresenter() {
        return new RegisterActivityPresenter();
    }

    // fungsi untuk provide presenter pada activity login
    @Provides
    public MapActivityContract.Presenter provideMapActivityPresenter() {
        return new MapActivityPresenter();
    }
}
