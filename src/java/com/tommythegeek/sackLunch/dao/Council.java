/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;

/**
 * A council is a list of committees
 * @author Thomas Bodine
 */
public class Council {
    public ArrayList<Committee> subcommittee;
    
    public Council(){
        Committee none = new Committee();
        none.setFullname("do nothing committee");
        none.setFullname("none");
        none.setIndex(0);
        subcommittee = new ArrayList<Committee>();
        subcommittee.add(none);
    }
    
}
