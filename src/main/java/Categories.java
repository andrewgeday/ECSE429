package main.java;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Categories {

	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

	private int id;
	private String title;
	private String description;
	private Todo todo;

	public Categories(String title) {
		this.title = title;
		this.id = ID_GENERATOR.getAndIncrement();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

}
