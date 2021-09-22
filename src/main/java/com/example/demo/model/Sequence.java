package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

//@Entity
//@Table(name = "my_seq")
public class Sequence {
	@Id
	private long id;

	private long next_val;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNext_val() {
		return next_val;
	}

	public void setNext_val(long next_val) {
		this.next_val = next_val;
	}

	public Sequence(long id, long next_val) {
		super();
		this.id = id;
		this.next_val = next_val;
	}
}
