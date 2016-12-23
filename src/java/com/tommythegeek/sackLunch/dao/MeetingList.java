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
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
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
        while( slotsAreEmpty(this.meeting)){
            nextMeet.add(Calendar.DATE, mondayIsDaysAway);
            mondayIsDaysAway = 7;
            int slot = findSlot( nextMeet);
            if (this.meeting.get(slot).equals(""))
                this.meeting.set(slot, format1.format(nextMeet.getTime()));
        }
    }

    private boolean slotsAreEmpty(ArrayList<String> meeting) {
        for (String slot: meeting){
            if ( slot.equals(""))
                return true;
        }
        return false;
    }

    private int findSlot(Calendar nextMeet) {
        int day = nextMeet.get(Calendar.DAY_OF_MONTH);
        int slot = 0;
        while ( day > 0){
            day -= 7;
            if ( day > 0)
                slot++;
        }
        return slot;
    }
    
}
