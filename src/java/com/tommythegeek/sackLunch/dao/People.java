package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class People {
    private static final List<Person> CROWD = new ArrayList<>();   
    public int population;
    public People(){
        population = CROWD.size();
    }
    public int updatePop(){
        population = CROWD.size();
        return population;
    }
    
    public static Status validates(Person user) {
        String login = user.getLogin();
        String userPass = user.getPassword();
        Status result = new Status();
        Validator victor;
        victor = new Validator();
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
        yokel.setRowid(CROWD.size()-1);
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
}
