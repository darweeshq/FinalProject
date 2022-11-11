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
//			Thread.sleep(3000);
			try {
			LoadingTime.loadingTime();
			List<WebElement> clickOnItem = driver.findElements(By.xpath(clickOnItemTry));
//			Thread.sleep(1000);
			clickOnItem.get(0).click();
//			LoadingTime.loadingTime();


			} catch (Exception e) {
//				Thread.sleep(1000);
				LoadingTime.loadingTime();
				List<WebElement> clickOnItem = driver.findElements(By.xpath(clickOnItemCatch));
//				Thread.sleep(1000);
				clickOnItem.get(0).click();
//				LoadingTime.loadingTime();


			}
//			Thread.sleep(2000);
		}
		public void setSearchBoxValue(String searchText) throws InterruptedException {
//			LoadingTime.loadingTime();
			WebElement getSearchBox = driver.findElement(By.xpath(getSearchBoxXpath));
			getSearchBox.click();
			getSearchBox.clear();
//			Thread.sleep(1000);
			getSearchBox.sendKeys(searchText);
			getSearchBox.sendKeys(Keys.ENTER);
//			Thread.sleep(2000);

		}
		public void pickColorSizeQuantity() throws InterruptedException {
			try {
			LoadingTime.loadingTime();
			WebElement clickOnSize = driver.findElement(By.xpath(clickonsizeXpath));
			clickOnSize.click();
//			Thread.sleep(1000);
			}catch(Exception e) {
//				LoadingTime.loadingTime();
				System.out.println("There is no size to select for this Item.");
			}
			try {
//			LoadingTime.loadingTime();
			List<WebElement> clickOnColor = driver.findElements(By.xpath(clickOnColorXpath));
			clickOnColor.get(0).click();
			LoadingTime.loadingTime();

//			Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println("There is no color to select for this Item.");
			}
			try {
//			LoadingTime.loadingTime();
			WebElement addToCart = driver.findElement(By.xpath(addToCartXpath));
			addToCart.click();
			LoadingTime.loadingTime();
			}catch(Exception e) {
//			LoadingTime.loadingTime();
				WebElement addToCart = driver.findElement(By.id(addToCartById));
				addToCart.click();
				LoadingTime.loadingTime();
				
			}
		}
	}
