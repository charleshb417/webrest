package com.charlesbishop.webrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appuser")
public class AppUser {
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="userrole")
	private String userrole;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userrole;
	}

	public void setUserRole(String userrole) {
		this.userrole = userrole;
	}

	@Override
	public String toString() {
		return "AppUser [username=" + username + ", password=" + password
				+ ", userrole=" + userrole + "]";
	}
}
