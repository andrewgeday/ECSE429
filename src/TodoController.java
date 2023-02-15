import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoController {

	static ArrayList<Todo> todos = new ArrayList<>();
	static ArrayList<Categories> categories = new ArrayList<>();
	static ArrayList<Project> projects = new ArrayList<>();

	public static Todo createTodoByTitle(String title) {
		Todo todo = new Todo(title);
		todos.add(todo);
		return todo;
	}

	public static Categories createCategoryByTitle(String title) {
		Categories c = new Categories(title);
		categories.add(c);
		return c;
	}

	public static Project createProjectByTitle(String title) {
		Project p = new Project(title);
		projects.add(p);
		return p;
	}

	public static Todo getTodoByTitle(String title) {
		for (Todo todo : todos) {
			if (todo.getTitle().equals(title)) {
				return todo;
			}
		}
		throw new IllegalArgumentException("No todo with the title " + title + " exists");
	}

	public static Todo getTodoByID(UUID id) {
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exists");
	}

	public static Todo updateTodo(UUID id, String title, String description, Boolean doneStatus) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setDoneStatus(doneStatus);
			return todo; 
		} else {
			throw new IllegalArgumentException("No todo with the id " + id + " exists");
		}
	}

	public static void deleteTodoByID(UUID id) {
		Todo todo = getTodoByID(id);
		if (todo == null) {
			throw new IllegalArgumentException("No todo with the id " + id + " exists");
		}
		todos.remove(todo);
		if (todos.contains(todo)) {
			throw new IllegalArgumentException("Error could not delete todo with id " + id);
		}
	}

	public static Categories getCategoryByID(UUID id) {
		for (Categories category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}
		throw new IllegalArgumentException("No category with the id " + id + " exists");
	}

	public static ArrayList<Categories> getCategories(UUID id) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			return todo.getCategories();
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exists");
	}

	public static List<Project> getProjects(UUID id) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			return todo.getTaskof();
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exists");
	} 

	public static Todo addCategory(UUID todo_id, UUID category_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo != null) {
			Categories category = getCategoryByID(category_id);
			if (category == null)
				throw new IllegalArgumentException("No category with the id " + category_id + " exists");
			ArrayList<Categories> c = todo.getCategories();
			if(c.contains(category))
				throw new IllegalArgumentException("The category with id " + category_id + " already exists for todo with id " + todo_id);
			todo.setCategories(category);
			return todo;
		}
		throw new IllegalArgumentException("No todo with the id " + todo_id + " exists");
	}

	public static Todo removeCategoryFromTodo(UUID todo_id, UUID category_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo == null)
			throw new IllegalArgumentException("No todo with the id " + todo_id + " exists");
		Categories category = getCategoryByID(category_id);
		if (category == null)
			throw new IllegalArgumentException("No category with id " + category_id + " exists");

		ArrayList<Categories> cat = todo.getCategories();
		for (Categories c : cat) {
			if (c.getId() == category_id) {
				cat.remove(c);
				return todo;
			}
		}
		throw new IllegalArgumentException(
				"A category with id " + category_id + " does not exist for todo with id " + todo_id);

	}
 
	public static ArrayList<Project> getTaskof(UUID id) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			return todo.getTaskof();
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exists");
	}

	public static Todo addTaskof(UUID todo_id, UUID task_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo != null) {
			Project p = getProjectByID(task_id);
			if (p == null) 
				throw new IllegalArgumentException("No project with the id " + task_id + " exists");
			ArrayList<Project> projs = todo.getTaskof();
			if(projs.contains(p))
				throw new IllegalArgumentException("The project with id " + task_id + " already exists for todo with id " + todo_id);
			todo.setTaskof(p);
			return todo;
		}
		throw new IllegalArgumentException("No todo with the id " + todo_id + " exists");
	}

	public static Project getProjectByID(UUID id) { 
		for (Project project : projects) {
			if (project.getId() == id) {
				return project;
			}
		}
		throw new IllegalArgumentException("No project with the id " + id + " exists");
	}

	public static Todo removeTaskofFromTodo(UUID todo_id, UUID task_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo == null) {
			throw new IllegalArgumentException("No todo with the id " + todo_id + " exists");
		}
		Project pr = getProjectByID(task_id);
		if(pr == null) 
			throw new IllegalArgumentException("No project with the id " + task_id + " exists");
		ArrayList<Project> proj = todo.getTaskof();
		if(! proj.contains(pr))
		throw new IllegalArgumentException(
				"A project with id " + task_id + " does not exist for todo with id " + todo_id);
		proj.remove(pr);
		return todo;
	}
	
	public static ArrayList<Todo> getAllTodos() {
		return todos;
	}
	
	public static void removeAllTodos() {
		todos.clear();
	}
}
