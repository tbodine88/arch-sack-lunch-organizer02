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
                result = rs.getInt("id");
            }
            return result + 1;
        } catch (SQLException sqle) {
            lastError = sqle.getMessage();
            return result;
        } finally {
           if ( charge != null) try {charge.close();} catch(SQLException sqle){}
           if ( rs != null) try {rs.close();} catch(SQLException sqle){}
        } // end finally
    } // end nextIdFor
    private java.sql.Date djava2sql( Date dt){
        java.sql.Date sqlDate = new java.sql.Date( dt.getTime());
        return sqlDate;
    } 
    public Status addPerson(Person yokel) throws SQLException {
        PreparedStatement ps=null;
        Status stat = new Status();
        int id = this.nextIdFor("PERSON","ROWID");
        String cmd= "INSERT INTO PERSON (" +
          "rowid,name,phone,email,can_deliver,updated,committees," +
          "login,password,hint,Success,failure,fail_count,permission )" +
           " values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
	    ps = CONN.prepareStatement(cmd);
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
            ps.execute();
        } catch (SQLException sqle){
            lastError = sqle.getMessage();
            stat.ok= false;
            stat.message = lastError;
            return stat;
        } finally {
           if ( ps != null) try {ps.close();} catch(SQLException sqle){}
        }
        return stat;
    }

    @SuppressWarnings("null")
    Status updatePerson(Person guy) throws SQLException {
        Status stat = new Status();
        int rowsWithThisId =0;
        int rowId =guy.getRowid();
        String cmd="Select count(ROWID) k from PERSON where ROWID=" + rowId ;
        Statement s = CONN.createStatement();
        ResultSet rs = null;
        try {
            rs = s.executeQuery(cmd);
            while(rs.next()){
                rowsWithThisId += rs.getInt("k");
            }
        } catch (SQLException sqle){
            lastError = sqle.getMessage();
            stat.ok = false;
            stat.message=lastError;
            return stat;
        } finally {
           if ( s != null) try {s.close();} catch(SQLException sqle){}
           if ( rs != null) try {rs.close();} catch(SQLException sqle){}
        }
        if ( rowsWithThisId < 1 ){
            stat.ok =false;
            stat.message="No person has the rowid :" + rowId;
            return stat;
        }
        if ( rowsWithThisId > 1 ){
            stat.ok =false;
            stat.message= rowsWithThisId +" people have the same rowid : " + rowId;
            return stat;
            
        }
        PreparedStatement ps = null;
        StringBuilder sb = new StringBuilder("UPDATE PERSON SET name='");
        try {
            String coma = "',";
	    sb.append( guy.getName()).append(coma);
	    sb.append("email='").append(guy.getEmail()).append(coma);
	    sb.append("phone='").append(guy.getPhone()).append(coma);
	    sb.append("can_deliver=")
                    .append(guy.isCan_deliver()? "true" : "false")
                    .append(",");
	    Date dt = guy.getUpdated();
	    sb.append("updated='").append(djava2sql(dt).toString())
                    .append(coma);
	    sb.append("committees='").append(guy.getCommittees()).append(coma);
	    sb.append("login='").append(guy.getLogin()).append(coma);
	    sb.append("password='").append(guy.getPassword()).append(coma);
	    sb.append("hint='").append(guy.getHint()).append(coma);
	    dt = guy.getSuccess();
	    sb.append("Success='").append(djava2sql(dt).toString())
                    .append(coma);
	    dt = guy.getFailure();
	    sb.append("failure='").append(djava2sql(dt).toString())
                    .append(coma);
	    sb.append("fail_count=").append("").append(guy.getFail_count())
                    .append(",");
	    sb.append("permission=").append("")
                    .append(guy.getPermissionAsInteger())
                    .append(" ");
            sb.append("where RowID=").append(guy.getRowid());
            ps = CONN.prepareStatement(sb.toString());
            ps.execute();
        } catch (SQLException sqle){
            lastError = sqle.getMessage();
            stat.ok = false;
            stat.message=lastError;
            return stat;
        } finally {
           if ( ps != null) try {ps.close();} catch(SQLException sqle){}
        }
        return stat;
    }
   
}
