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

	// To enter the data of the user before confirm booking
	@Test(dataProvider = "dp", description = "entering data and checking")
	public void hotelBooking(String fName, String lName, String email, String phno) throws Exception {
		//creating extent report
		report = new ExtentReports();
						
		//specifying that we need html report, with name module_name.html
		report.attachReporter(new ExtentHtmlReporter("EndtoEndHBooking.html"));
		
		test = report.createTest("Check End to End Hotel Booking");
		//click on hotel tab
		test.info("Select Hotel Tab");
		new HotelSearch(driver).goToHotelTab();
		//click on search
		test.info("click on search");
		new HotelSearch(driver).clickSearch();
		
		//click on view room
		test.info("click on view room");
		new HotelBooking(driver).ClickViewRoom();

		// To switch to a diff tab
		Set<String> ids = driver.getWindowHandles();
		List<String> idlist = new ArrayList<String>(ids);
		driver.switchTo().window(idlist.get(1));
		test.info("switch to diff tab");
		
		Thread.sleep(2000);
		new HotelBooking(driver).clickBookNow();
		test.info("click on book now");
		
		driver.manage().deleteAllCookies();
		test.info("check for all valid data");
		new HotelBooking(driver).enterdata(fName, lName, email, phno);
		Thread.sleep(5000);
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
