package com.medibank.posts.postsWebService.model;

import java.io.Serializable;

/** Model Class to represent TypiCode Post */
public class TypiCodePost implements Serializable {

	private static final long serialVersionUID = 1L;
	int userId;
	int id;
	String title;
	String body;

	public TypiCodePost() {

	}

	public TypiCodePost(int userId, int id, String title, String body) {
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
