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
		try {
			List<WebElement> load = driver.findElements(By.xpath("//a"));
			int size = (load.size())-1;
			int i=0;

			while((i!=size)) {
				if(load.get(i).isEnabled()==true) {
					i++;
				}	
			}
		}catch(Exception e) {}
		try {
			List<WebElement> load = driver.findElements(By.xpath("//script"));
			
			int size = (load.size())-1;
			System.out.println("The Number of scripts loaded : "+size);
			int i=0;

			while((i!=size)) {
				if(load.get(i).isEnabled()==true) {
					i++;
				}	
			}
			long v = System.currentTimeMillis();
			long x = v-s;
			System.out.println("the loading time for the page is: "+x+ " mSeconds");
		}catch(Exception e) {}

	}
}
	




