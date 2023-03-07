Feature: Todo management

  Scenario: Creating a new todo
    Given the user wants to create a new todo
    When they provide a title for the todo
    And they provide a description (optional)
    Then a new todo with the provided title and description should be created

  Scenario Outline: Creating a new todo
    Given the user wants to create a new todo
    When they provide a <title> for the todo
    And they provide a <description> (optional)
    Then a new todo with the provided title and description should be created

    Examples: 
      | title           | description           |
      | Buy groceries   |                       |
      | Clean the house | Get cleaning supplies |
      | Pay bills       | Check account balance |

  Scenario: Marking a todo as complete
    Given the user has an existing todo
    And the todo is not marked as complete
    When they mark the todo as complete
    Then the todo should be marked as complete

  Scenario Outline: Marking a todo as complete
    Given the user has an existing todo with title "<title>"
    And the todo is not marked as complete
    When they mark the todo as complete
    Then the todo should be marked as complete

    Examples: 
      | title      |
      | "Shopping" |
      | "Cleaning" |
      | "Exercise" |

  Scenario: Updating an existing todo
    Given the user has an existing todo
    When they update the todo's title
    And they update the todo's description
    Then the todo's title and description should be updated

  Scenario Outline: Updating an existing todo
    Given the user has an existing todo
    When they update the todo's title to <new_title>
    And they update the todo's description to <new_description>
    Then the todo's title and description should be updated

    Examples: 
      | new_title       | new_description             |
      | Buy groceries   |                             |
      | Clean the car   | Vacuum and wash             |
      | Schedule doctor | Call to make an appointment |

  Scenario: Assigning a category to a todo
    Given the user has an existing todo
    And they have an existing category
    When they assign the category to the todo
    Then the category should be associated with the todo

  Scenario Outline: Assigning a category to a todo
    Given the user has an existing todo
    And they have an existing <category>
    When they assign the <category> to the todo
    Then the <category> should be associated with the todo

    Examples: 
      | category |
      | Personal |
      | Work     |
      | Errands  |

  Scenario: Assigning a project to a todo
    Given the user has an existing todo
    And they have an existing project
    When they assign the project to the todo
    Then the project should be associated with the todo

  Scenario Outline: Assigning a project to a todo
    Given the user has an existing todo
    And they have an existing <project>
    When they assign the <project> to the todo
    Then the <project> should be associated with the todo

    Examples: 
      | project        |
      | Home project   |
      | Work project   |
      | School project |
