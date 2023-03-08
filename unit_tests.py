import unittest
import api_fetcher
import json
from json.decoder import JSONDecodeError

url = "http://localhost:4567/todos"


class TestRestApi(unittest.TestCase):

    def test_a_get_todos(self):
        print("test_get_todos")
        print()
        response = api_fetcher.get_data(url)
        print(response)
        print()

    def test_b_post_todos(self):
        print("test_post_todos")
        print()
        doneStatus = False
        json_data = json.dumps({'title': 'aTitle', 'description': 'aDescription', 'doneStatus': doneStatus})
        response = api_fetcher.post_data(url, json_data)
        print(response)
        print()

    def test_c_get_todo_by_id(self):
        print("test_get_todo_by_id")
        print()
        todo_id = 1
        response = api_fetcher.get_data(url + "/" + str(todo_id))
        print(response)
        print()

    def test_d_update_todo_by_id(self):
        print("test_update_todo_by_id")
        print()
        todo_id = 2
        doneStatus = True
        json_data = json.dumps({'title': 'newTitle', 'description': 'newDescription', 'doneStatus': doneStatus})
        response = api_fetcher.post_data(url + "/" + str(todo_id), json_data)
        print(response)
        print()

    def test_e_delete_todo_by_id(self):
        print("test_delete_todo_by_id")
        print()
        todo_id = 26
        try:
            response = api_fetcher.delete_data(url + "/" + str(todo_id))
            print(response)
        except JSONDecodeError:
            print("Success")
        print()

    def test_f_get_categories_by_todo_id(self):
        print("test_get_categories_by_todo_id")
        print()
        todo_id = 1
        response = api_fetcher.get_data(url + "/" + str(todo_id) + "/categories")
        print(response)
        print()

    def test_g_add_category_to_todo_by_id(self):
        print("test_add_category_to_todo_by_id")
        print()
        todo_id = 27
        category_id = 1
        json_data = json.dumps({'id': str(category_id)})
        try:
            response = api_fetcher.post_data(url + "/" + str(todo_id) + "/categories", json_data)
            print(response)
        except JSONDecodeError:
            print("Success")
        print()

    # def test_h_delete_category_from_todo_by_id(self):
    #     print("test_delete_category_from_todo_by_id")
    #     print()
    #     todo_id = 27
    #     category_id = 1
    #     try: 
    #         response = api_fetcher.delete_data(url + "/" + str(todo_id) + "/categories/" + str(category_id))
    #         print(response)
    #     except JSONDecodeError:
    #         print("Success")
    #     print()

    def test_i_add_taskof_to_todo_by_id(self):
        print("test_add_taskof_to_todo_by_id")
        todo_id = 28
        taskof_id = 1
        json_data = json.dumps({'id': str(taskof_id)})
        try:
            response = api_fetcher.post_data(url + "/" + str(todo_id) + "/tasksof", json_data)
            print(response)
        except JSONDecodeError:
            print("Success")
        print()

    def test_j_delete_taskof_from_todo_by_id(self):
        print("test_delete_taskof_from_todo_by_id")
        todo_id = 28
        taskof_id = 1
        try:
            response = api_fetcher.delete_data(url + "/" + str(todo_id) + "/tasksof/" + str(taskof_id))
            print(response)
        except JSONDecodeError:
            print("Success")
        print()

    def test_k_get_taskof_by_todo_id(self):
        print("test_get_taskof_by_todo_id")
        todo_id = 1
        response = api_fetcher.get_data(url + "/" + str(todo_id) + "/tasksof")
        print(response)


if __name__ == '__main__':
    unittest.main()
