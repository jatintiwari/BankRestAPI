package com.bank.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	private String username;
	private String password;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Collection<Transactions> tx= new ArrayList<Transactions>();
	
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
	public Collection<Transactions> getTx() {
		return tx;
	}
	public void setTx(Collection<Transactions> tx) {
		this.tx = tx;
	} 

}
