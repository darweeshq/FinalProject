
package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.LoadingTime;


public class ItemPage {
	WebDriver driver;
	String ItemPriceXpath = "//span[@data-price-type=\"finalPrice\"]";
	String ItemTitleXpath ="//span[@class=\"base\"]";
	String clickOnItemTry = "//span[@class=\"product-image-wrapper\"]";
	String clickOnItemCatch = "//a[@class=\"product-item-link\"]";
	String clickonsizeXpath ="//div[@class=\"swatch-option text\"]";
	String clickOnColorXpath = "//div[@option-type=\"1\"]";
	String addToCartXpath = "//div/div/div/div/button[@type=\"submit\"]";
	String addToCartById = "product-addtocart-button";

	public ItemPage(WebDriver driver) {
		this.driver = driver;
	}
	public String getItemPrice() throws InterruptedException {
		List<WebElement> getItemPrice = driver.findElements(By.xpath(ItemPriceXpath));
		String price = getItemPrice.get(0).getText();
		return price;

	}
	public String getItemTitle() throws InterruptedException {
		WebElement getItemTitle = driver.findElement(By.xpath(ItemTitleXpath));
		String Title = getItemTitle.getText();
		return Title;

	}
	public void pickColorSizeSubmit() throws InterruptedException {
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

