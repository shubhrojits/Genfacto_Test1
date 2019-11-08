package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import environment.Setup;

public class Landing_page {
	
	@FindBy(how=How.ID,using="UserName")
	public WebElement UserName_Textbox;
	
	@FindBy(how=How.ID,using="Password")
	public WebElement Password_Textbox;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"loginForm\"]/div[3]/button")
	public WebElement Sign_in_button;
	
	@FindBy(how=How.PARTIAL_LINK_TEXT,using="Forgot password")
	public WebElement Forgot_password_link;
	
	@FindBy (how=How.PARTIAL_LINK_TEXT,using="Links")
	public WebElement Links_link;
	
	@FindBy(how=How.LINK_TEXT,using="Services")
	public WebElement Services_link;
	
	@FindBy(how=How.LINK_TEXT,using="About us")
	public WebElement About_Us_link;
	
	@FindBy(how=How.LINK_TEXT,using ="Contact us")
	public WebElement Contact_us_link;
	
	
	
	
	
	
	public Landing_page (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

}
