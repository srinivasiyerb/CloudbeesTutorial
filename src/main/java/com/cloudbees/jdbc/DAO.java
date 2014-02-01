/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.cloudbees.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author harpreet
 */
public class DAO {

    String url = "jdbc:mysql://ec2-23-21-211-172.compute-1.amazonaws.com:3306/countriesdb";
    Connection conn;
    Statement stmt;

    public DAO() {
    }

    public void connect() {

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(url);
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void setConnection (Connection c){
        conn = c;
    }

    public ResultSet getAll() {
        ResultSet rst = null; 
        try{
            stmt = conn.createStatement();
            rst = stmt.executeQuery("use countriesdb; select * from countries");
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst;
    }
    
    public ResultSet getCapital (String country){
        ResultSet rst = null; 
        try{
            stmt = conn.createStatement();
            rst = stmt.executeQuery("SELECT CAPITAL FROM COUNTRIES WHERE COUNTRY = '"+ country+"'");
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst;
        
    }
    
    public void disconnect (){
        try{
            if (conn != null){
                if (stmt != null) stmt.close ();
                
                conn.close ();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
