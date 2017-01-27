/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.Date;

/**
 * log entry for things that will be donated or will be donated
 * @author Thomas Bodine
 */
class VolunteerLog {
    public Date day;
    public Person donor;
    public Item gift;
    public boolean recieved;

    public VolunteerLog(){
        day = new Date();
        donor = new Person();
        gift = new Item();
        recieved = false;
    }    
    public VolunteerLog(Date dia, Person hombre, Item cosa){
        day = dia;
        donor = hombre;
        gift = cosa;
        recieved = false;
    }    
    public VolunteerLog(Date dia, Person hombre, Item cosa, Boolean taken){
        day = dia;
        donor = hombre;
        gift = cosa;
        recieved = taken;
    }    
    
}
