package com.collections.testMethods;
import java.awt.Label;
import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.collections.pageObjects.HomePage;
import com.collections.pageObjects.RegisterPage;
import com.collections.pageObjects.SearchProducts;
import com.collections.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TC02_CollectionCheckoutTest extends TestBase {

	SearchProducts searchPage;
	HomePage homePage;
	RegisterPage registerPage;

	// SerpPage serpPage;
	public TC02_CollectionCheckoutTest() {

		super();
	}

	@DataProvider(name = "createaccount")
	public String[][] getSearchData() {
		String[][] testdata = getData("TestData.xlsx", "register");
		return testdata;
	}

	@DataProvider(name = "checkoutdata")
	public String[][] checkoutdata() {
		String[][] testdata = getData("TestData.xlsx", "checkout");
		return testdata;
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		
		extent.flush();
		extent.endTest(test);

	}
	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}

	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		extent.addSystemInfo("Target Browser Name", prop.getProperty("browserType"));
		extent.addSystemInfo("Category", prop.getProperty("Category"));
		test.log(LogStatus.INFO, result.getName() + "test Started");
	}

	@BeforeClass
	public void setUp() throws InterruptedException {
		try {
			initialize();
			homePage = new HomePage(driver);
			registerPage = new RegisterPage(driver);
			searchPage = new SearchProducts(driver);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test(enabled = true, priority = 0, dataProvider="createaccount")
	public void TS01_VerifyRegistration(String email, String fname, String lname, String add1, String add2, String city, 
			String state, String zip, String phone, String pass, String confpass, String gender, String month) throws InterruptedException {

		try {
			test.log(LogStatus.PASS, "***************TC01_Verify Regostratoin Test****************");

			test.log(LogStatus.PASS, "Click on My Account link");
			homePage.clickonMyAccount();
			test.log(LogStatus.PASS, "Enter the Email: " + email);
			registerPage.enterEmail(email);;
			test.log(LogStatus.PASS, "Click on Register button");
			registerPage.clickRegister();
			test.log(LogStatus.PASS, "Enter the Register data");
			registerPage.registration(fname,lname,add1,add2,city,state,zip,phone,pass,confpass,gender,month);
			waitForPageToLoad(20);

		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to validate login page without password Credentials");

		}

	}
	@Test(enabled=true, priority=1, dataProvider="checkoutdata")
	public void checkoutProduct(String cardnum, String expdate, String cvv) throws InterruptedException {
		Thread.sleep(4000);
		test.log(LogStatus.PASS, "***************TC02_checkout product****************");
		try {
		test.log(LogStatus.PASS, "Enter the product in the search box with Table");
		searchPage.enterSearch("table");
		test.log(LogStatus.PASS, "Select the Product from list of Products");
	    searchPage.selectItem();
	    test.log(LogStatus.PASS, "Add More items");
		searchPage.addMoreItem();
		test.log(LogStatus.PASS, "Choose Runner size");
		searchPage.chooseoption();
		test.log(LogStatus.PASS, "Click on Add to Card");
		searchPage.addtoCart();
		test.log(LogStatus.PASS, "Click on Continue shopping button");
		searchPage.Continueshopbtn();
		test.log(LogStatus.PASS, "click on Cart Icon");
		searchPage.cartbtn();
		waitForPageToLoad(20);
		test.log(LogStatus.PASS, "Click on Secure checkout button");
		searchPage.securecheckout();
		waitForPageToLoad(20);
		test.log(LogStatus.PASS, "Enter the Card Details "+ cardnum);
		searchPage.cardnum(cardnum);
		waitForPageToLoad(20);
		test.log(LogStatus.PASS, "Enter the Card Expiry date "+ expdate);
		searchPage.cardexp(expdate);
		waitForPageToLoad(20);
		test.log(LogStatus.PASS, "Enter the CVV code "+ cvv);
		searchPage.cardCode(cvv);
		waitForPageToLoad(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
