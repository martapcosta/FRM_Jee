package com.marta.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.marta.dao.CommentDAO;
import com.marta.dao.IssueDAO;
import com.marta.dao.UserDAO;
import com.marta.util.SessionUtils;

@ManagedBean(name = "issueController")
@SessionScoped
public class IssueDetailsController {

	private int id;
	private Issue issue;
	private List<Comment> comments;
	private String commentTextArea;
	private boolean editable = false;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public IssueDetailsController() throws Exception {
		comments = new ArrayList<>();
	}

	public void loadIssue() throws Exception {
		this.issue = IssueDAO.getInstance().getIssue(this.id);
		this.comments = CommentDAO.getInstance().getCommentsByIssue(this.id);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Issue getIssue() {
		return issue;
	}

	public int getId() {
		return id;
	}

	public void setId(int editid) {
		this.id = editid;
	}
	
	 public void changeEditable() {
	        editable = !editable;
	    }
	public void deleteComment(Comment comment) throws Exception {
		CommentDAO.getInstance().deleteComment(comment.getComment_id());

		
		this.comments = CommentDAO.getInstance().getCommentsByIssue(this.id);

		
		//TODO add Faces message!
	}
	
	public void editComment(Comment comment) throws Exception {
		CommentDAO.getInstance().updateComment(comment);

		
		this.comments = CommentDAO.getInstance().getCommentsByIssue(this.id);

		
		//TODO add Faces message!
	}

	
	public void addComment() throws Exception {
		Comment newComment = new Comment();
		newComment.setComment_text(commentTextArea);
		newComment.setCreation_date(new Date());
		newComment.setIssues_id(this.id);
		newComment.setCommentby(UserDAO.getUser(SessionUtils.getUserId()));

		try {
			CommentDAO.getInstance().addComment(newComment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO add warning message
		}

		// Reload comments
		this.commentTextArea = "";
		this.comments = CommentDAO.getInstance().getCommentsByIssue(this.id);

		// TODO add message of added comment
	}

	public String getCommentTextArea() {
		return commentTextArea;
	}

	public void setCommentTextArea(String commentTextArea) {
		this.commentTextArea = commentTextArea;
	}
}
