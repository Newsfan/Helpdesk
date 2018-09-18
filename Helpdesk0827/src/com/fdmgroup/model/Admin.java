package com.fdmgroup.model;

import javax.persistence.*;

@Entity
@Table(name="admin")
@PrimaryKeyJoinColumn(name = "employee_id")
@NamedQueries({
	@NamedQuery(name="Admin.findByUsername", 
			query="SELECT a FROM Admin a WHERE a.id = (SELECT e.id FROM Employee e WHERE e.username = :username)"),
	@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
})
public class Admin extends Employee {
	
	public Admin() {}
	public Admin(Employee e) {
		this.setFirstname(e.getFirstname());
		this.setLastname(e.getLastname());
		this.setUsername(e.getUsername());
		this.setId(e.getId());
	}
}
