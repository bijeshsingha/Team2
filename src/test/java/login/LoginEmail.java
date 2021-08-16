package login;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginEmail {
	WebDriver driver;
	Scanner sc=new Scanner(System.in);
	public LoginEmail(WebDriver driver)
	{
		this.driver=driver;
	}
	By hover=By.id("spnMyAcc");                                              //element for hovering over the button to login
	By login=By.id("shwlogn");                                              //login option selection
	By email=By.id("txtEmail");                                            //email textbox
	By password=By.id("txtEmail2");                                       //passwordtextbox
	By emailen=By.id("shwotp");                                          //to enter email
	By passen=By.xpath("//input[@ng-click=\"Authenticate('P')\"]");     //to enter password button
    By logouthover=By.xpath("//div[@class='user_n_icn']");
    By logout=By.linkText("Log Out");
    By otp=By.xpath("//a[normalize-space()='Login with OTP']");         //to login with otp 
    By otptxt=By.id("txtEmail1");
	
	public void setEmail(String User,String stats) throws InterruptedException
	{   driver.manage().timeouts().implicitlyWait(124, TimeUnit.SECONDS);
		driver.findElement(hover).click();
		driver.findElement(login).click();
		driver.findElement(email).sendKeys(User);
		
		driver.findElement(emailen).click();
        WebDriverWait wt1=new WebDriverWait(driver,5);
		
		try {
			
			wt1.until(ExpectedConditions.invisibilityOf(driver.findElement(emailen))); //if password page is not visible it means error
			}
			catch(Exception e) {
				if(stats.equals("valid")) {
					Assert.fail(e.getMessage());
				}
				else if(stats.equals("invalid")) {
					System.out.println("Testing passed");
				}
				
			}
		
		
	}
	public void enPassword(String pass,String status,int a) throws InterruptedException
	{   driver.manage().timeouts().implicitlyWait(124, TimeUnit.SECONDS);
	   if(a==1) {
		driver.findElement(password).sendKeys(pass);
		driver.findElement(passen).click();
		}
	   else
	   {
		   driver.findElement(otp).click();
		   System.out.println("Enter OTP");
		   String otpa = sc.nextLine();	
		   driver.findElement(otptxt).sendKeys(otpa);
		   driver.findElement(otp).click();
		   
		   
	   }
		
	   WebDriverWait wt1=new WebDriverWait(driver,5);

		
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
  public void log_in(String user,String pass,String Status,String stats,int otp) throws InterruptedException
  {
	  setEmail(user,stats);
	  enPassword(pass,Status,otp);
  }

}

