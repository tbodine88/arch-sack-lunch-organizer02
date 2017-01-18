/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Owner
 */
public class Meeting {
    private int index;
    private Date date;
    private Committee committee;
    
    public String toString(){
        return String.format("%02d %10s %s", index, 
                committee.getName(), this.getDateString());
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }
    /**
     * 
     * @return a properly formatted dateString mm/dd/yyyy  
     */
    public String getDateString() {
      Calendar myCal = new GregorianCalendar();
      String aDate;
      myCal.setTime(date);
      int month = myCal.get(Calendar.MONTH)+1;
      int calday = myCal.get(Calendar.DAY_OF_MONTH);
      int year = myCal.get(Calendar.YEAR);
      aDate = String.format("%02d/%02d/%04d",month,calday,year);
      return aDate;
    }
    /**
     * true if committee and date are equal
     * @param tryst
     * @return 
     */
    public boolean  equals(Meeting tryst){
       if ( this.committee.equals(tryst.getCommittee())){
           if( this.date.compareTo(tryst.getDate()) == 0){
               return true;
           }
       }
       return false;
    }
    
    
}
