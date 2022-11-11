package core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoadingTime {

	static WebDriver driver;

	public LoadingTime(WebDriver driver) {
		this.driver = driver;
	}
	public static void loadingTime() throws InterruptedException {
		System.out.println("---------------------------------------------");
		long s = System.currentTimeMillis();
		try{while((driver.getTitle().length()==0)) {

		}}catch(Exception e){System.out.println("Couldn't catch the title");}
		System.out.println("Page title is: "+ driver.getTitle());
		long n = System.currentTimeMillis();
		long b = n-s;
		System.out.println("the loading time for the title is: "+b+" mSeconds");
		try {
			List<WebElement> load = driver.findElements(By.xpath("//img"));
			int size = load.size()-1;
			System.out.println("The Number of images loaded : "+size);
			int i=0;

			while((i!=size)) {
				if(load.get(i).isEnabled()==true) {
					i++;
				}	
			}
		}catch(Exception e) {}
		try {
			List<WebElement> load = driver.findElements(By.xpath("//div"));
			int size = load.size()-1;
			System.out.println("The Number of divissions loaded : "+size);
			int i=0;

			while((i!=size)) {
				if(load.get(i).isEnabled()==true) {
					i++;
				}	
			}
		}catch(Exception e) {}
		try {
			List<WebElement> load = driver.findElements(By.xpath("//script"));
			
			int size = load.size()-1;
			System.out.println("The Number of scripts loaded : "+size);
			int i=0;

			while((i!=size)) {
				if(load.get(i).isEnabled()==true) {
					i++;
				}	
			}
			long e = System.currentTimeMillis();
			long f = e-s;
			System.out.println("the loading time for the page is: "+f+ " mSeconds");
		}catch(Exception e) {}

	}
}
	




