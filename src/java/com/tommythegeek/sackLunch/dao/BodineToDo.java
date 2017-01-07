/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 *
 * @author Owner
 */
public class BodineToDo extends Exception {

    /**
     * Creates a new instance of <code>BodineToDo</code> without detail message.
     */
    public BodineToDo() {
    }

    /**
     * Constructs an instance of <code>BodineToDo</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BodineToDo(String msg) {
        super(msg);
    }
}
