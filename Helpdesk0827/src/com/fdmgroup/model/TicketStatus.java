package com.fdmgroup.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="ticket_status")
@NamedQueries({
	@NamedQuery(name="Status.findAll", query="SELECT s FROM TicketStatus s")
})
public class TicketStatus {
	
	@Id
	@Column(name="status_name")
	private String name;
	@Column(name="note")
	private String note;
	
	public TicketStatus() {}
	
	public TicketStatus(String name, String note) {
		super();
		this.name = name;
		this.note = note;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return name+" " +note;
	}
	
	@Override
	public boolean equals(Object st) {
		// TODO Auto-generated method stub
		return name.equals(((TicketStatus)st).getName());
	}
}
