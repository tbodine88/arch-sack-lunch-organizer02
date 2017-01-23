/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
                int permNumber = rs.getInt("PERMISSION");
                guy.setPermission(permNumber);
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
    int nextIdFor(String table, String colName){
        String query = "Select max(" + colName +") id from " + table ;
        Statement charge = null;
        ResultSet rs = null;
        int result = -1;
        try {
            charge = CONN.createStatement();
            rs = charge.executeQuery(query);
            while( rs.next()){
                rs.getInt("id");
            }
            return result + 1;
        } catch (SQLException sqle) {
            lastError = sqle.getMessage();
            return result;
        } finally {
           if ( charge != null) try {charge.close();} catch(SQLException sqle){}
           if ( rs != null) try {rs.close();} catch(SQLException sqle){}
        }
    }
    private java.sql.Date djava2sql( Date dt){
        java.sql.Date sqlDate = new java.sql.Date( dt.getTime());
        return sqlDate;
    } 
    public boolean addPerson(Person yokel) throws SQLException {
        int id = this.nextIdFor("PERSON","ROWID");
        String cmd= "INSERT INTO PERSON (" +
          "rowid,name,phone,email,can_deliver,updated,committees," +
          "login,password,hint,Success,failure,fail_count,permission )" +
           " values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = CONN.prepareStatement(cmd);
        ps.setInt(1, id);
        ps.setString(2, yokel.getName());
        ps.setString(3, yokel.getPhone());
        ps.setString(4, yokel.getEmail());
        ps.setBoolean(5, yokel.isCan_deliver());
        Date dt = yokel.getUpdated();
        ps.setDate(6, djava2sql(dt));
        ps.setString(7, yokel.getCommittees());
        ps.setString(8, yokel.getLogin());
        ps.setString(9, yokel.getPassword());
        ps.setString(10, yokel.getHint());
        dt = yokel.getSuccess();
        ps.setDate(11, djava2sql(dt));
        dt = yokel.getFailure();
        ps.setDate(12, djava2sql(dt));
        ps.setInt(13, yokel.getFail_count());
        int perm = yokel.getPermissionAsInteger();
        ps.setInt(14, perm);
        try{
            ps.execute();
        } catch (SQLException sqle){
            lastError = sqle.getMessage();
            return false;
        } finally {
           if ( ps != null) try {ps.close();} catch(SQLException sqle){}
        }
        return true;
    }
   
}
