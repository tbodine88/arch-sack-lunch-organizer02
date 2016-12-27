/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.util.regex.Pattern;

/**
 *
 * @author Thomas Bodine
 */
public class Validator {
    // validate user names passwords and other strings
    private static final Pattern USER_PATTERN = 
              Pattern.compile("^[-\\w._]{6,24}$");
    private static final Pattern PASS_PATTERN = 
              Pattern.compile("^[-\\w\\._]{8,}$");
    private static final Pattern EMAIL_PATTERN = 
              Pattern.compile("^[-\\w._]{1,64}@[-\\w._]{1,127}");
    private static final Pattern PHONE_PATTERN = 
              Pattern.compile("^(\\d*)[-.(]?(\\d{3})[-.)]?(\\d{3})[-_.]?(\\d{4})$");
    private static final Pattern HINT_PATTERN = 
              Pattern.compile("^[-_ \\w]+$");
    public Status result;
    public Validator(){
        result = new Status();
        result.ok= false;
        result.message="nothing valid";
    }
    
    public Status validUserName( String login){
        if (this.result.ok=USER_PATTERN.matcher(login).matches()) {
            this.result.message = "valid login name";
        }else {
            this.result.message = "The Login name must be from six to twenty " + 
                    "four characters. It must " +
                    "only contain numbers, letters, dashes, underscores " 
                    + "and periods.";
        }
        return result;
    }
    public Status validPassword( String userPass){
        if ( this.result.ok = PASS_PATTERN.matcher(userPass).matches()) {
            this.result.message = "valid password";
        }else {
            this.result.message = "The password must be at least eight " + 
                    "characters. It must contain only" +
                    " numbers, letters, dashes, underscores " + 
                    "and periods.";
            
        }
        return this.result;
    }
    public Status validEmail( String email){
        if ( this.result.ok =EMAIL_PATTERN.matcher(email).matches()) {
            this.result.message = "valid email";
        } else {
            this.result.message = "The email should be comprised of two parts " +
                    "separated by the @" +
                    "sign.<br> The first part is no more than 64 characters. The " +
                    "second part is no more than 127 characters. <br>" +
                    " The allowed characters are letters, numbers, periods, " +
                    "underscores and dashes";
        }
        return this.result;
    }
    public Status validPhoneNumber(String number){
        if ( this.result.ok= PHONE_PATTERN.matcher(number).matches() ){
            this.result.message = "valid phone number";
        }else{
            this.result.message = "Phone numbers must be just numbers " +
                    " and punctuation symbols.<br>" +
                    "It should be like 1-222-333-4444";
        }
        return this.result;
    }
    public Status validHint(String hint, String login, String password){
        if ( ! HINT_PATTERN.matcher(hint).matches()){
            this.result.message = "The hint must only contain numbers, letters, " +
                    "dashes, underscores and periods.";
            this.result.ok = false;
            return this.result;
        }
        if (hint.contains(login) || hint.contains(password)){
            this.result.message = "The hint must not contain the password " +
                    "or login.";
            this.result.ok = false;
            return this.result;
        }
        this.result.message = "Good hint!";
        this.result.ok = true;
        return this.result;
    }
    
}
