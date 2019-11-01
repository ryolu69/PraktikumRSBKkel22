/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rio
 */
public class Koneksi {
     public static void main(String[] args){
    Koneksi obj_koneksi = new Koneksi();
    System.out.println(obj_koneksi.get_connection());
}
    
public Connection get_connection(){
   Connection connection = null;
   
   try {
       Class.forName("com.mysql.cj.jdbc.Driver");
       connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/kel22", "root", "");
   } catch (Exception e) {
       System.out.println(e);
   }
   return connection;
}
}
