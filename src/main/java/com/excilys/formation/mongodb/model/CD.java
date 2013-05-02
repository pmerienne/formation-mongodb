package com.excilys.formation.mongodb.model;

public class CD extends Media {

	public CD() {
		super(Type.CD);
	}

	public CD(String title) {
		super(Type.CD, title);
	}

	public CD(String title, Integer year) {
		super(Type.CD, title, year);
	}

	@Override
	public String toString() {
		return "CD [id=" + getId() + ", type=" + getType() + ", title=" + getTitle() + ", year=" + getYear() + ", addresses=" + getAddresses() + "]";
	}

}
