package pages;
import org.openqa.selenium.WebDriver;

public class GoToURL {
	WebDriver driver;
	final static String homePageUrl = "https://magento.softwaretestingboard.com/";
	final static String CheckOutLink = "https://magento.softwaretestingboard.com/checkout/#shipping";

	public GoToURL(WebDriver driver) {
		this.driver = driver;
	}
	public void HomePage() throws InterruptedException {
		driver.get(homePageUrl);
		Thread.sleep(3000);
	}
	public void CheckOutPage() throws InterruptedException {
		driver.get(CheckOutLink);
		Thread.sleep(3000);
	}
}
