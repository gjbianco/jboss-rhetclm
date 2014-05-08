package org.jboss.rhetclm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="locations")
public class Location implements Serializable {
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue
	private int id;
	
	// Should also be unique!
	@NotEmpty
	@Size(max = 20)
	private String city;

	public Location() {}
	
	public Location(String city) {
		this.city = city;
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
   
	@Override
	public String toString() {
		return city;
	}
	
}
