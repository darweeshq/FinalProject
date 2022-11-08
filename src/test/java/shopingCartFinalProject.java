import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import core.OpenBrowsers;
import core.ReadCsvFile;
import core.TakeScreenShot;
import core.readAllureTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import pages.AssertionCompare;
import pages.CheckOutPage;
import pages.GetItemInfo;
import pages.GoToURL;
import pages.PageActions;

@Test
public class shopingCartFinalProject {

	WebDriver driver;
	ArrayList<String> outputHeaders = new ArrayList<String>();
	ArrayList<ArrayList<String>> outputData = new ArrayList<ArrayList<String>>();
	int ActualNumberOfItems;


	@BeforeSuite
	public void beforeSuite() throws InterruptedException, IOException {
		driver = OpenBrowsers.openchromeWithOptions();
		//		driver = OpenBrowsers.openFFWithOptions();
		outputHeaders.add("Input Shirt search field");
		outputHeaders.add("Item Name");
		outputHeaders.add("Item Price");
		GoToURL goTo = new GoToURL(driver);
		goTo.HomePage();
		
		
		//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

	}
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] screenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	@DataProvider (name = "SearchItems")
	public Object[][] dpMethod() throws Exception {
		List<String[]> lines = ReadCsvFile.readAllLines("inputSearch1.csv");
		lines.remove(0);
		Object[][] data = new Object[lines.size()][lines.get(0).length];
		int index = 0;
		for(String[] line : lines) {
			data[index] = line;
			index++;
		}
		return data;
	}	
	@Test (dataProvider = "SearchItems",testName = "Purchase items and add to cart")
	public void TestShopping(String ItemName) throws Exception {

		TakeScreenShot takeScr = new TakeScreenShot(driver);
		takeScr.takeScreenShot("Shirt-shot-"+ItemName+".jpg");

		System.out.println("Item search for: " + ItemName );
		System.out.println("- - - - - - - - - - - - - - - - -");
		Thread.sleep(3000);
		PageActions make = new PageActions(driver);
		make.setSearchBoxValue(ItemName);
		Thread.sleep(1000);
		make.clickOnFirstItem();
		Thread.sleep(3000);
		make.pickColorSizeQuantity();

		GetItemInfo Item = new GetItemInfo(driver);
		String itemTitle = Item.getItemTitle();
		String itemPrice = Item.getItemPrice();

		takeScr.takeScreenShot("Shirtshot-"+itemTitle+".jpg");
		Allure.addAttachment("ScreenShot"+itemTitle, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		Thread.sleep(3000);
		ArrayList<String> results = new ArrayList<String>();
		results.add(ItemName);
		results.add(itemTitle);
		results.add(itemPrice);
		outputData.add(results);

	}
	@Test(dependsOnMethods = {"TestShopping"}, testName = "Test Email")
	public void fillCheckOutFormEmailTest() throws Exception {
		GoToURL goTo = new GoToURL(driver);
		goTo.CheckOutPage();
		Thread.sleep(10000);
		CheckOutPage checkOutForm = new CheckOutPage(driver);
		checkOutForm.fillCheckOutFormTestEmail();
		Thread.sleep(3000);
		String ExpectedValue = "valid email";
		AssertionCompare Email = new AssertionCompare(driver);
		Email.emailAssertionCompare().contains(ExpectedValue);
		if((Email.emailAssertionCompare().contains(ExpectedValue))==true) {
			System.out.println("The entered value of Email is not correct");
		}
		Assert.assertFalse(Email.emailAssertionCompare().contains(ExpectedValue),"The entered value of Email is not correct");
	}
	@Test(dependsOnMethods = {"TestShopping"}, testName = "Test phone number")
	public void fillCheckOutFormPhoneTest() throws Exception {
		GoToURL goTo = new GoToURL(driver);
		goTo.CheckOutPage();
		Thread.sleep(10000);
		CheckOutPage checkOutForm = new CheckOutPage(driver);
		checkOutForm.fillCheckOutFormPhoneTest();
		Thread.sleep(7000);
		String ExpectedValue = "Payment Method";
		AssertionCompare Phone = new AssertionCompare(driver);
		Phone.phoneAssertionCompare().contains(ExpectedValue);
		if((Phone.phoneAssertionCompare().contains(ExpectedValue))==true) {
			System.out.println("The entered value of Phone is not correct");
		}
		Assert.assertFalse(Phone.phoneAssertionCompare().contains(ExpectedValue),"The entered value of Phone is not correct");
	}
	@AfterSuite
	public void allureResults() throws IOException {
		readAllureTest allureReport = new readAllureTest();
		allureReport.allureTest();
	}

}