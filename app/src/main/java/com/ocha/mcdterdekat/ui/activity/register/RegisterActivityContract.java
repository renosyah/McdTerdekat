package com.ocha.mcdterdekat.ui.activity.register;

import androidx.annotation.Nullable;

import com.ocha.mcdterdekat.base.BaseContract;
import com.ocha.mcdterdekat.model.user.UserModel;
import com.ocha.mcdterdekat.ui.activity.login.LoginActivityContract;

// adalah class contract untuk activity ini
// yg mana class ini akan menghandle
// fungsi-fungsi apa saja yg dibutkan untuk
// komunikasi antar view dengan presenter
public class RegisterActivityContract {

    // inteface view yg akan diimplement oleh
    // view seperti aktivity atau fragment
    public interface View extends BaseContract.View {

        // fungsi response saat mendapatkan data
        public void onAdd(@Nullable UserModel user);

        // fungsi response saat progress atau loading
        public void showProgressAdd(Boolean show);

        // fungsi response saat mendapatkan error
        public void showErrorAdd(String error);
    }

    // inteface presenter yg akan diimplement oleh
    // presenter seperti aktivity presenter atau fragment presenter
    public interface Presenter extends BaseContract.Presenter<RegisterActivityContract.View> {

        // fungsi untyk mendapatkan data
        // wisata kuliner terdekat
        public void add(UserModel user, boolean enableLoading);
    }
}
