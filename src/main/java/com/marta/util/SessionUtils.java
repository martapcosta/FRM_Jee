package com.marta.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.marta.beans.User;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static Integer getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (Integer) session.getAttribute("userid");
		else
			return null;
	}
	
	public static void setUserInSession(User user) {
		HttpSession session = getSession();
		if (session != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userid", user.getId());
		}
	}

}
