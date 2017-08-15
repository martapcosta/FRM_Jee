package com.marta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.marta.beans.User;

public class UserDAO {

	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DatabaseConnect.getConnection();
			ps = con.prepareStatement("Select username, password from Users where username = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DatabaseConnect.close(con);
		}
		return false;
	}

	public static boolean userAvailable(String user) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DatabaseConnect.getConnection();
			ps = con.prepareStatement("Select username from Users where username = ?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// result found, means user already exists
				return false;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DatabaseConnect.close(con);
		}
		return true;
	}

	public static boolean create(String user, String password, String email) {

		int i = 0;
		if (user != null && password != null) {
			Connection con = null;
			PreparedStatement ps = null;

			try {

				con = DatabaseConnect.getConnection();
				ps = con.prepareStatement("INSERT INTO users(username, password, email) VALUES(?,?,?)");
				ps.setString(1, user);
				ps.setString(2, password);
				ps.setString(3, email);
				i = ps.executeUpdate();
				System.out.println("Data Added Successfully");

			} catch (SQLException ex) {
				System.out.println("Register error -->" + ex.getMessage());
				return false;
			} finally {
				DatabaseConnect.close(con);
			}
		}
		if (i > 0) {
			return true;
		} else
			return false;
	}

	public static User getUser(String username) {

		Connection con = null;
		PreparedStatement ps = null;

		User user = new User();
		try {
			
			con = DatabaseConnect.getConnection();
			ps = con.prepareStatement("select * from Users where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("idusers"));
				user.setUser_type(rs.getString("user_type"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException ex) {
			System.out.println("Get user error -->" + ex.getMessage());
		} finally {
			DatabaseConnect.close(con);
		}
		return user;
	}
	
	public static User getUser(int userid) {

		Connection con = null;
		PreparedStatement ps = null;

		User user = new User();
		try {
			
			con = DatabaseConnect.getConnection();
			ps = con.prepareStatement("select * from Users where idusers = ?");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("idusers"));
				user.setUser_type(rs.getString("user_type"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException ex) {
			System.out.println("Get user error -->" + ex.getMessage());
		} finally {
			DatabaseConnect.close(con);
		}
		return user;
	}

	public static List<User> getUsers() {
		List<User> users = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			con = DatabaseConnect.getConnection();

			String sql = "select * from users";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			// process result set
			while (rs.next()) {

				User u = new User();
				u.setId(rs.getInt("idusers"));
				u.setUser_type(rs.getString("user_type"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));

				// add it to the list of users
				users.add(u);
			}
		} catch (SQLException ex) {
			System.out.println("GetUsers error -->" + ex.getMessage());
		} finally {
			DatabaseConnect.close(con);
		}
		return users;
	}

}
