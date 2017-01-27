/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;
import java.util.Date;

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
    }

    
