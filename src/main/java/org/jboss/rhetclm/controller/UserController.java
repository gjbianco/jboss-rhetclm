package org.jboss.rhetclm.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.rhetclm.model.User;
import org.jboss.rhetclm.service.UserManager;

@Model
public class UserController {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private UserManager userManager;
	
	@Produces
	@Named
	private User user;
	
	@PostConstruct
	private void initUser() {
		user = new User();
	}
	
	public void register() {
		userManager.register(user);
	}
}
