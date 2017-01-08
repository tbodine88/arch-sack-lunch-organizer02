/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import com.sun.javafx.util.Utils;

/**
 *
 * @author Owner
 */
public class groupMemberChecker {
    private String[] acceptedGroup;
    public groupMemberChecker(){
        acceptedGroup =new String[1];
        acceptedGroup[0]="x";
    };
    public groupMemberChecker( String groupList ){
        acceptedGroup = Utils.split( groupList,",");
    }
    public boolean ok(String groupList){
        for ( String group: acceptedGroup){
            if ( groupList.contains(group)){
                return true;
            }
        }
        return false;
    }
    public boolean reject(String groupList){
        return ! ok(groupList);
    }
}
