import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
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
import core.GoToURL;
import core.InputCsvFile;
import core.OrderCost;
import pages.ItemPage;
import pages.SearchBox;
import pages.SearchResult;
import pages.ShippingForm;

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
		ItemPage make = new ItemPage(driver);
		SearchBox type = new SearchBox(driver);
		type.setSearchBoxValue(ItemName);
		SearchResult search = new SearchResult(driver);
		search.clickOnFirstItem();;
		make.pickColorSizeSubmit();

		ItemPage Item = new ItemPage(driver);
		String itemTitle = Item.getItemTitle();
		String itemPrice = Item.getItemPrice();
		
		TakeScreenShot takeScr = new TakeScreenShot(driver);
		takeScr.takeScreenShot("Shirtshot-"+itemTitle+".jpg");
		
		Allure.addAttachment("ScreenShot"+itemTitle, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

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
	public void getResult() throws IOException {
		CsvResultFile setData = new CsvResultFile(outputHeaders, outputData);
		setData.csvResultFile("outputSearchResults.csv");
		File resultFile = new File( "outputSearchResults.csv");
	    Allure.addAttachment(resultFile.getName(), FileUtils.openInputStream(resultFile));
	}
	@Test(dependsOnMethods = {"getResult"}, testName = "Compare total cost of Actual purchase and Expected")
	public void fillShippingFormTest() throws Exception {
		GoToURL goTo = new GoToURL(driver);
		goTo.CheckOutPage();

		ShippingForm checkOutForm = new ShippingForm(driver);
		checkOutForm.fillShippingForm();;

		//Get cost of items and shipping rate from CSV file - ExpectedValue
		Cart sales = new Cart(driver);
		double actualValue = sales.getTotalCartSalesIncludShipping();

		//Get cost of items and shipping rate from Website - RealValue
		OrderCost orderPrice = new OrderCost();
		double ExpectedValue = orderPrice.ItemsSumValue();

		System.out.println("Expected value of purchased items including shipping : " +"$"+ExpectedValue);
		System.out.println("Real value seen on Page of purchased items including shipping : " +"$"+actualValue);
		Assert.assertEquals(actualValue, ExpectedValue);


	}
	

}