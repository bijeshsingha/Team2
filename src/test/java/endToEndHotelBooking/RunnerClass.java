package endToEndHotelBooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import frameWork.Base;
import frameWork.Excel;

public class RunnerClass extends Base {
	
	ExtentTest test;
	
	@Test(priority = 1)
	public void f() {
		// creating extent report
		report = new ExtentReports();
				
		//specifying that we need html report, with name module_name.html
		report.attachReporter(new ExtentHtmlReporter("EndtoEndHBooking.html"));
	}


	// To verify we are on search result page
	@Test(dependsOnMethods = "f",description = "To verify results are present")
	public void resultPage() throws Exception {
		test = report.createTest("Check the Result Page");

		test.info("Select Hotel Tab");
		new HotelSearch(driver).goToHotelTab();
		

		test.info("Click on search");
		new HotelSearch(driver).clickSearch();


		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelListing?"));

		test.info("Test Passed");
	}

	// To verify the view room button is working
	@Test(dependsOnMethods = "resultPage", description = "To verify the view room button is working")
	public void viewRoom() throws Exception {

		test = report.createTest("Check the Result Page");
		
		test.info("click on view room");
		new HotelBooking(driver).ClickViewRoom();

		// To switch to a diff tab
		Set<String> ids = driver.getWindowHandles();
		List<String> idlist = new ArrayList<String>(ids);
		driver.switchTo().window(idlist.get(1));

		// Assertion
		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelDescription?"));
		test.info("Test passed");
	}

	// To check select view room button is working
	@Test(dependsOnMethods = "viewRoom")
	public void selectRoomButton() throws Exception {

		test = report.createTest("Check the select Room button");
		try {
			wt.until(ExpectedConditions.visibilityOfElementLocated(new HotelBooking(driver).e_selectRoom));
			Assert.assertEquals(true, true);
		} catch (Exception e) {
			Assert.assertEquals(false, true);
		}
		test.info("Test Passed");
	}

	// To check Book Now Function
	@Test(dependsOnMethods = "selectRoomButton")
	public void bookNow() throws Exception {

		Thread.sleep(2000);
		test = report.createTest("Check the book now button");

		new HotelBooking(driver).clickBookNow();
		Assert.assertTrue(driver.findElement(new HotelBooking(driver).e_payment).isDisplayed());
		test.info("Test Passed");
	}

	// To enter the data of the user before confirm booking
	@Test(dataProvider = "dp", description = "entering data and checking", dependsOnMethods = "bookNow")
	public void dataValid(String fName, String lName, String email, String phno) throws Exception {
		driver.manage().deleteAllCookies();

		test = report.createTest("Check for all valid data");

		new HotelBooking(driver).enterdata(fName, lName, email, phno);
		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Travel/Traveller?"));
		test.info("Test Passed");
	}

	// Data Provider
	@DataProvider
	public Object[][] dp() {
		Object data[][] = new Object[1][4];
		Excel ex = new Excel("src/test/resources/Excel/Book1.xlsx");
		for (int j = 1; j < 5; j++) {
			data[0][j - 1] = ex.readData("Sheet1", 1, j);
		}
		return data;
	}


}
