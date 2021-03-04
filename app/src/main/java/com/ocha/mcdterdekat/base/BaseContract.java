package com.ocha.mcdterdekat.base;

// package ini memiliki satu buah file yakni sebuah base template yang akan di gunakan oleh setiap
// activity dan fragment contract demi menjaga konsistensi dari code pada setiap contract terutama
// fungsi2 yg bersifat mandatory.
public class BaseContract {


    // interface ini akan diimplement
    // ke class presenter dari activity atau fragment
    public interface Presenter<T> {

        // fungsi yg dipanggil saat inisialisasi
        // activity atau fragment
        void subscribe();

        // fungsi yg dipanggil saat
        // activity atau fragment
        // di destroy
        void unsubscribe();

        // fungsi yg dipanggil saat inisialisasi
        // activity atau fragment
        // dengan tambahan parameter view dari activity
        // untuk operasi yg dilakukan
        // pada class presenter
        void attach(T view);
    }

    // interface ini akan diimplement
    // ke class view activity atau fragment
    public interface View {}
}
