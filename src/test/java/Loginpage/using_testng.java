package Loginpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class using_testng {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
 
	@BeforeMethod
	public void setup() throws Exception
	{ 
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_3.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Login_using_testNG");
		extent.attachReporter(spark);
		
		test=extent.createTest("Login_Test_TestNG_approach");
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signin");
	}
	@Test 
	public void inputcredentials()
	{
		try {
			driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
			driver.findElement(By.id("password")).sendKeys("goco@123");
			driver.findElement(By.id("remember-me")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			System.out.println("Logged in successfully");
			test.pass("Logged in successfully");
		}
		catch(Exception e){
			System.out.println("Login failed");
			test.fail("Login failed");
		}
	}
	@AfterMethod
	public void result()
	{
		extent.flush();
		driver.quit();
	}
}
