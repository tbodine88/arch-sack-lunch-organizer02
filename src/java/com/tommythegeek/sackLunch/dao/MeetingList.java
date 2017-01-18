/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  a list of the next sack lunch meetings
 * @author Thomas Bodine
 */
public class MeetingList {
    public final Meeting first;
    public final Meeting second;
    public final Meeting third;
    public final Meeting fourth;
    public final Meeting fifth;
    public ArrayList<Meeting> meeting;
    private final Schedule source;
    
    public MeetingList(Schedule sked){
        meeting = new ArrayList<>(5);
        meeting.add(first = sked.nextFirst());
        meeting.add(second = sked.nextSecond());
        meeting.add(third = sked.nextThird());
        meeting.add(fourth = sked.nextFourth());
        meeting.add(fifth = sked.nextFifth());
        source = sked;
    }

    public ArrayList<String>  getDates() {
    
        ArrayList<String> card = new ArrayList<>();
        Calendar myCal = new GregorianCalendar();
        String aDate;
        Date day;
        for( Meeting bee : meeting){
          day = bee.getDate();
          myCal.setTime(day);
          int month = myCal.get(Calendar.MONTH)+1;
          int calday = myCal.get(Calendar.DAY_OF_MONTH);
          int year = myCal.get(Calendar.YEAR);
          aDate = String.format("%02d/%02d/%04d",month,calday,year);
          card.add(aDate);
        } // end for
        return card;
    }

    public ArrayList<Integer> getIndices() {
        ArrayList<Integer> idx = new ArrayList<>();
        for ( Meeting tryst : meeting){
            idx.add(source.indexOf(tryst));
        }
        return idx;
    }
}
