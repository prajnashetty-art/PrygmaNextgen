package footer_section;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Navigation_footer_section {

		WebDriver driver;
		ExtentReports extent;
		ExtentSparkReporter spark;
		WebDriverWait wait;
		ExtentTest test;
	@BeforeClass
	public void reportsetup() throws InterruptedException
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		spark=new ExtentSparkReporter(projectpath+"/Reports/Report_20.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Naviagation to footer sections");
		extent.attachReporter(spark);
	}
	@BeforeMethod
	public void webdriversetup() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("https://www.prygmanextgen.com/");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement footer = driver.findElement(By.tagName("footer"));
		js.executeScript("arguments[0].scrollIntoView();", footer);
		Thread.sleep(5000);
	}
	@Test(priority=1)
	public void aboutusnavigation() throws InterruptedException
	{
		test=extent.createTest("Navigation to about us page");
		try {
			Thread.sleep(2000);
			driver.findElement(By.linkText("About Us")).click();
			String actualurl=driver.getCurrentUrl();
			Assert.assertTrue(actualurl.contains("about"));
			System.out.println("Navigated to about us page");
			test.pass("Navigated to about us page");
		}catch(AssertionError e) {
			System.out.println("Failed to Navigate to about us page");
			test.fail("Failed to Navigate to about us page");
			throw e;
		}
	}
	@Test(priority=2)
	public void contactusnavigation()
	{
		test=extent.createTest("Navigation to contact us page");
		try {
			driver.findElement(By.linkText("Contact us")).click();
			String actualurl=driver.getCurrentUrl();
			Assert.assertTrue(actualurl.contains("contact"));
			System.out.println("Navigated to Contact us page");
			test.pass("Navigated to Contact us page");
		}catch(AssertionError e) {
			System.out.println("Failed to Navigate to Contact us page");
			test.fail("Failed to Navigate to Contact us page");
			throw e;
		}
	}
	@Test(priority=3)
	public void Blogpagenavigation()
	{
		test=extent.createTest("Navigation to Blog page");
		try {
			driver.findElement(By.linkText("Blog")).click();
			String actualurl=driver.getCurrentUrl();
			Assert.assertTrue(actualurl.contains("blog"));
			System.out.println("Navigated to Blog page");
			test.pass("Navigated to Blog page");
		}catch(AssertionError e) {
			System.out.println("Failed to Navigate to Blog page");
			test.fail("Failed to Navigate to Blog page");
			throw e;
		}
	}
	@Test(priority=4)
	public void helpandsupportnavigation()
	{
		test=extent.createTest("Navigation to Help & Support page");
		try {
			driver.findElement(By.linkText("Help & Support")).click();
			String actualurl=driver.getCurrentUrl();
			Assert.assertTrue(actualurl.contains("support"));
			System.out.println("Navigated to Help & Support page");
			test.pass("Navigated to Help & Support page");
		}catch(AssertionError e) {
			System.out.println("Failed to Navigate to Help & Support page");
			test.fail("Failed to Navigate to Help & Support page");
			throw e;
		}
	}
	@Test(priority=5)
	public void privacypolicynavigation()
	{
		test=extent.createTest("Navigation to Privacy Policy page");
		try {
			driver.findElement(By.linkText("Privacy Policy")).click();
			String actualurl=driver.getCurrentUrl();
			Assert.assertTrue(actualurl.contains("privacy"));
			System.out.println("Navigated to Privacy Policy page");
			test.pass("Navigated to Privacy Policy page");
		}catch(AssertionError e) {
			System.out.println("Failed to Navigate to Privacy Policy age");
			test.fail("Failed to Navigate to Privacy Policy page");
			throw e;
		}
	}
	@Test(priority=6)
	public void termsnavigation()
	{
		test=extent.createTest("Navigation to Terms page");
		try {
			driver.findElement(By.linkText("Terms")).click();
			String actualurl=driver.getCurrentUrl();
			Assert.assertTrue(actualurl.contains("terms"));
			System.out.println("Navigated to Terms page");
			test.pass("Navigated to Privacy Policy page");
		}catch(AssertionError e) {
			System.out.println("Failed to Navigate to Terms page");
			test.fail("Failed to Navigate to Terms page");
			throw e;
		}
	}
		
	@AfterMethod
	public void exit()
	{
		driver.quit();
	}
	@AfterClass
	public void generatereport()
	{
		extent.flush();
	}

}
