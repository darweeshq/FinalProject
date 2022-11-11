package pages;
import java.util.List;
import core.GoToURL;
import core.LoadingTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {
	WebDriver driver;
	String countRealNumberOfItemsInCart ="//span[@class=\"counter-number\"]";
	String TotalPriceAndShipping = "//span[@class=\"price\"]";
	String showCart= "//a[@class=\"action showcart\"]";
	String countItemsInCart = "//a[@title=\"Remove item\"]";
	String removeItem = "//button[@data-role=\"action\"]";

	public Cart(WebDriver driver) {
		this.driver = driver;
	}
	public int ItemsInCart() throws InterruptedException {
		int NumberOfPurchasedItems = 0;
		try {
			LoadingTime.loadingTime();
			String getNumberOfPurchasedItems = driver.findElement(By.xpath(countRealNumberOfItemsInCart)).getText();
			NumberOfPurchasedItems = Integer.parseInt(getNumberOfPurchasedItems);
		}catch(Exception e) {
			System.out.println("Couldn't Read Cart value, Cart is empty [0] items");
		}
		return NumberOfPurchasedItems;

	}
	public double getTotalCartSalesIncludShipping() throws InterruptedException {
		LoadingTime.loadingTime();
		List<WebElement> totalAndSHipping = driver.findElements(By.xpath(TotalPriceAndShipping));
		String total = totalAndSHipping.get(4).getText().replace("$", "");
		double tatal1 = Double.parseDouble(total);
		return tatal1;
		
	}
	public void remvoeItem() throws InterruptedException {
		GoToURL HomePage = new GoToURL(driver);
		HomePage.HomePage();
		LoadingTime.loadingTime();
		driver.findElement(By.xpath(showCart)).click();
		LoadingTime.loadingTime();
		List<WebElement> ItemsInCart = driver.findElements(By.xpath(countItemsInCart));
		ItemsInCart.size();
		try {
			System.out.println(" The Number of Items in Cart before removing item is: "+ItemsInCart.size());
			ItemsInCart.get(0).click();
			LoadingTime.loadingTime();
			driver.findElements(By.xpath(removeItem)).get(1).click();
			LoadingTime.loadingTime();


		}catch(Exception e) {
			System.out.println("No Items in Cart to remove");
		}
		HomePage.HomePage();
		LoadingTime.loadingTime();
		System.out.println(" The actual number of items in Cart is: "+driver.findElements(By.xpath(countItemsInCart)).size());

	}

}


