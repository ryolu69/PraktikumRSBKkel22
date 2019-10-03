/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kelompok22;

import javax.ejb.Local;

/**
 *
 * @author Evita Cindy S
 */
@Local
public interface LoginSessionLocal {

    boolean Login(String Nama, String Nim);

    boolean isLoginStatus();

    void setLoginStatus(boolean param);
    
}
