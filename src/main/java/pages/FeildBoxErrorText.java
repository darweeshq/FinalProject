package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FeildBoxErrorText {
	WebDriver driver;
	String EmailTextXpath = "//div[@for=\"customer-email\"]";
	String PhoneXpath = "//div/div[@class=\"step-title\"]";

	public FeildBoxErrorText(WebDriver driver) {
		this.driver = driver;

	}
	public String emailAssertionCompare() {
		String ActualValue = driver.findElement(By.xpath(EmailTextXpath)).getText();
		return ActualValue;
	
	}
	public String phoneAssertionCompare() {
		List<WebElement> ActualValueList = driver.findElements(By.xpath(PhoneXpath));
		String ActualValue = ActualValueList.get(1).getText();
		return ActualValue;
		
	}
}
