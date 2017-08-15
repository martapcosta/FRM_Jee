package com.marta.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.marta.dao.IssueDAO;
import com.marta.util.SessionUtils;

@ManagedBean(name = "dtBasicView")
@ViewScoped
public class BasicView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Issue> issues;
	private List<String> types;

	private String type;
	private Logger logger = Logger.getLogger(getClass().getName());


	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private Issue selectedIssue;

	@ManagedProperty("#{issuesController}")
	private IssuesController service;

	@PostConstruct
	public void init() {
		issues = service.getIssues();
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public List<String> getTypes() {
		return types;
	}

	public Issue getSelectedIssue() {
		return selectedIssue;
	}

	public void setSelectedIssue(Issue selectedIssue) {
		this.selectedIssue = selectedIssue;
	}

	public void setService(IssuesController service) {
		this.service = service;
	}

	public void deleteIssue() {
		try {
			IssueDAO.getInstance().deleteIssue(selectedIssue.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectedIssue = null;
	}

	public String addIssue(Issue theIssue) {

		logger.info("Adding issue: " + theIssue);

		try {
			Date creation_date = new Date();
			java.sql.Date dc = new java.sql.Date(creation_date.getTime());
			theIssue.setCreation_date(dc);
			theIssue.setLast_modification_date(dc);
			theIssue.user_create.setId(SessionUtils.getUserId());
			// add issue to the database
			IssueDAO.getInstance().addIssue(theIssue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			

			return null;
		}

		return "homepage?faces-redirect=true";
	}

	public String updateIssue() {

		logger.info("Updating issue: " + selectedIssue.getId());

		try {

			Date modification_date = new Date();
			java.sql.Date dm = new java.sql.Date(modification_date.getTime());
			selectedIssue.setLast_modification_date(dm);

			// add issue to the database
			IssueDAO.getInstance().updateIssue(selectedIssue);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return "homepage?faces-redirect=true";
	}

	public void onRowSelect(SelectEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("issuedetails.xhtml?id=" + selectedIssue.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}