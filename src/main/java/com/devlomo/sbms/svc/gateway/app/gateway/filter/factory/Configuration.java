package com.devlomo.sbms.svc.gateway.app.gateway.filter.factory;

import org.springframework.stereotype.Component;

@Component
public class Configuration {
	
	private String msg;
	private String cookieValue;
	private String cookieName;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCookieValue() {
		return cookieValue;
	}
	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}
	public String getCookieName() {
		return cookieName;
	}
	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}
	@Override
	public String toString() {
		return "Configuration [msg=" + msg + ", cookieValue=" + cookieValue + ", cookieName=" + cookieName + "]";
	}
}
