/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Items - things used in lunches or actions involved such as delivery
 * @author Thomas Bodine
 */
class Item {
    private int index;
    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Item copy(){
        Item xcopy = new Item();
        xcopy.setIndex( this.getIndex());
        xcopy.setName(this.getName());
        return xcopy;
    }
}
