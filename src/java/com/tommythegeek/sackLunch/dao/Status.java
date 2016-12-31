/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 *
 * @author Thomas Bodine
 * 
 * Provide more information than true or False 
 */
public class Status {
    public boolean ok;
    public String message;
    public Status() {
        ok = true;
        message = "its ok";
    }
    public boolean fail(){
        return ! ok;
    }
}
