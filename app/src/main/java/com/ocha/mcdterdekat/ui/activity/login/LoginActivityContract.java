package com.ocha.mcdterdekat.ui.activity.login;

import androidx.annotation.Nullable;

import com.ocha.mcdterdekat.base.BaseContract;
import com.ocha.mcdterdekat.model.location.LocationModel;
import com.ocha.mcdterdekat.model.user.UserModel;

// adalah class contract untuk activity ini
// yg mana class ini akan menghandle
// fungsi-fungsi apa saja yg dibutkan untuk
// komunikasi antar view dengan presenter
public class LoginActivityContract {

    // inteface view yg akan diimplement oleh
    // view seperti aktivity atau fragment
    public interface View extends BaseContract.View {

        // fungsi response saat mendapatkan data
        public void onLogin(@Nullable UserModel user);

        // fungsi response saat progress atau loading
        public void showProgressLogin(Boolean show);

        // fungsi response saat mendapatkan error
        public void showErrorLogin(String error);
    }

    // inteface presenter yg akan diimplement oleh
    // presenter seperti aktivity presenter atau fragment presenter
    public interface Presenter extends BaseContract.Presenter<View> {

        // fungsi untyk mendapatkan data
        // wisata kuliner terdekat
        public void login(UserModel user, boolean enableLoading);
    }
}
