package com.app.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.app.exception.BookException;
import com.app.validation.ValidateBookDetails;

@Component
@Entity
@Table(name = "book")
/**
 * Class that contains Book details
 * */
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id",nullable = false)
	@NotNull
	private Integer book_id;
	@NotNull
	@Column(unique = true)
	private String name;
	@NotNull
	private float rating;
	@NotNull
	private String description;
	@NotNull
	private String author;
	@NotNull
	private Date edition;
	@NotNull
	private double price;
	@NotNull
	private String publisher;
	@NotNull
	private String language;
	@NotNull
	private int stock;
	@NotNull
	private String category;
	@NotNull
	private byte[] image;

	
	public Book() throws IOException {
		super();
	}


	public Book(Integer book_id) throws BookException {
		super();
		ValidateBookDetails.checkNullValue(book_id, "book_id");
		this.book_id = book_id;
	}
	
	public Book(String name, float rating, String description, String author, Date edition, double price,
			String publisher, String language, int stock, String category) throws BookException {
		super();
		ValidateBookDetails.checkNullValue(name, "name");
		this.name = name;
		ValidateBookDetails.checkNullValue(rating, "rating");
		this.rating = rating;
		ValidateBookDetails.checkNullValue(description, "description");
		this.description = description;
		ValidateBookDetails.checkNullValue(author, "author");
		this.author = author;
		ValidateBookDetails.checkNullValue(edition, "edition");
		this.edition = edition;
		ValidateBookDetails.checkNullValue(price, "price");
		this.price = price;
		ValidateBookDetails.checkNullValue(publisher, "publisher");
		this.publisher = publisher;
		ValidateBookDetails.checkNullValue(language, "language");
		this.language = language;
		ValidateBookDetails.checkNullValue(stock, "stock");
		this.stock = stock;
		ValidateBookDetails.checkNullValue(category, "category");
		this.category = category;
	}

	public Book(Integer book_id, String name, float rating, String description, String author, Date edition,
			double price, String publisher, String language, int stock, String category) throws BookException {
		
		this(name,rating,description,author,edition,price,publisher,language,stock,category);
		ValidateBookDetails.checkNullValue(book_id, "book_id");
		this.book_id = book_id;
		
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) throws BookException {
		ValidateBookDetails.checkNullValue(book_id,"book_id");
		this.book_id = book_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws BookException {
		ValidateBookDetails.checkNullValue(name,"name");
		this.name = name;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) throws BookException {
		ValidateBookDetails.checkNullValue(rating,"rating");
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws BookException {
		ValidateBookDetails.checkNullValue(description,"description");
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) throws BookException {
		ValidateBookDetails.checkNullValue(author,"author");
		this.author = author;
	}

	public Date getEdition() {
		return edition;
	}

	public void setEdition(Date edition) throws BookException {
		ValidateBookDetails.checkNullValue(edition,"edition");
		this.edition = edition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws BookException {
		ValidateBookDetails.checkNullValue(price,"price");
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) throws BookException {
		ValidateBookDetails.checkNullValue(publisher,"publisher");
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) throws BookException {
		ValidateBookDetails.checkNullValue(language,"language");
		this.language = language;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) throws BookException {
		ValidateBookDetails.checkNullValue(stock,"stock");
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) throws BookException {
		ValidateBookDetails.checkNullValue(category,"category");
		this.category = category;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", name=" + name + ", rating=" + rating + ", description=" + description
				+ ", author=" + author + ", edition=" + edition + ", price=" + price + ", publisher=" + publisher
				+ ", language=" + language + ", stock=" + stock + ", category=" + category + ", image="
				+ Arrays.toString(image) + "]";
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((book_id == null) ? 0 : book_id.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((edition == null) ? 0 : edition.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + Float.floatToIntBits(rating);
		result = prime * result + stock;
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (book_id == null) {
			if (other.book_id != null)
				return false;
		} else if (!book_id.equals(other.book_id))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (edition == null) {
			if (other.edition != null)
				return false;
		} else if (!edition.equals(other.edition))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (Float.floatToIntBits(rating) != Float.floatToIntBits(other.rating))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

}
