package com.fdmgroup.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConnection {

	private static final String PU_NAME = "HelpdeskPU";
	private static DbConnection instance = null;
	private EntityManagerFactory emf = null;
	
	private DbConnection() {
		init();
	}
	
	private void init() {
		if (emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory(PU_NAME);
		}
	}
	
	public static DbConnection getInstance() {
		if (instance == null) {
			instance = new DbConnection();
		}
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
