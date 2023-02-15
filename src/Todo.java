import java.util.ArrayList;
import java.util.UUID;

public class Todo {

	private UUID id;
	private String title;
	private boolean doneStatus;
	private String description;
	private ArrayList<Categories> categories;
	private ArrayList<Project> taskof;

	public Todo(String title) {
		this.title = title;
		this.id = UUID.randomUUID();
		this.description = "";
		this.doneStatus = false;
		categories = new ArrayList<>();
		taskof = new ArrayList<>();
	}

	public Todo(String title, String description, Boolean doneStatus) {
		this.title = title;
		this.id = UUID.randomUUID();
		this.description = description;
		this.doneStatus = doneStatus;
		categories = new ArrayList<>();
		taskof = new ArrayList<>();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public ArrayList<Categories> getCategories() {
		return categories;
	}

	public void setCategories(Categories category) {
		categories.add(category);
	}

	public ArrayList<Project> getTaskof() {
		return taskof;
	}

	public void setTaskof(Project taskof) {
		this.taskof.add(taskof);
	}

}
