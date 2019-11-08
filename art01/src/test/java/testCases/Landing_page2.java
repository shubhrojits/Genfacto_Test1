package testCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pages.Landing_page;
import testSuite.SeleniumAbstractTest;

public class Landing_page2 extends SeleniumAbstractTest {
	
	@Test
	public void login() throws InterruptedException
	{
		TC_name = "Login with valid Credentials";
		Landing_page lpage = new Landing_page(driver);
		lpage.UserName_Textbox.sendKeys("admin1");
		lpage.Password_Textbox.sendKeys("admin123");
		lpage.Sign_in_button.click();
		Thread.sleep(5000);
		System.out.println("First TC");
		exec_result= "Passed";
		//return exec_result;
	}
	
	@Test
	public void navigate_user_details()
	{
		TC_name = "Navigate to User details page";
		driver.findElement(By.linkText("User")).click();
		System.out.println("Second TC");
		exec_result ="Failed";
		//return exec_result;
	}

}
