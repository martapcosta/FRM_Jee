package com.marta.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.marta.beans.Comment;
import com.marta.beans.User;

public class CommentDAO {

	private static CommentDAO instance;
	private Logger logger = Logger.getLogger(getClass().getName());

	public static CommentDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new CommentDAO();
		}

		return instance;
	}

	public List<Comment> getComments() throws Exception {

		List<Comment> comments = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			con = DatabaseConnect.getConnection();

			String sql = "Select * from Comments order by cdate";

			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			// process result set
			while (rs.next()) {

				// retrieve data from result set row
				int id = rs.getInt("id");
				int idissues = rs.getInt("idissues");
				String content = rs.getString("content");
				int author_id = rs.getInt("author");
				Date cdate = rs.getDate("cdate");

				// get users
				User author = UserDAO.getUser(author_id);

				// create new comment object
				Comment tempComment = new Comment(author, content, id, cdate, idissues);

				// add it to the list of comments
				comments.add(tempComment);
			}

			return comments;
		} finally {
			DatabaseConnect.close(con);
		}
	}

	public List<Comment> getCommentsByIssue(int issueId) throws Exception {

		List<Comment> comments = new ArrayList<>();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			con = DatabaseConnect.getConnection();

			String sql = "select * from Comments where idissues = ? order by cdate";

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, issueId);

			rs = stmt.executeQuery();

			// process result set
			while (rs.next()) {

				// retrieve data from result set row
				int id = rs.getInt("id");
				int idissues = rs.getInt("idissues");
				String content = rs.getString("content");
				int author_id = rs.getInt("author");
				Date cdate = rs.getDate("cdate");

				// get users
				User author = UserDAO.getUser(author_id);

				// add it to the list of comments
				comments.add(new Comment(author, content, id, cdate, idissues));
			}

			return comments;
		} finally {
			DatabaseConnect.close(con);
		}
	}

	public void addComment(Comment theComment) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DatabaseConnect.getConnection();

			String sql = "insert into comments (idissues,author,cdate,content) values (?,?,?,?)";

			stmt = con.prepareStatement(sql);

			// set params
			stmt.setInt(1, theComment.getIssues_id());
			stmt.setInt(2, theComment.getCommentby().getId());
			stmt.setDate(3, new Date(theComment.getCreation_date().getTime()));
			stmt.setString(4, theComment.getComment_text());

			stmt.execute();
		} finally {
			DatabaseConnect.close(con);
		}

	}

	public Comment getComment(int commentId) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnect.getConnection();

			String sql = "select * from comments where id=?";

			stmt = con.prepareStatement(sql);

			// set params
			stmt.setInt(1, commentId);

			rs = stmt.executeQuery();

			Comment thecomment = null;

			// retrieve data from result set row
			if (rs.next()) {

				int id = rs.getInt("id");
				int idissues = rs.getInt("idissues");
				String content = rs.getString("content");
				int author_id = rs.getInt("author");
				Date cdate = rs.getDate("cdate");

				// get users
				User author = UserDAO.getUser(author_id);

				// create new comment object
				thecomment = new Comment(author, content, id, cdate, idissues);

			} else {
				throw new Exception("Could not find issue id: " + commentId);
			}

			return thecomment;
		} finally {
			DatabaseConnect.close(con);
		}
	}

	public void updateComment(Comment thecomment) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DatabaseConnect.getConnection();
			String sql = "update comments set content=? where id=?";

			stmt = con.prepareStatement(sql);
			stmt.setString(1, thecomment.getComment_text());
			stmt.setLong(2, thecomment.getComment_id());

			stmt.execute();
		} finally {
			DatabaseConnect.close(con);
		}
	}

	public void deleteComment(long commentId) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DatabaseConnect.getConnection();
			String sql = "delete from comments where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, commentId);

			stmt.execute();
		} finally {
			DatabaseConnect.close(con);
		}
	}

}
