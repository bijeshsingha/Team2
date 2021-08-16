package signUp;

import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import frameWork.Base;

public class Runner extends Base {
	Scanner sc = new Scanner(System.in);
	ExtentTest tc;
	@BeforeMethod
	public void deleteCookies() throws Exception{
		//Deletes all cookies
		driver.manage().deleteAllCookies();
		//Refresh the page
		driver.navigate().refresh();
	}
	
	@Test
	public void report() throws Exception{
		report=new ExtentReports();
		//specifying that we need html report
		report.attachReporter(new ExtentHtmlReporter("Report_Signup.html"));
	}
	
	//tc_openUrl1
	@Test(dependsOnMethods = "report",priority=1,enabled=true,description="To check website is opening and signup option available")
	  public void openUrl() throws Exception {
		//For opening the Url
			tc=report.createTest("Check URL open and signup button");
			OpenUrl url= new OpenUrl(driver);
			url.openUrl();
			Assert.assertTrue(driver.findElement(url.signUp).isDisplayed());
			tc.info("The url is opened and signup option is available");						// passing step information
			tc.pass("Test Case Passed");
	}
	
	 //To test Invalid Test cases
	
	//tc_signup02
	@Test(priority=2,enabled=true,description="To check signup with invalid email")
		 public void signupEmail02() throws InterruptedException {
			tc=report.createTest("Check signup with invalid email");
			  Email em=new Email(driver);
			  em.signupEmail("jimin@gmail","invalid");
			  
			  tc.info("Email id is invalid");
			  tc.pass("Test Case Passed");
	}
	 
		//tc_signup03
	 @Test(priority=3,enabled=true,description="To check signup with valid email invalid otp")
	 public void signupEmail03() throws InterruptedException {
		 tc=report.createTest("Check with with valid email invalid otp");
		  Email em=new Email(driver);
		  em.signupEmail("jimin@gmail.com","invalid");    
		  System.out.println("Enter OTP");
		  String enter_otp = sc.nextLine();               //Manual input otp
		  em.emailOtp(enter_otp,"invalid");
		  tc.info("Otp is invalid");
		  tc.pass("Test Case Passed");
		  
	}
	
	 //tc_signup05
	  @Test(priority=4,enabled=true,description="To check signup with invalid mobile no.")
	  public void signupPhone03() throws InterruptedException {
		  tc=report.createTest("Check with invalid Mobile number");
		  Mobile m_no=new Mobile(driver);
		  m_no.signupMobile("89768","invalid");
		  tc.info("Mobile number is invalid");
		  tc.pass("Test Case Passed");
	  }
	  
	 
		  
	
	 //tc_signup06
	 @Test(priority=5,enabled=true,description="To check signup with valid mobile no. invalid otp")
	  public void signupPhone02() throws InterruptedException{
		 tc=report.createTest("Check with valid Mobile number ,invalid Otp");
		  Mobile m_no=new Mobile(driver);
		  m_no.signupMobile("8976846960","invalid");
		  System.out.println("Enter OTP");
		  String enter_otp = sc.nextLine();				  //Manual input otp
		  m_no.signupOtp(enter_otp,"invalid");
		  tc.info("Otp is invalid");
		  tc.pass("Test Case Passed");
	
	}
	 
	  //To test valid test cases
	  //tc_signup01
	  @Test(priority=6,enabled=true,description="To check signup with valid email valid otp")
	  public void signupEmail01() throws InterruptedException {
		  tc=report.createTest("Check with valid email and valid otp");
		  Email em=new Email(driver);
		  em.signupEmail("dexan10728@asmm5.com","valid");
		  System.out.println("Enter OTP");
		  String enter_otp = sc.nextLine();						  //Manual input otp
		  em.emailOtp(enter_otp,"valid");
		  tc.info("Signed up successfully");
		  tc.pass("Test Case Passed");
	}
	  //tc_signup04
	  @Test(priority=7,enabled=true,description="To check signup with valid mobile no. valid otp")
	  public void signupPhone01() throws InterruptedException {
		  tc=report.createTest("Check for valid mobile number and valid otp");
		  	Mobile m_no=new Mobile(driver);
			m_no.signupMobile("9619848760","valid");
			System.out.println("Enter OTP");
			String enter_otp = sc.nextLine();					  //Manual input otp
			m_no.signupOtp(enter_otp,"valid");
			tc.info("Signed up successfully");
			tc.pass("Test Case Passed");
	}


}
