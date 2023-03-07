package com.examples.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.java.Categories;
import main.java.Project;
import main.java.Todo;
import main.java.TodoController;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class MyStepdefs {

    private ArrayList<Todo> todoList;
    private Todo todo;
    private Categories category;
    private Project project;
    private String errorMessage = null;
    @Given("a user wants to create a new Todo")
    public void a_user_wants_to_create_a_new_todo() {
        todoList = new ArrayList<>();
    }
    @When("they provide a title {string} and optional description {string}")
    public void they_provide_a_title_and_optional_description(String title, String description) {
        try {
            todo = TodoController.createTodoByTitle(title, description);
            todoList.add(todo);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("a new Todo with this title {string} and description {string} is created and returned")
    public void a_new_todo_with_the_provided_information_is_created_and_returned(String title, String description) {
        assertNotNull(todo);
        assertEquals(todo.getTitle(), title);
        assertEquals(todo.getDescription(), description);
        assertNull(errorMessage);
    }

    @Then("an error message {string} is returned")
    public void an_error_message_is_returned(String string) {
        assertEquals(errorMessage, string);
    }

    @Given("a Todo with id {string}, title {string} and description {string} exists")
    public void a_user_wants_to_update_an_existing_todo(String id, String title, String description) {
        todo = TodoController.createTodoByIdAndTitle(id, title, description);
    }

    @When("they provide the Todo ID {string} and updated title {string}, description {string}, doneStatus {string}")
    public void they_provide_the_todo_s_id_and_updated_title_description_and_or_done_status(String id, String title, String description, String doneStatus) {
        boolean status = Boolean.parseBoolean(doneStatus);
        int uid = Integer.parseInt(id);
        try {
            todo = TodoController.updateTodo(uid, title, description, status);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the Todo title {string}, description {string}, doneStatus {string} is updated")
    public void the_todo_with_the_provided_id_is_updated_with_the_new_information_and_returned(String title, String description, String doneStatus) {
        assertEquals(todo.getTitle(), title);
        assertEquals(todo.getDescription(), description);
    }

    @Given("a Category with id {string}, and title {string} and a Todo with id {string} and title {string} exist")
    public void a_user_wants_to_add_a_category_to_a_todo(String category_id, String category_title, String todo_id, String todo_title) {
        category = TodoController.createCategoryByTitle(category_title);
        category.setId(Integer.parseInt(category_id));
        todo = TodoController.createTodoByIdAndTitle(todo_id, todo_title, "");
    }

    @When("they provide the Category's ID {string} and the Todo ID {string}")
    public void they_provide_the_category_s_id_and_the_todo_s_id(String category_id, String todo_id) {
        try {
            todo = TodoController.addCategory(Integer.parseInt(todo_id), Integer.parseInt(category_id));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the Category with the provided ID is added to the Todo with the provided ID and returned")
    public void the_category_with_the_provided_id_is_added_to_the_todo_with_the_provided_id_and_returned() {
        assertEquals(todo.getCategories(), category);
    }

    @When("they provide the Category's ID {string} and the Todo ID {string} to remove")
    public void they_provide_the_category_s_id_and_the_todo_id_to_remove(String cat_id, String todo_id) {
        try {
            todo = TodoController.removeCategoryFromTodo(Integer.parseInt(todo_id), Integer.parseInt(cat_id));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("the Category with the provided ID is removed from the Todo with the provided ID and returned")
    public void the_category_with_the_provided_id_is_removed_from_the_todo_with_the_provided_id_and_returned() {
        assertNull(todo.getCategories());
    }

    @When("they provide the Todo ID {string}")
    public void they_provide_the_todo_id(String id) {
        try {
            todo = TodoController.deleteTodoByID(Integer.parseInt(id));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("the Todo with the provided ID is deleted")
    public void the_todo_with_the_provided_id_is_deleted() {
        assertNull(todo);
    }


}
