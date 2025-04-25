Feature: Delete Planet

  As a user
  I want to delete a planet
  So that I can correct my findings

  Background:
    Given the user is on the  Planet1 Planetarium homepage

  Scenario Outline: Delete an individual planet
    When the user deletes the planet "<planetName>"
    Then the moon "<planetName>" should be removed from the Planetarium homepage

    Examples:
      | planetName        |
      | Mercury           |
      | Venus             |
      | Mars-Red          |



