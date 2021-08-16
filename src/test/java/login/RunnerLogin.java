package login;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import frameWork.Base;

public class RunnerLogin extends Base {
	ExtentTest tc;
	@BeforeMethod

	 public void deleteCookies() throws Exception{

	 //Deletes all cookies

	 driver.manage().deleteAllCookies();

	 //Refresh the page

	 driver.navigate().refresh();

	 }
	@Test(priority=1)
	public void f()
	{
		report=new ExtentReports();
		report.attachReporter(new ExtentHtmlReporter("login.html"));
	}
  @Test(priority=5,description="valid email&password")
  public void LoginEmail() throws InterruptedException, FileNotFoundException, IOException {
	  
	 
	  LoginEmail le=new LoginEmail(driver);
	  le.log_in("ameypote08@gmail.com", "086993","invalid","valid",1);
	  tc=report.createTest("valid email and password");
	  tc.info("valid email and valid password");
	  tc.pass("Test Case Passed");
	  
	  
  }
  @Test(priority=3,description="valid phone&password",enabled=true)
  public void LoginPhone() throws InterruptedException, FileNotFoundException, IOException {
	  
	  PhoneLogin le=new PhoneLogin(driver);
	  le.loginp("8169948282", "12345678@","invalid","invalid");
	  tc=report.createTest("valid phone number and valid password");
	  tc.info("valid phone number and valid password");
	  
	  tc.pass("Test Case Passed");
  }
  
  @Test(priority=6,description="valid email&invalidPassword",enabled=true)
  public void LoginEimail() throws FileNotFoundException, IOException, InterruptedException
  {
	  	  LoginEmail le=new LoginEmail(driver);
	      le.log_in("ameypote08@gmail.com", "08693","invalid","invalid",1);
	      tc=report.createTest("invalid Password");
	      tc.info("invalid Password");
	      tc.pass("Test Case Passed");
  }
  
  @Test(priority=2,description="valid phone&invalidPassword",enabled=true)
  public void LoginInPhone() throws FileNotFoundException, IOException, InterruptedException
  {
	 
	  PhoneLogin le=new PhoneLogin(driver);
	  le.loginp("8169948282", "1234568@","invalid","invalid");
	  tc=report.createTest("Valid phone number and invalid password");
	  tc.info("Valid phone number and invalid password");
	  tc.pass("Test Case Passed");
  }
  @Test(priority=7,description="invalid phone",enabled=false)
  public void LoginPhoneIn() throws FileNotFoundException, IOException, InterruptedException
  {
	 
	  PhoneLogin le=new PhoneLogin(driver);
	  le.loginp("81699482", "1234568@","valid","invalid");
	  tc=report.createTest("TestCase05");
	  tc.info("invalid creds");
	  tc.pass("Test Case Passed");
  }
  @Test(priority=7,description="valid email&validotp",enabled=true)
  public void LoginEimailOtp() throws FileNotFoundException, IOException, InterruptedException
  {
	  	  LoginEmail le=new LoginEmail(driver);
	  	  
	      le.log_in("ameypote08@gmail.com","123","invalid","invalid",2);
	      
	      tc=report.createTest("VALID Email valid Otp");
	      tc.info("VALID Email valid Otp");
	      tc.pass("Test Case Passed");
  }
  @Test(priority=9,description="invalid email",enabled=true)
  public void LoginEimailInvalid() throws FileNotFoundException, IOException, InterruptedException
  {
	  	  LoginEmail le=new LoginEmail(driver);
	  	  
	      le.log_in("naetye@protonmail.com","12345678@","invalid","invalid",1);
	      
	      tc=report.createTest("invalid mailid");
	      tc.info("invalid mailid");
	      tc.pass("Test Case Passed");
  }
	

}
