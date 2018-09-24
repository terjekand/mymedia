/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class POST_DataBase {
    public static final POST_DataBase DB_PELDANY = new POST_DataBase();
    @PersistenceContext(unitName = "UsersDB")
    private EntityManager em;
    private POST_DataBase(){
        
    }
    
    public static POST_DataBase getDataBase(){
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
            //log.trace("Disconnected!");
        }
        em = null;
    }
    public Post save(Post entity) throws IllegalStateException, IllegalArgumentException, Exception {

        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }

        if (entity == null) {
            throw new IllegalArgumentException("A mentendõ entitás null!");
        }

        try {
            em.getTransaction().begin();

            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }

            em.getTransaction().commit();

            return entity;
        } catch (PersistenceException e) {
            throw new Exception("JPA hiba!", e);
        }
    }

    public void delete(Post entity) throws IllegalStateException, IllegalArgumentException, Exception {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }

        if (entity == null || entity.getId() == 0) {
            throw new IllegalArgumentException("A törlendõ entitás null vagy nincs ID-je!");
        }

        try {

            User delEntity = em.find(User.class, entity.getId());

            if (delEntity.getId() == null) {
                throw new IllegalArgumentException("A törlendõ entitás nincs az adatbázisban!");
            }

            em.getTransaction().begin();
            em.remove(delEntity);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new Exception("JPA hiba", e);
        }
    }
    
    public List<Post> getPostsOfUser(User user){
        if(!connected()){
            throw new IllegalStateException("Nincs adatbÃ¡zis-kapcsolat");
        }
        try{
            Query query = em.createNamedQuery("Post.getPostsOfUser", Post.class);
            query.setParameter("un", user);
            @SuppressWarnings("unchecked")
            List<Post> posts = new ArrayList<Post>();
            posts = query.getResultList();
            return posts;
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
    }
    
    
}
