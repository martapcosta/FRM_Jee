package com.marta.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Comment {
	private User commentby;
	private String comment_text;
	private int comment_id;
	private int issues_id;
	private Date cdate;

	public Comment() {
		
	}
	
	public Comment(User commentby, String comment_text, int comment_id, Date cdate, int issues_id) {
		this.commentby = commentby;
		this.comment_text = comment_text;
		this.comment_id = comment_id;
		this.cdate = cdate;
		this.issues_id = issues_id;
	}

	public int getIssues_id() {
		return issues_id;
	}

	public void setIssues_id(int issues_id) {
		this.issues_id = issues_id;
	}

	public User getCommentby() {
		return commentby;
	}

	public void setCommentby(User commentby) {
		this.commentby = commentby;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public long getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public Date getCreation_date() {
		return cdate;
	}

	public void setCreation_date(Date cdate) {
		this.cdate = cdate;
	}

}