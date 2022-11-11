
	package pages;

	import java.util.List;

import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

import core.LoadingTime;

	public class GetItemInfo {
		WebDriver driver;
		String ItemPriceXpath = "//span[@data-price-type=\"finalPrice\"]";
		String ItemTitleXpath ="//span[@class=\"base\"]";

		public GetItemInfo(WebDriver driver) {
			this.driver = driver;
		}
		public String getItemPrice() throws InterruptedException {
			LoadingTime.loadingTime();
			List<WebElement> getItemPrice = driver.findElements(By.xpath(ItemPriceXpath));
			String price = getItemPrice.get(0).getText();
			return price;
			
		}
		public String getItemTitle() throws InterruptedException {
			LoadingTime.loadingTime();
			WebElement getItemTitle = driver.findElement(By.xpath(ItemTitleXpath));
			String Title = getItemTitle.getText();
			return Title;
			
		}
	}

