package com.lib.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lib.entities.IssuedBook;

public class IssuedBookDao {
    private Connection con;

    public IssuedBookDao(Connection con) {
        this.con = con;
    }

    public boolean issueBook(int bookId, int studentId) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT quantity FROM books WHERE id = " + bookId);
            if (rs.next() && rs.getInt("quantity") > 0) {
                String query = "INSERT INTO issued_books (book_id, student_id, issue_date, return_date) VALUES (" +
                        bookId + ", " + studentId + ", CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY))";
                st.executeUpdate(query);
                st.executeUpdate("UPDATE books SET quantity = quantity - 1 WHERE id = " + bookId);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<IssuedBook> getIssuedBooks(int studentId) {
        List<IssuedBook> list = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM issued_books WHERE student_id = " + studentId);
            while (rs.next()) {
                IssuedBook book = new IssuedBook();
                book.setId(rs.getInt("id"));
                book.setBookId(rs.getInt("book_id"));
                book.setStudentId(rs.getInt("student_id"));
                book.setIssueDate(rs.getDate("issue_date"));
                book.setReturnDate(rs.getDate("return_date"));
                book.setRenewed(rs.getBoolean("renewed"));
                list.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean renewBook(int issueId) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT renewed FROM issued_books WHERE id = " + issueId);
            if (rs.next() && !rs.getBoolean("renewed")) {
                st.executeUpdate("UPDATE issued_books SET return_date = DATE_ADD(return_date, INTERVAL 7 DAY), renewed = true WHERE id = " + issueId);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBook(int issueId) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT book_id FROM issued_books WHERE id = " + issueId);
            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                st.executeUpdate("DELETE FROM issued_books WHERE id = " + issueId);
                st.executeUpdate("UPDATE books SET quantity = quantity + 1 WHERE id = " + bookId);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
