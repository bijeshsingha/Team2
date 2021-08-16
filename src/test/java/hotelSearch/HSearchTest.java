package hotelSearch;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import frameWork.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HSearchTest extends Base {
	 Properties prop = new Properties();
	ExtentTest tc;
	 @Test(priority = 1)
		public void report() throws Exception {
			report = new ExtentReports();
			// specifying that we need html report
			report.attachReporter(new ExtentHtmlReporter("HotelSearch.html"));
		}
	 
	  @Test(enabled = true, dependsOnMethods = "report" ,  description = "To verify hotel landing page")
	  public void landingPage() throws FileNotFoundException, IOException{	  
		  tc = report.createTest("To verify hotel landing page");
		  prop.load(new FileInputStream("src/test/resources/hSearch.property"));//LOADING PROPERTY FILE
			driver.get(prop.getProperty("url"));
			tc.info("URL opened");
			driver.findElement(By.cssSelector(prop.getProperty("hotelTab"))).click();//CLICKING ON HOTEL IN TITLE BAR
			tc.info("Clicked on Hotal Tab");
			tc.pass("Test Passed");
	  }
	  @Test(enabled = true, dependsOnMethods="landingPage", description = "To verify blank entry in city field\n")
	  public void checkCity(){
		  tc = report.createTest("To verify blank entry in city field");
		  driver.manage().deleteAllCookies(); //DELETE BROWSER COOKIES
		  driver.findElement(By.className(prop.getProperty("city"))).click();//CLICK CITY DROPDOWN
		  tc.info("CLICK CITY DROPDOWN");
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click(); //HIT SEARCH
		  tc.info("HIT SEARCH");
		  wt.until(ExpectedConditions.alertIsPresent());
		  tc.info("ALERT FOUND");
		  driver.switchTo().alert().accept();//ACCEPT ALERT
		  tc.pass("Test Passed");
	  }
	  @Test(enabled = true, dependsOnMethods="checkCity", description = "To verify same check in check out date inputs")
	  public void sameDates() throws Exception {
		  tc = report.createTest("To verify same check in check out date inputs");
		  List<WebElement> ls=driver.findElements(By.className(prop.getProperty("city-list")));//LOAD ALL ELEMENTS OF CITY DROPDWON IN LIST
		  ls.get(3).click();
		  driver.findElement(By.linkText(prop.getProperty("same-date"))).click();// SELECT DATE
		  driver.findElement(By.linkText(prop.getProperty("same-date"))).click();// SELECT DATE
		  driver.findElement(By.id(prop.getProperty("close-room"))).click();// CLOSE ROOMS OPTION
		  tc.info("SELECTING SAME DATES AND CLOSE ROOMS OPTION");
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();//HIT SEARCH
		  tc.info("HIT SEARCH");
		  wt.until(ExpectedConditions.alertIsPresent());
		  tc.info("ALERT FOUND");
		  driver.switchTo().alert().accept();//ACCEPT ALERT
		  tc.pass("Test Passed");
	  }
	  @Test(enabled = true, dependsOnMethods="sameDates", description = "To verify if the calander disables dates before yesterday")
	  public void disabledInDates(){
		  tc = report.createTest("To verify if the calander disables dates before yesterday");
		  int count=0;
		  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECK IN DATE
		  tc.info("SELECTING CHECK IN DATE");
		  try {			
			  driver.findElement(By.xpath(prop.getProperty("before-yest"))).click();//SELECT A DATE BEFORE YESTERDAY
		  }
		  catch(ElementClickInterceptedException e){
			  count=1;
			  System.out.println("Not interactable - Dates before yesterday disabled");//PASS CASE IF EXCEPTION IN THROWN
			  tc.info("PASS CASE IF EXCEPTION IN THROWN");
		  }
		  if(count==0){	//THIS CONDITION IS TO PURPOSELY FAIL THE TEST CASE IF THE TRY BLOCK RUNS
			  driver.findElement(By.xpath(prop.getProperty("valid-date"))).click();//SELECTED A VALID DATE
			  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();//HIT SEARCH
			  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECK IN DATE, WHICH WONT BE VISIABLE IF SEARCH EXECUTES
			  tc.info("SELECTING CHECK IN DATE, WHICH WONT BE VISIABLE IF SEARCH EXECUTES");
		  }
		  tc.pass("Test Passed");
	  }
	  @Test(enabled = true, dependsOnMethods="disabledInDates", description = "To verify if the check out calender allows dates before check in date to be selected")
	  public void disabledOutDate(){
		  tc = report.createTest("To verify if the check out calender allows dates before check in date to be selected");
		  int count=0;
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  tc.info("SELECTING TODAY'S DATE");
		  try {			
			  driver.findElement(By.xpath(prop.getProperty("before-checkin"))).click();//SELECT A DATE BEFORE THE CHECK IN DATE
			  tc.info("SELECTING A DATE BEFORE THE CHECK IN DATE");
		  }
		  catch(ElementClickInterceptedException e){
			  count=1;
			  System.out.println("Not interactable - Dates before check in date disabled");//PASS CASE IF EXCEPTION IN THROWN
			  tc.info("PASS CASE IF EXCEPTION IN THROWN");
		  }
		  if(count==0){	//THIS CONDITION IS TO PURPOSELY FAIL THE TEST CASE IF THE TRY BLOCK RUNS
			  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();//HIT SEARCH
			  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECK IN DATE, WHICH WONT BE VISIABLE IF SEARCH EXECUTES
		  }
		  tc.pass("Test Passed");
	  }
	  @Test(enabled = true, dependsOnMethods="disabledOutDate", description = "To verify output when check in date is of yesterday's")
	  public void yestDate(){
		  tc = report.createTest("To verify output when check in date is of yesterday's");
		  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECKIN CALENDER
		  driver.findElement(By.xpath(prop.getProperty("yest-date"))).click();//SELECT YESTERDAY'S DATE
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();
		  WebDriverWait wt = new WebDriverWait(driver, 20);
		  wt.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("no-result"))));//WAIT UNTIL THE LOADING FINISHES AND A SITE ALERT APPEARS
		  driver.findElement(By.cssSelector(prop.getProperty("exit-no-result"))).click();//GO BACK TO SELECT VALID DATES - EXIT BUTTON
		  tc.info("ALERT APPEARS");
		  tc.pass("Test Passed");
	  }
	  @Test(enabled = true, dependsOnMethods="yestDate", description = "To verify if the gap between check in and check out date is not more than 28 days")
	  public void daysGap(){
		  tc = report.createTest("To verify if the gap between check in and check out date is not more than 28 days");
		  driver.findElement(By.className(prop.getProperty("city"))).click();
		  List<WebElement> ls=driver.findElements(By.className(prop.getProperty("city-list")));//LOAD ALL ELEMENTS OF CITY DROPDWON IN LIST
		  ls.get(3).click();
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  driver.findElement(By.xpath(prop.getProperty("date-28"))).click();//SELECT A DATE 28 DATES AHEAD
		  driver.findElement(By.id(prop.getProperty("close-room"))).click();
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();
		  driver.switchTo().alert().accept();
		  tc.info("ALERT APPEARS");
		  tc.pass("Test Passed");
	  }
	  @Test(enabled = true, dependsOnMethods="daysGap", description = "To verify listing of hotels after search using valid inputs")
	  public void positiveSearch(){
		  tc = report.createTest("To verify listing of hotels after search using valid inputs");
		  driver.findElement(By.className(prop.getProperty("city"))).click();
		  List<WebElement> ls=driver.findElements(By.className(prop.getProperty("city-list")));
		  ls.get(3).click();
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  driver.findElement(By.xpath(prop.getProperty("valid-date"))).click();//SELECTED A VALID DATE
		  driver.findElement(By.id(prop.getProperty("close-room"))).click();
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();
		  WebDriverWait wt = new WebDriverWait(driver, 20);
		  wt.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("view-btn"))));// WAIT UNTIL VIEW NOW BUTTON LOADS IN HOTEL LIST
		  tc.info("WAIT UNTIL VIEW NOW BUTTON LOADS IN HOTEL LIST");
		  driver.findElement(By.className(prop.getProperty("view-btn"))).click();//CLICK VIEW NOW
		  tc.info("VIEW NOW BUTTON FOUND");
		  
		tc.pass("Test Passed");
	  }
}
