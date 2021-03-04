package com.ocha.mcdterdekat.di.component;

import com.ocha.mcdterdekat.di.module.ActivityModule;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivity;
import com.ocha.mcdterdekat.ui.activity.map.MapActivity;
import com.ocha.mcdterdekat.ui.activity.register.RegisterActivity;

import dagger.Component;

// ini adalah interface komponen aktivity
// agar fungsi inject dapat dipanggil
// maka fungsi tersebut sebelumnya harus didelarasi
// di interface ini
@Component(modules = { ActivityModule.class })
public interface ActivityComponent {

    // fungsi yg akan digunakan untuk diinject di activity login
    void inject(LoginActivity loginActivity);

    // fungsi yg akan digunakan untuk diinject di activity register
    void inject(RegisterActivity registerActivity);


    // fungsi yg akan digunakan untuk diinject di activity map
    void inject(MapActivity mapActivity);
}
