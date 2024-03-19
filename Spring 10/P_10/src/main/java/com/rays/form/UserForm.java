package com.rays.form;

import javax.validation.constraints.NotEmpty;

public class UserForm  {
	
	protected Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "firstName is requried")
	public String firstName;

	@NotEmpty(message = "lastname is requried")
	public String lastName;

	@NotEmpty(message = "loginid is requried")
	public String loginId;

	@NotEmpty(message = "password is requried")
	public String password;

	
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

	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
