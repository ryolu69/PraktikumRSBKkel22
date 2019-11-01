/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import javax.ejb.Stateless;
import com.model.User; 
import java.util.List;  
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext; 


/**
 *
 * @author WIN 10
 */
@Stateless
public class UserDAO implements UserDAOLocal {
    @PersistenceContext 
    private EntityManager em; 
    @Override 
    public boolean credential(String userName, String password) { 
    List<User> s = (List<User>)em.createQuery("select e from User e where e.userName='"+userName+"' and e.password='"+password+"'").getResultList(); 
    System.out.println("is list empty ?"+s.isEmpty()+" for the"+userName+" and "+password);
    if(!s.isEmpty()) 
    return true; 
    else 
    return false; 
    } 
    @Override 
    public boolean checkUser(String userName) { 
    List<User> s = (List<User>)em.createQuery("select e from User e where e.userName='"+userName+"'").getResultList();
    if(s.isEmpty()) 
    return true; 
    else 
    return false; 
    }
    @Override
    public void addUser(User user){
        em.merge(user); 
        em.flush();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
