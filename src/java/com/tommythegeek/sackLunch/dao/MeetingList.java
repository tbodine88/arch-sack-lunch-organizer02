/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Thomas Bodine
 */
public class MeetingList {
    public ArrayList<String> meeting;
    
    public MeetingList(){
        this.meeting= new ArrayList<String>();
        for ( int i = 0 ; i< 5; i++ ){
            this.meeting.add("");
        }
        Calendar nextMeet = Calendar.getInstance();
        // figure out Next Mondays date    
        // Figure out the next meeting date for each group

        int thisWeekDay = nextMeet.get(Calendar.DAY_OF_WEEK);
        int mondayIsDaysAway;
        switch (thisWeekDay){
            case(Calendar.SUNDAY): mondayIsDaysAway =1; break;
            case(Calendar.MONDAY): mondayIsDaysAway =7; break;
            case(Calendar.TUESDAY): mondayIsDaysAway =6; break;
            case(Calendar.WEDNESDAY): mondayIsDaysAway =5; break;
            case(Calendar.THURSDAY): mondayIsDaysAway =4; break;
            case(Calendar.FRIDAY): mondayIsDaysAway =3; break;
            default: mondayIsDaysAway =2; break;
        }
        nextMeet.add(Calendar.DATE, mondayIsDaysAway);
        int dayOfMonth = nextMeet.get(Calendar.DAY_OF_MONTH);
        int slot =0;
        while (dayOfMonth > 0) {
            dayOfMonth -= 7;
            if (dayOfMonth > 0) slot++;
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        for (int ax=0; ax < 5 ; ax++){
            String meet = new String(format1.format(nextMeet.getTime()));
            meeting.set(slot,meet);
            slot = ( slot + 1 ) % 5;
            nextMeet.add(Calendar.DATE,7);
        }
    }
    
}
