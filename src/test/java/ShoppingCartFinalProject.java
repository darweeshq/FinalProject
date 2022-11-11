import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import core.CsvResultFile;
import core.GetData;
import core.TakeScreenShot;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import pages.Cart;
import pages.CheckOutPage;
import pages.GetItemInfo;
import core.GoToURL;
import pages.InputCsvFile;
import pages.Order;
import pages.PageActions;

@Test
public class ShoppingCartFinalProject extends BaseClass {
	
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] screenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	@DataProvider (name = "SearchItems")
	public Object[][] getData() throws Exception {
		GetData inputFile = new GetData();
		return inputFile.getData("inputSearch.csv");
	}
	@Test (dataProvider = "SearchItems",testName = "Purchase items and add to cart")
	public void TestShopping(String ItemName) throws Exception {

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
		
		TakeScreenShot takeScr = new TakeScreenShot(driver);
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
		CsvResultFile setData = new CsvResultFile(outputHeaders, outputData);
		setData.csvResultFile("outputSearchResults.csv");
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
	

}