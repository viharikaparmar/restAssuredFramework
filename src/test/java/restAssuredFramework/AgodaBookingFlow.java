package restAssuredFramework;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AgodaBookingFlow {
	
	Properties p1;
	
	@BeforeTest
	public void setEnv() throws IOException
	{
		FileReader reader=new FileReader("C:\\Users\\viharika.parmar\\eclipse-workspace\\restAssuredFramework\\src\\test\\resources\\environment.properties");  
		p1 = new Properties();
		p1.load(reader);
	
	}
	
	
	
	@Test(dataProvider="hotelids")
	public void search(String id) {
		
		RestAssured.baseURI = p1.getProperty("baseurl");
		
		given().log().all().
				param("checkInDate", "2019-08-20T14:00:00").
				param("checkOutDate", "2019-08-21T00:00:00").
				param("ids", "[{\"hotelId\":\""+id+"\"}]").
				param("room1", "2").
				header("x-api-key","G0O6MHNGv9HU4kIfRIWm").
		when().
				get("/agd/v1/hotels").
		then().log().all().
				assertThat().statusCode(200);
		
		System.out.println("done");

	}
	
	@Test
	public void testcase1() {
		System.out.println("Test case1");
	}
	@DataProvider(name="hotelids")
	public Object[][] getVendors(){
		return new Object[][] {{"785533"},{"2814176"}};
	}

}
