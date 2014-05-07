package org.jboss.rhetclm.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.rhetclm.model.User;

@Model
public class UserController {
	
	@Inject
	private UserManager userManager;
	
	@Inject
	private LocationManager locationManager;
	
	private FacesContext fc = FacesContext.getCurrentInstance();
	
	@Produces
	@Named
	private User newUser;
	
	@Produces
	@Named
	private String userLocation;
	
	@PostConstruct
	private void initNew() {
		newUser = new User();
		userLocation = "";
	}

	public void register() {
		try {
			newUser.setLocation(locationManager.find(userLocation));
			userManager.add(newUser);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "User registered!", "User was successfully registered.");
			fc.addMessage(null, m);
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User registration failed.", "Registering the user failed.");
			fc.addMessage(null, m);
		}
		initNew();
	}
	
	public List<User> list() {
		return userManager.findAll();
	}
	
}
