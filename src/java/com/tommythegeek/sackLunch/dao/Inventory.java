/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;

/**
 *
 * @author Thomas Bodine
 */
public class Inventory {
    private final ArrayList<Item> item;
    private final ArrayList<ItemVolunteered> donation;
    private final ArrayList<Committee> committee;
    
    public Inventory(){
        item = new ArrayList<>();
        donation = new ArrayList<>();
        committee = new ArrayList<> ();
    }
    
    public void add( Item thing){
        this.item.add(thing);
    }
    
    public void add (ItemVolunteered gift){
        this.donation.add(gift);
    }
    
    public void add (Committee group){
        this.committee.add(group);
    }
    
    public  int find( Item thing){
        int result;
        for ( Item i : item){
            if ( i.getName().equals(thing.getName())){
                result = i.copy().getIndex();
                return result;
            }
        }
        return -1;
    }
    public  int find( ItemVolunteered thing){
        for ( ItemVolunteered i : donation){
        if ( i.getItem().getName().equals(thing.getItem().getName()))
            return i.getIndex();
        }
        return -1;
    }
    public  int find(Committee thing){
        
        for ( Committee c : committee )
        if ( c.getName().equals(thing.getName())){
            return thing.getIndex();
        }
        return -1;
    }
    public void delete( Item thing ){
	    int target;
	    target = find(thing);
	    if ( target > -1 ){
		    item.remove(target);
	    }
    }
    public void delete( ItemVolunteered thing){
	    int target;
	    target = find(thing);
	    if ( target > -1 ){
		    item.remove(target);
	    }
    }
    public void delete( Committee thing){
	    int target;
	    target = find(thing);
	    if ( target > -1 ){
		    item.remove(target);
	    }
    }
    public Item getItem( int selector ){
        Item thing;
        thing = item.get(selector);
        return thing;
    }
    public ItemVolunteered getDonation( int selector){
        return donation.get(selector);
    }
    
    public Committee getCommittee( int selector){
        return committee.get(selector);
    }
}
