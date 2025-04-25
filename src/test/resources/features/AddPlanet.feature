Feature: Add Planet

  Background:
    Given the user is on the planetarium homepage and selects Planet

  #  Happy Path 1
  Scenario: Valid user should add the planet successfully
    When the user provides a valid planet name "Mercury"
    Then the planet name "Mercury" should be saved on the Planetarium Home Page

  #  Happy Path 2
  Scenario: Valid user should add a planet with image
    When the user provides a valid planet name "Testdone12" and image path "C:\\Users\\havisha\\Downloads\\jupiter.jpg"
    Then the planet name "Testdone12" should be saved on the Planetarium Home Page

  #  Sad Path Scenarios (Negative Testing)
  Scenario Outline: user provides invalid planet data should not be accepted
    When the user provides a invalid planet name "<planetName>" and image path "<imagePath>"
    Then an alert should appear saying "<message>"

    Examples:
      | planetName                                | imagePath                                            | message                                   |
      |                                           | C:\\Users\\havisha\\Downloads\\jupiter.jpg           | Invalid planet name         |
      | ThisPlanetNameIsWayTooLongToBeValidXYZ   |                                                      | Invalid planet name |
      | Pl@net!                                   |                                                      | Invalid planet name    |
      | PlanetWithGIF                             | C:\\Users\\havisha\\Downloads\\testing.gif            | Invalid file type   |
      |                                     |                                                      | Invalid planet name                       |

  # Format Support Testing
  Scenario Outline: Planet should be added with supported image formats
    When the user provides a valid planet name "<planetName>" and image path "<imagePath>"
    Then the planet name "<planetName>" should be saved on the Planetarium Home Page

    Examples:
      | planetName     | imagePath                                           |
      | Venus          | C:\\Users\\havisha\\Downloads\\pluto.jpg           |
      | Earth_2025     | C:\\Users\\havisha\\Downloads\\sun123.png          |
      | Mars-Red       | C:\\Users\\havisha\\Downloads\\jupiter.jpg         |
