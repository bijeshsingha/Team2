package flight1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import frameWork.Base;

public class FlightScript01 extends Base{
	
    Properties prop = new Properties();
	ExtentTest test;

	@Test(priority = 1)
	public void f() {
		// creating extent report
		report = new ExtentReports();
				
		//specifying that we need html report, with name module_name.html
		report.attachReporter(new ExtentHtmlReporter("flightBooking.html"));
	}

	@Test(enabled= true,priority = 2)
	public void aPropLoadMethod() throws Exception
	{
		// creating test in report
		test = report.createTest("Load Property File");
		
		// passing step information
		test.info("Loading property file");
			
		prop.load(new FileInputStream("src/test/resources/flight1.property"));
		driver.get(prop.getProperty("url"));
		
		test.info("Test Passed, property file loaded.");

		test.pass("Test passed");
	}
	
	// for one way trip
	@Test(enabled= false,dependsOnMethods = "aPropLoadMethod", priority=2)
	public void checkOneway() throws Exception {
		
		test = report.createTest("Check One Way Flight option");
		
		test.info("Select one way trip option");
		
		// to click one way button 
		driver.findElement(By.xpath(prop.getProperty("oneway"))).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("easemytrip"));
		Thread.sleep(2000);
		test.pass("Test Passed");

	}
	
	// for checking round trip
		@Test(enabled= true, dependsOnMethods="aPropLoadMethod", priority=2)
		public void checkRoundtrip() throws Exception
		{	
			test = report.createTest("Check round trip option");
			
			test.info("Select round trip option");
			driver.findElement(By.xpath(prop.getProperty("roundtrip"))).click();
			Assert.assertTrue(driver.getCurrentUrl().contains("easemytrip"));
			Thread.sleep(2000);
			test.pass("Test Passed");
		}
	
	// departure date
	@Test(enabled= true,dependsOnMethods = "checkRoundtrip",priority=2)
	public void tc_date01() throws Exception, IOException {
		
		test = report.createTest("Check Departure date option");
		
		test.info("Select the date");
		// to select departure date tab
		driver.findElement(By.cssSelector(prop.getProperty("dateTab"))).click();
		// to select departure date from the calendar
		driver.findElement(By.id(prop.getProperty("dateId"))).click();
		Thread.sleep(2000);
		  Assert.assertTrue(driver.getCurrentUrl().contains("easemytrip"));

		  test.pass("Test Passed");
	}
	
	// arrival date
	@Test(enabled= true, dependsOnMethods = "tc_date01")
	public void tc_date02() throws Exception{
		test = report.createTest("Check Arrival date option");
		
		test.info("Select the date");
		
		// to select arrival date tab for round trip
		driver.findElement(By.xpath(prop.getProperty("arrivalBox"))).click();
		// to select arrival date from the calendar
		driver.findElement(By.xpath(prop.getProperty("arrivalDate"))).click();		
		Thread.sleep(2000);
		  Assert.assertTrue(driver.getCurrentUrl().contains("easemytrip"));
		  
		  test.pass("Test Passed");
	}
	
	// to check if more than 9 travellers are taken into consideration
	@Test(enabled= true, dependsOnMethods = "tc_date02")
	  public void tc_traveller9() throws Exception {
		test = report.createTest("Check the maximum travellers");
		
		test.info("Select travellers more than 9");
	      driver.findElement(By.cssSelector(prop.getProperty("dropDown"))).click();
	      for(int i=0;i<8;i++)
	      {
	    	  driver.findElement(By.cssSelector(prop.getProperty("selectTravellers"))).click();
	    	  Thread.sleep(2000);
	      }
    	  driver.findElement(By.cssSelector(prop.getProperty("selectChild"))).click();

	      Thread.sleep(3000);
    	  // to click "OK" on the alert generated
	      Alert ac= driver.switchTo().alert();
	       String txt=ac.getText();
	       System.out.println(txt);
	       ac.accept();      
		 Thread.sleep(2000);
			  Assert.assertTrue(driver.getCurrentUrl().contains("easemytrip"));
			  Thread.sleep(2000);
			  test.pass("Test Passed");
			}

	// to select travellers
	@Test(enabled= true,dependsOnMethods = "tc_traveller9")
	public void tc_traveller01() throws Exception, IOException {
		
		test = report.createTest("Check travellers option");
		
		test.info("Select the travellers");
		driver.navigate().to(prop.getProperty("url"));
		// to select the drop down tab in travellers
		driver.findElement(By.cssSelector(prop.getProperty("dropDown"))).click();
		// to select total travellers(8)
		driver.findElement(By.cssSelector(prop.getProperty("minusTravellers"))).click();
		// to click on done button
		driver.findElement(By.cssSelector(prop.getProperty("doneBtn"))).click();
		  Assert.assertTrue(driver.getCurrentUrl().contains("easemytrip"));

		Thread.sleep(2000);
		  test.pass("Test Passed");
	}
	
	// to decide the flying class
	@Test(enabled= true,dependsOnMethods = "tc_traveller01")
	  public void tc_class01() throws Exception, IOException{
		
		test = report.createTest("Check class option");
		
		test.info("Select the class");
		  // to click on class tab
		  driver.findElement(By.cssSelector(prop.getProperty("classBtn"))).click();
		  // to choose the class
		  driver.findElement(By.xpath(prop.getProperty("ecoClass"))).click();
		  // to click on done button 
		  driver.findElement(By.cssSelector(prop.getProperty("doneBtn1"))).click();
		  Assert.assertTrue(driver.getCurrentUrl().contains("easemytrip"));
		  Thread.sleep(2000);
		  test.pass("Test Passed");
	  }
	  
	// to search the flights
	@Test(enabled= true,dependsOnMethods = "tc_class01")
	  public void tc_search01() throws Exception, IOException{
		test = report.createTest("Check search button");
		
		test.info("Click on the search button");
		
	      driver.findElement(By.cssSelector(prop.getProperty("searchBtn"))).click();
	      Thread.sleep(2000);
			Assert.assertTrue(driver.getCurrentUrl().contains("FlightList"));

			test.pass("Test Passed");
	  }

}
