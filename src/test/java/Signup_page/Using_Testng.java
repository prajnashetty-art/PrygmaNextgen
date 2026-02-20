package Signup_page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Using_Testng {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeMethod
	public void setup() 
	{   String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_9.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Signup_using_TestNG");
		extent.attachReporter(spark);
		
		test=extent.createTest("Signup_testNG_approach");
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signup");
	}
	@Test 
	public void inputdata() throws InterruptedException
	{
		try {
			driver.findElement(By.id("name")).sendKeys("Dia K");
			driver.findElement(By.id("email")).sendKeys("rolgh04@fentaoba.com");
			driver.findElement(By.id("password")).sendKeys("Role@123");
			driver.findElement(By.id("confirmPassword")).sendKeys("Role@123000");
			//Thread.sleep(2000);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement element=driver.findElement(By.xpath("//button[@type='submit']"));
			js.executeScript("arguments[0].scrollIntoView();", element);
		//	Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='terms']")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			System.out.println("Registered successfully");
			test.pass("Registered successfully");
		}
		catch (Exception e)
		{
			System.out.println("Signup unsuccesssful");
			test.fail("Signup unsuccesssful");
		}
	}
	@AfterMethod
	public void result()
	{
		driver.quit();
		extent.flush();
	}
}
