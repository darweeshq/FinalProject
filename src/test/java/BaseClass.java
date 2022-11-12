import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import core.OpenBrowsers;
import core.readAllureTest;
import io.qameta.allure.Attachment;
import core.GoToURL;
import core.LoadingTime;

public class BaseClass{
	
		WebDriver driver;
		ArrayList<String> outputHeaders = new ArrayList<String>();
		ArrayList<ArrayList<String>> outputData = new ArrayList<ArrayList<String>>();
		int ActualNumberOfItems;
		



		@BeforeSuite
		public void beforeSuite() throws InterruptedException, IOException {
			driver = OpenBrowsers.openchromeWithOptions();
			LoadingTime time = new LoadingTime(driver);

			outputHeaders.add("Input Shirt search field");
			outputHeaders.add("Item Name");
			outputHeaders.add("Item Price");
			GoToURL goTo = new GoToURL(driver);
			goTo.HomePage();
		}
		@Attachment(value = "Screenshot", type = "image/png")
		public byte[] screenshot() {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
		@AfterSuite
		public void allureResults() throws IOException {
			readAllureTest allureReport = new readAllureTest();
			allureReport.allureTest();
		}
}
