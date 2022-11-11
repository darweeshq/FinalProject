package core;
import org.openqa.selenium.WebDriver;

public class GoToURL {
	WebDriver driver;
	final static String homePageUrl = "https://magento.softwaretestingboard.com/";
	final static String CheckOutLink = "https://magento.softwaretestingboard.com/checkout/#shipping";
	final static String reviewProduct = "https://magento.softwaretestingboard.com/didi-sport-watch.html";

	public GoToURL(WebDriver driver) {
		this.driver = driver;
	}
	public void HomePage() throws InterruptedException {
		driver.get(homePageUrl);
		LoadingTime.loadingTime();
	}
	public void CheckOutPage() throws InterruptedException {
		driver.get(CheckOutLink);
		LoadingTime.loadingTime();
	}
	public void reviewPage() throws InterruptedException {
		driver.get(reviewProduct);
		LoadingTime.loadingTime();
	}
}
