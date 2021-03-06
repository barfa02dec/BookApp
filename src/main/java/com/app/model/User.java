package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import com.app.exception.BookException;
import com.app.validation.ValidateUserData;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "user")
 /**
 * Class that contains User details 
 *  */
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id",nullable = false)
	private int user_id;
	@NotNull
	private String name;
	//@JsonIgnore
	@NotNull
	private String password;
	@NotNull
	private String email;
	@NotNull
	private String contact;
	@NotNull
	private String address;
	@NotNull
	private String role;

	public User() { 
		super();
	}
	
	public User(int user_id) throws BookException {
		super();
		ValidateUserData.checkNullValue(user_id, "user_id");
		this.user_id = user_id;
	}

	public User(@NotNull String name, @NotNull String password, @NotNull String email, @NotNull String contact,
			@NotNull String address, @NotNull String role) throws BookException {
		super();
		ValidateUserData.checkNullValue(name, "name");
		this.name = name;
		ValidateUserData.checkNullValue(password, "password");
		this.password = password;
		ValidateUserData.checkNullValue(email, "email");
		this.email = email;
		ValidateUserData.checkNullValue(contact, "contact");
		this.contact = contact;
		ValidateUserData.checkNullValue(address, "address");
		this.address = address;
		ValidateUserData.checkNullValue(role, "role");
		this.role = role;
	}

	public User(int user_id, @NotNull String name, @NotNull String password, @NotNull String email,
			@NotNull String contact, @NotNull String address, @NotNull String role) throws BookException {
		this(name,password,email,contact,address,role);
		ValidateUserData.checkNullValue(user_id, "user_id");
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) throws BookException {
		ValidateUserData.checkNullValue(user_id, "user_id");
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws BookException {
		ValidateUserData.checkNullValue(name, "name");
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws BookException {
		ValidateUserData.checkNullValue(password, "password");
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws BookException {
		ValidateUserData.checkNullValue(email, "email");
		
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) throws BookException {
		ValidateUserData.checkNullValue(contact, "contact");
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws BookException {
		ValidateUserData.checkNullValue(address, "address");
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) throws BookException {
		ValidateUserData.checkNullValue(role, "role");
		this.role=role;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", contact=" + contact + ", address=" + address + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + user_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	
}
