package com.ocha.mcdterdekat.ui.activity.register;

import com.ocha.mcdterdekat.model.response.ResponseModel;
import com.ocha.mcdterdekat.model.user.UserModel;
import com.ocha.mcdterdekat.service.RetrofitService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivityPresenter implements RegisterActivityContract.Presenter {

    // inisialisasi komposit disposal
    private CompositeDisposable subscriptions = new CompositeDisposable();

    // inisialisasi pool koneksi
    // ke api backend dengan retrofit
    private RetrofitService api = RetrofitService.create();

    // deklarasi view
    private RegisterActivityContract.View view;

    @Override
    public void add(UserModel user, boolean enableLoading) {

        // apakah loading digunakan
        if (enableLoading) {

            // tampilkan loading
            view.showProgressAdd(true);
        }

        // membuat instance thread untuk request
        Disposable subscribe = api.add(user.clone())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseModel<String>>() {

                    // pada saat berhasil request
                    // dan mendapatkan response
                    @Override
                    public void accept(ResponseModel<String> result) throws Exception {

                        // apakah loading digunakan
                        if (enableLoading) {

                            // jangan tampilkan loading
                            view.showProgressAdd(false);
                        }

                        // jika hasil bukan null
                        if (result != null) {

                            // dan error bukan null
                            // dan error kosong
                            if (result.Error != null && !result.Error.isEmpty()) {

                                // tampilkan error
                                view.showErrorAdd(result.Error);
                                return;
                            }

                            // tampilkan data
                            view.onAdd(user.clone());
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
                            view.showProgressAdd(false);
                        }

                        // tampilkan error
                        view.showErrorAdd(throwable.getMessage());
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
    public void attach(RegisterActivityContract.View view) {
        this.view = view;
    }


}