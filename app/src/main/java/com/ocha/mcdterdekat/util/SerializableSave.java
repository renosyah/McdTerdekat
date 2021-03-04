package com.ocha.mcdterdekat.util;

import android.content.Context;

import com.ocha.mcdterdekat.model.BaseModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// ini adalah class untuk mendeklarasikan
// fungsi yg nantinya akna digunakan berkali kali
// oleh program
// dalam kasus ini adalah penyimpanan cache
public class SerializableSave {

    // deklarasi variabel
    private Context context;

    // deklarasi variabel
    private String filename;

    // konstruktor class
    public SerializableSave(Context context, String filename) {
        this.context = context;
        this.filename = filename;
    }

    // fungsi turunan
    // untuk menyimpan object
    // bertipe seriazable
    public boolean save(Serializable s){

        // deklarasi variabel
        boolean save = false;

        // coba
        try {

            // membuat instance file output stream
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);

            // membuat instance object output stream
            ObjectOutputStream os = new ObjectOutputStream(fos);

            // lalu isi object
            os.writeObject(s);

            // tutup stream
            os.close();

            // tutup koneksi
            fos.close();

            // variabel save di set ke true
            save = true;

            // jika terjadi exception
        } catch (IOException e) {

            // tinggalkan jejak
            // di log console
            e.printStackTrace();
        }

        // kembalikan nilai balik
        // dari fungsi
        return save;
    }

    // fungsi turunan
    // untuk meload object
    // bertipe seriazable
    public Serializable load(){

        // deklarasi variabel dan isi dulu
        // dengan nilai null
        Serializable data = null;

        // coba
        try {

            // membuat instance file input stream
            FileInputStream fis = context.openFileInput(filename);

            // membuat instance object input stream
            ObjectInputStream file = new ObjectInputStream(fis);

            // isi data yg diambil dan dibaca ke
            // variebl yg akan menampung nilai
            data = (Serializable) file.readObject();

            // tutup file
            file.close();

            // tutup koneksi
            fis.close();

            // jika terjadi exception
            // dan class tidak ditemukan
        } catch (IOException | ClassNotFoundException e) {

            // tinggalkan jejak
            // di log console
            e.printStackTrace();
        }

        return data;

    }

    // fungsi turunan
    // untuk menghapus object
    // bertipe seriazable
    public boolean delete(){

        // ubah menjadi file
        // untuk mendaptkan akses operasi
        // terhadap file os
        File f = new File(context.getFilesDir(), filename);

        // balikan status hapus sebagai nilai balik
        return f.delete();
    }

    // ini adalah class sederhana
    // untuk menyimpan cache
    public static class SimpleCache extends BaseModel {

        // deklarasi variabel
        public String data;

        // konstruktor
        public SimpleCache(String data) {
            this.data = data;
        }
    }
}
