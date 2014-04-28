package org.jboss.rhetclm.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.User;

public class UserManager {
	
//	@Inject
	@PersistenceContext(unitName = "rhetclm")
	private EntityManager em;
	
	@Inject
	//private Event<User> userEventSrc;
	
	public void register(User user) {
		em.persist(user);
		//userEventSrc.fire(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return (List<User>) em.createQuery("select * from users;").getResultList();
	}
}
