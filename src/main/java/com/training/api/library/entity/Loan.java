package com.training.api.library.entity;

import java.time.LocalDate;

import com.training.api.library.entity.composite.LoanId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
@IdClass(LoanId.class) // Composite primary key class
public class Loan {
	
	@Id
	@Column(name="username")
	private String username;
	
	@Id
	@Column(name="book_id")
	private int bookId;
	
	@Column(name="loan_date")
	private LocalDate loanDate;
	
	@Column(name="loan_due_date")
	private LocalDate loanDueDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getLoanDueDate() {
		return loanDueDate;
	}

	public void setLoanDueDate(LocalDate loanDueDate) {
		this.loanDueDate = loanDueDate;
	}

	@Override
	public String toString() {
		return "Loan [username=" + username + ", bookId=" + bookId + ", loanDate=" + loanDate + ", loanDueDate="
				+ loanDueDate + "]";
	}
	
}
