package com.nopCommerce.stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.nopCommerce.pageObjects.AddCustomerPage;
import com.nopCommerce.pageObjects.LoginPage;
import com.nopCommerce.pageObjects.SearchCustomerPage;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;


public class Steps extends BaseClass {
	
	@Before
	public void setup() throws IOException {
		
		// Logger steps started
		logger=Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		// Logger steps end
		
		
		// config.properties file - loading steps started
		configProp=new Properties();
		FileInputStream configPropFile=new FileInputStream("config.properties");
		configProp.load(configPropFile);
		// config.properties file - loading steps end
		
		
		String br=configProp.getProperty("browser");

		if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }

        else if (br.equals("ie")) {
            System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
            driver = new InternetExplorerDriver();
        }
        
        else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }
		
	}
	
	
	
	// --------------------------------- Login.feature --> stepDefinitions ---------------------------------
	
	@Given("Launch Chrome browser")
	public void launch_chrome_browser() {
		logger.info("*************** Launching Browser ***************");
		lp=new LoginPage(driver);
	    
	}

	@When("Opening URL {string}")
	public void opening_url(String url) {
		logger.info("*************** Opening URL ***************");
		driver.get(url);
		driver.manage().window().maximize();
	   
	}

	@Then("Enters Email as {string} and password as {string}")
	public void enters_email_as_and_password_as(String email, String password) {
		logger.info("*************** Entering UserName and Password ***************");
		lp.setUserName(email);
		lp.setPassword(password);
	    
	}

	@Then("Click on Log In button")
	public void click_on_log_in_button() throws InterruptedException {
		logger.info("*************** Clicking on Login button ***************");
		lp.clickLogin();
		Thread.sleep(4000);
	    
	}

	@Then("Page Title should be {string}")
	public void opens(String expTitle) throws InterruptedException {
		
		logger.info("*************** Validating Login Credentials ***************");
		if(driver.getPageSource().contains("Login was unsuccessful. Please correct the errors and try again.\r\n"
				+ "The credentials provided are incorrect"))
		{
			logger.info("*************** Invalid data/Login Failed ***************");
			driver.close();
			Assert.assertTrue(false);
		}
		else {
			logger.info("*************** Valid data/Login Successfull ***************");
			Assert.assertEquals(expTitle, driver.getTitle());
		}
		Thread.sleep(4000);
	}

	@Then("Click on Logout button")
	public void click_on_logout_button() {
		logger.info("*************** Clicking on Logout button ***************");
		lp.clickLogout();
	   
	}

	@Then("Close the browser")
	public void close_the_browser() {
		logger.info("*************** Closing the browser ***************");
		driver.quit();
	    
	}
	
	
	// --------------------------------- Customers.feature --> stepDefinitions ---------------------------------
	
	// --------------------------------- Add a new Customer ----------------------------------------------------
	
	
	@When("User can view Dashboard Page")
	public void user_can_view_dashboard_page() {
		
		logger.info("*************** Started add a new customer info ***************");
		addCust=new AddCustomerPage(driver);
		
		logger.info("*************** Dashboard page title validation ***************");
		Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	   
	}
	
	@When("Click on Customers Menu")
	public void click_on_customers_menu() throws InterruptedException {
		logger.info("*************** Clicking on Customers Menu ***************");
		Thread.sleep(3000);
        addCust.clickOnCustomersMenu();
	   
	}
	
	@Then("Click on Customers category")
	public void click_on_customers_category() throws InterruptedException {
		logger.info("*************** Clicking on Customers category ***************");
		Thread.sleep(2000);
        addCust.clickOnCustomersCategory();
	   
	}

	@Then("Click on Add new button")
	public void click_on_add_new_button() {
		logger.info("*************** Clicking on Add new button ***************");
		addCust.clickOnAddnew();
	   
	}

	@Then("User can view Add a new Customer Page")
	public void user_can_view_add_a_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	   
	}

	@When("Enter details in all fields of Customer info")
	public void enter_details_in_all_fields_of_customer_info() throws InterruptedException {
		
		logger.info("*************** Entering Customer details ***************");
        
		String email = randomString().toLowerCase() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        // Registered - default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Balaji");
        addCust.setLastName("Ravichandran");
        addCust.setDob("04/17/1992"); 			// Format is MM/DD/YYY
        addCust.setCompanyName("One7");
        addCust.setAdminContent("For Automation Testing Purpose...............");
	    
	}

	@When("Click on Save button")
	public void click_on_save_button() {
		logger.info("*************** Clicking on Save button ***************");
		addCust.clickOnSave();
	  
	}

	@Then("Displays confirmation message {string}")
	public void displays_confirmation_message(String string) {
		logger.info("*************** Validating new customer added confirmation message ***************");
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	  
	}

	
	// --------------------------------- Search a Customer by Email ID ---------------------------------
	
	
	@Then("Enter EMail id into Email field")
	public void enter_e_mail_id_into_email_field() {
		logger.info("*************** Started searching a existing Customer by Email ID ***************");
		searchCust=new SearchCustomerPage(driver);
        searchCust.setEmail("admin@yourStore.com");

	}

	@Then("Click on Search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
        Thread.sleep(3000);
	    
	}

	@Then("Displays Customer info with Email in the Search table")
	public void displays_customer_info_with_email_in_the_search_table() {
		logger.info("*************** Validating existing Customer by giving Email ID ***************");
		boolean status=searchCust.searchCustomerByEmail("admin@yourStore.com");
        Assert.assertEquals(true, status);
	    
	}
	
	
	// --------------------------------- Search a Customer by Name -------------------------------------
	

	@Then("Enter FirstName into First name field")
	public void enter_first_name_into_first_name_field() {
		logger.info("*************** Started searching a existing Customer by Name ***************");
		searchCust=new SearchCustomerPage(driver);
        searchCust.setFirstName("John");
	    
	}

	@Then("Enter LastName into Last name field")
	public void enter_last_name_into_last_name_field() {
		searchCust.setLastName("Smith");
	  
	}
	
	@Then("Displays Customer info with Name in the Search table")
	public void displays_customer_info_with_name_in_the_search_table() {
		logger.info("*************** Validating existing Customer by giving Name ***************");
		boolean status=searchCust.searchCustomerByName("John Smith");
        Assert.assertEquals(true, status);
	    
	}

}
