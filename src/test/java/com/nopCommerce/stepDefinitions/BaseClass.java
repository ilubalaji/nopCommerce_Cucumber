package com.nopCommerce.stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.nopCommerce.pageObjects.AddCustomerPage;
import com.nopCommerce.pageObjects.LoginPage;
import com.nopCommerce.pageObjects.SearchCustomerPage;

public class BaseClass {
	
	public WebDriver driver;
	
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	
	public static Logger logger;
	Properties configProp;
	
	
	public static String randomString() {
		String generatedString1=RandomStringUtils.randomAlphabetic(7);
		return generatedString1;
				
	}

}
