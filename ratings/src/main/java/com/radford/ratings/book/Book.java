package com.radford.ratings.book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	private String id;
	
	private String title;
	private String author;
	private Integer pages;
	private Integer rating;
	
	public Book() {}
	
	public Book(String id, String title, String author, Integer pages, Integer rating) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.rating = rating;
	}
	
	public String getid() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Integer getPages() {
		return pages;
	}
	
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
