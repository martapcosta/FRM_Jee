package com.marta.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.marta.dao.UserDAO;

@ManagedBean(name = "dtDialogView")
public class CreateDialogView implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;
	private Map<String, String> types = new HashMap<String, String>();

	private String state;
	private Map<String, String> states = new HashMap<String, String>();

	private String priority;
	private Map<String, String> priorities = new HashMap<String, String>();

	private List<User> users;

	@PostConstruct
	public void init() {
		// types
		types = new HashMap<String, String>();
		types.put("New feature", "New feature");
		types.put("Improvement", "Improvement");
		types.put("Specs", "Specs");
		types.put("Recall", "Recall");
		types.put("Task", "Task");
		types.put("Anomaly", "Anomaly");

		// states
		states = new HashMap<String, String>();
		states.put("Closed", "Closed");
		states.put("Open", "Open");
		states.put("Re-Onpened", "Re-Onpened");

		// priorities
		priorities = new HashMap<String, String>();
		priorities.put("Blocking", "Blocking");
		priorities.put("Critical", "Critical");
		priorities.put("Important", "Important");
		priorities.put("Minor", "Minor");

		users = UserDAO.getUsers();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getTypes() {
		return types;
	}

	public Map<String, String> getStates() {
		return states;
	}

	public Map<String, String> getPriorities() {
		return priorities;
	}

	public List<User> getUsers() {
		return users;
	}
}