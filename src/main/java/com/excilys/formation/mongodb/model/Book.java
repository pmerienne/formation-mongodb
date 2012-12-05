package com.excilys.formation.mongodb.model;

import java.util.Date;
import java.util.List;

public class Book extends Media {

	private String author;

	private String isbn;

	public Book() {
		super();
	}

	public Book(String author, String isbn) {
		super();
		this.author = author;
		this.isbn = isbn;
	}

	public Book(String title, Date year, String author, String isbn) {
		super(title, year);
		this.author = author;
		this.isbn = isbn;
	}

	public Book(String title, Date year, List<Address> addresses, List<String> tags, String author, String isbn) {
		super(title, year, addresses, tags);
		this.author = author;
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", isbn=" + isbn + "]";
	}

}
