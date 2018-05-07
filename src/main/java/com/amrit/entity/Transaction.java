package com.amrit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
@Immutable
@Entity
public class Transaction {
	@Id
	private long time;
	private double amount;
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		amount = amount;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

}
