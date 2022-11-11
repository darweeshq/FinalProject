import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import core.OpenBrowsers;
import pages.Reviews;

@Test
public class ReviewTest {


	WebDriver driver;
	@BeforeSuite
	public void beforeSuite() throws InterruptedException, IOException {
		driver = OpenBrowsers.openchromeWithOptions();
		
	}
	@Test
	public void TestShopping() throws Exception {
		Reviews create = new Reviews(driver);
		String actualValue = create.review();
		System.out.println("The actual value is : "+ actualValue);
		Assert.assertTrue(actualValue.contains("moderation"), "Result will be true if the actual value contains [moderation]");

	}
}