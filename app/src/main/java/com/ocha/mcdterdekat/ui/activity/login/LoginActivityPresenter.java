package com.ocha.mcdterdekat.ui.activity.login;

import com.ocha.mcdterdekat.model.response.ResponseModel;
import com.ocha.mcdterdekat.model.user.UserModel;
import com.ocha.mcdterdekat.service.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {

    // inisialisasi komposit disposal
    private CompositeDisposable subscriptions = new CompositeDisposable();

    // inisialisasi pool koneksi
    // ke api backend dengan retrofit
    private RetrofitService api = RetrofitService.create();

    // deklarasi view
    private LoginActivityContract.View view;

    @Override
    public void login(UserModel user, boolean enableLoading) {

        // apakah loading digunakan
        if (enableLoading) {

            // tampilkan loading
            view.showProgressLogin(true);
        }

        // membuat instance thread untuk request
        Disposable subscribe = api.login(user.clone())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseModel<UserModel>>() {

                    // pada saat berhasil request
                    // dan mendapatkan response
                    @Override
                    public void accept(ResponseModel<UserModel> result) throws Exception {

                        // apakah loading digunakan
                        if (enableLoading) {

                            // jangan tampilkan loading
                            view.showProgressLogin(false);
                        }

                        // jika hasil bukan null
                        if (result != null){

                            // dan error bukan null
                            // dan error kosong
                            if (result.Error != null && !result.Error.isEmpty()){

                                // tampilkan error
                                view.showErrorLogin(result.Error);
                                return;
                            }

                            // tampilkan data
                            view.onLogin(result.Data);
                        }
                    }

                    // pada saat gagal request
                    // dan mendapatkan response error
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        // apakah loading digunakan
                        if (enableLoading) {

                            // jangan tampilkan loading
                            view.showProgressLogin(false);
                        }

                        // tampilkan error
                        view.showErrorLogin(throwable.getMessage());
                    }
                });

        // tambahkan ke subscription
        subscriptions.add(subscribe);
    }

    // untuk saat ini fungsi ini belum dipakai
    @Override
    public void subscribe() {

    }

    // bersihkan seleuruh subscribsi
    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }

    // fungsi inisialisasi view
    @Override
    public void attach(LoginActivityContract.View view) {
        this.view = view;
    }


}


