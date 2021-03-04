package com.ocha.mcdterdekat.di.component;

import com.ocha.mcdterdekat.BaseApp;
import com.ocha.mcdterdekat.di.module.ApplicationModule;

import dagger.Component;

// ini adalah interface komponen base
// agar fungsi inject dapat dipanggil
// maka fungsi tersebut sebelumnya harus didelarasi
// di interface ini
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

    // fungsi yg akan digunakan untuk diinject di base
    void inject(BaseApp application);
}