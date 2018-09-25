/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class UserDataBase implements Serializable{
    public static final UserDataBase DB_PELDANY = new UserDataBase();
    @PersistenceContext(unitName = "UsersDB")
    private EntityManager em;
    private UserDataBase(){
        
    }
    
    public static UserDataBase getDataBase(){
        return DB_PELDANY;
    }
   
    public void connectDB() throws Exception{
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("UsersDB");
        em = emFactory.createEntityManager();
    }
    
    public boolean connected(){
        if(em != null && em.isOpen())
            return true;
        return false;
    }
    
    public void disconnectDB(){
        if(connected()){
            em.close();
        }
        em = null;
    }
    
    public User save(User entity) throws IllegalStateException, IllegalArgumentException, Exception {

        if (!connected()) {
            throw new IllegalStateException("Nincs adatb�zis-kapcsolat!");
        }

        if (entity == null) {
            throw new IllegalArgumentException("A mentend� entit�s null!");
        }

        try {
            em.getTransaction().begin();

            if (entity.getId() == null) {
                em.persist(entity);  //új entitás --> persist (insert)
            } else {
                em.merge(entity);    //módosítás --> merge (update)
            }

            em.getTransaction().commit();

            return entity;
        } catch (PersistenceException e) {
            throw new Exception("JPA hiba!", e);
        }
    }

    public void delete(User entity) throws IllegalStateException, IllegalArgumentException, Exception {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatb�zis-kapcsolat!");
        }

        if (entity == null || entity.getId() == 0) {
            throw new IllegalArgumentException("A t�rlend� entit�s null vagy nincs ID-je!");
        }

        try {
            User delEntity = em.find(User.class, entity.getId());

            if (delEntity.getId() == null) {
                throw new IllegalArgumentException("A t�rlend� entit�s nincs az adatb�zisban");
            }

            em.getTransaction().begin();
            em.remove(delEntity);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new Exception("JPA hiba", e);
        }
    }

    public boolean validUser(User user){
        if(!connected()){
            throw new IllegalStateException("Nincs adatb�zis-kapcsolat!");
        }
        try{
             Query query = em.createNamedQuery("User.validUser", User.class);
             query.setParameter("un", user.getUsername());
             query.setParameter("pw", user.getPassword());
             
             @SuppressWarnings("unchecked")
             User entity = (User)query.getSingleResult();
             if(entity != null)
                return true;
             else 
                 return false;
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
    }

    public User getUser(String USERNAME){
        if(!connected()){
            throw new IllegalStateException("Nincs adatb�zis-kapcsolat!");
        }
        try{
             Query query = em.createNamedQuery("User.getUser", User.class);
             query.setParameter("un", USERNAME);
             
             try {
            	 @SuppressWarnings("unchecked")
            	 User entity = (User)query.getSingleResult();
            	 System.out.println(entity);
            	 return entity;
             }catch(NoResultException ex) {
            	 System.err.println(ex);
            	 return null;
             }  
             
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
    }
    
    public List<User> listAllUser(){
        if(!connected()){
            throw new IllegalStateException("Nincs adatb�zis-kapcsolat!t");
        }
        try{
            Query query = em.createNamedQuery("User.listAllUser", User.class);
            
            @SuppressWarnings("unchecked")
            List<User> users = new ArrayList<User>();
            users = query.getResultList();
            return users;
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
    }
}
