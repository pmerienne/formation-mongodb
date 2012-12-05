package com.excilys.formation.mongodb.model;

import java.util.Date;
import java.util.List;

public class DVD extends Media {

	public DVD() {
		super();
	}

	public DVD(String title, Date year) {
		super(title, year);
	}

	public DVD(String title, Date year, List<Address> addresses, List<String> tags) {
		super(title, year, addresses, tags);
	}
}
