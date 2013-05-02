package com.excilys.formation.mongodb.model;

public class DVD extends Media {

	public DVD() {
		super(Type.DVD);
	}

	@Override
	public String toString() {
		return "DVD [id=" + getId() + ", type=" + getType() + ", title=" + getTitle() + ", year=" + getYear() + ", addresses=" + getAddresses() + "]";
	}

}
