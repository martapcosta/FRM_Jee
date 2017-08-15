package com.marta.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.marta.dao.IssueDAO;

@ManagedBean(name = "issuesController")
@ApplicationScoped
public class IssuesController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Issue> issues;
	private List<Issue> filteredIssues;
	private IssueDAO issueDAO;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public IssuesController() throws Exception {
		issues = new ArrayList<>();
		
		issueDAO = IssueDAO.getInstance();
	}
	
	public List<Issue> getFilteredIssues() {
        return filteredIssues;
    }
 
    public void setFilteredIssues(List<Issue> filteredIssues) {
        this.filteredIssues = filteredIssues;
    }
	
	public List<Issue> getIssues() {
		return issues;
	}

	public void loadIssues() {

		logger.info("Loading issues");
		
		issues.clear();

		try {
			
			// get all issues from database
			issues = issueDAO.getIssues();
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading issues", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
				
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	

	
}

