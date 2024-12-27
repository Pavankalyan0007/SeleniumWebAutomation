
@tag
Feature: Purchase the from Ecormmerce website.
  I want to use this template for my feature file
Background:
I landed on Ecommerce page.

  @tag2
  Scenario Outline: Positive Test for Submitting the order
    Given Logged in with username <name> and password <password>
    When I Added the product <Productname> and submit the order
    And Checkout <Productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in confirmation page.

    Examples: 
      | name                 | password   | Productname  |
      | pavansoppa@gmail.com | Pavan@2000 | ADIDAS ORIGINAL |
    
