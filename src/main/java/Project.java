package main.java;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Project {

	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

	private int id;
	private String title;
	private boolean completed;
	private boolean active;
	private String description;

//	public Project(int id, String title, boolean completed, boolean active, String description) {
//		this.id = ID_GENERATOR.getAndIncrement();
//		this.title = title;
//		this.completed = completed;
//		this.active = active;
//		this.description = description;
//	}

	public Project(String title) {
		super();
		this.id = ID_GENERATOR.getAndIncrement();
		this.title = title;
		this.completed = false;
		this.active = false;
		this.description = "";
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
