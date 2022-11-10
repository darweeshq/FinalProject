import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import core.OpenBrowsers;
import core.ReadCsvFile;
import core.TakeScreenShot;
import core.WriteCsvFile;
import core.readAllureTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import pages.Cart;
import pages.CheckOutPage;
import pages.GetItemInfo;
import pages.GoToURL;
import pages.InputCsvFile;
import pages.Order;
import pages.PageActions;

@Test
public class RemoveFromCart {

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
		List<String[]> lines = ReadCsvFile.readAllLines("removeTest.csv");
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

		Cart cartInfo = new Cart(driver);
		ActualNumberOfItems = cartInfo.ItemsInCart();

	}
	@Test(dependsOnMethods = {"TestShopping"}, testName = "Remove Item from Cart")
	public void RemoveItemFromCartTest() throws Exception {
		Cart Item = new Cart(driver);
		int countItemsBeforeRemove = Item.ItemsInCart();
		Item.remvoeItem();
		int expectedValue = countItemsBeforeRemove-1;
		int actualValue = Item.ItemsInCart();
		Assert.assertEquals(actualValue, expectedValue, "If test pass, it means the remvoing process is done right");
	}

}