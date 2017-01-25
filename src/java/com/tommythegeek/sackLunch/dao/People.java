package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class People {
    private static final List<Person> CROWD = new ArrayList<>();   
    private static Status stat;

    /**
     * enforce internal id matches location in array
     */
    private static void makeIndexMatchLocation() {
        for ( int i = 0; i < CROWD.size() ; i++){
            Person guy = CROWD.get(i);
            guy.setRowid(i);
        }
    }
    public static void deleteById(int rowId) {
        Person aGuy = CROWD.get(rowId);
        CROWD.remove(aGuy);
        makeIndexMatchLocation();
    }

 
    public int population;
    public People(){
        population = CROWD.size();
        stat = new Status();
    }
    public int updatePop(){
        population = CROWD.size();
        return population;
    }
    
    public static int getNextRowId(){
        return CROWD.size();
    }    
    public static Status validates(Person user) {
        String login = user.getLogin();
        String userPass = user.getPassword();
        Status result = new Status();
        Witness victor;
        victor = new Witness();
        if (login == null && userPass == null){
            result.ok = false;
            result.message = "login and password are null";
            return result;
        }
        if (login == null ){
            result.ok = false;
            result.message = "login is null";
            return result;
        }
        if (userPass == null){
            result.ok = false;
            result.message = "password is null";
            return result;
        }
        result = victor.validUserName(login);
        if (! result.ok){
            return result;
        }
        result = victor.validPassword(userPass);
        if(! result.ok){
          return result;
        }
        for (Person someone: CROWD){
            String accum = someone.getLogin();
            if ( !login.equals(accum)) {
                continue;
            }
            accum = someone.getPassword();
            boolean ok = userPass.equals(accum);
            if ( ok ) {
                result.ok = true;
                result.message = "login match found!";
                user.copy(someone);
                return result;
            }
        }
        result.ok=false;
        result.message = "No login and password combination matches those given";
        return result;
    }
    
    public static boolean authentic(Person user) {
        for (Person yokel : CROWD) {
            if (yokel.getLogin().equals(user.getLogin()) &&
                    yokel.getPassword().equals(user.getPassword()))
                return true;
        }
        return false;
    }
    public static void introduce(Person yokel){
        CROWD.add(yokel);
        yokel.setRowid(CROWD.size()-1); // This is erroneous code
                                        // waiting for data base to change
                                        // to actual index of yokel in data base
    }
    
    public static boolean isInCrowd( String newby){
        // Disallow user names with bad passwords
        Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(newby);
        boolean bad = m.find();
        if (bad){
            return false;
        }
        for( Person yokel : CROWD){
            if( newby.equals(yokel.getLogin())){
               return true;
            }
        }
        return false;
    }
    public static Person personById(int id){
        return CROWD.get(id);
    }
    public static Person personByLogin( String login){
        Person guy;
        for( int i = 0 ; i< CROWD.size() ; i++){
            guy = CROWD.get(i);
            if ( login.equals(guy.getLogin())){
                return guy;
            }
        }
        return null;
    }
    public static Status updateById(int id, Person guy){
        Person target;
        if ( id >= CROWD.size()){
            People.introduce(guy);
            return stat;
        }
        target = CROWD.get(id);
        String svalue;
        int ivalue;
        ivalue = guy.getRowid();
        if (!(ivalue == id) ){
            stat.ok= false;
            stat.message ="rowId doesnt match id";
            return stat;
        } 
        target.copy(guy);
        target.onlyCopyStrings(guy);
        return stat;
    }
    public static Status changeDetailsById(int id, Person guy){
        Person target = CROWD.get(id);
        String svalue;
        int ivalue;
        ivalue = guy.getRowid();
        if (!(ivalue == id) ){
            stat.ok= false;
            stat.message ="rowId doesnt match id";
            return stat;
        } 
        target.onlyCopyStrings(guy);
        return stat;
    }
}
