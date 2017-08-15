package com.marta.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.marta.beans.Issue;
import com.marta.beans.User;

import java.sql.Statement;
import java.util.logging.Logger;

public class IssueDAO {
	
	private static IssueDAO instance;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public static IssueDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new IssueDAO();
		}
		
		return instance;
	}

	public List<Issue> getIssues() throws Exception {

		List<Issue> issues = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			con = DatabaseConnect.getConnection();

			String sql = "Select * from Issues order by modification_date";

			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			// process result set
			while (rs.next()) {

				// retrieve data from result set row
				int id = rs.getInt("idissues");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String type = rs.getString("type");
				String priority = rs.getString("priority");
				int assignto = rs.getInt("assignto");
				String state = rs.getString("state");
				int iduser = rs.getInt("iduser");
				Date creation_date = rs.getDate("creation_date");
				Date modification_date = rs.getDate("modification_date");

				//get users
				User user_create = UserDAO.getUser(iduser);
				User user_assignto = UserDAO.getUser(assignto);
				
				// create new issue object
				Issue tempIssue = new Issue(id, title, description, type, priority, user_create, user_assignto, state,creation_date,modification_date);

				// add it to the list of issues
				issues.add(tempIssue);
			}

			return issues;
		} finally {
			DatabaseConnect.close(con);
		}
	}

	public void addIssue(Issue theIssue) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DatabaseConnect.getConnection();

			String sql = "insert into issues (title, description, type,priority,assignto,state,creation_date,modification_date,iduser) values (?,?,?,?,?,?,?,?,?)";

			stmt = con.prepareStatement(sql);

			// set params
			//stmt.setInt(1, theIssue.getId());
			stmt.setString(1, theIssue.getTitle());
			stmt.setString(2, theIssue.getDescription());
			stmt.setString(3, theIssue.getType());
			stmt.setString(4, theIssue.getPriority());
			stmt.setInt(5, theIssue.getAssign_to().getId());
			stmt.setString(6, theIssue.getState());
			stmt.setDate(7, (Date) theIssue.getCreation_date());
			stmt.setDate(8, (Date) theIssue.getLast_modification_date());
			stmt.setInt(9, theIssue.getUser_create().getId());

			stmt.execute();
		} finally {
			DatabaseConnect.close(con);
		}

	}

	public Issue getIssue(int issueId) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnect.getConnection();

			String sql = "select * from issues where idissues=?";

			stmt = con.prepareStatement(sql);

			// set params
			stmt.setInt(1, issueId);

			rs = stmt.executeQuery();

			Issue theIssue = null;

			// retrieve data from result set row
			if (rs.next()) {
				
				int id = rs.getInt("idissues");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String type = rs.getString("type");
				String priority = rs.getString("priority");
				int assignto = rs.getInt("assignto");
				int iduser = rs.getInt("iduser");
				String state = rs.getString("state");
				Date creation_date = rs.getDate("creation_date");
				Date modification_date = rs.getDate("modification_date");
				
				//get users
				User user_create = UserDAO.getUser(iduser);
				User user_assignto = UserDAO.getUser(assignto);
				
				// create new issue object
				theIssue = new Issue(id, title, description, type, priority, user_create, user_assignto, state,creation_date,modification_date);

			} else {
				throw new Exception("Could not find issue id: " + issueId);
			}

			return theIssue;
		} finally {
			DatabaseConnect.close(con);
		}
	}

	public void updateIssue(Issue theIssue) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DatabaseConnect.getConnection();
			
			
			String sql = "update issues " 
						+ " set title=?, description=?, type=?, priority=?, assignto=?, state=?, modification_date=?" 
						+ " where idissues=?";

			stmt = con.prepareStatement(sql);

			// set params
			stmt.setString(1, theIssue.getTitle());
			stmt.setString(2, theIssue.getDescription());
			stmt.setString(3, theIssue.getType());
			stmt.setString(4, theIssue.getPriority());
			stmt.setInt(5, theIssue.getAssign_to().getId());
			stmt.setString(6, theIssue.getState());
			stmt.setDate(7, (Date) theIssue.getLast_modification_date());
			stmt.setInt(8, theIssue.getId());
			
			stmt.execute();
		} finally {
			DatabaseConnect.close(con);
		}

	}

	public void deleteIssue(int issueId) throws Exception {

		Connection con = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;

		try {
			con = DatabaseConnect.getConnection();
			
			String sq11 = "delete from comments where idissues=?";

			String sql2 = "delete from issues where idissues=?";

			stmt1 = con.prepareStatement(sq11);
			stmt2 = con.prepareStatement(sql2);

			// set params
			stmt1.setInt(1, issueId);
			stmt2.setInt(1, issueId);

			stmt1.execute();
			stmt2.execute();
		} finally {
			DatabaseConnect.close(con);
		}
	}

}
