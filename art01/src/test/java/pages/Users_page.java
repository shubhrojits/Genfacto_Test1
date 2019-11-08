package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Users_page {
	
	@FindBy (how=How.XPATH,using="/html/body/div[2]/div/div[1]/ng-view/div/div/div[1]/h3")
	public WebElement add_User_Page_heading;
	
	@FindBy (how=How.XPATH, using="/html/body/div[2]/div/div[1]/ng-view/div/div/div[1]/form/div/form-control/div[1]/div[1]/input" )
	public WebElement first_Name_textbox;
	
	@FindBy (how=How.XPATH, using="/html/body/div[2]/div/div[1]/ng-view/div/div/div[1]/form/div/form-control/div[1]/div[1]/input")
	public WebElement validation_First_Name;
	
	@FindBy(how=How.CSS, using ="input[type='submit']" )
	public WebElement button_Save;
	
	/* other WebElements */

}
