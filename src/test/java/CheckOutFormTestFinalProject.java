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
public class CheckOutFormTestFinalProject {

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
		List<String[]> lines = ReadCsvFile.readAllLines("inputSearch.csv");
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
	@Test(dependsOnMethods = {"TestShopping"}, testName = "Compare Actual Quatity with Expected in Cart")
	public void NumberItemsInCart() throws Exception {

		InputCsvFile numberOfItems = new InputCsvFile();
		int ExpectedNumberofItems = numberOfItems.CountMethod();
		System.out.println("Actuel Number in Cart: " +ActualNumberOfItems +" ,Expected Number in Cart: "+ ExpectedNumberofItems);

		Assert.assertEquals(ActualNumberOfItems, ExpectedNumberofItems);


	}
	@Test(dependsOnMethods = {"NumberItemsInCart"},testName = "Create output CSV File of collected data")
	public void getResult() {
		List<String[]> data = new ArrayList<String[]>();
		for(ArrayList<String> row: outputData) {
			String[] row_data = new String[row.size()];
			for(int i= 0;i<row.size();i++) {
				row_data[i] = row.get(i);
			}
			data.add(row_data);
		}
		String[] headers = new String[outputHeaders.size()];
		for(int i= 0;i<outputHeaders.size();i++) {
			headers[i] = outputHeaders.get(i);
		}
		WriteCsvFile.writeDataLineByLine("outputSearchResults.csv", data, headers);

	}
	@Test(dependsOnMethods = {"getResult"}, testName = "Compare total cost of Actual purchase and Expected")
	public void fillCheckOutForm() throws Exception {
		GoToURL goTo = new GoToURL(driver);
		goTo.CheckOutPage();

		CheckOutPage checkOutForm = new CheckOutPage(driver);
		checkOutForm.fillCheckOutForm();
		Thread.sleep(3000);

		//Get cost of items and shipping rate from CSV file - ExpectedValue
		Cart sales = new Cart(driver);
		double actualValue = sales.getTotalCartSalesIncludShipping();

		//Get cost of items and shipping rate from Website - RealValue
		Order orderPrice = new Order();
		double ExpectedValue = orderPrice.ItemsSumValue();

		System.out.println("Expected value of purchased items including shipping : " +"$"+ExpectedValue);
		System.out.println("Real value seen on Page of purchased items including shipping : " +"$"+actualValue);
		Assert.assertEquals(actualValue, ExpectedValue);


	}
	@Test(dependsOnMethods = {"fillCheckOutForm"}, testName = "Allure Test Results")
	public void allureResults() throws IOException {
		readAllureTest allureReport = new readAllureTest();
		allureReport.allureTest();
	}

}