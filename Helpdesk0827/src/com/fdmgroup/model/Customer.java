package com.fdmgroup.model;

import javax.persistence.*;

@Entity
@Table(name="customer")
@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c"),
	@NamedQuery(name="Customer.findById", query="SELECT c FROM Customer c WHERE c.id = :id"),
	@NamedQuery(name="Customer.findByFname", query="SELECT c FROM Customer c WHERE c.fname = :fname"),
	@NamedQuery(name="Customer.findByLname", query="SELECT c FROM Customer c WHERE c.lname = :lname"),
	@NamedQuery(name="Customer.findByFullName", query="SELECT c FROM Customer c WHERE c.fname = :fname AND c.lname = :lname")
})
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_customer_id")
	@SequenceGenerator(name="seq_customer_id",sequenceName="seq_customer_id", allocationSize=1)
	@Column(name = "customer_id")
	private int id;
	@Column(name = "first_name")
	private String fname;
	@Column(name = "last_name")
	private String lname;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	
	public Customer() {}
	
	public Customer(String fname, String lname, String phone, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
