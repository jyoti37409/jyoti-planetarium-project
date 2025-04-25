Feature: Add Moon

  Background:
    Given the user is on the planetarium homepage and selects Moon

  ##############################################################
  #                      HAPPY PATH                            #
  ##############################################################

  Scenario Outline: Valid moon creation with optional image
    When the user provides a valid moon name "<moonName>", planet ID <planetId>, and image "<imagePath>"
    Then the moon name "<moonName>" should be saved on the Planetarium Home Page

    Examples:
      | moonName        | planetId | imagePath                                  |
      | MoonOne         | 1        |                                            |
      | Moon_Two        | 1        |                                            |
      | Moon-Three      | 1        |                                            |
      | Moon Four       | 1        | C:\Users\havisha\Downloads\pluto.jpg       |
      | M1234567890     | 1        |                                            |
      | Luna1           | 1        | C:\Users\havisha\Downloads\pluto.jpg       |
      | Deimos_22       | 1        |                                            |
      | Phoebe-Test     | 1        | C:\Users\havisha\Downloads\jupiter.jpg     |
      | Char_onice      | 1        |                                            |
      | Moon 2025       | 1        |                                            |

  ##############################################################
  #                      SAD PATH                              #
  ##############################################################

  Scenario Outline: Invalid moon creation triggers error
    When the user provides an invalid moon name "<moonName>", planet ID <planetId>, and image "<imagePath>"
    Then an moon alert should appear saying "<expectedAlert>"

    Examples:
      | moonName                                | planetId | imagePath | expectedAlert             |
      | @@@@!!                                   | 1        |           | Invalid moon name         |
      |                                         | 1        |           | Invalid moon name         |
      | MoonThatIsWayTooLongToBeAValidName12345  | 1        |           | Invalid moon name         |
      | MoonOne                                  | 1        |           | Invalid moon name         |
      | ValidName                                | 9        |           | Invalid planet ID         |
      | Moon@2024                                | 1        |           | Invalid moon name         |
      | Moon#New                                 | 1        |           | Invalid moon name         |
      | Moon-With-31-Characters-Too-Long-Here123 | 1        |           | Invalid moon name         |
      | TestMoon                                 | 999      |           | Invalid planet ID         |
      | Moon?Name                                | 1        |           | Invalid moon name         |
