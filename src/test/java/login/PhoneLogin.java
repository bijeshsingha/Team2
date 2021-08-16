package login;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PhoneLogin {
	WebDriver driver;
	Scanner sc=new Scanner(System.in);
	public PhoneLogin(WebDriver driver)
	{
		this.driver=driver;
	}
	By hover=By.id("spnMyAcc");                                            //element for hovering over the button to login
	By login=By.id("shwlogn");                                            //login option selection
	By email=By.id("txtEmail");                                          //email textbox
	By password=By.id("txtEmail2");                                     //passwordtextbox
	By emailen=By.id("shwotp");                                        //to enter email
	By passen=By.xpath("//input[@ng-click=\"Authenticate('P')\"]"); 
	By lgwp=By.xpath("//a[normalize-space()='Login with password']"); //to login using password
	By otplogin=By.id("OtpLgnBtn");
	By otpTextbox=By.id("txtEmail1");                                 //otp text box
	public void setPhn(String User,String status) throws InterruptedException
	{   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(hover).click();
		driver.findElement(login).click();
		driver.findElement(email).sendKeys(User);
		
		driver.findElement(emailen).click();
		WebDriverWait wt1=new WebDriverWait(driver,5);
		try {
			
			wt1.until(ExpectedConditions.invisibilityOf(driver.findElement(emailen)));
			}
			catch(Exception e) {
				if(status.equals("invalid")) {
					Assert.fail(e.getMessage());
				}
				else if(status.equals("valid")) {
					System.out.println("Testing passed");
				}
				
			}
		
		
	}
	public void enPassword(String pass,String status) throws InterruptedException
	{  
		driver.findElement(lgwp).click();
		driver.findElement(password).sendKeys(pass);

		driver.findElement(passen).click();
		WebDriverWait wt1=new WebDriverWait(driver,5);
		//String Status="valid";

try {
			
			wt1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//input[@ng-click=\"Authenticate('P')\"]"))));
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
	
		
	
  public void loginp(String user,String pass,String status,String stats) throws InterruptedException
  {
	  setPhn(user,stats);
	  enPassword(pass,status);
  }
  
}
