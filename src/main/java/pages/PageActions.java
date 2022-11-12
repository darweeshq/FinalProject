package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.LoadingTime;

public class PageActions {
	WebDriver driver;
	String clickOnItemTry = "//span[@class=\"product-image-wrapper\"]";
	String clickOnItemCatch = "//a[@class=\"product-item-link\"]";
	String getSearchBoxXpath = "//input[@id=\"search\"]";
	String clickonsizeXpath ="//div[@class=\"swatch-option text\"]";
	String clickOnColorXpath = "//div[@option-type=\"1\"]";
	String addToCartXpath = "//div/div/div/div/button[@type=\"submit\"]";
	String addToCartById = "product-addtocart-button";

	public PageActions(WebDriver driver) {
		this.driver = driver;
	}
	public void clickOnFirstItem() throws InterruptedException {
		try {
			List<WebElement> clickOnItem = driver.findElements(By.xpath(clickOnItemTry));
			clickOnItem.get(0).click();
			LoadingTime.loadingTime();


		} catch (Exception e) {
			List<WebElement> clickOnItem = driver.findElements(By.xpath(clickOnItemCatch));
			clickOnItem.get(0).click();


		}
	}
	public void setSearchBoxValue(String searchText) throws InterruptedException {
		WebElement getSearchBox = driver.findElement(By.xpath(getSearchBoxXpath));
		getSearchBox.click();
		getSearchBox.clear();
		getSearchBox.sendKeys(searchText);
		getSearchBox.sendKeys(Keys.ENTER);
		LoadingTime.loadingTime();


	}
	public void pickColorSizeQuantity() throws InterruptedException {
		try {
			WebElement clickOnSize = driver.findElement(By.xpath(clickonsizeXpath));
			clickOnSize.click();
		}catch(Exception e) {
			System.out.println("There is no size to select for this Item.");
		}
		try {
			List<WebElement> clickOnColor = driver.findElements(By.xpath(clickOnColorXpath));
			clickOnColor.get(0).click();
			LoadingTime.loadingTime();

		}catch(Exception e) {
			System.out.println("There is no color to select for this Item.");
		}
		try {

			WebElement addToCart = driver.findElement(By.xpath(addToCartXpath));

			addToCart.click();
			LoadingTime.loadingTime();
		}catch(Exception e) {
			WebElement addToCart = driver.findElement(By.id(addToCartById));

			addToCart.click();
			LoadingTime.loadingTime();

		}
	}
}
