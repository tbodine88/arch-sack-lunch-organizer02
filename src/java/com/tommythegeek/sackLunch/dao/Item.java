/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 *
 * @author Thomas Bodine
 * 
 * Item - action or thing used in sack lunch preparation
 */
public class Item {
    private Integer itemId;
    private String group;
    private String label;
    
    public Item(){
        itemId = 0;
        group = "none";
        label = "nothing";
    }
    public Item( int id, String committee, String moniker ){
        itemId = id;
        group = committee;
        label = moniker;
    }
    
    
    public int getId(){
        return (int) itemId;
    }
    public void setId( int id){
        itemId = id;
    }
    public String getCommittee(){
        return group;
    }
    public void setCommittee( String committee ){
        group = committee;
    }
    public  String getName (){
        return label;
    }
    public void setName( String moniker){
        label = moniker;
    }
    @Override
    public String toString(){
        return "@item(" + itemId + " " + group + " " + label + ")";
    }
}
