package com.collections.pageObjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	By emailtxt = By.id("BaseLoginViewModel_Email");
	By registerbtn = By.id("register-btn");
	By emailtxtbox = By.id("Address_Email");
	By fnametext = By.id("Address_FirstName");
	By addresstxt = By.id("Address_Line1");
	By lastnametxt = By.id("Address_LastName");
	By addres2txt = By.id("Address_Line2");
	By citytxt = By.id("Address_City");
	By statetxt = By.id("Address_State");
	By pcodetxt = By.id("Address_PostalCode");
	By phonetxt = By.id("Address_PhoneNumber");
	By passwordtxt = By.id("Password");
	By confpwdtxt = By.id("PasswordConfirm");
	By gendertxt = By.id("Gender");
	By bmonthtxt = By.id("BirthMonth");
	By submit = By.id("registration-submit");
	By keepOriginal = By.xpath("//*[@id=\"address-validation-modal\"]/div/div/div[4]/div/div/button[1]");
	
	public void enterEmail(String email) {
		
		driver.findElement(emailtxt).sendKeys(email);
	}
	
	public void clickRegister() {
		driver.findElement(registerbtn).click();
		refresh();
	}
	public void refresh() {
		driver.navigate().refresh();
	}
	public void registration(String fname, String lname, String add1, String add2, String city, String state, String zip , String phone, String pwd, String confpwd, String gender, String month) throws InterruptedException {
		refresh();
		String email = randomEmail();
		System.out.println(email);
		driver.findElement(emailtxtbox).clear();
		driver.findElement(emailtxtbox).sendKeys(email);
		driver.findElement(fnametext).sendKeys(fname);
		driver.findElement(lastnametxt).sendKeys(lname);
		driver.findElement(addresstxt).sendKeys(add1);
		driver.findElement(addres2txt).sendKeys(add2);
		driver.findElement(citytxt).sendKeys(city);
		getStateDropDown(state);
		driver.findElement(pcodetxt).sendKeys(zip);
		driver.findElement(phonetxt).sendKeys(phone);
		driver.findElement(passwordtxt).sendKeys(pwd);
		driver.findElement(confpwdtxt).sendKeys(confpwd);
		getGenderDropDown(gender);
		getMonthDropDown(month);
		driver.findElement(submit).click();
		Thread.sleep(2500);
		driver.findElement(keepOriginal).click();
	}
	public String randomEmail() {
		Random rand = new Random();
	      int res = rand.nextInt(10000);
		String email = "test" +res+"@gmail.com";
		return email;
	}
	
	public void getStateDropDown(String state) {
		Select select = new Select(driver.findElement(statetxt));
		select.selectByVisibleText(state);
	}
	
	public void getGenderDropDown(String gender) {
		Select select = new Select(driver.findElement(gendertxt));
		select.selectByVisibleText(gender);
	}

	public void getMonthDropDown(String month) {
		Select select = new Select(driver.findElement(bmonthtxt));
		select.selectByVisibleText(month);
	}


}
