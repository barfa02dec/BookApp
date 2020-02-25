package com.app.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.ForeignKey;
import org.springframework.stereotype.Component;

import com.app.exception.BookException;
import com.app.validation.ValidateOrderData;

@Component
@Entity
@Table(name = "orders")
/**
 * Class that contains Orders details
 * */

public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id",nullable = false)
	@NotNull
	private int order_id;
	@NotNull
	private LocalDate delivery;
	@NotNull
	private String status;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "book_id"))
	private Book book_id;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id"))
	private User user_id;
	
	public Orders() {
		super();
	}

	public Orders(LocalDate delivery, String status, Book book_id, User user_id) throws BookException {
		super();
		ValidateOrderData.checkDataValidation(delivery, "delivery");
		this.delivery = delivery;
		ValidateOrderData.checkDataValidation(status, "status");
		this.status = status;
		ValidateOrderData.checkDataValidation(book_id, "book_id");
		this.book_id = book_id;
		ValidateOrderData.checkDataValidation(user_id, "user_id");
		this.user_id = user_id;
	}

	public Orders(int order_id, LocalDate delivery, String status, Book book_id, User user_id) throws BookException {
		this(delivery,status,book_id,user_id);
		ValidateOrderData.checkDataValidation(order_id, "order_id");
		this.order_id = order_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) throws BookException {
		ValidateOrderData.checkDataValidation(order_id, "order_id");
		this.order_id = order_id;
	}

	public LocalDate getDelivery() {
		return delivery;
	}

	public void setDelivery(LocalDate delivery) throws BookException {
		ValidateOrderData.checkDataValidation(delivery, "delivery");
		this.delivery = delivery;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) throws BookException {
		ValidateOrderData.checkDataValidation(status, "status");
		this.status = status;
	}

	public Book getBook_id() {
		return book_id;
	}

	public void setBook_id(Book book_id) throws BookException {
		ValidateOrderData.checkDataValidation(book_id, "book_id");
		this.book_id = book_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) throws BookException {
		ValidateOrderData.checkDataValidation(user_id, "user_id");
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", delivery=" + delivery + ", status=" + status + ", book_id=" + book_id
				+ ", user_id=" + user_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book_id == null) ? 0 : book_id.hashCode());
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + order_id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		Orders other = (Orders) obj;
		if (book_id == null) {
			if (other.book_id != null)
				return false;
		} else if (!book_id.equals(other.book_id))
			return false;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
		if (order_id != other.order_id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
}
