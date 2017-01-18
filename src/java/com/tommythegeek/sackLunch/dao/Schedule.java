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
 * Schedule - a list of Meetings past to future
 * @author Owner
 */
public class Schedule {
    private final ArrayList<Meeting> theSked;
    private final ArrayList<Committee> congress;
    
    /**
     * Schedule - no argument constructor fills the list of Committees
     *    makes an empty list to hold meetings
     */
    public Schedule(){
        final String[] comName = "first second third fourth fifth".split("[ ]");
        theSked = new ArrayList<>();
        congress = new ArrayList<>();
        int xedni=0;
        for ( String name : comName){
            Committee panel = new Committee();
            panel.setName(name);
            panel.setFullname(name + " Monday");
            panel.setIndex(xedni++);
            congress.add(panel);
        }//end for
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Calendar newday = new GregorianCalendar(year,0,1);
        int monDate = 1 + daysToMonday(newday);
        newday = new GregorianCalendar(year,0,monDate);
        Meeting firstOfYear = new Meeting();
        firstOfYear.setDate(newday.getTime());
        firstOfYear.setCommittee(congress.get(0));
        firstOfYear.setIndex(0);
        theSked.add(firstOfYear);
        for( int wk = 1 ; wk < 18 ; wk++){
            addAnotherMonday();
        }
    }// end Schedule  
    /**
     * returns info about the meeting at index
     * @param index
     * @return 
     */
    public String getMeetingInfo(int index){
        if ( index >= theSked.size() || index < 0){
            return "null";
        }
        Meeting tryst = theSked.get(index);
        return "" + tryst.getIndex() + ": " + tryst.getCommittee().getName() +
                " " + tryst.getDateString();
    }
    /**
     * get the meeting at index in schedule
     * @param index
     * @return 
     */
    public Meeting getMeetingByIndex( int index){
        if ( index >= theSked.size() || index < 0){
            return null;
        }
        Meeting tryst = theSked.get(index);
        return tryst;
    }
    /**
     *  return the schedule size 
     */
    public int size(){
        return theSked.size();
    }
    /**
     * slotsFull() - test that all five Committees are at the end of the Schedule
     * @return 
     */
    public boolean slotsFull(){
        boolean firstFound = false;
        boolean secondFound = false;
        boolean thirdFound = false;
        boolean fourthFound = false;
        boolean fifthFound = false;
        if (theSked.size()< 5 ){
            return false;
        }// end if
        int i = theSked.size();
        while( i > 0 && 
                !(
                firstFound && secondFound && 
                thirdFound && fourthFound && 
                fifthFound
                )){
            Meeting met = theSked.get(i);
            switch(met.getCommittee().getIndex()){
                case 0: firstFound = true; break;
                case 1: secondFound = true; break;
                case 2: thirdFound = true; break;
                case 3: fourthFound = true; break;
                case 4: fifthFound = true; break;
                default:  break;
            }// end switch
            i--;
        }// end while
        return(
                firstFound && 
                secondFound && 
                thirdFound && 
                fourthFound && 
                fifthFound
                );
    }// end slotsFull 
    /**
     * getMeetingInfo the index of the next Monday after today
     * @param today
     * @return 
     */
    public int indexOfNextMonday(Calendar today){
        Date now = today.getTime();
        for(int i = 0 ; i < theSked.size() ; i++){
            Meeting bee = theSked.get(i);
            Date beeDay = bee.getDate();
            if ( now.compareTo(beeDay) < 0 )
                return i;
            }
            return -1;
    }// end indexOfNextMonday
    /**
     *  Are all Committees represented in the schedule after today
     * @param today
     * @return false if a committee is missing
     */
    public boolean slotsFull(Calendar today){
        boolean firstFound = false;
        boolean secondFound = false;
        boolean thirdFound = false;
        boolean fourthFound = false;
        boolean fifthFound = false;
        int i = indexOfNextMonday(today);
        while(i < theSked.size()){
            Meeting met = theSked.get(i);
            switch(met.getCommittee().getIndex()){
                case 0: firstFound = true; break;
                case 1: secondFound = true; break;
                case 2: thirdFound = true; break;
                case 3: fourthFound = true; break;
                case 4: fifthFound = true; break;
                default:  break;
            }// end switch
        }// end while
        return  firstFound && secondFound && thirdFound && fourthFound && fifthFound;
    }
    /**
     *  daysToMonday - calculate how far in the future is Monday
     * @param today
     * @return the number of days until Monday 
     */
    private int daysToMonday(Calendar today ){
        int mondayIsDaysAway;
        int thisWeekDay = today.get(Calendar.DAY_OF_WEEK);
        switch (thisWeekDay){
            case(Calendar.SUNDAY): mondayIsDaysAway =1; break;
            case(Calendar.MONDAY): mondayIsDaysAway =7; break;
            case(Calendar.TUESDAY): mondayIsDaysAway =6; break;
            case(Calendar.WEDNESDAY): mondayIsDaysAway =5; break;
            case(Calendar.THURSDAY): mondayIsDaysAway =4; break;
            case(Calendar.FRIDAY): mondayIsDaysAway =3; break;
            default: mondayIsDaysAway =2; break;
        } // end switch
        return mondayIsDaysAway;
    } // end daysToMonday
    /**
     * addNextMonday - add the Monday after today to the Schedule
     * @return Meeting added
     */
    public Meeting addNextMonday(){
        Meeting bee = new Meeting();
        Calendar today = Calendar.getInstance();
        int mondayIsDaysAway = daysToMonday(today);
        today.add(Calendar.DATE, mondayIsDaysAway);
        int mday = today.get(Calendar.DAY_OF_MONTH);
        Committee chosen = chooseCommittee(mday);
        bee.setCommittee(chosen);
        bee.setDate(today.getTime());
        theSked.add(bee);
        return bee;
    }// end addNextMonday
    /**
     * private chooseCommittee( integer dayOfMonth)
     * @param dayOfMonth - the day of the month such as 3 for the third of august.
     * @return Committee - which contains the committee numbers and name
     */
    private Committee chooseCommittee(int mday){
        int groupIndex = -1;
        do{
            mday -= 7;
            groupIndex++;
        }while(mday > 0);
        return congress.get(groupIndex);
    }// end chooseCommittee

