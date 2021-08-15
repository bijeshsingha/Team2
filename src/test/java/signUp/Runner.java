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
	public void deleteCookies() throws Exception {
		// Deletes all cookies
		driver.manage().deleteAllCookies();
		// Refresh the page
		driver.navigate().refresh();
	}

	@Test(priority = 1)
	public void report() throws Exception {
		report = new ExtentReports();
		// specifying that we need html report
		report.attachReporter(new ExtentHtmlReporter("SignUpReport1.html"));
	}

	@Test(priority = 2, enabled = true, description = "To check website is opening and signup option available")
	public void openUrl() throws Exception {
		// For opening the Url
		OpenUrl url = new OpenUrl(driver);
		url.openUrl();
		Assert.assertTrue(driver.findElement(url.signUp).isDisplayed());
		tc = report.createTest("TestCase01");
		tc.info("Opening url"); // passing step information

	}

	// To test Invalid Test cases
	@Test(priority = 3, enabled = true, description = "To check signup with valid email invalid otp")
	public void signupEmail02() throws InterruptedException {

		Email em = new Email(driver);
		em.signupEmail("jimin@gmail.com", "invalid");
		System.out.println("Enter OTP");
		String enter_otp = sc.nextLine(); // Manual input otp
		em.emailOtp(enter_otp, "invalid");
		tc = report.createTest("TestCase03");
		tc.info("SignupInvalid");

	}

	@Test(priority = 4, enabled = true, description = "To check signup with invalid email")
	public void signupEmail03() throws InterruptedException {

		Email em = new Email(driver);
		em.signupEmail("jimin@gmail", "invalid");
		tc = report.createTest("TestCase04");
		tc.info("SignupInvalid");

	}

	@Test(priority = 5, enabled = true, description = "To check signup with valid mobile no. invalid otp")
	public void signupPhone02() throws InterruptedException {

		Mobile m_no = new Mobile(driver);
		m_no.signupMobile("8976846960", "invalid");
		System.out.println("Enter OTP");
		String enter_otp = sc.nextLine(); // Manual input otp
		m_no.signupOtp(enter_otp, "invalid");
		tc = report.createTest("TestCase06");
		tc.info("SignupInvalid");

	}

	@Test(priority = 6, enabled = true, description = "To check signup with invalid mobile no.")
	public void signupPhone03() throws InterruptedException {

		Mobile m_no = new Mobile(driver);
		m_no.signupMobile("89768", "invalid");
		tc = report.createTest("TestCase07");
		tc.info("SignupInvalid");

	}

	// To test valid test cases
	@Test(priority = 7, enabled = true, description = "To check signup with valid email valid otp")
	public void signupEmail01() throws InterruptedException {

		Email em = new Email(driver);
		em.signupEmail("janardhananushree@gmail.com", "valid");
		System.out.println("Enter OTP");
		String enter_otp = sc.nextLine(); // Manual input otp
		em.emailOtp(enter_otp, "valid");

		tc = report.createTest("TestCase02");
		tc.info("SignupValid");

	}

	@Test(priority = 8, enabled = true, description = "To check signup with valid mobile no. valid otp")
	public void signupPhone01() throws InterruptedException {

		Mobile m_no = new Mobile(driver);
		m_no.signupMobile("9987316649", "valid");
		System.out.println("Enter OTP");
		String enter_otp = sc.nextLine(); // Manual input otp
		m_no.signupOtp(enter_otp, "valid");
		tc = report.createTest("TestCase05");
		tc.info("SignupValid");

	}

}
