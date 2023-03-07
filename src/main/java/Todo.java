package main.java;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Todo {

	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

	private int id;
	private String title;
	private boolean doneStatus;
	private String description;
	private Categories categories;
	private ArrayList<Project> taskof;

	public Todo(String title) {
		this.title = title;
		this.id = ID_GENERATOR.getAndIncrement();
		this.description = "";
		this.doneStatus = false;
		taskof = new ArrayList<>();
	}

	public Todo(String title, String description, Boolean doneStatus) {
		this.title = title;
		this.id = ID_GENERATOR.getAndIncrement();
		this.description = description;
		this.doneStatus = doneStatus;
		taskof = new ArrayList<>();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDoneStatus() {
		return doneStatus;
	}

	public void setDoneStatus(boolean doneStatus) {
		this.doneStatus = doneStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories category) {
		this.categories = category;
	}

	public ArrayList<Project> getTaskof() {
		return taskof;
	}

	public void setTaskof(Project taskof) {
		this.taskof.add(taskof);
	}

}
