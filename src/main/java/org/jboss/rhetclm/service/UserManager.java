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

	/**
	 * Add the User to the underlying datasource. The User must have a unique username.
	 * @param user User to be persisted.
	 * @return Returns the User object back if username is unique; null otherwise.
	 */
	public User add(User user) {
		if(user.getUsername().equals(findUser(user.getUsername())))
			// should invalidate input (eventually)
			return null;
		else
			em.persist(user);
		return user;
	}
	
	/**
	 * Query for a user with the provided username (usernames are unique).
	 * @param username
	 * @return
	 */
	public User findUser(String username) {
		return (User) em.createQuery("from User where username = :username").setParameter("username", username).getSingleResult();
	}
	
	/**
	 * Query for all persisted users.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return (List<User>) em.createQuery("from User").getResultList();
	}
	
	/**
	 * Removes user with provided username from persistence (usernames are unique).
	 * @param username
	 */
	public void remove(String username) {
		User u = findUser(username);
		em.remove(u);
	}
}
