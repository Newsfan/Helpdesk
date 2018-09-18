package com.fdmgroup.model;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="Employee.findAllNonAdmins", 
			query="SELECT e FROM Employee e WHERE NOT EXISTS(SELECT a FROM Admin a WHERE e.id=a.id)")
})
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_emp_id")
	@SequenceGenerator(name="seq_emp_id",sequenceName="seq_emp_id", allocationSize=1)
	@Column(name="employee_id")
	private int id;
	@Column(name="username")
	private String username;
	@Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	private String lastname;
	
	public Employee() {}

	public Employee(int id, String firstname, String lastname, String username) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
