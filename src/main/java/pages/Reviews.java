package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import core.GoToURL;
import core.LoadingTime;

public class Reviews {
		WebDriver driver;
		String reviewSectionXpath ="//a[@id=\"tab-label-reviews-title\"]";
		String starsXpath = "//label[@id=\"Rating_5_label\"]";
		String nameName = "nickname";
		String summaryId ="summary_field";
		String reviewFieldId ="review_field";
		String submitXpath = "//button[@class=\"action submit primary\"]";
		String getTextXpath = "//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]";
		
		public Reviews(WebDriver driver) {
			this.driver = driver;
		}
		public String review() throws InterruptedException {

//			Thread.sleep(5000);
			GoToURL go = new GoToURL(driver);
			go.reviewPage();

//			LoadingTime time = new LoadingTime(driver);
			WebElement reviewSection = driver.findElement(By.xpath(reviewSectionXpath));
			reviewSection.click();
			LoadingTime.loadingTime();
			Actions action = new Actions(driver);
			WebElement stars = driver.findElement(By.xpath(starsXpath));
			action.moveToElement(stars).perform();
//			Thread.sleep(3000);
			action.click(stars).perform();
			WebElement name = driver.findElement(By.name(nameName));
			name.click();
			name.sendKeys("John Q");
			WebElement summary = driver.findElement(By.id(summaryId));
			summary.click();
			summary.sendKeys("Beautiful product");
			WebElement reviewfield = driver.findElement(By.id(reviewFieldId));
			reviewfield.click();
			reviewfield.sendKeys("Great value, received on time, definitely will buy from this store again ");
//			Thread.sleep(3000);
			WebElement submit = driver.findElement(By.xpath(submitXpath));
			submit.click();
			LoadingTime.loadingTime();
			WebElement getText = driver.findElement(By.xpath(getTextXpath));
			return getText.getText();
			
}
}