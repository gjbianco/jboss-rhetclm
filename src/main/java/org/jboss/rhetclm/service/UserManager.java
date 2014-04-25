package org.jboss.rhetclm.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.rhetclm.model.User;

public class UserManager {
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Event<User> userEventSrc;
	
	public void register(User user) {
		log.info("Registering user: " + user.getUsername() + ": " + user.getLastname());
		em.persist(user);
		userEventSrc.fire(user);
	}
	
	public List<User> findAllUsers() {
		return (List<User>) em.createQuery("select * from users;").getResultList();
	}
}
