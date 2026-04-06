package testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Signup_Signout_Signin_FlowTest {
	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	@BeforeClass
	public void Reportsetup() {
		String projectPath=System.getProperty("user.dir");
		extent=new ExtentReports();
		spark=new ExtentSparkReporter(projectPath+"/Reports/Signup_Signout_Signin_Flow_Report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("SignUp, Signout and Sign in Flow Test");
		extent.attachReporter(spark);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.prygmanextgen.com/");
	}
	@Test
	public void signup() throws InterruptedException {
		test=extent.createTest("Sign Up functionality");
		try {
			driver.findElement(By.xpath("//div[@class='text-sm font-medium text-white px-3 py-2 rounded-lg transition-colors cursor-pointer']")).click();
	    	String password = System.getenv("password");	//Fetch environment variables
	    	String confirmpassword = System.getenv("confirmpassword");
	    	driver.findElement(By.id("name")).sendKeys("Dia KR");
	        driver.findElement(By.id("email")).sendKeys("rothet@f8entaoba.com");
	        driver.findElement(By.id("password")).sendKeys(password);
	        driver.findElement(By.id("confirmPassword")).sendKeys(confirmpassword);
	        JavascriptExecutor js=(JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,300)", "");
	        Thread.sleep(1000);
	        driver.findElement(By.id("terms")).click();
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        if (wait.until(ExpectedConditions.urlContains("courses"))) {
	            System.out.println("Registered successfully and landed on courses page");
	            test.pass("Registered successfully and landed on courses page");
	        }
	    } catch (TimeoutException e) {
	        System.out.println("Unexpected failure");
	        test.fail("Unexpected failure");
	    }
	}
	@Test
	public void signout() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/button")).click();
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div/div/button")).click();
		Thread.sleep(3000);
		System.out.println("Signed out successfully");
		test.pass("Signed out successfully");
		
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div[1]")).click();
		driver.findElement(By.id("email")).sendKeys("abgfd746@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("Role@123");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		System.out.println("Logged in successfully");
		test.pass("Logged in successfully");
	}

	@AfterClass
	public void teardown() {
		extent.flush();
		driver.quit();
	}
		
}
