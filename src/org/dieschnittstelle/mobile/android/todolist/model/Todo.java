package org.dieschnittstelle.mobile.android.todolist.model;

import java.util.Calendar;
import java.util.Date;

public class Todo {

	private long id;
	private String name;
	private String description;
	private boolean isDone;
	private boolean isFavorite;
	private Date date;

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Todo updateFrom(Todo item) {
		this.setId(item.getId());
		this.setDescription(item.getDescription());
		this.setName(item.getName());
		this.setDone(item.isDone());
		this.setFavorite(item.isFavorite());
		this.setDate(item.getDate());
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getId() == ((Todo) obj).getId();
	}

	@Override
	public String toString() {
		String isDoneString;
		if (this.isDone) {
			isDoneString = "done";
		} else {
			isDoneString = "notDone";
		}

		String isFavoriteString;
		if (this.isFavorite) {
			isFavoriteString = "favorite";
		} else {
			isFavoriteString = "notFavorite";
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getDate());

		return "{Todo " + this.id + ", " + this.name + ", " + this.description + ", " + isDoneString + ", " + isFavoriteString
				+ ", " + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR) + " }";
	}

}