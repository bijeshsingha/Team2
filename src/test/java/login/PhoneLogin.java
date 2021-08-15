package login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PhoneLogin {
	WebDriver driver;
	
	public PhoneLogin(WebDriver driver)
	{
		this.driver=driver;
	}
	By hover=By.id("spnMyAcc");                  //element for hovering over the button to login
	By login=By.id("shwlogn");                  //login option selection
	By email=By.id("txtEmail");                //email textbox
	By password=By.id("txtEmail2");           //passwordtextbox
	By emailen=By.id("shwotp");              //to enter email
	By passen=By.xpath("//input[@ng-click=\"Authenticate('P')\"]"); 
	By lgwp=By.xpath("//a[normalize-space()='Login with password']"); //to login using password
	
	public void setPhn(String User) throws InterruptedException
	{   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(hover).click();
		driver.findElement(login).click();
		driver.findElement(email).sendKeys(User);
		
		driver.findElement(emailen).click();
		
		
	}
	public void enPassword(String pass,String status) throws InterruptedException
	{   driver.findElement(lgwp).click();
		driver.findElement(password).sendKeys(pass);

		driver.findElement(passen).click();
		WebDriverWait wt1=new WebDriverWait(driver,5);
		//String Status="valid";

try {
			
			wt1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//input[@ng-click=\\\"Authenticate('P')\\\"]"))));
			}
			catch(Exception e) {
				if(status.equals("valid")) {
					Assert.fail(e.getMessage());
				}
				else if(status.equals("invalid")) {
					System.out.println("Testing passed");
				}
				
			}
	}
  public void loginp(String user,String pass,String status) throws InterruptedException
  {
	  setPhn(user);
	  enPassword(pass,status);
  }
  

	
	

}
