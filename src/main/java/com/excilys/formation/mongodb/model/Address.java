package com.excilys.formation.mongodb.model;

import java.util.Arrays;

public class Address {

	private String city;

	private Integer[] coordinates;

	public Address() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Integer[] coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + Arrays.hashCode(coordinates);
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (!Arrays.equals(coordinates, other.coordinates))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", coordinates=" + Arrays.toString(coordinates) + "]";
	}

}
