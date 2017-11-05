#Author: nidhinjos@gmail.com
Feature: First View Web Application Features

  @fv
  Scenario: User Logs in with Valid Credentials
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    And User Name should be Displayed on Home Screen
    Then User should be logged out successfully

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: User tries to Login with Invalid Credentials
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos1@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then Error Message as Expected must be Displayed
      """
      Unauthorized: your email or password are incorrect
      """

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: Logged in User Closes Browesr and Launches it Again
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    And User Name should be Displayed on Home Screen
    Then After the user closes the browser and launches again
    Then Session should not be persisted

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: User Updates Name and Logs Out
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    When User Clicks on Name Under Profile
    And User Updates First Name
      | FName | Joss |
    And User Updates Last Name
      | LName | Sheldon |
    And Clicks Done
    Then Profile Name should reflect the Update

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: User Logs in and updates the Name Back to Original
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    Then Profile Name should reflect the Previous Update
    When User Clicks on Name Under Profile
    And User Updates First Name
      | FName | Nidhin |
    And User Updates Last Name
      | LName | Joseph |
    And Clicks Done
    Then Profile Name should reflect the Update

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: User Sets a 5 minute Time Notification for the First Child
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    When User Selects the First Child
    Then User then sets a time based notification from their first stop.
      | Time | Five |
    And User Clicks Done to Complete the setup
    When the User Clicks on Time Notification
    Then The RadioButton Corresponding to the Time Based Notification set should be Selected

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: User Sets a 10 minute Time Notification for the First Child
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    When User Selects the First Child
    Then User then sets a time based notification from their first stop.
      | Time | Ten |
    And User Clicks Done to Complete the setup
    When the User Clicks on Time Notification
    Then The RadioButton Corresponding to the Time Based Notification set should be Selected

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: User Sets  Time Notification for the First Child to None
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    When User Selects the First Child
    Then User then sets a time based notification from their first stop.
      | Time | None |
    And User Clicks Done to Complete the setup
    When the User Clicks on Time Notification
    Then The RadioButton Corresponding to the Time Based Notification set should be Selected

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: Check Map Loads without Errors
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    When User Selects Map
    Then Map should load without Errros

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: Check Map can be Zoomed In on
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    When User Selects Map
    Then User should be able to zoom in on the map

  #-------------------------------------------------------------------------------------------------------------
  @fv
  Scenario: Check Map can be Zoomed Out on
    Given First View Login page is Accesible
    When The user provides an email address
      | Email | nidhinjos@gmail.com |
    And The user provides a password
      | Password | RlZub3YyMDE3 |
    And Click on the Login button
    Then User should be logged in successfully
    When User Selects Map
    Then User should be able to zoom out on the map
