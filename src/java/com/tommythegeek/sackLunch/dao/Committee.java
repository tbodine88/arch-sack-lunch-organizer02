/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 * Committee Associates Committee number with name
 * @author Thomas
 */
public class Committee {
    private int index;
    private String name;
    private String fullname;

    public Committee(){
	    index = 0;
	    name = "none";
	    fullname= name;
    }
    public Committee(int i, String n, String f ){
	    index = 1;
	    name = n;
	    fullname = f;
    }
    public int getIndex() {
        return index;
    }

    public String getFullname() {
        return fullname;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
	    return fullname;
    }
}
