package frameWork;

import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Base {

	// WebDriver instance
	public WebDriver driver;

	// ExtentReports instance
	public ExtentReports report;

	// WebDriverWait object
	public WebDriverWait wt;

	// Properties instance
	public Properties prop = new Properties();

	@BeforeTest
	public void beforeTest() throws Exception {

		// load the property file
		prop.load(new FileInputStream("src/test/resources/base.property"));

		// set the path of .exe file
		System.out.println("Select a browser: ");
		System.out.println("1.Chrome");
		System.out.println("2.Firefox");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			System.setProperty(prop.getProperty("ckey"), prop.getProperty("cvalue"));
			driver = new ChromeDriver();
			break;
		}
		case 2: {
			System.setProperty(prop.getProperty("gkey"),prop.getProperty("gvalue"));
			driver = new FirefoxDriver();
		}
		}

		// maximize the window
		driver.manage().window().maximize();

		// adding implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// To open the url
		driver.get(prop.getProperty("url"));

		// Explicit wait for 20s
		wt = new WebDriverWait(driver, 20);

	}

	@AfterTest
	public void afterTest() {

		// quit the browser
		driver.quit();

		// for writing report in physical memory
		report.flush();
	}

}