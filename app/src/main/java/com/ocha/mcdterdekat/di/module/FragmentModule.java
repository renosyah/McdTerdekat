package com.ocha.mcdterdekat.di.module;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

// ini adalah class dimana
// setiap melakukan injecksi
// presenter ke fragment
// maka akan di provide presenter
// untuk aktivity yg bersangkutan
@Module
public class FragmentModule {

    // dekalrasi variabel fragment
    private Fragment fragment;

    // konstruktor class
    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    // fungsi untuk provide activity
    // dengan nilai balik adalah variabel fragment
    // yg telah diinisialisasi
    @Provides
    public Fragment provideFragment() {
        return fragment;
    }
}
