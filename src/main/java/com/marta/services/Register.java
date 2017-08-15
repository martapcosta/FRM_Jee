package com.marta.services;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.marta.dao.UserDAO;
import com.marta.util.EncryptUtils;


@ManagedBean
@SessionScoped
public class Register implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
    private String username;
	private String msg;
    private String pwd;
    
    
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String validateUsernameCreation() {

		//Check if username already exists
		if (!UserDAO.userAvailable(this.username)) {
			FacesContext.getCurrentInstance().addMessage("username",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Username already taken!", "Username already taken!"));
			return "register";
		}
		
		boolean valid = UserDAO.create(username, EncryptUtils.encrypt(pwd),email);
		
		if (valid) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registration successful", "You can now use your Username and Password to sign in"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true); //To keep the message on redirect
			return "login";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Problem on registration", "Please enter your username, password and email again"));
			return "register";
		}
	}

}

