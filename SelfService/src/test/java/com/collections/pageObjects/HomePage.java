package com.collections.pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	By myaccount = By.id("my-accounts");

   public void clickonMyAccount() throws InterruptedException {
	   System.out.println("Trying to click on My Account");
	   Thread.sleep(2000);
	   WebElement element = driver.findElement(myaccount); 
	   Actions act = new Actions(driver);
	   act.moveToElement(element).build().perform();
	   act.click().perform();
	   System.out.println("finished with Trying to click on My Account");
	   
   }


}