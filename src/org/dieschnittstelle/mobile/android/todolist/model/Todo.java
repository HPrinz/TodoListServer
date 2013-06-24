package org.dieschnittstelle.mobile.android.todolist.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.NONE) 
@XmlRootElement(name = "todo") 
public class Todo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6410064189686738560L;

	private long id;
	private String name;
	private String description;
	private boolean isDone;
	private boolean isFavorite;
	
	@XmlElement(name = "date", required = true) 
	@XmlJavaTypeAdapter(JsonDateAdapter.class)
	private Date date;

	/**
	 * Creates a Todo
	 * 
	 * @param id
	 *            the Todo's identifier
	 * @param name
	 *            the Todo's name (max. 80 signs)
	 * @param description
	 *            the Todo's description (max. 255 signs)
	 * @param isDone
	 *            whether the Todo is done or not
	 * @param isFavorite
	 *            whether the Todo is marked as favorite
	 * @param date
	 *            the date the Todo should be finished
	 */
	
	public Todo(long id, String name, String description, boolean isDone,
			boolean isFavorite, Date date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isDone = isDone;
		this.isFavorite = isFavorite;
		this.date = date;
	}

	public Todo() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
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

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	/**
	 * @return the date the todo should be finished. null if no date is set.
	 */
	@XmlJavaTypeAdapter(JsonDateAdapter.class)
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date the todo should be finished. null if no date is set.
	 */
	@XmlJavaTypeAdapter(JsonDateAdapter.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		return this.getId() == ((Todo) other).getId();
	}

	public Todo updateFrom(Todo item) {
		this.setDescription(item.getDescription());
		this.setName(item.getName());
		this.setDate(item.getDate());
		this.setDone(item.isDone);
		this.setFavorite(item.isFavorite);

		return this;
	}

	public String toString() {
		String isDoneString;
		if (this.isDone){
			isDoneString = "done";
		}else{
			isDoneString = "notDone";
		}
		
		String isFavoriteString;
		if (this.isFavorite){
			isFavoriteString = "favorite";
		}else{
			isFavoriteString = "notFavorite";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.date);
		
		return "{Todo " + this.id + ", " + this.name + ", " + this.description
				+ ", " + isDoneString + ", " + isFavoriteString + ", " +  cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR)+" }";
	}

	class JsonDateAdapter extends XmlAdapter<String, Date> {
		
		private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		@Override
		public String marshal(Date v) throws Exception {
			return df.format(v);
		}

		@Override
		public Date unmarshal(String v) throws Exception {
			return df.parse(v);
		}

	}

}
