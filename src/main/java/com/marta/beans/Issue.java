package com.marta.beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Issue implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
    public String title;
    public String description;
    public String type;
    public String priority;
    public User user_create = new User();
    public User assign_to = new User();
    public String state;
    public Date creation_date;
    public Date last_modification_date;
    
    public Issue() {}
    
    
    public Issue(int id, String title, String description, String type, String priority, User user_id, User assign_to, String state, Date creation_date, Date modification_date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.priority = priority;
        this.user_create = user_id;
        this.assign_to = assign_to;
        this.state = state;
        this.creation_date = creation_date;
        this.last_modification_date = modification_date;
    }
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public User getUser_create() {
		return user_create;
	}

	public void setUser_create(User user_id) {
		this.user_create = user_id;
	}

	public User getAssign_to() {
		return assign_to;
	}

	public void setAssign_to(User assign_to) {
		this.assign_to = assign_to;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getLast_modification_date() {
		return last_modification_date;
	}

	public void setLast_modification_date(Date last_modification_date) {
		this.last_modification_date = last_modification_date;
	}
	
}