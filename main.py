from Flask import Flask, jsonify, request
import api_fetcher

app = Flask(__name__)

@app.route('/todos', methods=['POST'])
def create_todo(title, description, donStatus):
    data = api_fetcher.post_data('http://localhost:4567/todos', {'title': title, 'description': description, 'doneStatus': donStatus})
    return jsonify(data)

@app.route('/todos', methods=['GET'])
def get_todos():
    data = api_fetcher.get_data('http://localhost:4567/todos', {})
    return jsonify(data)

@app.route('/todos/<int:todo_id>', methods=['GET'])
def get_todo_by_id(todo_id):
    data = api_fetcher.get_data('http://localhost:4567/todos/{}'.format(todo_id), {})
    return jsonify(data)

@app.route('/todos/<int:todo_id>', methods=['PUT']) # update    
def update_todo_by_id(todo_id, title, description, donStatus):
    data = api_fetcher.post_data('http://localhost:4567/todos/{}'.format(todo_id), {'title': title, 'description': description, 'doneStatus': donStatus})
    return jsonify(data)

@app.route('/todos/<int:todo_id>', methods=['DELETE'])
def delete_todo_by_id(todo_id):
    data = api_fetcher.post_data('http://localhost:4567/todos/{}'.format(todo_id), {})
    return jsonify(data)

@app.route('/todos/<int:todo_id>/categories', methods=['GET'])
def get_categories_by_todo_id(todo_id):
    data = api_fetcher.get_data('http://localhost:4567/todos/{}/categories'.format(todo_id), {})
    return jsonify(data)

@app.route('/todos/<int:todo_id>/categories', methods=['POST'])
def add_category_to_todo_by_id(todo_id, category_id):
    data = api_fetcher.post_data('http://localhost:4567/todos/{}/categories'.format(todo_id), {'category_id': category_id})
    return jsonify(data)

@app.route('/todos/<int:todo_id>/categories/<int:category_id>', methods=['DELETE'])
def delete_category_from_todo_by_id(todo_id, category_id):
    data = api_fetcher.post_data('http://localhost:4567/todos/{}/categories/{}'.format(todo_id, category_id), {})
    return jsonify(data)

@app.route('/todos/<int:todo_id>/taskof', methods=['GET'])
def get_taskof_by_todo_id(todo_id):
    data = api_fetcher.get_data('http://localhost:4567/todos/{}/taskof'.format(todo_id), {})
    return jsonify(data)

@app.route('/todos/<int:todo_id>/taskof', methods=['POST'])
def add_taskof_to_todo_by_id(todo_id, taskof_id):
    data = api_fetcher.post_data('http://localhost:4567/todos/{}/taskof'.format(todo_id), {'taskof_id': taskof_id})
    return jsonify(data)

@app.route('/todos/<int:todo_id>/taskof/<int:taskof_id>', methods=['DELETE'])
def delete_taskof_from_todo_by_id(todo_id, taskof_id):
    data = api_fetcher.post_data('http://localhost:4567/todos/{}/taskof/{}'.format(todo_id, taskof_id), {})
    return jsonify(data)
           

if __name__ == '__main__':
    app.run(debug=True)




