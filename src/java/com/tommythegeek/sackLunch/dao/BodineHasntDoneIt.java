/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 *
 * @author Thomas Bodine
 */
public class BodineHasntDoneIt extends Exception {

    /**
     * Creates a new instance of <code>BodineHasntDoneIt</code> without detail
     * message.
     */
    public BodineHasntDoneIt() {
    }

    /**
     * Constructs an instance of <code>BodineHasntDoneIt</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BodineHasntDoneIt(String msg) {
        super(msg);
    }
}
