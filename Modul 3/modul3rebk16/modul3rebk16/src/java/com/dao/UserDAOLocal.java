/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;
import javax.ejb.Local;

/**
 *
 * @author WIN 10
 */
@Local
public interface UserDAOLocal {
    public boolean credential(String userName, String password);
    public boolean checkUser (String userName);
    void addUser (User user);
}
