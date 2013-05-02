package com.excilys.formation.mongodb.model;

import java.util.List;

public class Book extends Media {

	private String author;

	private String isbn;

	public Book() {
		super(Type.Book);
	}

	public Book(String author, String isbn) {
		super(Type.Book);
		this.author = author;
		this.isbn = isbn;
	}

	public Book(String title, Integer year, String author, String isbn) {
		super(Type.Book, title, year);
		this.author = author;
		this.isbn = isbn;
	}

	public Book(String title, Integer year, List<Address> addresses, String author, String isbn) {
		super(Type.Book, title, year, addresses);
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
	public String toString() {
		return "Book [id=" + getId() + ", type=" + getType() + ", title=" + getTitle() + ", year=" + getYear() + ", addresses=" + getAddresses() + ", author="
				+ author + ", isbn=" + isbn + "]";
	}
}
