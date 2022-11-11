import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.GetData;
import core.TakeScreenShot;
import io.qameta.allure.Allure;
import pages.AssertionCompare;
import pages.CheckOutPage;
import pages.GetItemInfo;
import core.GoToURL;
import core.LoadingTime;
import pages.PageActions;

@Test
public class TestCheckOutFillingForm extends BaseClass {


	@DataProvider (name = "SearchItems")
	public Object[][] getData() throws Exception {
		GetData inputFile = new GetData();
		return inputFile.getData("inputSearch1.csv");
	}
	@Test (dataProvider = "SearchItems",testName = "Purchase items and add to cart")
	public void TestShopping(String ItemName) throws Exception {

		TakeScreenShot takeScr = new TakeScreenShot(driver);
		takeScr.takeScreenShot("Shirt-shot-"+ItemName+".jpg");

		System.out.println("Item search for: " + ItemName );
		System.out.println("- - - - - - - - - - - - - - - - -");
		LoadingTime.loadingTime();
		PageActions make = new PageActions(driver);
		make.setSearchBoxValue(ItemName);
		LoadingTime.loadingTime();
		make.clickOnFirstItem();
		LoadingTime.loadingTime();
		make.pickColorSizeQuantity();

		GetItemInfo Item = new GetItemInfo(driver);
		String itemTitle = Item.getItemTitle();
		String itemPrice = Item.getItemPrice();

		takeScr.takeScreenShot("Shirtshot-"+itemTitle+".jpg");
		Allure.addAttachment("ScreenShot"+itemTitle, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		LoadingTime.loadingTime();
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
		LoadingTime.loadingTime();
		CheckOutPage checkOutForm = new CheckOutPage(driver);
		checkOutForm.fillCheckOutFormTestEmail();
		LoadingTime.loadingTime();
		String ExpectedValue = "valid email";
		AssertionCompare Email = new AssertionCompare(driver);
		Email.emailAssertionCompare().contains(ExpectedValue);
		if((Email.emailAssertionCompare().contains(ExpectedValue))==true) {
			System.out.println("The entered value of Email is not correct");
		}
		Assert.assertTrue(Email.emailAssertionCompare().contains(ExpectedValue),"The entered value of Email is not correct");
	}
	@Test(dependsOnMethods = {"TestShopping"}, testName = "Test phone number")
	public void fillCheckOutFormPhoneTest() throws Exception {
		GoToURL goTo = new GoToURL(driver);
		goTo.CheckOutPage();
		LoadingTime.loadingTime();
		CheckOutPage checkOutForm = new CheckOutPage(driver);
		checkOutForm.fillCheckOutFormPhoneTest();
		LoadingTime.loadingTime();
		String ExpectedValue = "Valid Number";
		AssertionCompare Phone = new AssertionCompare(driver);
		Phone.phoneAssertionCompare().contains(ExpectedValue);
		if((Phone.phoneAssertionCompare().contains(ExpectedValue))==true) {
			System.out.println("The entered value of Phone is not correct");
		}
		Assert.assertTrue(Phone.phoneAssertionCompare().contains(ExpectedValue),"The entered value of Phone is not correct");
	}	

}