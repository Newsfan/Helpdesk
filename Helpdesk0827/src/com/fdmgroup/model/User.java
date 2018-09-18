package com.fdmgroup.model;

import javax.persistence.*;

@Entity
@Table(name="user_password")
@NamedQueries({
	@NamedQuery(name="User.findByUsername", query="SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name="User.findByUsernameAndPassword", query="SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
})
public class User {

	@Id
	@Column(name="employee_id")
	private int id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
}
