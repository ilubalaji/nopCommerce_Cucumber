Feature: Customers

Background: Common steps for all scenarios

		Given Launch Chrome browser
		When Opening URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
		Then Enters Email as "admin@yourstore.com" and password as "admin"
		And Click on Log In button
		
		Then User can view Dashboard Page
		
	@Sanity	
	Scenario: Add a new Customer
		
		When Click on Customers Menu
    Then Click on Customers category
    And Click on Add new button
    Then User can view Add a new Customer Page
    When Enter details in all fields of Customer info
    And Click on Save button
    Then Displays confirmation message "The new customer has been added successfully."
 		Then Close the browser
 	
 	@Regression	
 	Scenario: Search a Customer by Email ID
 		
 		When Click on Customers Menu
    Then Click on Customers category
    Then Enter EMail id into Email field
    And Click on Search button
    Then Displays Customer info with Email in the Search table
    Then Close the browser
  
  @Regression  
  Scenario: Search a Customer by Name
 		
 		When Click on Customers Menu
    Then Click on Customers category
    Then Enter FirstName into First name field
    Then Enter LastName into Last name field
    And Click on Search button
    Then Displays Customer info with Name in the Search table
    Then Close the browser