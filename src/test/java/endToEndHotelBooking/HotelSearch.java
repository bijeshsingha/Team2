package endToEndHotelBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HotelSearch {
	WebDriver driver;

	//Constructor
	public HotelSearch(WebDriver driver) {
		this.driver = driver;
	}
	
	//Elements
	By e_Hotel = By.cssSelector("a[href='https://www.easemytrip.com/hotels/']");
	By e_Search = By.cssSelector("input[value='Search']");
	
	//to go to hotel Tab
	public void goToHotelTab() {
		driver.findElement(e_Hotel).click();
	}
	
	//to click on search button
	public void clickSearch() {
		driver.findElement(e_Search).click();
	}
	
}
