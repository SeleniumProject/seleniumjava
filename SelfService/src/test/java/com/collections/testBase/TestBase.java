package com.collections.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.collections.excelReaders.Excel_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	private static final Random generator = new Random();
	static final String SOURCE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
	static SecureRandom secureRnd = new SecureRandom();

	public static Properties prop;
	public static WebDriver driver;
	public static FileInputStream fis;
	public static ExtentTest test;
	public static ExtentReports extent;
	static DesiredCapabilities cap;
	Excel_Reader excel;
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/com/collections/testReports/test"
				+ formater.format(calendar.getTime()) + ".html", false);
	}

	/**
	 * This method is to determine the reading the property file.
	 * 
	 * @throws IOException
	 */
	public static void properties() throws IOException {
		prop = new Properties();
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/com/collections/configs/config.properties");
			prop.load(fis);
			System.out.println("Properties from config file " + prop);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String[][] getData(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + "/src/main/resources/testData/" + excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}

	public static void initialize() throws IOException, InterruptedException {
		properties();

		invokeBrowser(prop.getProperty("browserType"));
		driver.get(prop.getProperty("siteurl"));
		
		driver.manage().window().maximize();
	

	}

	public void Login(String username, String password) {

	}

	/**
	 * Invoke the browser for all levels.
	 * 
	 * @param browser
	 */
	public static void invokeBrowser(String browser) {

		if (System.getProperty("os.name").contains("Windows")) {
			if (browser.equalsIgnoreCase("firefox")) {

				// DesiredCapabilities cap = new DesiredCapabilities();
				// cap.setCapability("proxy", proxy);
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				String strFFBinaryPath = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
				options.setBinary(strFFBinaryPath);
				driver = new FirefoxDriver(options);

				// driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				//System.setProperty("webdriver.chrome.driver","X://chromedriver.exe");		
				ChromeOptions options = new ChromeOptions();
				//options.addArguments("--incognito");
				//options.addArguments("--start-maximized");
			    //  options.addArguments("--headless");
			   //	options.addExtensions(new File("X://extension_3_40_1_0.crx")); 
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				 driver = new ChromeDriver(capabilities);
			
				
			} else if (browser.equalsIgnoreCase("IE")) {
				// WebDriverManager.iedriver().setup();

				DesiredCapabilities cap = new DesiredCapabilities();
				 cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				 cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, true);
				 DesiredCapabilities.internetExplorer().setCapability("ignoreProtectedModeSettings", true);
				 cap.setCapability("IE.binary", "C:/Program Files (x86)/Internet Explorer/iexplore.exe");
				 cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				 cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				  cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				  cap.setCapability("requireWindowFocus", true);
				  cap.setCapability("screenResolution", "1280x1024");
				 cap.setJavascriptEnabled(true);
				 //cap.setCapability("requireWindowFocus", true);
				 cap.setCapability("enablePersistentHover", false);
				 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
				  driver = new InternetExplorerDriver(cap);
			} else {
				System.out.println("Browser Name not found");
			}

		}

	}

	public void getScreenShot(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/resources/screenshots/";
			File destFile = new File(
					(String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectDashBoardElement(String elementName) throws InterruptedException {
		Thread.sleep(5000);
		WebElement ele = driver.findElement(By.className("padding-bottom-15"));
		List<WebElement> li = ele.findElements(By.tagName("a"));
		for (WebElement webElement : li) {
			if (webElement.getText().equalsIgnoreCase(elementName)) {
				webElement.click();

				break;
			}
		}
	}

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP,
					result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/test/automation/uiAutomation/screenshot/";
			destFile = new File(
					(String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(SOURCE.charAt(secureRnd.nextInt(SOURCE.length())));
		return sb.toString();
	}

	public void acceptAlert() {
		Alert al = driver.switchTo().alert();
		al.accept();
	}

	public void dismissAlert() {
		Alert al = driver.switchTo().alert();
		al.dismiss();

	}

	/*public void waitforElementPresent(String element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(element))));
	}*/

	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
			Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");
		}
	}

	public int findBrokenLinks(By elementProperty) {
		try {
			List<WebElement> links = driver.findElements(elementProperty);

			System.out.println("Total links are " + links.size());

			for (int i = 0; i < links.size(); i++) {

				WebElement ele = links.get(i);

				String linkUrl = ele.getAttribute("href");
 System.out.println("Links are :"+linkUrl);
				URL url = new URL(linkUrl);

				HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

				httpURLConnect.setConnectTimeout(3000);

				httpURLConnect.connect();

				if (httpURLConnect.getResponseCode() == 200) {
					System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - Code :"
							+ httpURLConnect.getResponseCode());
				}
				if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
					System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
							+ HttpURLConnection.HTTP_NOT_FOUND);
				}
			}
		} catch (Exception e) {

		}
		return 200;

	}

}

/*
 * DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
 * capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
 * capabilities.setCapability(InternetExplorerDriver.
 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
 * capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
 * capabilities.setCapability("allow-blocked-content", true);
 * capabilities.setCapability("allowBlockedContent", true);
 * System.setProperty("webdriver.ie.driver", Log.gsAutomationAutoPath +
 * "IEDriverServer.exe"); WebDriver driver = new
 * InternetExplorerDriver(capabilities);
 */
