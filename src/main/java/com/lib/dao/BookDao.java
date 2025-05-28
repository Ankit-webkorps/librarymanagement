package com.lib.dao;

import com.lib.entities.Book;
import java.sql.*;
import java.util.*;

public class BookDao {
	private Connection con;

	public BookDao(Connection con) {
		this.con = con;
	}

	public boolean deleteBook(int id) {
		boolean f = false;
		try {
			Statement st = this.con.createStatement();

			st.executeUpdate("DELETE FROM issued_books WHERE book_id=" + id);

			int i = st.executeUpdate("DELETE FROM books WHERE id=" + id);
			f = i > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean updateBook(Book book) {
		boolean f = false;
		try {
			Statement st = con.createStatement();
			int i = st.executeUpdate(
					"UPDATE books SET name='" + book.getName() + "', author='" + book.getAuthor() + "', edition='"
							+ book.getEdition() + "', quantity=" + book.getQuantity() + " WHERE id=" + book.getId());
			f = i > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean increaseQuantity(int bookId, int quantity) {
		boolean success = false;
		try {
			String sql = "UPDATE books SET quantity = quantity + ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, bookId);
			int rows = ps.executeUpdate();
			success = (rows > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public boolean updateQuantity(int bookId, int newQuantity) {
		boolean success = false;
		try {
			Statement stmt = con.createStatement();
			int rows = stmt.executeUpdate("UPDATE books SET quantity = " + newQuantity + " WHERE id = " + bookId);
			if (rows > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public boolean addBook(Book book) {
		try {
			Statement st = con.createStatement();
			String query = "INSERT INTO books(name, author, edition, quantity) VALUES('" + book.getName() + "','"
					+ book.getAuthor() + "','" + book.getEdition() + "'," + book.getQuantity() + ")";
			return st.executeUpdate(query) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



	public Book getBookById(int id) {
		try {
			Statement st = con.createStatement();
			String query = "SELECT * FROM books WHERE id=" + id;
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setEdition(rs.getString("edition"));
				book.setQuantity(rs.getInt("quantity"));
				return book;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 public List<Book> getAllBooks() {
	        List<Book> books = new ArrayList<>();
	        try {
	            Statement stmt = con.createStatement();
	            String query = "SELECT * FROM books";
	            ResultSet rs = stmt.executeQuery(query);

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

}
