package org.jboss.rhetclm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="users")
public class User implements Serializable {

	@Id
	private int id;
	
	// Should also be unique!
	@NotNull
	@Size(max = 5)
	private String initials;
	
	@NotNull
	@Size(max = 20)
	private String firstname;
	
	@NotNull
	@Size(max = 20)
	private String lastname;
	
	@Size(max = 20)
	private String nickname;
	
	private int location_id;
	private boolean is_in;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}   
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}   
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}   
	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}   
	public int getLocation_id() {
		return this.location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}   
	public boolean getIs_in() {
		return this.is_in;
	}

	public void setIs_in(boolean is_in) {
		this.is_in = is_in;
	}
   
}
