package com.ocha.mcdterdekat.ui.activity.map;

import com.ocha.mcdterdekat.model.location.LocationModel;
import com.ocha.mcdterdekat.model.location.RequestLocation;
import com.ocha.mcdterdekat.model.response.ResponseModel;
import com.ocha.mcdterdekat.service.RetrofitService;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MapActivityPresenter implements MapActivityContract.Presenter {

    // inisialisasi komposit disposal
    private CompositeDisposable subscriptions = new CompositeDisposable();

    // inisialisasi pool koneksi
    // ke api backend dengan retrofit
    private RetrofitService api = RetrofitService.create();

    // deklarasi view
    private MapActivityContract.View view;

    @Override
    public void getListLocation(RequestLocation requestLocation, boolean enableLoading) {


        // apakah loading digunakan
        if (enableLoading) {

            // tampilkan loading
            view.showProgressGetListLocation(true);
        }

        // membuat instance thread untuk request
        Disposable subscribe = api.locations(requestLocation.clone())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseModel<ArrayList<LocationModel>>>() {

                    // pada saat berhasil request
                    // dan mendapatkan response
                    @Override
                    public void accept(ResponseModel<ArrayList<LocationModel>> result) throws Exception {

                        // apakah loading digunakan
                        if (enableLoading) {

                            // jangan tampilkan loading
                            view.showProgressGetListLocation((false));
                        }

                        // jika hasil bukan null
                        if (result != null) {

                            // dan error bukan null
                            // dan error kosong
                            if (result.Error != null && !result.Error.isEmpty()) {

                                // tampilkan error
                                view.showErrorGetListLocation((result.Error));
                                return;
                            }

                            // tampilkan data
                            view.onGetListLocation((result.Data));
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
                            view.showProgressGetListLocation((false));
                        }

                        // tampilkan error
                        view.showErrorGetListLocation((throwable.getMessage()));
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
    public void attach(MapActivityContract.View view) {
        this.view = view;
    }


}
