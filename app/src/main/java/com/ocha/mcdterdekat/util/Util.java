package com.ocha.mcdterdekat.util;

import java.util.Calendar;
import java.util.Date;

public class Util {

    // fungsi untuk menambah tanggal
    public static Date getDate(int daysAdded){
        Date dt = new  Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, daysAdded);
        return c.getTime();
    }
}
