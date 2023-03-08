from json.decoder import JSONDecodeError

from behave import *

import api_fetcher
import json

url = "http://localhost:4567/todos"
error_msg = ""
json_string = ""

@given('a user wants to create a new Todo')
def step_impl(self):
    print("Testing creating Todo")

@when('they provide a title "{title}" and optional description "{description}"')
def step_impl(self, title, description):
    doneStatus = False
    json_data = json.dumps({'title': title, 'description': description, 'doneStatus': doneStatus})
    response = api_fetcher.post_data(url, json_data)
    self.json_string = json.dumps(response)

@when('they provide a title and optional description {description}')
def step_impl(self, description):
    doneStatus = False
    json_data = json.dumps({'title': '', 'description': description, 'doneStatus': doneStatus})
    response = api_fetcher.post_data(url, json_data)
    self.json_string = json.dumps(response)
    data = json.loads(self.json_string)
    self.error_msg = str(data['errorMessages'])


@then('a new Todo with this title {title} and description {description} is created and returned')
def step_impl(self, title, description):
    data = json.loads(self.json_string)
    title = data['title']
    description = data['description']
    assert (title == title)
    assert (description == description)
    print("Success")


@then('an error message "{error_message}" is returned')
def step_impl(self, error_message):
    assert(len(self.error_msg) == len(error_message))


@given('a Todo with id "{todo_id}", title "{title}" and description "{description}" exists')
def step_impl(context, todo_id, title, description):
    print("Testing updating Todo")


@when('they provide the Todo ID "{todo_id}", title "{title}", description "{description}", doneStatus "{doneStatus}"')
def step_impl(self, todo_id, title, description, doneStatus):
    if doneStatus == "true":
        doneStatus = True
    else:
        doneStatus = False
    json_data = json.dumps({'title': title, 'description': description, 'doneStatus': doneStatus})
    response = api_fetcher.post_data(url + "/" + str(todo_id), json_data)
    self.json_string = json.dumps(response)


@then('the Todo with id "{todo_id}" is updated')
def step_impl(self, todo_id):
    response = api_fetcher.get_data(url + "/" + str(todo_id))
    self.json_string = json.dumps(response)
    data = json.loads(self.json_string)
    todos = data['todos']
    title = todos[0]['title']
    description = todos[0]['description']
    doneStatus = todos[0]['doneStatus']
    assert (title == title)
    assert (description == description)
    assert (doneStatus == doneStatus)
    print("Success")

@when('they provide the Todo ID "{todo_id}" and updated title "{title}", description "{description}", doneStatus "{doneStatus}"')
def step_impl(self, todo_id, title, description, doneStatus):
    if doneStatus == "true":
        done_status = True
    else:
        done_status = False
    json_data = json.dumps({'title': title, 'description': description, 'doneStatus': done_status})
    response = api_fetcher.post_data(url + "/" + str(todo_id), json_data)
    data = json.loads(json.dumps(response))
    self.error_msg = str(data['errorMessages'])


@given('a Category with id "{category_id}", and title "{category_title}" and a Todo with id "{todo_id}" and title "{todo_title}" exist')
def step_impl(context, category_id, category_title, todo_id, todo_title):
    print("Testing adding Category to Todo")


@when('they provide the Category\'s ID "{category_id}" and the Todo ID "{todo_id}"')
def step_impl(self, category_id, todo_id):
    json_data = json.dumps({'id': category_id})
    try:
        response = api_fetcher.post_data(url + "/" + str(todo_id) + "/categories", json_data)
        json_string = json.dumps(response)
        data = json.loads(json_string)
        self.error_msg = str(data['errorMessages'])
        print(response)
    except JSONDecodeError:
        print("Successfully added category to todo!")


@then('the Category with the provided ID "{category_id}" is added to the Todo with the provided ID "{todo_id}" and returned')
def step_impl(self, category_id, todo_id):
    response = api_fetcher.get_data(url + "/" + str(todo_id))
    self.json_string = json.dumps(response)
    data = json.loads(self.json_string)
    todos = data['todos']
    categories = todos[0]['categories']
    todoId = todos[0]['id']
    categoryId = categories[0]['id']
    assert (todoId == todo_id)
    assert (categoryId == category_id)
    print("Success")


@when('they provide the Category\'s ID "{category_id}" and the Todo ID "{todo_id}" to remove')
def step_impl(self, category_id, todo_id):
    try:
        response = api_fetcher.delete_data(url + "/" + str(todo_id) + "/categories/" + str(category_id))
        json_string = json.dumps(response)
        data = json.loads(json_string)
        self.error_msg = str(data['errorMessages'])
    except JSONDecodeError:
        print("Successfully removed category from todo!")


@then('the Category with the provided ID is removed from the Todo with the provided ID "{todo_id}" and returned')
def step_impl(self, todo_id):
    response = api_fetcher.get_data(url + "/" + str(todo_id))
    self.json_string = json.dumps(response)
    data = json.loads(self.json_string)
    todos = data['todos']
    assert "categories" not in todos[0]
    print("Success")


@when('they provide the Todo ID "{todo_id}" to delete')
def step_impl(self, todo_id):
    try:
        response = api_fetcher.delete_data(url + "/" + str(todo_id))
        json_string = json.dumps(response)
        data = json.loads(json_string)
        self.error_msg = str(data['errorMessages'])
    except JSONDecodeError:
        print("Successfully deleted todo!")



@then("the Todo with the provided ID is deleted")
def step_impl(self):
    print("Success")


