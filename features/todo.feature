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
    When they provide a title and optional description "<description>"
    Then an error message "<error_message>" is returned

    Examples: 
      | description                      | error_message                                |
      |                                  | ['Failed Validation: title : can not be empty']|
      | Milk, bread, and eggs            | ['Failed Validation: title : can not be empty']|

  Scenario: Updating a Todo

  Scenario Outline: Updating an existing Todo
    Given a Todo with id "<todo_id>", title "<title>" and description "<description>" exists
    When they provide the Todo ID "<todo_id>", title "<title>", description "<description>", doneStatus "<doneStatus>"
    Then the Todo with id "<todo_id>" is updated

    Examples: 
      | todo_id | title          | description                                 | doneStatus  |
      |       3 | Buy Groceries  | Milk, bread, and eggs                       | true        |
      |       4 | Clean House    | Sweep floors and dust furniture             | false       |
      |       5 | Go for a Run   | 30 minutes in the park                      | false       |
      |       6 | Finish Project | Submit code review and update documentation | true        |

  Scenario Outline: Error updating a Todo
    When they provide the Todo ID "<todo_id>" and updated title "<title>", description "<description>", doneStatus "<doneStatus>"
    Then an error message "<error_message>" is returned

    Examples: 
      | todo_id | title          | description     | done_status | error_message                                 |
      |      -1 | New Title      | New description | false       | ['No such todo entity instance with GUID or ID -1 found']                 |

  Scenario: Adding a Category to a Todo

  Scenario Outline: Adding a Category to a Todo
    Given a Category with id "<category_id>", and title "<category_title>" and a Todo with id "<todo_id>" and title "<todo_title>" exist
    When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>"
    Then the Category with the provided ID "<category_id>" is added to the Todo with the provided ID "<todo_id>" and returned

    Examples: 
      | category_id | todo_id | category_title | todo_title          |
      |           1 |       3 | Office         | Buy Groceries       |
      |           2 |       4 | Home           | Clean House         |
      |           1 |       5 | Office         | Go for a Run        |
      |           2 |       6 | Home           | Finish Project      |

    Scenario Outline: Error adding a Category to a Todo
      When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>"
      Then an error message "<error_message>" is returned

      Examples:
        | category_id | todo_id | error_message |
        |          -1 |       1 | ['Could not find thing matching value for id'] |
        |           1 |      -1 | ['Could not find parent thing for relationship todos/-1/categories']     |


    Scenario: Removing a Category from a Todo

      Scenario Outline: Removing a Category from a Todo
        Given a Category with id "<category_id>", and title "<category_title>" and a Todo with id "<todo_id>" and title "<todo_title>" exist
        When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>" to remove
        Then the Category with the provided ID is removed from the Todo with the provided ID "<todo_id>" and returned

        Examples:
          | category_id | todo_id | category_title | todo_title          |
          |           1 |       3 | Office         | Buy Groceries       |
          |           2 |       4 | Home           | Clean House         |
          |           1 |       5 | Office         | Go for a Run        |
          |           2 |       6 | Home           | Finish Project      |

      Scenario Outline: Error removing a Category from a Todo
        When they provide the Category's ID "<category_id>" and the Todo ID "<todo_id>" to remove
        Then an error message "<error_message>" is returned

        Examples:
          | category_id | todo_id | error_message |
          |          -1 |       1 | ['Could not find any instances with todos/1/categories/-1'] |
          |           1 |      -1 | ['Cannot invoke "uk.co.compendiumdev.thingifier.core.domain.instances.ThingInstance.getRelationships()" because "parent" is null']     |
          |           1 |       2 | ['Could not find any instances with todos/2/categories/1']     |

      Scenario: Deleting a Todo

        Scenario Outline: Deleting a Todo
          Given a Todo with id "<todo_id>", title "<title>" and description "<description>" exists
          When they provide the Todo ID "<todo_id>" to delete
          Then the Todo with the provided ID is deleted

          Examples:
            | todo_id | title          | description                                 |
            |       3 | Buy Groceries  | Milk, bread, and eggs                       |
            |       4 | Clean House    | Sweep floors and dust furniture             |
            |       5 | Go for a Run   | 30 minutes in the park                      |
            |       6 | Finish Project | Submit code review and update documentation |

          Scenario Outline: Error deleting a Todo
            When they provide the Todo ID "<todo_id>" to delete
            Then an error message "<error_message>" is returned

            Examples:
              | todo_id | error_message                                 |
              |      -1 | ['Could not find any instances with todos/-1']                 |
