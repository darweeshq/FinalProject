import java.io.ByteArrayInputStream;
import java.util.ArrayList;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.GetData;
import core.LoadingTime;
import core.TakeScreenShot;
import io.qameta.allure.Allure;
import pages.Cart;
import pages.ItemPage;
import pages.SearchBox;
import pages.SearchResult;

@Test
public class RemoveFromCart extends BaseClass {
	@DataProvider (name = "SearchItems")
	public Object[][] dpMethod() throws Exception {
			GetData inputFile = new GetData();
			return inputFile.getData("removeTest.csv");
	}	
	@Test (dataProvider = "SearchItems",testName = "Purchase items and add to cart")
	public void TestShopping(String ItemName) throws Exception {
		TakeScreenShot takeScr = new TakeScreenShot(driver);
		takeScr.takeScreenShot("Shirt-shot-"+ItemName+".jpg");

		System.out.println("Item search for: " + ItemName );
		System.out.println("- - - - - - - - - - - - - - - - -");
		ItemPage make = new ItemPage(driver);
		SearchBox type = new SearchBox(driver);
		type.setSearchBoxValue(ItemName);
		SearchResult search = new SearchResult(driver);
		search.clickOnFirstItem();
		make.pickColorSizeSubmit();

		ItemPage Item = new ItemPage(driver);
		String itemTitle = Item.getItemTitle();
		String itemPrice = Item.getItemPrice();

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