package org.jboss.rhetclm.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.rhetclm.model.User;

@Stateless
@Named
public class UserController {
	
	@Produces
	@Named
	private static User user = new User();
	
	@Inject
	private UserManager userManager;
	
	public void register(User user) {
		userManager.add(user);
	}
	
	public List<User> list() {
		return userManager.findAll();
	}
	
}
