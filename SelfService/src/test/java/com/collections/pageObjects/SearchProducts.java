package com.collections.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SearchProducts {
	WebDriver driver;

	public SearchProducts(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

 By searchtxt = By.xpath("//*[@id='header-search-box']");
 By searchbtn = By.id("desktop-submit-button");
 By products = By.xpath("//*[@id='products']/div/div/div/a");
 By plusbtn = By.xpath("//*[@id='product']/div[2]/div[2]/div[5]/div/div/div[1]/div/div/button[2]");
 By chooseoption = By.xpath("//*[@id='product']/div[2]/div[2]/div[3]/div[2]/ul/li/div/button");
 By addocart = By.xpath("//*[@id='product']/div[2]/div[2]/div[5]/div/div/div[2]");
 By continueshopbtn = By.xpath("//*[@id='pdp-modal']/div/div/div[2]/button");
 By CartOption = By.xpath("//*[@id='container-icons']/div/div/ul/li[3]/a");
 By securecheckout = By.id("secure-checkout");
 By cardnumtxt = By.id("card-number");
 By cardexp = By.id("card-expiration");
 By cardCode = By.id("card-code");
 
 
 public void enterSearch(String search) throws InterruptedException {
//	 Thread.sleep(2500);
//	 driver.navigate().refresh();
	 driver.findElement(searchtxt).sendKeys(search);
	 driver.findElement(searchbtn).click();
	
 }
 
 
 public void selectItem() throws InterruptedException {
	 
	 Thread.sleep(3500);
	 List<WebElement> ls = driver.findElements(products);
	 Thread.sleep(100);
	 ls.get(1).click();
 }

 public void chooseoption() throws InterruptedException {
	 Thread.sleep(4000);
	 List<WebElement> ls = driver.findElements(chooseoption);
	 Thread.sleep(1500);
	 ls.get(0).click();
 }

 public void addMoreItem() throws InterruptedException {
	 Thread.sleep(1500);
	 driver.findElement(plusbtn).click();
 }
 
 public void addtoCart() throws InterruptedException {
	 Thread.sleep(1000);
	 driver.findElement(addocart).click();
 }
 
 public void Continueshopbtn() throws InterruptedException {
	 Thread.sleep(2500);
	 driver.findElement(continueshopbtn).click();
 }
 
 public void cartbtn() throws InterruptedException {
	 Thread.sleep(1000);
	 driver.findElement(CartOption).click();
 }
 public void securecheckout() throws InterruptedException {
	 Thread.sleep(2500);
	 driver.findElement(securecheckout).click();
 }
 
 public void cardnum(String card) throws InterruptedException {
	 Thread.sleep(1500);
	 driver.findElement(cardnumtxt).click();
	 driver.findElement(cardnumtxt).sendKeys(card);
}
 
 public void cardexp(String exp) {
	 try {
		Thread.sleep(1500);
		driver.findElement(cardexp).click();
		driver.findElement(cardexp).sendKeys(exp);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}
 public void cardCode(String code) {
	 driver.findElement(cardCode).click();
	 driver.findElement(cardCode).sendKeys(code);
}
}
