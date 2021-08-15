package endToEndHotelBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelBooking {
	WebDriver driver;

	public HotelBooking(WebDriver driver) {
		this.driver = driver;
	}

	By e_viewRoom = By.className("bookhBtn");
	By e_selectRoom = By.className("ouline-btn");
	By e_bookNow = By.cssSelector("a.fill-btn");
	By e_fn = By.id("txtFirstName1");
	By e_ln = By.id("txtLastName1");
	By e_email = By.id("txtEmailId");
	By e_phno = By.id("txtCPhone");
	By e_payment = By.cssSelector("div.coonpayment");
	By e_makeP = By.linkText("Make Payment");

	// view room function
	public void ClickViewRoom() throws Exception, Exception {

		WebDriverWait wt = new WebDriverWait(driver, 20);
		wt.until(ExpectedConditions.visibilityOfElementLocated(e_viewRoom));

		driver.findElement(e_viewRoom).click();
	}

	// select Room
	public void ClickSelectRoom() {
		driver.findElement(e_selectRoom).click();
	}

	// Book now
	public void clickBookNow() {
		driver.findElement(e_bookNow).click();
	}

	// Entering data
	public void enterdata(String fName, String lName, String email, String phno) throws InterruptedException {

		// entering first name
		driver.findElement(e_fn).sendKeys(fName);

		// entering second name
		driver.findElement(e_ln).sendKeys(lName);

		// entering email
		driver.findElement(e_email).sendKeys(email);

		// entering ph no
		driver.findElement(e_phno).sendKeys(phno);

		// click on continue to payment
		driver.findElement(e_payment).click();
		
	}
}