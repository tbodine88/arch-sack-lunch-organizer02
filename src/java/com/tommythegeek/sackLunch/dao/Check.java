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
        private ArrayList<Item> list;
        private int first;
        private int last;
    
        public Check() {
          list = new ArrayList<>();
          first=last=-1;
        }
        public int firstIndex(){
            return first;
        }
        public int lastIndex(){
            return last;
        }
        /**
         *  add an item to the check list, or update it if it's there already
         * @param jewel
         * @return 
         */
        public boolean add( Item jewel){
            if ( this.find( jewel) != null ){
                return update(jewel);
            }
            Item emerald = jewel.copy();
            last++;
            if ( first < 0 ){
                first = last;
            }
            emerald.setIndex(last);
            list.add(emerald);
            return true;
        }
        /**
         * 
         * @param jewel - new values for a list entry
         *   if index less than zero then search for name and update group
         *   else search for index and update name and group
         * @return true if jewel was found in list
         */
        public boolean update( Item jewel){
            int index = jewel.getIndex();
            for( Item element : list){
                if ( index < 0){
                    if ( element.getName().equals(jewel.getName())){
                        element.setCommittee(jewel.getCommittee());
                        return true;
                    }// end if element
                } else {
                    if ( index == element.getIndex()){
                        element.setName(jewel.getName());
                        element.setCommittee(jewel.getCommittee());
                        return true;
                    }// end if index
                } // end if index < 0
            }// end for
            return false;
        }// end update
        /**
         * 
         * @param trash - item to remove from list
         * @return true if trash was in list
         */
        public boolean delete( Item trash){
            int index = trash.getIndex();
            for( Item element : list){
                if ( index < 0){
                    if ( element.getName().equals(trash.getName())){
                        list.remove(element);
                        return true;
                    }// end if element
                } else {
                    if ( index == element.getIndex()){
                        list.remove(element);
                        return true;
                    }// end if index
                } // end if index < 0
            }// end for
            return false;
        }
        public Item findByIndex( int facet){
            for ( Item emerald: list){
                if ( emerald.getIndex() == facet){
                    return emerald;
                }
            }
                return null;
        }
        public Item findByName( String name){
            for ( Item emerald: list){
                if ( emerald.getName() == name){
                    return emerald;
                }
            }
                return null;
        }
        public Item find( Item jewel){
            int index = jewel.getIndex();
            for( Item emerald : list){
                if ( index <0 ){
                    if( emerald.getName().equalsIgnoreCase(jewel.getName())){
                        return emerald;
                    }// end if emerald
                } else {
                    if ( index == emerald.getIndex()){
                        return emerald;
                    }// end if index
                }// end if emerald
            }// end for
            return null;
        }
/**
 * 
 * @param dsel - committee number 1,2,3,4 or 5
 * @return list of items required by the committee
 */
    public ArrayList<Item> forCommittee(int dsel) {
        ArrayList<Item> things = new ArrayList<>();
        for ( Item cosa : list){
            String committee = cosa.getCommittee();
            if ( committee.contains(""+dsel))
                things.add(cosa);
       }
        return things;
    }
} // end Check