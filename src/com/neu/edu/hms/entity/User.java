package com.neu.edu.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author aasha
 */
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="first_name")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,100}", message = "Letters only!")
	String firstName;
	
	@Column(name="last_name")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,100}", message = "Letters only!")
	String lastName;
	
	@Column(name="login")
	@NotNull
	@Size(min=1, message = "is required")
	String login;
	
	@Column(name="password")
	@NotNull
	@Size(min=1, message = "is required")
	String password;
	
	public User() {
		
	}

	public User(String firstName, String lastName, String login, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
