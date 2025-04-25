Feature: Secure login to access the Planetarium
  As a user
  I want to securely access my account
  So I can interact with the Planetarium in a secure environment
# Happy Path Scenario
  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user provides a valid username "Batman" while login
    And the user provides a valid password "Iamthenight1939" while login
    When the user clicks the "login" button
    Then the user should be redirected to the Planetarium home page

# Sad Path Scenario
  Scenario Outline: Failed login with invalid credentials
    Given the user is on the login page
    When the user provides an invalid username "<username>"
    And provides an invalid password "<password>"
    And clicks the "Login" button
    Then the user should see an error message "Invalid credentials"
    And the user should remain on the login page

    Examples:
      | username                          | password        |
      | Sairam                              | s12             |
      | 123Steve                          | PassWord123     |
      | _admin                            | Pass2024        |
      | -user                             | Welcome123      |
      | admin!                            | Hello123        |
      | Tom                               | password        |
      | Sarah_99                          | abc             |
      | Alice                             | 123456          |
      | TestUser                          | Password        |
      | validUser                         | A_1             |
      | User@Name                         | Secure456       |
      | Steve Jobs                        | Macbook1        |
      | anna-                             | Aa1             |
      | L                                 | HelloWorld123   |
      | longusername_exceeding_30_chars  | ValidPass123    |
      | JohnDoe                           | 123             |
      | UsEr_12                           | Ab_cd           |
      | betaUser                          | PASSWORD123     |
      | MyUser                            | lowerpass123    |
      | NotAUser                          | UPPERCASEONLY   |
