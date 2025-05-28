package com.lib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lib.entities.Book;
import com.lib.entities.IssuedBook;

public class StudentDao {
	private Connection con;

	public StudentDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean issueBook(int bookId, int studentId, String returnDate, int quantity) {
		try {
			Statement stmt = con.createStatement();

			String issueDate = java.time.LocalDate.now().toString();

			String query = "INSERT INTO issued_books (book_id, student_id, issue_date, return_date, quantity) "
					+ "VALUES (" + bookId + ", " + studentId + ", '" + issueDate + "', '" + returnDate + "', "
					+ quantity + ")";

			int result = stmt.executeUpdate(query);

		
			String updateBooks = "UPDATE books SET quantity = quantity - " + quantity + " WHERE id = " + bookId;
			stmt.executeUpdate(updateBooks);

			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<IssuedBook> getIssuedBooks(int studentId) {
		List<IssuedBook> list = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT ib.*, b.name AS book_title FROM issued_books ib "
					+ "JOIN books b ON ib.book_id = b.id " + "WHERE ib.student_id = " + studentId;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				IssuedBook book = new IssuedBook();
				book.setId(rs.getInt("id"));
				book.setBookId(rs.getInt("book_id"));
				book.setStudentId(rs.getInt("student_id"));
				book.setIssueDate(rs.getDate("issue_date"));
				book.setReturnDate(rs.getDate("return_date"));
				book.setBookTitle(rs.getString("book_title"));
				book.setQuantity(rs.getInt("quantity"));
				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<IssuedBook> getAllIssuedBooksWithDetails() {
		List<IssuedBook> list = new ArrayList<>();

		try {
			String query = "SELECT ib.*, b.name AS book_title, u.name AS student_name " + "FROM issued_books ib "
					+ "JOIN books b ON ib.book_id = b.id " + "JOIN users u ON ib.student_id = u.id";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				IssuedBook book = new IssuedBook();
				book.setId(rs.getInt("id"));
				book.setBookId(rs.getInt("book_id"));
				book.setStudentId(rs.getInt("student_id"));
				book.setIssueDate(rs.getDate("issue_date"));
				book.setReturnDate(rs.getDate("return_date"));
				book.setBookTitle(rs.getString("book_title"));
				book.setStudentName(rs.getString("student_name")); // Add this field to your model
				book.setQuantity(rs.getInt("quantity")); // optional if available

				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean renewBook(int issuedBookId) {
		boolean success = false;
		try {

			String selectQuery = "SELECT return_date FROM issued_books WHERE id = " + issuedBookId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			if (rs.next()) {
				Date returnDate = rs.getDate("return_date");
				LocalDate returnLocalDate = returnDate.toLocalDate();
				LocalDate today = LocalDate.now();

				if (returnLocalDate.equals(today.plusDays(1))) {
					LocalDate newReturnDate = returnLocalDate.plusDays(7);
					String updateQuery = "UPDATE issued_books SET return_date = '" + newReturnDate
							+ "', renewed = true WHERE id = " + issuedBookId;
					int rowsUpdated = stmt.executeUpdate(updateQuery);
					success = rowsUpdated > 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public List<Book> searchBooks(String keyword) {
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM books WHERE name LIKE '%" + keyword + "%'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setEdition(rs.getString("edition"));
				book.setQuantity(rs.getInt("quantity"));
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}

	public boolean returnBook(int issueId) {
	    boolean success = false;
	    try {
	       
	        Statement stmt = con.createStatement();

	        String sql = "DELETE FROM issued_books WHERE id = " + issueId;

	        int rows = stmt.executeUpdate(sql);
	        success = (rows > 0);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return success;
	}


	public List<IssuedBook> getRenewableBooks(int studentId) {
		List<IssuedBook> list = new ArrayList<>();
		try {

			LocalDate tomorrow = LocalDate.now().plusDays(1);
			String query = "SELECT ib.*, b.name AS book_title FROM issued_books ib "
					+ "JOIN books b ON ib.book_id = b.id " + "WHERE ib.student_id = " + studentId
					+ " AND ib.return_date = '" + tomorrow + "'";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				IssuedBook ib = new IssuedBook();
				ib.setId(rs.getInt("id"));
				ib.setBookId(rs.getInt("book_id"));
				ib.setReturnDate(rs.getDate("return_date"));
				ib.setBookTitle(rs.getString("book_title"));
				list.add(ib);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
