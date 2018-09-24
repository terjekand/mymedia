/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Users", schema = "MY_SCHEMA")

@NamedQueries({
    @NamedQuery(name = "User.validUser", query = "SELECT u FROM User u WHERE u.username =:un AND u.password =:pw"),
    @NamedQuery(name = "User.getUser", query = "SELECT u FROM User u WHERE u.username =:un"),
    @NamedQuery(name = "User.listAllUser", query = "SELECT u FROM User u")
})

public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "USERNAME", nullable = false, updatable = false)
    private String username;
    
    @Column(name = "PASSWORD", nullable = false, updatable = true)
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
    
    
    
}
