package pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {
	WebDriver driver;
	String countRealNumberOfItemsInCart ="//span[@class=\"counter-number\"]";
	String TotalPriceAndShipping = "//span[@class=\"price\"]";

	public Cart(WebDriver driver) {
		this.driver = driver;
	}
	public int ItemsInCart() throws InterruptedException {
		int NumberOfPurchasedItems = 0;
		try {
			Thread.sleep(4000);
			String getNumberOfPurchasedItems = driver.findElement(By.xpath(countRealNumberOfItemsInCart)).getText();
			NumberOfPurchasedItems = Integer.parseInt(getNumberOfPurchasedItems);
			Thread.sleep(1000);
		}catch(Exception e) {
			System.out.println("Couldn't Read Cart value");
		}
		return NumberOfPurchasedItems;

	}
	public double getTotalCartSalesIncludShipping() throws InterruptedException {
		Thread.sleep(4000);
		List<WebElement> totalAndSHipping = driver.findElements(By.xpath(TotalPriceAndShipping));
		String total = totalAndSHipping.get(4).getText().replace("$", "");
		double tatal1 = Double.parseDouble(total);
		return tatal1;
		
	}

}


