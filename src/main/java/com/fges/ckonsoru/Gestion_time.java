package com.fges.ckonsoru;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Gestion_time {
   public Date dateStr_to_Date ( String date) throws ParseException{
    Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    return date1;
   }
   public LocalDateTime dateDate_to_LocalDateTime(Date date){
    LocalDateTime date2 = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    return date2;
   }
   public long Duration_2_LocalDateTime_to_SECOND (LocalDateTime date_debut , LocalDateTime date_fin){
    long diff = ChronoUnit.SECONDS.between(date_debut,  date_fin);
    return diff;
   }
   public long Get_Duration_Day(LocalDateTime date_debut , LocalDateTime date_fin){
    long diff = ChronoUnit.DAYS.between(date_debut,  date_fin);
    return diff;
   }
   public long Get_Duration_Hour(long diff_hour){
       return diff_hour /3600%24;
   }
   public long Get_Duration_Minutes(long diff_hour){
       return diff_hour /60%60;
   }
   public long Get_Duration_Seconds(long diff_hour){
       return diff_hour %60;
   }
   public String date_heure ( String date , String heure){
       return date +" "+heure;
   }
   public String hh_mm_ss (long heure, long minute, long second){
       return heure+":"+minute+":"+second;
   }

   public String  traitement_date(LocalDateTime now, String date)throws ParseException {
       long dif_heure;
       long dif_minute;
       long dif_second;
       // je commence par transformer la date d' annulation en date
        Date madate1= this.dateStr_to_Date(date);
        //je vais transformer cette date en localdatetime
        LocalDateTime madate2 = this.dateDate_to_LocalDateTime(madate1);
        //je vais faire la difference entre mes 2 localdatetime
        long diff_date = this.Duration_2_LocalDateTime_to_SECOND(now, madate2);
        //je prends chaque difference : 
        dif_heure = this.Get_Duration_Hour(diff_date);
        dif_minute = this.Get_Duration_Minutes(diff_date);
        dif_second = this.Get_Duration_Seconds(diff_date);

        return this.hh_mm_ss(dif_heure, dif_minute, dif_second);
   }


   public long  traitement_H24(LocalDateTime now, String date)throws ParseException {
    // je commence par transformer la date d' annulation en date
    Date madate1= this.dateStr_to_Date(date);
    //je vais transformer cette date en localdatetime
   LocalDateTime madate2 = this.dateDate_to_LocalDateTime(madate1);
    //je vais faire la difference entre mes 2 localdatetime
    long diff_jour = this.Get_Duration_Day(now, madate2);
    //je prends la difference en heure : 
    //dif_jour = this.Get_Duration_Hour(diff_jour);
    return diff_jour;
   }    
}
