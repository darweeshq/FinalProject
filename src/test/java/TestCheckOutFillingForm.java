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
import pages.FeildBoxErrorText;
import pages.ItemPage;
import core.GoToURL;
import core.LoadingTime;
import pages.SearchBox;
import pages.SearchResult;
import pages.ShippingForm;

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
		ItemPage make = new ItemPage(driver);
		SearchBox type = new SearchBox(driver);
		type.setSearchBoxValue(ItemName);
		LoadingTime.loadingTime();
		SearchResult search = new SearchResult(driver);
		search.clickOnFirstItem();
//		LoadingTime.loadingTime();
		make.pickColorSizeSubmit();

		ItemPage Item = new ItemPage(driver);
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
		ShippingForm checkOutForm = new ShippingForm(driver);
		checkOutForm.fillShippingFormTestEmail();
		LoadingTime.loadingTime();
		String ExpectedValue = "valid email";
		FeildBoxErrorText Email = new FeildBoxErrorText(driver);
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
		ShippingForm checkOutForm = new ShippingForm(driver);
		checkOutForm.fillShippingFormPhoneTest();
		LoadingTime.loadingTime();
		String ExpectedValue = "Valid Number";
		FeildBoxErrorText Phone = new FeildBoxErrorText(driver);
		Phone.phoneAssertionCompare().contains(ExpectedValue);
		if((Phone.phoneAssertionCompare().contains(ExpectedValue))==true) {
			System.out.println("The entered value of Phone is not correct");
		}
		Assert.assertTrue(Phone.phoneAssertionCompare().contains(ExpectedValue),"The entered value of Phone is not correct");
	}	

}