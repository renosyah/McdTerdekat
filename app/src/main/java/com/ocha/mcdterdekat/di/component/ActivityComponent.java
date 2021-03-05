package com.ocha.mcdterdekat.di.component;

import com.ocha.mcdterdekat.di.module.ActivityModule;
import com.ocha.mcdterdekat.ui.activity.map.MapActivity;

import dagger.Component;

// ini adalah interface komponen aktivity
// agar fungsi inject dapat dipanggil
// maka fungsi tersebut sebelumnya harus didelarasi
// di interface ini
@Component(modules = { ActivityModule.class })
public interface ActivityComponent {

    // fungsi yg akan digunakan untuk diinject di activity map
    void inject(MapActivity mapActivity);
}
