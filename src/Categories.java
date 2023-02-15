import java.util.UUID;

public class Categories {
	private UUID id;
	private String title;
	private String description;
	private Todo todo;

	public Categories(String title, UUID id, String description, Todo todo) {
		this.title = title;
		this.id = id;
		this.description = description;
		this.todo = todo;
	}

	public Categories(String title) {
		this.title = title;
		this.id = UUID.randomUUID();
		this.description = "";
	}

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
