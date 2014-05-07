package org.jboss.rhetclm.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.User;

@Stateless
public class UserRegistration {
	
	@PersistenceContext
	private EntityManager em;
	
	public User register(User user) {
		em.persist(user);
		em.flush();
		return user;
	}
}
