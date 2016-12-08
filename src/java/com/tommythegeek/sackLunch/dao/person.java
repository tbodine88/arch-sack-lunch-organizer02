/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Thomas Bodine
 */
public class person implements Serializable {
    
	private int rowid;
	private String name;
	private String    phone;
	private String    email;
	private boolean   can_deliver;
	private Date   updated;
	private String    committees;
	private String    login;
	private String    password;
	private String    hint;
	private Date   Success;
	private Date   failure;
	private int   fail_count;
	private SackLunchPermission  permission;

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCan_deliver() {
        return can_deliver;
    }

    public void setCan_deliver(boolean can_deliver) {
        this.can_deliver = can_deliver;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getCommittees() {
        return committees;
    }

    public void setCommittees(String committees) {
        this.committees = committees;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Date getSuccess() {
        return Success;
    }

    public void setSuccess(Date Success) {
        this.Success = Success;
    }

    public Date getFailure() {
        return failure;
    }

    public void setFailure(Date failure) {
        this.failure = failure;
    }

    public int getFail_count() {
        return fail_count;
    }

    public void setFail_count(int fail_count) {
        this.fail_count = fail_count;
    }

    public SackLunchPermission getPermission() {
        return permission;
    }

    public void setPermission(SackLunchPermission permission) {
        this.permission = permission;
    }

        
    
    public person() {
        
    }
    
    
}
