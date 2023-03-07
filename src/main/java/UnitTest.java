package main.java;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTest {

	private Todo todo;
	private Project p;
	private Categories c;
	private Todo todo1;
	private Project p1;
	private Categories c1;    
	private Categories c2;
	private Project p2;
	String errorMsg;

	@BeforeEach
	public void setUp() {
		todo = TodoController.createTodoByTitle("Test", "");
		p = TodoController.createProjectByTitle("Test");
		c = TodoController.createCategoryByTitle("Test");

		c2 = TodoController.createCategoryByTitle("test2");
		p2 = TodoController.createProjectByTitle("test2");
		todo.setCategories(c);
		todo.setTaskof(p);
		todo.setDescription("a description");
		todo.setDoneStatus(false);
		todo.setId((int) Math.random());

		// false todo
		todo1 = new Todo("Fake");
		p1 = new Project("Fake");
		c1 = new Categories("Fake");
		todo.setId((int) Math.random());
		todo1.setDescription("fake");
		todo1.setDoneStatus(false);
		todo1.setCategories(c1);
		todo1.setTaskof(p1);

	}

	@AfterEach
	public void clear() {
		TodoController.removeAllTodos();
	}

	@Test
	public void testCreateTodoByTitle() {
		String title = "Test";
		Todo tmp = new Todo(title);
		Todo t = null;
		t = TodoController.createTodoByTitle(title, "");
		assertNotNull(t);
		assertEquals(tmp.getTitle(), t.getTitle());
	}

	@Test
	public void testGetTodoByTitle() {
		Todo t = TodoController.getTodoByTitle(todo.getTitle());
		assertNotNull(t);
	}

	@Test
	public void testGetTodoByID() {
		Todo tmp = TodoController.getTodoByID(todo.getId());
		assertNotNull(tmp);
		assertEquals(todo, tmp);
	}

	@Test
	public void testUpdateTodo() {
		int id = (int) Math.random();
		String title = "Test";
		String description = "test";
		boolean doneStatus = true;
		Todo t = new Todo(title);
		t.setId(id);
		t.setDescription(description);
		t.setDoneStatus(doneStatus);

		todo.setId(id);
		todo = TodoController.updateTodo(id, title, description, doneStatus);
		assertNotNull(todo);
		assertEquals(t.getDescription(), todo.getDescription());
	}

	@Test
	public void testGetCategoryByID() {
		int id = (int) Math.random();
		c.setId(id);
		Categories cat = TodoController.getCategoryByID(id);
		assertNotNull(cat);
		assertEquals(c.getId(), cat.getId());
	}

	@Test
	public void testGetCategoryByFalseID() {
		int id = (int) Math.random();
		int rand_id = (int) Math.random();
		c.setId(id);
		Categories cat = null;
		try {
			cat = TodoController.getCategoryByID(rand_id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(cat);
		assertEquals("No category with the id " + rand_id + " exists", errorMsg);
	}  

	@Test
	public void testGetCategories() {
		Categories cats = TodoController.getCategories(todo.getId());
		assertEquals(todo.getCategories(), cats);
	}

	@Test
	public void testGetProjects() {
		List<Project> projs = TodoController.getProjects(todo.getId());
		assertEquals(todo.getTaskof().get(0), projs.get(0));
	}

	@Test
	public void testGetProjectsByNonExistingTodoID() {
		List<Project> projs = null;
		int id = todo1.getId();
		try {
			projs = TodoController.getProjects(id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No todo with the id " + id + " exists", errorMsg);
		assertNull(projs);
	}

	@Test
	public void testGetTodoByFalseTitle() {
		String title = todo1.getTitle();
		Todo tmp = null;
		try {
			tmp = TodoController.getTodoByTitle(title);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("No todo with the title " + title + " exists", errorMsg);
	}

	@Test
	public void testGetTodoByFalseID() {
		int id = todo1.getId();
		Todo tmp = null;
		try {
			tmp = TodoController.getTodoByID(id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("No todo with the id " + id + " exists", errorMsg);
	}

	@Test
	public void testDeleteTodo() {
		int id = todo.getId();
		TodoController.deleteTodoByID(id);
		Todo tmp = null;
		try {
			tmp = TodoController.getTodoByID(todo.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No todo with the id " + id + " exists", errorMsg);
		assertNull(tmp);
		
		try {  
			TodoController.deleteTodoByID(id);
		} catch(IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No todo with the id " + id + " exists", errorMsg);
	}

	@Test
	public void testDeleteTodoByFalseID() {
		try {
			TodoController.deleteTodoByID(todo1.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No todo with the id " + todo1.getId() + " exists", errorMsg);
	}

	@Test
	public void testRemoveCategoryFromTodo() {
		TodoController.removeCategoryFromTodo(todo.getId(), c.getId());
		assertNotEquals(todo.getCategories(), c);
	}

	@Test
	public void testRemoveCategoryFromTodoByFalseTodoID() {
		try {
			TodoController.removeCategoryFromTodo(todo1.getId(), c.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No todo with the id " + todo1.getId() + " exists", errorMsg);
	}

	@Test
	public void testRemoveCategoryFromTodoByFalseCategoryID() {
		try {
			TodoController.removeCategoryFromTodo(todo.getId(), c2.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("A category with id " + c2.getId() + " does not exist for todo with id " + todo.getId(), errorMsg);
	}

	@Test
	public void testRemoveCategoryFromTodoByNonExistingCategory() {
		try {
			TodoController.removeCategoryFromTodo(todo.getId(), c1.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No category with the id " + c1.getId() + " exists", errorMsg);
	}

	@Test
	public void testUpdateTodoByFalseID() {
		int id = todo1.getId();
		String title = "new title";
		String description = "test";
		boolean doneStatus = true;
		try {
			TodoController.updateTodo(id, title, description, doneStatus);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No todo with the id " + id + " exists", errorMsg);
	}

	@Test
	public void testAddTaskof() {
		todo = TodoController.addTaskof(todo.getId(), p2.getId());
		assertNotNull(todo);
		assertNotNull(todo.getTaskof());
		assertTrue(todo.getTaskof().contains(p2));
	}

	@Test
	public void testAddTaskOfFalseTodoID() {
		int id = todo1.getId();
		int task_id = p.getId();
		Todo tmp = null;
		try {
			tmp = TodoController.addTaskof(id, task_id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("No todo with the id " + id + " exists", errorMsg);
	}

	@Test
	public void testAddTaskOfFalseProjectID() {
		int id = todo.getId();
		int task_id = p1.getId();
		Todo tmp = null;
		try {
			tmp = TodoController.addTaskof(id, task_id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("No project with the id " + task_id + " exists", errorMsg);

	}

	@Test
	public void testAddTaskOfExistingProjectForTodo() {
		int id = todo.getId();
		int task_id = p.getId();
		Todo tmp = null;
		try {
			tmp = TodoController.addTaskof(id, task_id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("The project with id " + task_id + " already exists for todo with id " + id, errorMsg);

	}

	@Test
	public void testRemoveTaskFromTodo() {
		todo = TodoController.removeTaskofFromTodo(todo.getId(), p.getId());
		assertNotNull(todo);
		assertFalse(todo.getTaskof().contains(p));
	}

	@Test
	public void testRemoveTaskFromTodoByFalseTodoID() {
		try {
			TodoController.removeTaskofFromTodo(todo1.getId(), p.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No todo with the id " + todo1.getId() + " exists", errorMsg);
	}

	@Test
	public void testRemoveTaskFromTodoByFalseProjectID() {
		try {
			TodoController.removeTaskofFromTodo(todo.getId(), p1.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("No project with the id " + p1.getId() + " exists", errorMsg);
	}

	@Test
	public void testRemoveTaskFromTodoByNonExistingProjectForTodo() {
		try {
			TodoController.removeTaskofFromTodo(todo.getId(), p2.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("A project with id " + p2.getId() + " does not exist for todo with id " + todo.getId(), errorMsg);
	}

	@Test
	public void testAddCategoryToTodo() {
		todo = TodoController.addCategory(todo.getId(), c2.getId());
		assertNotNull(todo);
		assertNotNull(todo.getCategories());
		assertEquals(todo.getCategories(), c2);
	}

	@Test
	public void testAddCategoryToTodoByFalseTodoID() {
		int id = todo1.getId();
		int category_id = c.getId();
		Todo tmp = null;
		try {
			tmp = TodoController.addCategory(id, category_id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("No todo with the id " + id + " exists", errorMsg);
	}

	@Test
	public void testAddCategoryToTodoByFalseCategoryID() {
		int id = todo.getId();
		int category_id = c1.getId();
		Todo tmp = null;
		try {
			tmp = TodoController.addCategory(id, category_id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("No category with the id " + category_id + " exists", errorMsg);
	}

	@Test
	public void testAddCategoryToTodoByExistingCategoryForTodo() {
		int id = todo.getId();
		int category_id = c.getId();
		Todo tmp = null;
		try {
			tmp = TodoController.addCategory(id, category_id);
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(tmp);
		assertEquals("The category with id " + category_id + " already exists for todo with id " + id, errorMsg);
	}

	@Test
	public void testRemoveCategoryFromTodoByNonExistingCategoryForTodo() {
		try {
			TodoController.removeCategoryFromTodo(todo.getId(), c2.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertEquals("A category with id " + c2.getId() + " does not exist for todo with id " + todo.getId(), errorMsg);
	}

	@Test
	public void testGetTaskOf() {
		ArrayList<Project> c = TodoController.getTaskof(todo.getId());
		assertNotNull(c);
		assertEquals(todo.getTaskof().size(), c.size());
	}

	@Test
	public void testGetTaskOfByFalseTodoID() {
		ArrayList<Project> c = null;
		try {
			c = TodoController.getTaskof(todo1.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(c);
		assertEquals("No todo with the id " + todo1.getId() + " exists", errorMsg);
	}

	@Test
	public void testGetCategoriesByFalseTodoID() {
		Categories c = null;
		try {
			c = TodoController.getCategories(todo1.getId());
		} catch (IllegalArgumentException e) {
			errorMsg = e.getMessage();
		}
		assertNull(c);
		assertEquals("No todo with the id " + todo1.getId() + " exists", errorMsg);
	}

	@Test
	public void testGetAllTodos() {
		ArrayList<Todo> todos = TodoController.getAllTodos();
		assertNotNull(todos);
		assertEquals(1, todos.size());
	}

}
