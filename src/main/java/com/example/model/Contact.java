package com.example.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.exception.BusinessException;

@Entity
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	public Contact() {
		this.name = "";
		this.phone = "";
		this.email = "";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		Contact c = (Contact) obj;
		if(this.name.equals(c.getName()) &&
				this.phone.equals(c.getPhone()) &&
				this.email.equals(c.getEmail())) {
			return true;
		} else {
			return false;
		}
	}

	public void validate() throws BusinessException {
		if(this.name == null || this.name.length() == 0 || this.name == "") {
			throw new BusinessException();
		}
	}

}
