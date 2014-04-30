package org.jboss.rhetclm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.User;

@Stateless
public class UserManager {
	
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	public void register(User user) {
		em.persist(user);
	}
	
	public User findUser(String username) {
		return (User) em.createQuery("from User where username = :username").setParameter("username", username).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return (List<User>) em.createQuery("from User").getResultList();
	}
}
