/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 *
 * @author Thomas Bodine
 */
public class ItemVolunteered {
    private int index;
    private Meeting meeting;
    private Item item;
    private Person donor;    

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Person getDonor() {
        return donor;
    }

    public void setDonor(Person donor) {
        this.donor = donor;
    }
    public boolean equals( ItemVolunteered thing) throws BodineToDo{
        throw new BodineToDo();
    } 
    public ItemVolunteered copy (){
        ItemVolunteered xcopy = new ItemVolunteered();
        xcopy.setDonor(this.getDonor());
        xcopy.setIndex(this.getIndex());
        xcopy.setItem(this.getItem());
        xcopy.setMeeting(this.getMeeting());
        return xcopy;
    }
}
