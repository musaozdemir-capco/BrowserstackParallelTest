@regression
Feature: Reset Password

  Background:
    Given I click Login button in login page

  @resetPassword
  Scenario: reset password
    Given I click I forgot my password button
    And I enter username as "abc.def@geed.com"
    And I click reset password button
    Then I verify message displayed included "abc.def@geed.com" on the same page

