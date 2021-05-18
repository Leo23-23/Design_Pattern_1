package com.fges.ckonsoru;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.lang.Math;

public class Gestion_time {
    public int Get_hour ( LocalTime time1, String time2){
        Date date1 = Time.valueOf(time1);
        Date date2 = Time.valueOf(time2);
        System.out.println(date1);
        System.out.println(date2);
        //GregorianCalendar cal = new GregorianCalendar();
       // cal.setTime(date2);
        int finH = date2.getHours(); // finH = 10
        int debutH = date1.getHours(); //debutH = 8
        int result_hour = finH - debutH;
        return Math.abs(result_hour) ;
    }
    public int Get_minute ( LocalTime time1, String time2){
        Date date1 = Time.valueOf(time1);
        Date date2 = Time.valueOf(time2);
        //GregorianCalendar cal = new GregorianCalendar();
        //cal.setTime(date2);
        int finM = date2.getMinutes(); // finH = 10
        //cal.setTime(date1);    
        int debutM = date1.getMinutes(); //debutH = 8
        int result_minute = finM - debutM;
        return Math.abs(result_minute);
    }
    public int Get_seconde ( LocalTime time1, String time2){
        Date date1 = Time.valueOf(time1);
        Date date2 = Time.valueOf(time2);
        //GregorianCalendar cal = new GregorianCalendar();
        //cal.setTime(date2);
        int finS = date2.getSeconds(); // finH = 10
        //cal.setTime(date1);
        int debutS = date1.getSeconds(); //debutH = 8
        int result_second = finS - debutS;
        return Math.abs(result_second);
    }
    
}
