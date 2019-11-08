package testCases;

import org.testng.annotations.Test;

import com.google.common.base.Verify;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

//import java.awt.List;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import pages.Landing_page;

public class FirstTest {
	
	WebDriver driver = new ChromeDriver();
	//Landing_page lpage = new Landing_page(driver);
	
  @Test (dataProvider="credential")
  public void first(String uid, String pwd) {
	  try {
		  Thread.sleep(5000);
		  //lpage.UserName_Textbox.sendKeys("ABCD");
		  //driver.findElement(By.id("UserName")).sendKeys("admin1");
		  //WebElement Login_input= driver.findElement(By.id("UserName"));
		  System.out.println("UID: "+uid+ "\nPWD: "+pwd);
		  WebDriverWait wait = new WebDriverWait(driver,30);
		  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName[contains(text(),'Username']")));
		  //wait.until(elementToBeClickable(By.cssSelector(".btn.btn-success")));
		  WebElement Login_input= wait.until(ExpectedConditions.elementToBeClickable(By.id("UserName")));
				  //driver.findElement(By.cssSelector("input[id^=User][id$=Name]"));
		  //WebElement Login_input= driver.findElement(By.cssSelector("input[id^=User][id$=Name]"));
		  Login_input.sendKeys(uid);
		  driver.findElement(By.name("Password")).sendKeys(pwd);
		  //WebElement Login_button= driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button"));
		  WebElement Login_button= driver.findElement(By.cssSelector(".btn.btn-success"));
		  Login_button.click();
		  //Thread.sleep(20000);
		  //driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2/span[contains(text(),'Admin Home')]")));
		  //WebElement Admin_home_title= driver.findElement(By.xpath("//h2/span"));
		  WebElement Admin_home_title= driver.findElement(By.xpath("//h2/span"));
		  //WebElement Admin_home_title= driver.findElement(By.cssSelector(".ng-binding"));
		  
		  String Admin_home_title_text = Admin_home_title.getText();
		  System.out.println(Admin_home_title_text);
		  //Assert.assertEquals(expected, actual);
		  //Verify.verify(expression);
		  Thread.sleep(5000);
		  //driver.navigate().to("http://172.20.0.11/genfacto.com/");
		  //driver.navigate().back();
		  WebElement Logout_dropdown = driver.findElement(By.xpath("//*[@id=\"logoutForm\"]/ul/li/a"));
		  Logout_dropdown.click();
		  driver.findElement(By.partialLinkText("Sign out")).click();
		  if(Admin_home_title_text.equals("Admin Home")) {
			  System.out.println("Test Case Passed");
		  }
		  else {
			  System.out.println("Test Case Failed");
		  }
	  }
	  catch (Exception e)
	  {
		  System.out.println(e.getMessage());
	  }
	  
  }
  
  @Test
  public void second() {
	  try {
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  WebElement User_link = driver.findElement(By.linkText("User"));
		  User_link.click();
		  Thread.sleep(5000);
		  WebElement User_title = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ng-view/div/div/div[2]/h3"));
		  List<WebElement> inputs = driver.findElements(By.xpath("//h3"));
		  System.out.println(inputs.size());
		  
		  System.out.println(inputs.indexOf(User_title));
		  String User_title_text = User_title.getText();
		  System.out.println(User_title_text);
		  
		  Thread.sleep(5000);
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("window.scrollBy(0,1000)");
		  
		  WebElement select = driver.findElement(By.id("select2-chosen-16"));
		  select.click();
		  driver.findElement(By.id("select2-result-label-19")).click();
		  driver.navigate().back();
		  
		  Select pagination = new Select(select);
		  pagination.selectByVisibleText("25");
		  if(User_title_text.equals("Users")) {
			  System.out.println("Test Case Passed");
		  }
		  else {
			  System.out.println("Test Case Failed");
		  }
	  }
	  catch (Exception e) {
		  System.out.println(e.getMessage());
	  }
	 
  }
  
  @DataProvider(name="credential")
  public Object[][] getDatafromDP(){
	  return new Object[][]
			  {
				  {"admin","admin123"},
				  {"admin1","admin123"},
				  {"admin2","admin123"},
				  {"admin3","admin123"},
				  {"admin4","admin123"}
			  };
  }
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeTest
  public void beforeTest() {
	  System.setProperty("WebDriver.Chrome.driver", "D:\\Eclipse2\\art01\\src\\test\\java\\chromeDriver\\chromedriver.exe");
	  driver.get("http://172.20.0.11/genfacto.com/");
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }

}
