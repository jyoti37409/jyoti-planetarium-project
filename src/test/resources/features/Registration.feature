Feature: User Registration

  # any starting steps shared between ALL scenarios/scenario outlines can be declared in a Background
  Background:
    Given  the user is on the login page
    When   the user clicks the register link


  # Happy Path Scenario
  Scenario: Valid credential data should allow for registering with the Planetarium
    When   the user provides username "Robin" while registering
    And    the user provides password "Bobb1" while registering
    When   the user clicks the register button
    # Note the syntax below, assuming you have a plugin for intelisense
    # the section wrapped in quotes tells Cucumber you want to inject the data
    # into the step code that is executed
    Then   an alert should appear saying "Account created successfully"
    And    the user should be redirected to the login page

  # Sad Path Scenario
  Scenario Outline: Invalid credential data should not allow for registering with the Planetarium
    When   the user provides username "<username>" while registering
    And    the user provides password "<password>" while registering
    When   the user clicks the register button
    Then   an alert should appear saying "<message>"
    And the user should remain on the register page

  # Examples is used to create a data table that Cucumber will look to for test data during runtime
  # For each row of data, Cucumber will execute the associated Scenario Outline
  Examples:
    |username|password|message|
    |Batman  |Bobb1   |Invalid username    |
    |bobb    |Bobb1   |Invalid username    |
    |Robin!%?|Bobb1   |Invalid username    |
    |3obin   |Bobb1   |Invalid username    |
    |Robin   |Bob3    |Invalid password    |
    |Robin   |bobby   |Invalid password    |
    |Robin   |Bobby   |Invalid password    |
    |Robin   |bobb3   |Invalid password    |
    |Robin   |BOBB3   |Invalid password    |
    |Robin   |Bobb3!? |Invalid password    |
    |Robin   |3obbY   |Invalid password    |

