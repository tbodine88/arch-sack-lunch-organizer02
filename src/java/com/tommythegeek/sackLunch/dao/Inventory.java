/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;

/**
 *
 * @author Thomas Bodine
 * 
 * Inventory - list of items and actions needed for a successful sack lunch
 *    packing session
 */
public class Inventory {
    private static final ArrayList<Item> COSA = new ArrayList<>();
    private static final ArrayList<DonationRecord> DONATION = new ArrayList<>();

    public static void dumpThings(ArrayList<String> itemid, ArrayList<String> itemGroup, ArrayList<String> item) {
        String sid;
        Integer id;
        for( Item it : COSA){
            id = it.getId();
            sid = id.toString();
            itemid.add(sid);
            itemGroup.add(it.getCommittee());
            item.add(it.getName());
        }
    }
    
    /**
     * count - number of items in inventory 
     */
    public int quentas; 
    public int donTally;
    public Inventory(){
        quentas = COSA.size();
        donTally = DONATION.size();
    }
    public void reCount(){
        quentas = COSA.size();
        donTally = DONATION.size();
    }
    public static void addThing( String group, String thing){
        Item anItem = new Item();
        anItem.setCommittee(group);
        anItem.setName(thing);
        anItem.setId(COSA.size());
        COSA.add(anItem);
    }
    public static int findThing( String thing){
        for (int i = 0 ; i < COSA.size() ; i++){
            if ( COSA.get(i).getName().equals(thing)){
                return i;
            }
        }
        return -1;
    }
    public static int findThing( String thing, int start){
        for (int i = start ; i < COSA.size() ; i++){
            if ( COSA.get(i).getName().equals(thing)){
                return i;
            }
        }
        return -1;
    }
    public static void deleteThing( int target ){
        if ( COSA.size() > target || target < 0){
            return;
        }
        COSA.remove(target);
    }
    public static void modifyThing ( int target, String group, String name){
        if (  target >= COSA.size() || target < 0){
            return;
        }
        Item thing = COSA.get(target);
        if ( group != null){
            thing.setCommittee(group);
        }
        if ( name != null){
            thing.setName(name);
        }
    }
    public static void addDonation(Person giver, Item thing,Meeting meet){
        DonationRecord dr = new DonationRecord();
        dr.setDonor(giver);
        dr.setItem(thing);
        dr.setMeet(meet);
        dr.setIndex(DONATION.size());
        DONATION.add(dr);
    }
    public static DonationRecord[] getDonations( Meeting meet) throws BodineHasntDoneIt{
        throw new BodineHasntDoneIt("need to get donations for a meeting");
    }
}
