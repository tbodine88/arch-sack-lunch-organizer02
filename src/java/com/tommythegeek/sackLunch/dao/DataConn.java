/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
   
}
