package com.lib.dao;

import com.lib.entities.*;
import java.sql.*;

public class UserDao {
	private Connection con;

	public UserDao(Connection con) {
		this.con = con;
	}

	public boolean isEmailExists(String email) {
	    boolean exists = false;
	    try {
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
	        if (rs.next()) {
	            exists = true;
	        }
	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return exists;
	}

	
	
	
	public boolean registerUser(User user) {
		try {
			Statement st = con.createStatement();
			String query = "INSERT INTO users(name, email, role, password, membership_number) VALUES ('"
					+ user.getName() + "', '" + user.getEmail() + "', '" + user.getRole() + "', '" + user.getPassword()
					+ "', '" + user.getMembershipNumber() + "')";

			return st.executeUpdate(query) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public User loginUser(String membershipNumber, String password) {
		try {
			Statement st = con.createStatement();
			String query = "SELECT * FROM users WHERE membership_number = '" + membershipNumber + "' AND password = '"
					+ password + "'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setPassword(rs.getString("password"));
				user.setMembershipNumber(rs.getString("membership_number"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
