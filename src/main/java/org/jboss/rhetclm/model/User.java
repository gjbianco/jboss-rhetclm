package org.jboss.rhetclm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 5L;
	
	@Id
	@GeneratedValue
	private int id;
	
	// Should also be unique!
	@NotNull
	@Size(max = 8)
	private String username;
	
	@NotNull
	@Size(max = 20)
	private String firstname;
	
	@NotNull
	@Size(max = 20)
	private String lastname;
	
	@Size(max = 20)
	private String nickname;
	
	@NotNull
	private Location location;
	private boolean is_in;

	public User() {}
	
	public User(String username, String firstname, String lastname, String nickname, Location location) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.nickname = nickname;
		this.location = location;
		this.is_in = false;
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
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}   
	
	public boolean getIs_in() {
		return this.is_in;
	}

	public void setIs_in(boolean is_in) {
		this.is_in = is_in;
	}
	
	@Override
	public String toString() {
		return firstname + " \"" + nickname + "\" " + lastname + " (" + username + ")";
	}
   
}
