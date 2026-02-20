package Courses;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Normal_approach {

	public static void main(String[] args) {
		
		String projectpath=System.getProperty("user.dir");
		ExtentReports extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_10.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Signup_signout_signin");
		extent.attachReporter(spark);
		ExtentTest test=extent.createTest("Signup_signout_signin");
		
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		try {
			driver.get("https://www.prygmanextgen.com/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div[2]")).click();
			driver.findElement(By.id("name")).sendKeys("Rial S");
			driver.findElement(By.id("email")).sendKeys("abgfd746@fentaoba.com");
			driver.findElement(By.id("password")).sendKeys("Role@123");
			driver.findElement(By.id("confirmPassword")).sendKeys("Role@123");
			Thread.sleep(2000);
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement element=driver.findElement(By.xpath("//button[@type='submit']"));
			js.executeScript("arguments[0].scrollIntoView();", element);
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//input[@id='terms']")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(15000);
			System.out.println("Registered successfully");
			test.pass("Registered successfully");
			
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
			
			driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[1]")).click();
			Thread.sleep(2000);
			System.out.println("Landed on courses page");
			test.pass("Landed on courses page");
			
		}
		catch (Exception e)	{
			System.out.println("Navigation_failed");
			test.fail("Navigation_failed");
		}
		finally {
		extent.flush();
		driver.quit();
		}
}}
