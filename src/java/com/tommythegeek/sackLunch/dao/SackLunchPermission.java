/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 *
 * @author Thomas Bodine
 */
public class SackLunchPermission {
    public final static int MEMBER = 1;
    public final static int FACILITATOR = 8;
    public final static int ADMINISTRATOR = 16;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < SackLunchPermission.FACILITATOR){
        this.value = SackLunchPermission.MEMBER;
        } else if (value < SackLunchPermission.ADMINISTRATOR){
            this.value = SackLunchPermission.FACILITATOR;
        } else
            this.value = SackLunchPermission.ADMINISTRATOR;
    }
    
    public SackLunchPermission(){
        value = SackLunchPermission.MEMBER;
    }
    public SackLunchPermission(int perm){
        if ( perm < SackLunchPermission.FACILITATOR){
            value = SackLunchPermission.MEMBER;
        }else if ( perm < SackLunchPermission.ADMINISTRATOR){
            value = SackLunchPermission.FACILITATOR;
        } else {
            value = SackLunchPermission.ADMINISTRATOR;
        }
    }
    public String toString(){
        switch(value){
            case SackLunchPermission.ADMINISTRATOR:
                return "ADMINISTRATOR";
            case SackLunchPermission.FACILITATOR: 
                return "FACILITATOR";
            default:
                return "MEMBER";
        }
    }
}
    