    /**
     * addAnotherMonday - puts another Monday into the schedule
     * @return Meeting added
     */
    public Meeting addAnotherMonday(){
        int lastMeetingIdx = theSked.size() -1;
        Meeting lastMeeting = theSked.get(lastMeetingIdx);
        Date lastMeetingDate = lastMeeting.getDate();
        Calendar card = new GregorianCalendar();
        card.setTime(lastMeetingDate);
        int mondayIsDaysAway = daysToMonday(card);
        card.add(Calendar.DATE, mondayIsDaysAway);
        lastMeeting = new Meeting();
        lastMeeting.setIndex(++lastMeetingIdx);
        lastMeeting.setDate(card.getTime());
        int mday = card.get(Calendar.DAY_OF_MONTH);
        Committee chosen =chooseCommittee(mday);
        lastMeeting.setCommittee(chosen);
        theSked.add(lastMeeting);
        
        return lastMeeting;
    }//end addAnotherMonday
    /**
     * getMeetingInfo the next ordinal Monday meeting
     * @param ordinal
     * @return 
     */
    private Meeting next(int ordinal){
        Meeting moba; //  (sebian communal working party)
        Calendar now = Calendar.getInstance();
        Date today = now.getTime();
        for( int i = 0 ; i < theSked.size() ; i++){
            moba = theSked.get(i); 
            Committee drone = moba.getCommittee();
            if ( drone.getIndex() == ordinal ){
               Date aday = moba.getDate();
               if ( aday.compareTo(today) > 0){
                   return moba;
               }// end if aday
            }// end if drone
        }// end for
        int thisWeekDay = now.get(Calendar.DAY_OF_WEEK);
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
        do{
            now.add(Calendar.DATE, mondayIsDaysAway);
            mondayIsDaysAway =7;
        }while( ordinal != findSlot(now));
        moba = new Meeting();
        moba.setDate(now.getTime());
        moba.setCommittee( congress.get(ordinal));
        moba.setIndex(theSked.size());
        theSked.add(moba);
        return moba;        
    } // end next
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
    public Meeting nextFirst() {
        return next(0);
    }

    public Meeting nextSecond() {
        return next(1);
    }

    public Meeting nextThird() {
        return next(2);
    }

    public Meeting nextFourth() {
        return next(3);
    }

    public Meeting nextFifth() {
        return next(4);
    }

    public Integer indexOf(Meeting tryst) {
        for( int i = 0 ; i < theSked.size() ; i++){
            if ( theSked.get(i).equals(tryst)){
                return i;
            }
        }
        return -1;
    }
}// end class Schedule

