package com.cgi.java.FilRouge.helper;

import java.util.List;

public class Menu {
	
	String title;
	String url;
	boolean active;
	private List<Menu> ssmenu;	

	public Menu(String ptitle, String purl) {
		title = ptitle;
		url = purl;
		active = false;
		}
	
	public Menu(String title, String url, List<Menu> ssmenu) {
		this(title, url);
		this.ssmenu = ssmenu;
	}

	public List<Menu> getSsmenu() {
		return ssmenu;
	}

	public void setSsmenu(List<Menu> ssmenu) {
		this.ssmenu = ssmenu;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
