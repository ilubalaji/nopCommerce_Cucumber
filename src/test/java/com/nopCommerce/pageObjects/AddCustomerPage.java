package com.nopCommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	public WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(ldriver, this);
    }
    
    
    //Elements

    By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By lnkCustomers_category=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");

    By btnAddnew=By.xpath("//a[@class='btn btn-primary']");

    By txtEmail=By.xpath("//input[@id='Email']");
    By txtPassword=By.xpath("//input[@id='Password']");

    By txtcustomerRoles=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

    By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
    By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
    By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");


    By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
    By rdMaleGender=By.id("Gender_Male");
    By rdFeMaleGender=By.id("Gender_Female");

    By txtFirstName=By.xpath("//input[@id='FirstName']");
    By txtLastName=By.xpath("//input[@id='LastName']");

    By txtDob=By.xpath("//input[@id='DateOfBirth']");

    By txtCompanyName=By.xpath("//input[@id='Company']");

    By txtAdminComment=By.xpath("//textarea[@id='AdminComment']");

    By btnSave=By.xpath("//button[@name='save']");

    
    //Actions Methods

    public String getPageTitle()
    {
        return ldriver.getTitle();
    }

    public void clickOnCustomersMenu() {
        ldriver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomersCategory() {
        ldriver.findElement(lnkCustomers_category).click();
    }

    public void clickOnAddnew() {
        ldriver.findElement(btnAddnew).click();
    }

    public void setEmail(String email)
    {
        ldriver.findElement(txtEmail).sendKeys(email);
    }

    public void setPassword(String password)
    {
        ldriver.findElement(txtPassword).sendKeys(password);
    }


    public void setCustomerRoles(String role) throws InterruptedException
    {
        if(!role.equals("Vendors")) 			// If role is Vendors, then it should not delete the Register as per BRS
        {
            ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }

        ldriver.findElement(txtcustomerRoles).click();

        WebElement listitem;

        Thread.sleep(3000);

        if(role.equals("Administrators"))
        {
            listitem=ldriver.findElement(lstitemAdministrators);
        }
        else if(role.equals("Guests"))
        {
            listitem=ldriver.findElement(lstitemGuests);
        }
        else if(role.equals("Registered"))
        {
            listitem=ldriver.findElement(lstitemRegistered);
        }
        else if(role.equals("Vendors"))
        {
            listitem=ldriver.findElement(lstitemVendors);
        }
        else
        {
            listitem=ldriver.findElement(lstitemGuests);
        }

        //listitem.click();
        //Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor)ldriver;
        js.executeScript("arguments[0].click();", listitem);

    }

    public void setManagerOfVendor(String value)
    {
        Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
        drp.selectByVisibleText(value);
    }

    public void setGender(String gender)
    {
        if(gender.equals("Male"))
        {
            ldriver.findElement(rdMaleGender).click();
        }
        else if(gender.equals("Female"))
        {
            ldriver.findElement(rdFeMaleGender).click();
        }
        else
        {
            ldriver.findElement(rdMaleGender).click();	//Default radio button is Male
        }

    }

    public void setFirstName(String fname)
    {
        ldriver.findElement(txtFirstName).sendKeys(fname);
    }

    public void setLastName(String lname)
    {
        ldriver.findElement(txtLastName).sendKeys(lname);
    }

    public void setDob(String dob)
    {
        ldriver.findElement(txtDob).sendKeys(dob);
    }

    public void setCompanyName(String comname)
    {
        ldriver.findElement(txtCompanyName).sendKeys(comname);
    }

    public void setAdminContent(String content)
    {
        ldriver.findElement(txtAdminComment).sendKeys(content);
    }

    public void clickOnSave()
    {
        ldriver.findElement(btnSave).click();
    }

}
