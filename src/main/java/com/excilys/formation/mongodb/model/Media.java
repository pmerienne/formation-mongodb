package com.excilys.formation.mongodb.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(Book.class), @JsonSubTypes.Type(CD.class), @JsonSubTypes.Type(DVD.class) })
public abstract class Media {

	private ObjectId _id;

	private Type type;

	private String title;

	private Integer year;

	private List<Address> addresses = new ArrayList<Address>();

	public Media() {
		super();
	}

	public Media(Type type) {
		super();
		this.type = type;
	}

	public Media(Type type, String title) {
		super();
		this.type = type;
		this.title = title;
	}

	public Media(Type type, String title, Integer year) {
		super();
		this.type = type;
		this.title = title;
		this.year = year;
	}

	public Media(Type type, String title, Integer year, List<Address> addresses) {
		super();
		this.type = type;
		this.title = title;
		this.year = year;
		this.addresses = addresses;
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId _id) {
		this._id = _id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
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
		Media other = (Media) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Media [id=" + _id + ", type=" + type + ", title=" + title + ", year=" + year + ", addresses=" + addresses + "]";
	}

}
