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

	public void deleteCookies() throws Exception {

		// Deletes all cookies
		driver.manage().deleteAllCookies();

		// Refresh the page
		driver.navigate().refresh();
	}

	@Test(priority = 1)
	public void f() {
		report = new ExtentReports();
		report.attachReporter(new ExtentHtmlReporter("login.html"));
	}

	@Test(priority = 5, description = "valid email&password")
	public void LoginEmail() throws InterruptedException, FileNotFoundException, IOException {

		LoginEmail le = new LoginEmail(driver);
		le.log_in("ameypote08@gmail.com", "086993", "invalid", "invalid");
		tc = report.createTest("TestCase01");
		tc.info("valid creds");

	}

	@Test(priority = 3, description = "valid phone&password", enabled = true)
	public void LoginPhone() throws InterruptedException, FileNotFoundException, IOException {

		PhoneLogin le = new PhoneLogin(driver);
		le.loginp("8169948282", "12345678@", "invalid");
		tc = report.createTest("TestCase02");
		tc.info("valid creds");

	}

	@Test(priority = 6, description = "invalid email", enabled = false)
	public void inEmail() throws FileNotFoundException, IOException, InterruptedException {

		LoginEmail le = new LoginEmail(driver);
		le.log_in("ameypote08@gmailcom", "086993", "invalid", "invalid");
		tc = report.createTest("TestCase02");
		tc.info("valid creds");

	}

	@Test(priority = 4, description = "valid email&invalidPassword", enabled = true)
	public void LoginEimail() throws FileNotFoundException, IOException, InterruptedException {
		LoginEmail le = new LoginEmail(driver);
		le.log_in("ameypote08@gmail.com", "08693", "invalid", "invalid");
		tc = report.createTest("TestCase03");
		tc.info("invalid creds");

	}

	@Test(priority = 2, description = "valid phone&invalidPassword", enabled = true)
	public void LoginInPhone() throws FileNotFoundException, IOException, InterruptedException {

		PhoneLogin le = new PhoneLogin(driver);
		le.loginp("8169948282", "1234568@", "invalid");
		tc = report.createTest("TestCase04");
		tc.info("invalid creds");

	}

}
