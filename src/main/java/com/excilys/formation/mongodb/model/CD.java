package com.excilys.formation.mongodb.model;

import java.util.Date;
import java.util.List;

public class CD extends Media {

	public CD() {
		super();
	}

	public CD(String title, Date year) {
		super(title, year);
	}

	public CD(String title, Date year, List<Address> addresses, List<String> tags) {
		super(title, year, addresses, tags);
	}
}
