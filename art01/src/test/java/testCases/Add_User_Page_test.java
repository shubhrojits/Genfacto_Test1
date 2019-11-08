package testCases;

import org.testng.annotations.AfterClass;
import pages.Landing_page;
import testSuite.SeleniumAbstractTest;

import org.testng.annotations.BeforeClass;

public class Add_User_Page_test extends SeleniumAbstractTest {
	
	Landing_page lpage = new Landing_page(driver);
	
	@BeforeClass
	public void beforeclass()
	{
		
	}
	
	@AfterClass
	public void afterclass()
	{
		
	}

}
