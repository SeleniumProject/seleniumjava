package com.collections.pageObjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;

import com.collections.testBase.TestBase;

public class DesktopRequestPage {

	WebDriver driver;

	public DesktopRequestPage(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	// Objects for Person equipment is for

	By equipmentIsFor = By.xpath("//*[contains(text(),'equipment is for:')]//following::div[3]/input");

	public void enterPersonEquipmentIsFor(String enterEquipmentIsFor) {

		driver.findElement(equipmentIsFor).sendKeys(enterEquipmentIsFor);
	}

	// Objects for Additional users in the comment box is for

	By commentsBox = By.xpath("//*[contains(text(),'comments box below:')]//following::div[3]/textarea");

	public void enterAdditionalComments(String additionalComments) {

		driver.findElement(commentsBox).sendKeys(additionalComments);
	}

	// Objects for Location Of Equipment

	By location = By.xpath("//*[contains(text(),'Location of ')]//following::div[3]/input");

	public void enterLocationOfEquipment(String enterLocationOfEquipment) {

		driver.findElement(location).sendKeys(enterLocationOfEquipment);
	}
	// Objects for Replace an existing Computer

	By existingComputer = By.xpath("//*[contains(text(),'existing computer')]//following::div[3]/input");

	public void enterReplaceAnExistingComputer(String replaceExistingComputer) throws InterruptedException {

		driver.findElement(existingComputer).sendKeys(replaceExistingComputer);
		Thread.sleep(2000);
	}
	// Objects for Charge Code : Agresso Budget Code

	By agressoBudgetCode = By.xpath("//*[contains(text(),'Budget Code')]//following::div[3]/input");

	public void enterBudgetCode(String budgetCode) {

		driver.findElement(agressoBudgetCode).sendKeys(budgetCode);
	}
	// Objects for Charge Code : Authority to charge to this computer

	By authority = By.xpath("//*[contains(text(),'Budget Code')]//following::div[3]/input");

	public void enterAuthorityChargeCode(String authorityCode) {

		driver.findElement(authority).sendKeys(authorityCode);
	}

	// Objects for Charge Code : Any Additional comments
	By comments = By.xpath("//*[contains(text(),'Additional Comments')]//following::div[3]/textarea");

	public void enterAdditionalComment(String additionalComments) {

		driver.findElement(comments).sendKeys(additionalComments);
	}

	// Objects for Checkbox
	By checkBox = By.xpath("//input[@type='checkbox']");

	public void selectItemCheckBox() throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		List<WebElement> ele = driver.findElements(checkBox);
		executor.executeScript("arguments[0].click();",ele.get(0));
		
		Thread.sleep(2500);

        WebElement addIcon = driver.findElement(By.xpath("//*[@class='x-form-spinner x-form-spinner-default x-form-spinner-up x-form-spinner-up-default ']"));
		Actions action = new Actions(driver);
		action.moveToElement(addIcon).build().perform();
		action.click().build().perform();
	//executor.executeScript("arguments[0].click();", addIcon);
		//JavascriptExecutor executor = (JavascriptExecutor) driver;

		//WebElement element = driver.findElement(allRequestsContainer);

		//executor.executeScript("arguments[0].click();", element);

	}

	// Objects for Capture Item Price
	By price = By.xpath("//*[@data-ref='boxLabelEl']");

	public String getPriceLabel() {
		List<WebElement> ele = driver.findElements(price);
		return ele.get(0).getText();

	}

	// Objects for Validation Price
	By validatePrice = By.xpath("//*[@class='prices']/div");

	public String getTotalPriceOfItem() {
		return driver.findElement(validatePrice).getText();

	}

}
