Feature: Manage Todos

  Scenario: Creating a new Todo

  Scenario Outline: Creating a new Todo
    Given a user wants to create a new Todo
    When they provide a title "<title>" and optional description "<description>"
    Then a new Todo with this title "<title>" and description "<description>" is created and returned

    Examples: 
      | title          | description                                 |
      | Buy Groceries  | Milk, bread, and eggs                       |
      | Clean House    | Sweep floors and dust furniture             |
      | Go for a Run   | 30 minutes in the park                      |
      | Finish Project | Submit code review and update documentation |

  Scenario Outline: Error creating a new Todo
    Given a user wants to create a new Todo
    When they provide a title "<title>" and optional description "<description>"
    Then an error message "<error_message>" is returned

    Examples: 
      | title          | description                      | error_message                                |
      |                |                                  | Title is required                            |
      |                | Milk, bread, and eggs            | Title is required                            |

  Scenario: Updating a Todo

  Scenario Outline: Updating an existing Todo
    Given a Todo with id "<todo_id>", title "<title>" and description "<description>" exists
    When they provide the Todo ID "<todo_id>" and updated title "<title>", description "<description>", doneStatus "<doneStatus>"
    Then the Todo title "<title>", description "<description>", doneStatus "<doneStatus>" is updated

    Examples: 
      | todo_id | title          | description                                 | doneStatus  |
      |       1 | Buy Groceries  | Milk, bread, and eggs                       | true        |
      |       2 | Clean House    | Sweep floors and dust furniture             | false       |
      |       3 | Go for a Run   | 30 minutes in the park                      | false       |
      |       4 | Finish Project | Submit code review and update documentation | true        |

  Scenario Outline: Error updating a Todo
    When they provide the Todo ID "<todo_id>" and updated title "<title>", description "<description>", doneStatus "<done_status>"
    Then an error message "<error_message>" is returned

    Examples: 
      | todo_id | title          | description     | done_status | error_message                                 |
      |      -1 | New Title      | New description | false       | No todo with the id -1 exist                  |

  Scenario: Adding a Category to a Todo

  Scenario Outline: Adding a Category to a Todo
    Given a Category with id "<category_id>", and title "<category_title>" and a Todo with id "<todo_id>" and title "<todo_title>" exist
    When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>"
    Then the Category with the provided ID is added to the Todo with the provided ID and returned

    Examples: 
      | category_id | todo_id | category_title | todo_title          |
      |           1 |       1 | Work           | Buy Groceries       |
      |           2 |       2 | Home           | Clean House         |
      |           3 |       3 | Health         | Go for a Run        |
      |           4 |       4 | Personal       | Finish Project      |

    Scenario Outline: Error adding a Category to a Todo
      When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>"
      Then an error message "<error_message>" is returned

      Examples:
        | category_id | todo_id | error_message |
        |          -1 |       1 | No category with the id -1 exist |
        |           1 |      -1 | No todo with the id -1 exist     |
        |          1 |       1 | The category with id 1 already exists for todo with id 1     |

    Scenario: Removing a Category from a Todo

      Scenario Outline: Removing a Category from a Todo
        Given a Category with id "<category_id>", and title "<category_title>" and a Todo with id "<todo_id>" and title "<todo_title>" exist
        When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>" to remove
        Then the Category with the provided ID is removed from the Todo with the provided ID and returned

        Examples:
          | category_id | todo_id | category_title | todo_title          |
          |           1 |       1 | Work           | Buy Groceries       |
          |           2 |       2 | Home           | Clean House         |
          |           3 |       3 | Health         | Go for a Run        |
          |           4 |       4 | Personal       | Finish Project      |

      Scenario Outline: Error removing a Category from a Todo
        When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>" to remove
        Then an error message "<error_message>" is returned

        Examples:
          | category_id | todo_id | error_message |
          |          -1 |       1 | No category with the id -1 exist |
          |           1 |      -1 | No todo with the id -1 exist     |
          |           1 |       2 | A category with id 1 does not exist for todo with id 2     |

      Scenario: Deleting a Todo

        Scenario Outline: Deleting a Todo
          Given a Todo with id "<todo_id>", title "<title>" and description "<description>" exists
          When they provide the Todo ID "<todo_id>"
          Then the Todo with the provided ID is deleted

          Examples:
            | todo_id | title          | description                                 |
            |       1 | Buy Groceries  | Milk, bread, and eggs                       |
            |       2 | Clean House    | Sweep floors and dust furniture             |
            |       3 | Go for a Run   | 30 minutes in the park                      |
            |       4 | Finish Project | Submit code review and update documentation |

          Scenario Outline: Error deleting a Todo
            When they provide the Todo ID "<todo_id>"
            Then an error message "<error_message>" is returned

            Examples:
              | todo_id | error_message                                 |
              |      -1 | No todo with the id -1 exist                  |
