/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

/**
 *BodineMap - the Hashes weren't working for strings with same value
 *  trying this instead
 * 
 * @author Thomas Bodine
 */
import java.util.*;
public class BodineMap {
    private final ArrayList <String> thekey;
    private final ArrayList<Object> thing;
    private Integer nextIndex;
    
    public BodineMap(){
        thekey = new ArrayList<>();
        thing = new ArrayList<>();
        nextIndex = 0;
    }
    /**
     * clear out the Map
     */
    public void clear(){
        thekey.clear();
        thing.clear();
        nextIndex = 0;
    }
    
    /**
     * add objects to map
     * @param key  - The index key for map
     * @param cosa - the thing to put in
     * @return 
     */
    public int add( String key, Object cosa){
        boolean found = false;
        int addPoint = 0;
        for( int i = 0 ; i < thekey.size() ; i++){
            if ( key.equals(thekey.get(i))) {
                found = true;
                thing.remove(i);
                thing.add(i,cosa);
                addPoint = i;
            }
        }
        if ( ! found ){
            addPoint = nextIndex;
            thekey.add(nextIndex,key);
            thing.add(nextIndex++,cosa);
        }
        return addPoint;
    }
    public Object get( String key){
        for( int i = 0 ; i < thekey.size() ; i++){
            if ( key.equals(thekey.get(i))) {
                return thing.get(i);
            }
        }
        return null;
    }
}

    
    
