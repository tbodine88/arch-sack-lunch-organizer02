/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 * DonationRecord - an entry in the list of donations
 * @author Thomas Nodine
 */
public class DonationRecord {
    private Integer index;
    private Meeting meet;
    private Item item;
    private Person donor;

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the meet
     */
    public Meeting getMeet() {
        return meet;
    }

    /**
     * @param meet the meet to set
     */
    public void setMeet(Meeting meet) {
        this.meet = meet;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the donor
     */
    public Person getDonor() {
        return donor;
    }

    /**
     * @param donor the donor to set
     */
    public void setDonor(Person donor) {
        this.donor = donor;
    }
    
    @Override
    public String toString(){
        return "(" + donor.getName() + " will give " + item.getName() + " )";
    }
}
