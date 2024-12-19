package com.training.api.library.entity.composite;
import java.io.Serializable;
import java.util.Objects;

public class LoanId implements Serializable {

    private String username;
    private Long bookId;

    public LoanId() {}

    public LoanId(String username, Long bookId) {
        this.username = username;
        this.bookId = bookId;
    }

    // Getters, setters, equals, and hashCode
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanId loanId = (LoanId) o;
        return Objects.equals(username, loanId.username) && Objects.equals(bookId, loanId.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, bookId);
    }

	
}
