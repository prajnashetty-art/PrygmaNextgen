package Dashboard_section;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class dashboard {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	ExtentSparkReporter spark;
	
	@BeforeClass
	public void reportsetup()
	{
		String projectpath=System.getProperty("user.dir");
		spark=new ExtentSparkReporter(projectpath+"/Reports/Report_17.html");
		extent=new ExtentReports();
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Dashbaord_functionality");
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
		System.out.println("Login successful");
		Thread.sleep(5000);
	}
	@Test
	public void verifyurl()
	{
		test=extent.createTest("Verify dashboard URL");
		String expectedurl="https://www.prygmanextgen.com/dashboard";
		String actualurl=driver.getCurrentUrl();
		try {
		Assert.assertEquals(actualurl, expectedurl);
		System.out.println("URL is matching");
		test.pass("URL is matching");
		}catch(AssertionError e) {
		System.out.println("Actual URL is not matching with the expected URL");
		test.fail("Actual URL is not matching with the expected URL");
		throw e;
		}
	}
	@Test
	public void verifytitle()
	{
		test=extent.createTest("Verify dashboard title");
		String actualtitle=driver.getTitle();
		try {
		Assert.assertTrue(actualtitle.contains("My Courses"));
		System.out.println("Title is correct");
		test.pass("Title is correct");
		}catch (AssertionError e) {
		System.out.println("Actual title is not matching with the expected title");
		test.fail("Actual title is not matching with the expected title");
		throw e;
		}
	}
	@Test
	public void tomypurchases()
	{
		test=extent.createTest("Verify Navigation to My Purchases");
		driver.findElement(By.linkText("My Purchases")).click();
		String exppurchaseurl="https://www.prygmanextgen.com/dashboard/purchases";
		String actpurchurl=driver.getCurrentUrl();
		try {
			Assert.assertEquals(actpurchurl, exppurchaseurl);
			System.out.println("Navigation to my purchases page is successful");
			test.pass("Navigation to my purchases page is successful");
		}catch (AssertionError e) {
			System.out.println("Navigation to my purchases page failed");
			test.fail("Navigation to my purchases page failed");
			throw e;
		}
		
	}
	@Test
	public void toprofilesettings()
	{
		test=extent.createTest("Verify naviagtion to profile settings");
		driver.findElement(By.linkText("Profile Settings")).click();
		String expectedprofileurl="https://www.prygmanextgen.com/dashboard/profile";
		String actualprofileurl=driver.getCurrentUrl();
		try {
			Assert.assertEquals(actualprofileurl, expectedprofileurl);
			test.pass("Navigation to profile settings page is successful");
		}catch (AssertionError e){
			System.out.println("Navigation to profile settings page failed");
			test.fail("Navigation to profile settings page failed");
			throw e;
		}
	}
	@Test
	public void tohelpcenter()
	{
		test=extent.createTest("Verify naviagtion to Help Center");
		driver.findElement(By.linkText("Help Center")).click();
		String expectedhelpurl="https://www.prygmanextgen.com/help";
		String actualhelpurl=driver.getCurrentUrl();
		try {
			Assert.assertEquals(actualhelpurl, expectedhelpurl);
			System.out.println("Navigation to Help center page is successful");
			test.pass("Navigation to Help center page is successful");
		}catch (AssertionError e){
			System.out.println("Navigation to Help center page failed");
			test.fail("Navigation to Help center page failed");
			throw e;
		}
	}
	@Test
	public void logout() throws InterruptedException
	{
		test=extent.createTest("Verify sign out link");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[2]/div[1]/div[1]/div[1]/nav[1]/div[1]/button[1]")).click();
		Thread.sleep(2000);
		String expurl="https://www.prygmanextgen.com/";
		String acturl=driver.getCurrentUrl();
		try{
			Assert.assertEquals(acturl, expurl);
		    System.out.println("Naviagated back to homepage after logout");
		    test.pass("Naviagated back to homepage after logout");
		}catch(AssertionError e)
		{
			System.out.println("Failed to naviagte back to home page after logout");
			test.fail("Failed to naviagte back to home page after logout");
			throw e;
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
