package environment;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericClass {
	public void highlightElement(WebDriver driver, WebElement element) {
		  for (int i = 0; i < 3; i++)
		  {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 3px solid red;"); //"arguments[0].style.border='3px solid red'"
			  js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		  }
	  }

}
