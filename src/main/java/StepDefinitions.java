package main.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.java.Categories;
import main.java.Project;
import main.java.Todo;
import main.java.controller.TodoController;

public class StepDefinitions {

    private ArrayList<Todo> todoList;
    private Todo todo;
    private Categories addedCategory;
    private Project addedProject;
    private String errorMessage = null;

    
    @Given("the user has a Todo List")
    public void the_user_has_a_Todo_List() {
        todoList = new ArrayList<>();
    }

    @When("the user creates a new Todo with title {string} and optional description {string}")
    public void the_user_creates_a_new_Todo_with_title_and_optional_description(String title, String description) {
        try {
        	todo = TodoController.createTodoByTitle(title, description);
        	todoList.add(todo);
        } catch (IllegalArgumentException e) {
           errorMessage = e.getMessage();
        }
    }

    @Then("the Todo is created and returned")
    public void the_Todo_is_created_and_returned() {
        assertNotNull(todo);
        assertEquals(todo.getTitle(), "Example Todo");
        assertEquals(todo.getDescription(), "This is an example Todo");
        assertNull(errorMessage);
    }
    
    @When("the user updates the Todo with ID {int} with title {string}, description {string}, and done status {string}")
    public void the_user_updates_the_Todo_with_ID_with_title_description_and_done_status(Integer todoId, String title, String description, boolean doneStatus) {
        String id = todoId.toString();
    	try {
        	todo = TodoController.updateTodo(UUID.fromString(id), title, description, doneStatus);
        } catch (IllegalArgumentException e) {
            
            errorMessage = e.getMessage();
        }
    }

    @Then("the Todo is updated and returned")
    public void the_Todo_is_updated_and_returned() {
        assertNotNull(todo);
        assertEquals(todo.getTitle(), "Updated Todo");
        assertEquals(todo.getDescription(), "This is an updated Todo");
    }

    @Then("an error message {string} is thrown")
    public void an_error_message_is_thrown(String expectedErrorMessage) {
        assertNotNull(errorMessage);
        assertEquals(errorMessage, "\"No todo with the input id was found");
    }

    @Given("the user has a Todo with ID {int}")
    public void the_user_has_a_Todo_with_ID(Integer todoId) {
        todoList = new ArrayList<>();
    }

    @When("the user adds a Category with ID {int} to the Todo with id {int}")
    public void the_user_adds_a_Category_with_ID_to_the_Todo(Integer categoryId, Integer todoId) {
        String catId = categoryId.toString();
        String tId = todoId.toString();
    	try {
            todo = TodoController.addCategory(UUID.fromString(tId), UUID.fromString(catId));
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    @Then("the Category is added to the Todo and returned")
    public void the_Category_is_added_to_the_Todo_and_returned() {
        assertNotNull(addedCategory);
        assertTrue(todo.getCategories().contains(todo));
    }
}
