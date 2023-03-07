package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import main.java.Categories;
import main.java.Project;
import main.java.Todo;

public class TodoController {

	static ArrayList<Todo> todos = new ArrayList<>();
	static ArrayList<Categories> categories = new ArrayList<>();
	static ArrayList<Project> projects = new ArrayList<>();

	public static Todo createTodoByTitle(String title, String description) {
		if(title == "" || title == null) {
			throw new IllegalArgumentException("Title is required");
		}
		Todo todo = new Todo(title);
		todo.setDescription(description);
		todos.add(todo);
		return todo;
	}

	public static Todo createTodoByIdAndTitle(String id, String title, String description) {
		if(title == "" || title == null) {
			throw new IllegalArgumentException("Title is required");
		}
		Todo todo = new Todo(title);
		int iD = Integer.parseInt(id);
		todo.setId(iD);
		todo.setDescription(description);
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
		throw new IllegalArgumentException("No todo with the title " + title + " exist");
	}

	public static Todo getTodoByID(int id) {
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exist");
	}

	public static Todo updateTodo(int id, String title, String description, Boolean doneStatus) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setDoneStatus(doneStatus);
			return todo;
		} else {
			throw new IllegalArgumentException("No todo with the input id was found");
		}
	}

	public static Todo deleteTodoByID(int id) {
		Todo todo = getTodoByID(id);
		if (todo == null) {
			throw new IllegalArgumentException("No todo with the id " + id + " exist");
		}
		todos.remove(todo);
		if (todos.contains(todo)) {
			throw new IllegalArgumentException("Error could not delete todo with id " + id);
		}
		return null;
	}

	public static Categories getCategoryByID(int id) {
		for (Categories category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}
		throw new IllegalArgumentException("No category with the id " + id + " exist");
	}

	public static Categories getCategories(int id) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			return todo.getCategories();
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exist");
	}

	public static List<Project> getProjects(int id) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			return todo.getTaskof();
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exist");
	} 

	public static Todo addCategory(int todo_id, int category_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo != null) {
			Categories category = getCategoryByID(category_id);
			if (category == null)
				throw new IllegalArgumentException("No category with the id " + category_id + " exist");
			Categories c = todo.getCategories();
			if (c != null) {
				if(c.equals(category))
					throw new IllegalArgumentException("The category with id " + category_id + " already exists for todo with id " + todo_id);
			}
			todo.setCategories(category);
			return todo;
		}
		throw new IllegalArgumentException("No todo with the id " + todo_id + " exist");
	}

	public static Todo removeCategoryFromTodo(int todo_id, int category_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo == null)
			throw new IllegalArgumentException("No todo with the id " + todo_id + " exist");
		Categories category = getCategoryByID(category_id);
		if (category == null)
			throw new IllegalArgumentException("No category with id " + category_id + " exist");

		Categories cat = todo.getCategories();
		if (cat != null) {
			if (cat.getId() == category_id) {
				todo.setCategories(null);
				return todo;
			}
		}
		throw new IllegalArgumentException(
				"A category with id " + category_id + " does not exist for todo with id " + todo_id);

	}
 
	public static ArrayList<Project> getTaskof(int id) {
		Todo todo = getTodoByID(id);
		if (todo != null) {
			return todo.getTaskof();
		}
		throw new IllegalArgumentException("No todo with the id " + id + " exist");
	}

	public static Todo addTaskof(int todo_id, int task_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo != null) {
			Project p = getProjectByID(task_id);
			if (p == null) 
				throw new IllegalArgumentException("No project with the id " + task_id + " exist");
			ArrayList<Project> projs = todo.getTaskof();
			if(projs.contains(p))
				throw new IllegalArgumentException("The project with id " + task_id + " already exists for todo with id " + todo_id);
			todo.setTaskof(p);
			return todo;
		}
		throw new IllegalArgumentException("No todo with the id " + todo_id + " exist");
	}

	public static Project getProjectByID(int id) {
		for (Project project : projects) {
			if (project.getId() == id) {
				return project;
			}
		}
		throw new IllegalArgumentException("No project with the id " + id + " exist");
	}

	public static Todo removeTaskofFromTodo(int todo_id, int task_id) {
		Todo todo = getTodoByID(todo_id);
		if (todo == null) {
			throw new IllegalArgumentException("No todo with the id " + todo_id + " exist");
		}
		Project pr = getProjectByID(task_id);
		if(pr == null) 
			throw new IllegalArgumentException("No project with the id " + task_id + " exist");
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
