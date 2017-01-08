/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;

/**
 * List of things required for a lunch making
 * @author Thomas Bodine
 */
    public  class Check {
        public ArrayList<Item> list;
        public Check() {
          list = new ArrayList<>();
        }
    }