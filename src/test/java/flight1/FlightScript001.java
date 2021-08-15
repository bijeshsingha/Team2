package flight1;

import org.testng.annotations.Test;



public class FlightScript001 extends FlightScript01{
	@Test(enabled= true, priority= 1)
	public void onewayTrip() throws Exception {
		aPropLoadMethod();
		checkOneway();
		tc_date01();
		//tc_date02();
		tc_traveller9();
		tc_traveller01();
		tc_class01();
		tc_search01();
	}
	@Test(enabled= false, priority= 2)
	public void roundTrip() throws Exception {
		aPropLoadMethod();
		checkRoundtrip();
		tc_date01();
		tc_date02();
		tc_traveller9();
		tc_traveller01();
		tc_class01();
		tc_search01();
	}
	

}
