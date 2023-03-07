Feature: Manage Todos

  Scenario Outline: Creating a new Todo
    Given a user wants to create a new Todo
    When they provide a title "<title>" and optional description "<description>"
    Then a new Todo with the provided information is created and returned

    Examples: 
      | title          | description                                 |
      | Buy Groceries  | Milk, bread, and eggs                       |
      | Clean House    | Sweep floors and dust furniture             |
      | Go for a Run   | 30 minutes in the park                      |
      | Finish Project | Submit code review and update documentation |

  Scenario Outline: Error creating a new Todo
    Given a user wants to create a new Todo
    When they provide an invalid title "<title>" and optional description "<description>"
    Then an error message "<error_message>" is returned

    Examples: 
      | title          | description | error_message                             |
      |                |             | Title cannot be blank                     |
      | Too long title | Short       | Title cannot be longer than 50 characters |
      | New Todo       |             | Description cannot be blank               |

  Scenario Outline: Updating an existing Todo
    Given a user wants to update an existing Todo
    When they provide the Todo's ID "<todo_id>" and updated title "<title>", description "<description>", and/or done status "<done_status>"
    Then the Todo with the provided ID is updated with the new information and returned

    Examples: 
      | todo_id | title          | description                                 | done_status |
      |       1 | Buy Groceries  | Milk, bread, and eggs                       | true        |
      |       2 | Clean House    | Sweep floors and dust furniture             | false       |
      |       3 | Go for a Run   | 30 minutes in the park                      | false       |
      |       4 | Finish Project | Submit code review and update documentation | true        |

  Scenario Outline: Error updating a Todo
    Given a user wants to update an existing Todo
    When they provide an invalid Todo ID "<todo_id>" or invalid information to update the Todo with
    Then an error message "<error_message>" is returned

    Examples: 
      | todo_id | title          | description     | done_status | error_message                             |
      |      -1 | New Title      | New description | false       | Todo with ID -1 not found                 |
      |       1 |                |                 | true        | Title cannot be blank                     |
      |       2 | Too long title | Short           | false       | Title cannot be longer than 50 characters |
      |       3 | New Title      |                 | true        | Description cannot be blank               |
      |       4 |                |                 | abc         | Invalid done status value                 |

  Scenario Outline: Adding a Category to a Todo
    Given a user wants to add a Category to a Todo
    When they provide the Category's ID "<category_id>" and the Todo's ID "<todo_id>"
    Then the Category with the provided ID is added to the Todo with the provided ID and returned

    Examples: 
      | category_id | todo_id |
      |           1 |       1 |
      |           2 |       2 |
      |           3 |       3 |
      |           4 |       4 |

  Scenario Outline: Error adding a Category to a Todo
    Given a user wants to add a Category to a Todo
    When they provide an invalid Category ID "<category_id>" or Todo ID "<todo_id>"
    Then an error message "<error_message>" is returned

