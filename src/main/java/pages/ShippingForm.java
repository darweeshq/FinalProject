package pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.LoadingTime;

public class ShippingForm{
	WebDriver driver;
	public ShippingForm(WebDriver driver) {
		this.driver = driver;

	}
	public void fillShippingForm() throws InterruptedException {
		LoadingTime.loadingTime();
        
		List<WebElement> Email = driver.findElements(By.xpath("//input[@type=\"email\"]"));
		Email.get(1).click();
		Email.get(1).clear();
		Email.get(1).sendKeys("darweeshq@gmail.com");
		Thread.sleep(300);

		WebElement Country = driver.findElement(By.xpath("//option[@data-title=\"Israel\"]"));
		Country.click();
		
		WebElement firstName = driver.findElement(By.name("firstname"));
		firstName.click();
		firstName.clear();
		firstName.sendKeys("Darweesh");
		Thread.sleep(300);

		WebElement lastName = driver.findElement(By.name("lastname"));
		lastName.click();
		lastName.clear();
		lastName.sendKeys("Qaimari");
		Thread.sleep(300);

		WebElement StreetAddress0 = driver.findElement(By.name("street[0]"));
		StreetAddress0.click();
		StreetAddress0.clear();
		StreetAddress0.sendKeys("Wadi El Joz, Emro Al Qays st., ");
		Thread.sleep(300);

		WebElement StreetAddress1 = driver.findElement(By.name("street[1]"));
		StreetAddress1.click();
		StreetAddress1.clear();
		StreetAddress1.sendKeys("House # 61");
		Thread.sleep(300);

		WebElement City = driver.findElement(By.name("city"));
		City.click();
		City.clear();
		City.sendKeys("Jerusalem");
		Thread.sleep(300);

		WebElement postCode = driver.findElement(By.name("postcode"));
		postCode.click();
		postCode.clear();
		postCode.sendKeys("20777");
		Thread.sleep(300);

		WebElement phone = driver.findElement(By.name("telephone"));
		phone.click();
		phone.clear();
		phone.sendKeys("0525768719");
		LoadingTime.loadingTime();

		WebElement submitDetails = driver.findElement(By.xpath("//span[@data-bind=\"i18n: 'Next'\"]"));
		submitDetails.click();
		LoadingTime.loadingTime();
	}
	public void fillShippingFormTestEmail() throws InterruptedException {
		LoadingTime.loadingTime();

		List<WebElement> Email = driver.findElements(By.xpath("//input[@type=\"email\"]"));
		Email.get(1).click();
		Email.get(1).sendKeys("darweeshqgmail.com");
		Thread.sleep(300);
		
		WebElement Country = driver.findElement(By.xpath("//option[@data-title=\"Israel\"]"));
		Country.click();

		WebElement firstName = driver.findElement(By.name("firstname"));
		firstName.click();
		firstName.sendKeys("Darweesh");
		Thread.sleep(300);

		WebElement lastName = driver.findElement(By.name("lastname"));
		lastName.click();
		lastName.sendKeys("Qaimari");
		Thread.sleep(300);

		WebElement StreetAddress0 = driver.findElement(By.name("street[0]"));
		StreetAddress0.click();
		StreetAddress0.sendKeys("Wadi El Joz, Emro Al Qays st., ");
		Thread.sleep(300);

		WebElement StreetAddress1 = driver.findElement(By.name("street[1]"));
		StreetAddress1.click();
		StreetAddress1.sendKeys("House # 61");
		Thread.sleep(300);

		WebElement City = driver.findElement(By.name("city"));
		City.click();
		City.sendKeys("Jerusalem");
		Thread.sleep(300);

		WebElement postCode = driver.findElement(By.name("postcode"));
		postCode.click();
		postCode.sendKeys("20777");
		Thread.sleep(300);

		WebElement phone = driver.findElement(By.name("telephone"));
		phone.click();
		phone.sendKeys("0525768719");

		WebElement submitDetails = driver.findElement(By.xpath("//span[@data-bind=\"i18n: 'Next'\"]"));
		submitDetails.click();
		LoadingTime.loadingTime();

	}
	public void fillShippingFormPhoneTest() throws InterruptedException {
		LoadingTime.loadingTime();

		List<WebElement> Email = driver.findElements(By.xpath("//input[@type=\"email\"]"));
		Email.get(1).click();
		Email.get(1).clear();
		Email.get(1).sendKeys("darweeshq@gmail.com");
		Thread.sleep(300);
		
		WebElement Country = driver.findElement(By.xpath("//option[@data-title=\"Israel\"]"));
		Country.click();

		WebElement firstName = driver.findElement(By.name("firstname"));
		firstName.click();
		firstName.clear();
		firstName.sendKeys("Darweesh");
		Thread.sleep(300);

		WebElement lastName = driver.findElement(By.name("lastname"));
		lastName.click();
		lastName.clear();
		lastName.sendKeys("Qaimari");
		Thread.sleep(300);

		WebElement StreetAddress0 = driver.findElement(By.name("street[0]"));
		StreetAddress0.click();
		StreetAddress0.clear();
		StreetAddress0.sendKeys("Wadi El Joz, Emro Al Qays st., ");
		Thread.sleep(300);

		WebElement StreetAddress1 = driver.findElement(By.name("street[1]"));
		StreetAddress1.click();
		StreetAddress1.clear();
		StreetAddress1.sendKeys("House # 61");
		Thread.sleep(300);

		WebElement City = driver.findElement(By.name("city"));
		City.click();
		City.clear();
		City.sendKeys("Jerusalem");
		Thread.sleep(300);

		WebElement postCode = driver.findElement(By.name("postcode"));
		postCode.click();
		postCode.clear();
		postCode.sendKeys("20777");
		Thread.sleep(300);

		WebElement phone = driver.findElement(By.name("telephone"));
		phone.click();
		phone.clear();
		phone.sendKeys("A0525768719");

		WebElement submitDetails = driver.findElement(By.xpath("//span[@data-bind=\"i18n: 'Next'\"]"));
		submitDetails.click();
		LoadingTime.loadingTime();

	}
}


