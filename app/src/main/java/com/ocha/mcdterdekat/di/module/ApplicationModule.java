package com.ocha.mcdterdekat.di.module;


import android.app.Application;
import com.ocha.mcdterdekat.BaseApp;
import com.ocha.mcdterdekat.di.scope.PerApplication;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

// ini adalah class dimana
// setiap melakukan injecksi
// presenter ke activity
// maka akan di provide presenter
// untuk aktivity yg bersangkutan
// namun kali ini disederhanakan karna
// yg diinject adalah base
@Module
public class ApplicationModule {

    // delarasi variabel base app
    private BaseApp baseApp;

    // konstruktor class
    public ApplicationModule(BaseApp baseApp){
        this.baseApp = baseApp;
    }


    // fungsi untuk provide activity
    // dengan nilai balik adalah variabel base app
    // yg telah diinisialisasi
    @Provides
    @Singleton
    @PerApplication
    public Application provideApplication() {
        return baseApp;
    }
}