Feature: Login
	
	@Sanity
	Scenario: Login is successful for valid credentials users
	
		Given Launch Chrome browser
		When Opening URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
		Then Enters Email as "admin@yourstore.com" and password as "admin"
		And Click on Log In button
		
		Then Page Title should be "Dashboard / nopCommerce administration"
		Then Click on Logout button
		And Page Title should be "Your store. Login"
		Then Close the browser
