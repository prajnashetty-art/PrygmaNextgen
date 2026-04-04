package Dashboard_section;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class my_purchases {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	ExtentSparkReporter spark;
	
	@BeforeClass
	public void reportsetup() throws InterruptedException
	{
		String projectpath=System.getProperty("user.dir");
		spark=new ExtentSparkReporter(projectpath+"/Reports/Report_18.html");
		extent=new ExtentReports();
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("My Purchases");
		extent.attachReporter(spark);
	    
	}
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.navigate().to("https://www.prygmanextgen.com/signin");
		driver.findElement(By.id("email")).sendKeys("gocoyx121@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("Goco@123");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
	}
	@Test(priority=1)
	public void elementvisibility() throws InterruptedException
	{
		test=extent.createTest("My Purchases-section visibility");
		driver.findElement(By.linkText("My Purchases")).click();
		Thread.sleep(2000);
		try {
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'My Purchases')]")).isDisplayed());
		System.out.println("My purchases section is visible");
		test.pass("My purchases section is visible");
		}catch(AssertionError e)
		{
			System.out.println("My purchases section is not visible");
			test.fail("My purchases section is not visible");
			throw e;
		}
	}
	@Test(priority=2)
	public void verifytitle() throws InterruptedException
	{
		test=extent.createTest("Verify Title of My purchases page");
		driver.findElement(By.linkText("My Purchases")).click();
		String actualtitle=driver.getTitle();
		try {
		Assert.assertTrue(actualtitle.contains("My purchases"));
		System.out.println("Page title is correct");
		test.pass("Page title is correct");
		}catch(AssertionError e) {
		System.out.println("Page title is incorrect");
		test.fail("Page title is incorrect");
		throw e;
		}
	}
	@Test(priority=3)
	public void scrolltillbottom() throws InterruptedException
	{
		test=extent.createTest("My Purchases-Scrolling functionality");
		driver.findElement(By.linkText("My Purchases")).click();
		Thread.sleep(5000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		System.out.println("Scrolled my purchase page from top to bottom");
		System.out.println("Scrolling successful");
		test.pass("Scrolling successful");
		}catch (Exception e)
		{
			System.out.println("Scrolling failed");
			test.fail("Scrolling failed");
		}
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	@AfterClass
	public void reportgenerate()
	{
		extent.flush();
	}
	
}