@login  @regression
Feature: login Functionality

  Background:
    Given I click Login button in login page

  Scenario: Unsuccessful login with invalid entries
    When I enter username as "username" and password as "password"
    And I click login button
    Then verify that "Incorrect username, email or password" message displayed

  Scenario: Unsuccessful login with empty entries
    When I enter username as "" and password as ""
    And I click login button
    Then verify that "Please enter your email or username, and password." displayed

  Scenario: Unsuccessful login with valid username and invalid password
    When I enter username as "m.ozdemir" and password as "password"
    And I click login button
    Then verify that "Incorrect username, email or password" displayed

  Scenario: Unsuccessful login with empty username and invalid password
    When I enter username as "" and password as "password"
    And I click login button
    Then verify that "Please enter your email or username, and password." displayed

  @smoke
  Scenario:Successful login with valid credentials
    Given I enter user name as "m.ozdemir" and password as "Capco@3033"
    And I click login button
    Then I verify greeting message "Hi musa ozdemir" is displayed in dashboard

    @test
    Scenario: verify logo
     Given I verify logo displayed in login page




