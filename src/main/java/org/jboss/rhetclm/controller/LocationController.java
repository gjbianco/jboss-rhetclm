package org.jboss.rhetclm.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.rhetclm.model.Location;
import org.jboss.rhetclm.model.User;

@Model
public class LocationController {
	
	@Inject
	private LocationManager locationManager;
	
	@Inject
	private UserManager userManager;
	
	//@Inject
	private FacesContext fc = FacesContext.getCurrentInstance();
	
	@Produces
	@Named
	private Location newLocation;
	
	private Location foundLocation; // should not be set outside of this class
	public Location getFoundLocation() { return foundLocation; }
	
	private String toFindLocation;
	public String getToFindLocation() { return this.toFindLocation; }
	public void setToFindLocation(String toFindLocation) { this.toFindLocation = toFindLocation; }
	
	private List<User> usersAtLocation; // should not be set outside of this class
	public List<User> getusersAtLocation() { return this.usersAtLocation; }
	
	@PostConstruct
	private void initNewLocation() {
		newLocation = new Location();
	}
	
	// bad way of doing it
	// (httpservletrequest) facescontext.getcurrentinstance.getexternalcontext.getrequest
	
	public void register() {
		try{
			locationManager.add(newLocation);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Location registered!", "Location registration successful.");
			fc.addMessage("register_location", m);
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Location registration failed.", "Registering the location failed.");
			fc.addMessage("register_location", m);
		}
//		if(locationManager.add(newLocation) == null) {
//			// TODO add Faces message saying that registration failed
//		}
		initNewLocation();
	}
	
	/**
	 * Query for location stored in toFindLocation (assumes this is not null).
	 * Stores result in foundLocation.
	 * @return
	 */
	public void findLocation() {
		if(!FacesContext.getCurrentInstance().isPostback() && !FacesContext.getCurrentInstance().isValidationFailed()) {
			this.foundLocation = locationManager.find(toFindLocation);
			this.usersAtLocation = userManager.findByLocation(this.foundLocation);
		}
	}
	
	public List<Location> list() {
		return locationManager.findAll();
	}
	
}
