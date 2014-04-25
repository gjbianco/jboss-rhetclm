package org.jboss.rhetclm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity
@Table(name = "locations")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	// Should also be unique!
	@Size(max = 20)
	private String city;

	public Location() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
   
}
