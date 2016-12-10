package com.tommythegeek.sackLunch.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class People {
    private static final List<Person> CROWD = new ArrayList<>();   
    private static final Pattern USER_PATTERN = Pattern.compile("^\\w{6,12}$");
    private static final Pattern PASS_PATTERN = Pattern.compile("^[\\w\\.-]{8,16}$");
    
    public static Status validates(Person user) {
        String login = user.getLogin();
        String userPass = user.getPassword();
        Status result = new Status();
        if (login == null && userPass == null){
            result.ok = false;
            result.message = "login and password are null";
        }
        if (login == null ){
            result.ok = false;
            result.message = "login is null";
            return result;
        }
        if (login == null && userPass == null){
            result.ok = false;
            result.message = "password is null";
            return result;
        }
        if (!USER_PATTERN.matcher(login).matches()){
            result.ok = false;
            result.message = "login (" + login + ") is not 6 to 12 word characters in length";
            return result;
        }
   
        if(!PASS_PATTERN.matcher(userPass).matches()){
          result.ok = false;
          return result;
        }
        if (CROWD.stream().anyMatch((someone) -> (login.equals(someone.getLogin()) && userPass.equals(someone.getPassword())))) {
            result.ok = true;
            result.message = "login match found!";
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
    }
    
    public static boolean isInCrowd( Person newby){
        for( Person yokel : CROWD){
            if( (newby.getLogin()).equals(yokel.getLogin())){
               return true;
            }
        }
        return false;
    }
}
