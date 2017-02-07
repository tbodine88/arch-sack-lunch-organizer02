/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Thomas Bodine
 */
public class ItemVolunteered {
    private static ArrayList<VolunteerLog> log;
    /**
     * who is donating a butang on date
     * @param date
     * @param butang
     * @return 
     */
    public static Person donor(Date date, String butang) {
        Person guy = new Person();
        if ( log == null){
            return guy;
        }
        for( VolunteerLog entry : log){
            if (entry.day.compareTo(date) == 0 ){
                if( entry.gift.getName().equals(butang))
                    return entry.donor;
            } // end if
        } // end for
        return guy;
    }
    /**
     * 
     * @param butang - thing donated
     * @param date - when it will be or was donated
     * @param giver - the person donating
     * @return true successful always
     */
    public static boolean donate(Item butang, Date date,Person giver){
        VolunteerLog entry = new VolunteerLog(date, giver, butang);
        if ( log == null ){
            log = new ArrayList<>();
        }
        log.add(entry);
        return true;
    } // end donate
    
    
    public static String donators(Calendar today){
        GregorianCalendar wedB4 = (GregorianCalendar) today.clone();
        GregorianCalendar wedafter = (GregorianCalendar) today.clone();
        String [] dow = "Sunday Monday Tuesday Wednesday Thursday Friday Saturday".split("\\s");
        wedB4.add(Calendar.DATE, -4);
        wedafter.add(Calendar.DATE,3);
        StringBuilder sb = new StringBuilder();
        if ( log == null || log.isEmpty()){
            sb.append( "No one has responded that they will appear on ")
                .append(today.get(Calendar.DAY_OF_WEEK))
                .append(" ").append(today.get(Calendar.MONTH ) + 1)
                .append("/").append(today.get(Calendar.DAY_OF_MONTH))
                .append("/").append(today.get(Calendar.YEAR))
		.append("\\n");
        } else {
            String dayname = dow[today.get(Calendar.DAY_OF_WEEK)];
            sb.append("These committee members plan to appear ")
                .append(dayname)
                .append(" ").append(today.get(Calendar.MONTH)+ 1)
                .append("/").append(today.get(Calendar.DAY_OF_MONTH))
                .append("/").append(today.get(Calendar.YEAR))
		.append("\\n");
            for( VolunteerLog entry: log){
                Calendar  entryday = new GregorianCalendar();
                entryday.setTime(entry.day);
                if ( entryday.compareTo(wedB4) >=0 && entryday.compareTo(wedafter)<=0){
                    sb.append(entry.donor.getName())
                        .append(" : ")
                        .append(entry.gift.getName() )
                        .append(",");
                } // end if
            }// end for
            if (sb.length() == 0 ){
                sb.append( "No one has responded that they will appear on ")
                .append(today.get(Calendar.DAY_OF_WEEK))
                .append(" ").append(today.get(Calendar.MONTH)+1)
                .append("/").append(today.get(Calendar.DAY_OF_MONTH))
                .append("/").append(today.get(Calendar.YEAR))
		.append("\\n").append("\\n" + "regards FUUCA Sack lunch organizer");

            }
        } // end if log empty
        return sb.toString();
    } // end donators
    
   } // end ItemVolunteered
