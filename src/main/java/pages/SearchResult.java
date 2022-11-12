
package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.LoadingTime;


public class SearchResult {
	WebDriver driver;
	String clickOnItemTry = "//span[@class=\"product-image-wrapper\"]";
	String clickOnItemCatch = "//a[@class=\"product-item-link\"]";

	public SearchResult(WebDriver driver) {
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
			LoadingTime.loadingTime();



		}
	}


}



