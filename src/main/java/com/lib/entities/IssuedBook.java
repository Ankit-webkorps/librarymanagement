package com.lib.entities;

import java.sql.Date;

public class IssuedBook {
    private int id;
    private int bookId;
    private int studentId;
    private Date issueDate;
    private Date returnDate;
    private boolean renewed;
    private String bookTitle;
    private int quantity;
    
    private String studentName;

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date date) {
		this.issueDate = date;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date date) {
		this.returnDate = date;
	}
	public boolean isRenewed() {
		return renewed;
	}
	public void setRenewed(boolean renewed) {
		this.renewed = renewed;
	}

    // Getters and Setters


}
