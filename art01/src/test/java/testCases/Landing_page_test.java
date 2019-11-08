package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.Landing_page;
import testSuite.SeleniumAbstractTest;
import environment.GenericClass;
import environment.Setup;

public class Landing_page_test extends SeleniumAbstractTest {
	
	Landing_page lpage = new Landing_page(driver);
	GenericClass gc1 = new GenericClass();
	
	@BeforeClass
	public void beforeClass()
	{
		
		System.out.println("Hello Before Class");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("Hello After Class");
	}
	
	@Test
	public void existence_watermark_username()
	{
		TCID = "TC_Login_001";
		TC_name = "Existence of watermark for Username";
		Landing_page lpage = new Landing_page(driver);
		String Uname_text =  lpage.UserName_Textbox.getAttribute("placeholder");
		//driver.findElement(By.id("UserName")).sendKeys("SS");
		GenericClass gc1 = new GenericClass();
		gc1.highlightElement(driver, lpage.UserName_Textbox);
		//lpage.UserName_Textbox.sendKeys("Abcd");
		System.out.println(Uname_text);
		if(Uname_text.contentEquals("Username")) 
		{
			exec_result= "Passed";
		}
		else
		{
			exec_result= "Failed";
		}
	}
	
	@Test
	public void existence_watermark_password()
	{
	
		TCID = "TC_Login_002";
		TC_name = "Existence of watermark for Password";
		Landing_page lpage = new Landing_page(driver);
		String Pwd_text =  lpage.Password_Textbox.getAttribute("placeholder");
		GenericClass gc1 = new GenericClass();
		gc1.highlightElement(driver, lpage.Password_Textbox);
		System.out.println(Pwd_text);
		
		if(Pwd_text.contentEquals("Password")) 
		{
			exec_result= "Passed";
		}
		else
		{
			exec_result= "Failed";
		}
	}
	
	@Test(dataProvider="DP")
	public void link_options(String heading, String link) throws InterruptedException
	{
		TCID = "TC_Login_003";
		TC_name = "Validation of Link dropdown";
		Landing_page lpage = new Landing_page(driver);
		lpage.Links_link.click();
		Thread.sleep(5000);
		//WebElement TestLink = Services_link;
		lpage.Services_link.click();
		driver.navigate().back();
		System.out.println("heading:"+heading +" & link: "+link);
		exec_result= "Passed";
		
	}
	
	
	@Test
	public void login() throws InterruptedException
	{
		TCID = "TC_Login_004";
		TC_name = "Login with valid Credentials";
		Landing_page lpage = new Landing_page(driver);
		lpage.UserName_Textbox.clear();
		GenericClass gc1 = new GenericClass();
		gc1.highlightElement(driver, lpage.UserName_Textbox);
		lpage.UserName_Textbox.sendKeys("admin1");
		gc1.highlightElement(driver, lpage.UserName_Textbox);
		lpage.Password_Textbox.sendKeys("admin123");
		Thread.sleep(15000);
		lpage.Sign_in_button.click();
		Thread.sleep(15000);
		System.out.println("First TC");
		exec_result= "Passed";
		//gc1.highlightElement(driver, lpage.Sign_in_button);
		
		
		
		//return exec_result;
	}
	
	@Test
	public void navigate_user_details()
	{
		TCID = "TC_Login_005";
		TC_name = "Navigate to User details page";
		driver.findElement(By.linkText("User")).click();
		System.out.println("Second TC");
		exec_result ="Failed";
		//return exec_result;
		
	}
	
	
	@DataProvider(name="DP")
	public Object[][] getdataDP(){
		return new Object[][]
				{
					{"admin1","admin123"},
					{"admin2","admin123"},
				};
	}
}
