/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Thomas Bodine
 */
public class DataConn {
    private Connection CONN = null;
    private String lastError = null;
    
    public DataConn(String jdbcUrl ) throws SQLException{
        CONN = DriverManager.getConnection(jdbcUrl);
    }

    public String getLastError() {
        return lastError;
    }

    boolean loadCrowd(List<Person> CROWD) {
        boolean result = false;
        String query = "select ROWID,NAME,PHONE,EMAIL,CAN_DELIVER,UPDATED," +
                "COMMITTEES,LOGIN,PASSWORD,HINT, SUCCESS, FAILURE, FAIL_COUNT" + 
                ", PERMISSION from person";
        Statement charge = null;
        ResultSet rs = null;
        try {
            charge = CONN.createStatement();
            rs = charge.executeQuery(query);
            while( rs.next()){
                Person guy = new Person();
                guy.setRowid(rs.getInt("ROWID"));
                guy.setName(rs.getString("NAME"));
                guy.setPhone(rs.getString("PHONE"));
                guy.setEmail(rs.getString("EMAIL"));
                guy.setCan_deliver(rs.getBoolean("CAN_DELIVER"));
                guy.setUpdated(rs.getDate("UPDATED"));
                guy.setCommittees(rs.getString("COMMITTEES"));
                guy.setLogin(rs.getString("LOGIN"));
                guy.setPassword(rs.getString("PASSWORD"));
                guy.setHint(rs.getString("HINT"));
                guy.setSuccess(rs.getDate("SUCCESS"));
                guy.setFailure(rs.getDate("FAILURE"));
                guy.setFail_count(rs.getInt("FAIL_COUNT"));
                guy.setPermission(rs.getString("SUCCESS"));
                guy.setPermission(rs.getString("FAILURE"));
                guy.setPermission(rs.getString("FAIL_COUNT"));
                CROWD.add(guy);
                result = true;
            }// end while
        } catch (SQLException sqle) {
            lastError = sqle.getMessage();
            return false;
        } finally {
           if ( charge != null) try {charge.close();} catch(SQLException sqle){}
           if ( rs != null) try {rs.close();} catch(SQLException sqle){}
        }
        return result;
    }
   
}
