package main.java;

import java.util.UUID;

public class Project {

	public Project(UUID id, String title, boolean completed, boolean active, String description) {
		super();
		this.id = id;
		this.title = title;
		this.completed = completed;
		this.active = active;
		this.description = description;
	}

	public Project(String title) {
		super();
		this.id = UUID.randomUUID(); 
		this.title = title;
		this.completed = false;
		this.active = false;
		this.description = "";
	}

	private UUID id;
	private String title;
	private boolean completed;
	private boolean active;
	private String description;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
