package signUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import frameWork.Base;

public class OpenUrl extends Base {
	
WebDriver driver;
	public OpenUrl(WebDriver driver){
		this.driver=driver;
	}
	By account=By.id("spnMyAcc");  				//element for hovering over the button to login
	By signUp=By.id("shwlogn");                 //sign up option selection
	
	public void openUrl() throws InterruptedException{
	wt=new WebDriverWait(driver,5);
	driver.findElement(account).click();             //locating account option    
	driver.findElement(signUp).click();				//location signup option

	
	}
}
