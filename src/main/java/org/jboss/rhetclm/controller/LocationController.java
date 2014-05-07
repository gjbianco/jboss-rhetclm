package org.jboss.rhetclm.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.rhetclm.model.Location;

@Stateless
@Named
public class LocationController {
	
	@Inject
	private LocationManager locationManager;
	
	//@Inject
	private FacesContext fc;
	
	@Produces
	@Named
	private static Location location;
	
	@PostConstruct
	private void init() {
		fc = FacesContext.getCurrentInstance();
		location = new Location();
	}
	
	public void register() {
		if(locationManager.add(location) == null) {
			// should probably elicit a message since adding has failed, for some reason
		}
	}
	
	public List<Location> list() {
		return locationManager.findAll();
	}
	
}
