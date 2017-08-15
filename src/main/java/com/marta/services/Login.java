package com.marta.services;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.marta.dao.UserDAO;
import com.marta.util.EncryptUtils;
import com.marta.util.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = -5716169085709164435L;

	private String pwd;

	private String username;
	private String msg;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	// validate login
	public String validateUsernamePassword() {

		boolean valid = UserDAO.validate(username, EncryptUtils.encrypt(pwd));
		
		if (valid) {
			SessionUtils.setUserInSession(UserDAO.getUser(username));
			return "homepage";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password", "Please enter correct username and Password"));
			return "invalid-login";
		}
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
	
}
