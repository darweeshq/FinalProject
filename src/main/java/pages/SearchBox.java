package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.LoadingTime;

public class SearchBox {

	WebDriver driver;
	String getSearchBoxXpath = "//input[@id=\"search\"]";

	public SearchBox(WebDriver driver) {
		this.driver = driver;
	}

	public void setSearchBoxValue(String searchText) throws InterruptedException {
		WebElement getSearchBox = driver.findElement(By.xpath(getSearchBoxXpath));
		getSearchBox.click();
		getSearchBox.clear();
		getSearchBox.sendKeys(searchText);
		getSearchBox.sendKeys(Keys.ENTER);
		LoadingTime.loadingTime();


	}

}



