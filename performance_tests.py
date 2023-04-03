import unittest
import api_fetcher
import json
from json.decoder import JSONDecodeError
import psutil
import random
import string
import time
from prettytable import PrettyTable
import matplotlib.pyplot as plt
import numpy as np

url = "http://localhost:4567/todos"

nb_todos = 0
usages = []

class PerformanceTests(unittest.TestCase):
    
    @classmethod
    def setUpClass(cls):
        cls.populate_todos(nb_todos)

    @classmethod
    def populate_todos(cls, nb):
        'Create nb instances of todos and populate with random data'
        start = time.perf_counter()
        for i in range(nb):
            random_doneStatus = bool(random.getrandbits(1))
            # Generate a random string of length 10
            random_title = ''.join(random.choices(string.ascii_letters + string.digits, k=10))
            random_desc = ''.join(random.choices(string.ascii_letters + string.digits, k=10))
            json_data = json.dumps({'title': random_title, 'description': random_desc, 'doneStatus': random_doneStatus})
            api_fetcher.post_data(url, json_data)
        end = time.perf_counter()
        print(f"Populated {nb} todos in {end - start:0.4f} seconds")
        print()
        
    def test_update_todo(self):
        print("Update a todo")
        start = time.perf_counter()
        doneStatus = True
        # todo_id = random.randint(1, 100)
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        json_data = json.dumps({'title': 'newTitle', 'description': 'newDescription', 'doneStatus': doneStatus})
        response = api_fetcher.post_data(url + "/" + str(random_todo_id), json_data)
        end = time.perf_counter()
        print(f"Updated todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        print(response)
        print()

    def test_delete_todo(self):
        print("Delete a todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        try:
            response = api_fetcher.delete_data(url + "/" + str(random_todo_id))
            
        except JSONDecodeError:
            print("Success")
        end = time.perf_counter()
        print(f"Deleted todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        print()
    
    def test_get_todo(self):
        print("Get a todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        response = api_fetcher.get_data(url + "/" + str(random_todo_id))
        end = time.perf_counter()
        print(f"Got todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        print(response)
        print()
    
    def test_get_all_todos(self):
        print("Get all todos")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        end = time.perf_counter()
        print(f"Got all todos in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        print()
    
    def test_create_todo(self):
        print("Create a todo")
        start = time.perf_counter()
        doneStatus = True
        # Generate a random string of length 10
        random_title = ''.join(random.choices(string.ascii_letters + string.digits, k=10))
        random_desc = ''.join(random.choices(string.ascii_letters + string.digits, k=10))
        json_data = json.dumps({'title': random_title, 'description': random_desc, 'doneStatus': doneStatus})
        response = api_fetcher.post_data(url, json_data)
        end = time.perf_counter()
        print(f"Created todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        print(response)
        print()
    
    def test_get_categories_by_todo(self):
        print("Get categories by todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        response = api_fetcher.get_data(url + "/" + str(random_todo_id) + "/categories")
        end = time.perf_counter()
        print(f"Got categories by todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        print(response)
        print()

    def test_add_category_to_todo(self):
        print("Add category to todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        data = json.dumps({'id': '1'})
        try:
            response = api_fetcher.post_data(url + "/" + str(random_todo_id) + "/categories", data)
        except JSONDecodeError:
            print("Success")
        end = time.perf_counter()
        print(f"Added category to todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        # print(response)
        print()
    
    def test_delete_category_from_todo(self):
        print("Delete category from todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        try:
            response = api_fetcher.delete_data(url + "/" + str(random_todo_id) + "/categories/1")
        except JSONDecodeError:
            print("Success")
        end = time.perf_counter()
        print(f"Deleted category from todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        # print(response)
        print()
    
    def test_add_taskof_to_todo(self):
        print("Add task to todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        data = json.dumps({'id': '1'})
        try:
            response = api_fetcher.post_data(url + "/" + str(random_todo_id) + "/tasksof", data)
        except JSONDecodeError:
            print("Success")
        end = time.perf_counter()
        print(f"Added task to todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        # print(response)
        print()
    
    def test_delete_task_from_todo(self):
        print("Delete task from todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        try:
            response = api_fetcher.delete_data(url + "/" + str(random_todo_id) + "/tasksof/1")
        except JSONDecodeError:
            print("Success")
        end = time.perf_counter()
        print(f"Deleted task from todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        # print(response)
        print()
    
    def test_get_taskof_of_todo(self):
        print("Get task of todo")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        num_todos = len(response_data['todos'])
        random_index = random.randint(0, num_todos - 1)
        random_todo_id = response_data['todos'][random_index]['id']
        response = api_fetcher.get_data(url + "/" + str(random_todo_id) + "/tasksof")
        end = time.perf_counter()
        print(f"Got task of todo in {end - start:0.4f} seconds")
        self.cpu_usage()
        self.memory_usage()
        print(response)
        print()
    
    @classmethod
    def cpu_usage(cls):
        global usages
        # Get current CPU percent usage
        cpu_percent = psutil.cpu_percent()

        usages.append(cpu_percent)

        # Print CPU percent usage
        print(f"CPU usage: {cpu_percent}%")

    @classmethod
    def memory_usage(cls):
        # Get current available free memory in bytes
        available_memory = psutil.virtual_memory().available

        # Convert available memory to GB
        available_memory_gb = available_memory / (1024 ** 3)

        # Print available free memory in bytes
        print(f"Available free memory: {available_memory_gb} GB")

    @classmethod
    def tearDownClass(cls):
        print("Delete all todos")
        start = time.perf_counter()
        response = api_fetcher.get_data(url)
        response = json.dumps(response)
        response_data = json.loads(response)
        # print(response_data['todos'][0]['id'])
        for todo_id in response_data['todos']: 
            try:
                response = api_fetcher.delete_data(url + "/" + str(todo_id['id']))
                print(response)
            except JSONDecodeError:
                # print("Success")
                continue
        end = time.perf_counter()
        print(f"Deleted all todos in {end - start:0.4f} seconds")
        cls.cpu_usage()
        cls.memory_usage()
        print("Deleted all todos")
        print()

if __name__ == '__main__':
    av_memory = []
    nb_todos = 100
    print("Testing with 100 todos")
    start = time.perf_counter()
    unittest.main(argv=['first-arg-is-ignored'], exit=False)
    av_memory.append(psutil.virtual_memory().available / (1024 ** 3))
    end = time.perf_counter()
    time1 = end - start
    nb_todos = 200
    print("Testing with 200 todos")
    start = time.perf_counter()
    unittest.main(argv=['first-arg-is-ignored'], exit=False)
    av_memory.append(psutil.virtual_memory().available / (1024 ** 3))
    end = time.perf_counter()
    time2 = end - start
    nb_todos = 300
    print("Testing with 300 todos")
    start = time.perf_counter()
    unittest.main(argv=['first-arg-is-ignored'], exit=False)
    av_memory.append(psutil.virtual_memory().available / (1024 ** 3))
    end = time.perf_counter()
    time3 = end - start
    nb_todos = 400
    print("Testing with 400 todos")
    start = time.perf_counter()
    unittest.main(argv=['first-arg-is-ignored'], exit=False)
    av_memory.append(psutil.virtual_memory().available / (1024 ** 3))
    end = time.perf_counter()
    time4 = end - start
    nb_todos = 500
    print("Testing with 500 todos")
    start = time.perf_counter()
    unittest.main(argv=['first-arg-is-ignored'], exit=False)
    av_memory.append(psutil.virtual_memory().available / (1024 ** 3))
    end = time.perf_counter()
    time5 = end - start
    table = PrettyTable()
    times = [time1, time2, time3, time4, time5]
    todos = [100, 200, 300, 400, 500]

    # Compute average CPU usage
    cpu_usages = []
    usages1 = []
    usages2 = []
    usages3 = []
    usages4 = []
    usages5 = []
    for i in range(0, 12):
        usages1.append(usages[i])
    for i in range(12, 24):
        usages2.append(usages[i])
    for i in range(24, 36):
        usages3.append(usages[i])
    for i in range(36, 48):
        usages4.append(usages[i])
    for i in range(48, 60):
        usages5.append(usages[i])

    cpu_usages.append(sum(usages1) / len(usages1))
    cpu_usages.append(sum(usages2) / len(usages2))
    cpu_usages.append(sum(usages3) / len(usages3))
    cpu_usages.append(sum(usages4) / len(usages4))
    cpu_usages.append(sum(usages5) / len(usages5))

    print("Testing results")
    table.add_column("Number of instances", todos)
    table.add_column("Testing Time (seconds)", times)
    table.add_column("Average CPU usage (%)", cpu_usages)
    table.add_column("Available memory (GB)", av_memory)
    print(table)

    fig, (ax1, ax2, ax3) = plt.subplots(1, 3, figsize=(15, 5))
    print(times[0])
    print(cpu_usages[0])
    print(av_memory[0])

    ax1.plot(todos, times)
    ax1.set_title("Testing time")
    ax1.set_xlabel("Number of instances")
    ax1.set_ylabel("Time (seconds)")

    ax2.plot(todos, cpu_usages)
    ax2.set_title("Average CPU usage")
    ax2.set_xlabel("Number of instances")
    ax2.set_ylabel("CPU usage (%)")

    ax3.plot(todos, av_memory)
    ax3.set_title("Available memory")
    ax3.set_xlabel("Number of instances")
    ax3.set_ylabel("Available memory (GB)")

    fig.suptitle("Performance Testing results")
    plt.show()
