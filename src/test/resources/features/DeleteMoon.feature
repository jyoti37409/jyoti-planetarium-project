Feature: Delete Moon

  As a user
  I want to delete a moon
  So that I can correct my findings

  Background:
    Given the user is on the Planetarium homepage

  Scenario: Delete an individual moon
    Given the user is on the Planetarium homepage
    When the user enters the moon name "MoonOne" in the delete field
    And clicks on the delete button
    Then the moon "MoonOne" should no longer appear on the Planetarium homepage






