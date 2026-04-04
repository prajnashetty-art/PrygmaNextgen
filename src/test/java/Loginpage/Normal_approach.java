package Loginpage;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Normal_approach {

	public static void main(String[] args) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		
		ExtentReports extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter("Report_1.html");
=======
		   
		String projectpath=System.getProperty("user.dir");
		ExtentReports extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_1.html");
>>>>>>> temp-branch
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Login_normal_approach");
	    extent.attachReporter(spark);
		
		ExtentTest test=extent.createTest("Login Test - Normal Approach");
<<<<<<< HEAD
		
		ChromeDriver driver=new ChromeDriver();
		try
		{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signin");
		driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("goco@123");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Logged in successfully");
		test.pass("Logged in successfully");
		}
		catch (Exception e)
		{
			System.out.println("Login failed");
			test.pass("Login failed");
		}
		finally
		{
		driver.quit();
		extent.flush();
=======
		 
		ChromeDriver driver=new ChromeDriver();
		try{
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.get("https://www.prygmanextgen.com/");
			driver.navigate().to("https://www.prygmanextgen.com/signin");
			driver.findElement(By.id("email")).sendKeys("gocox121@fentaoba.com");
			driver.findElement(By.id("password")).sendKeys("Goco@123");
			driver.findElement(By.id("remember-me")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			System.out.println("Logged in successfully");
			test.pass("Logged in successfully");
		}
		catch (Exception e) {
			System.out.println("Login failed");
			test.fail("Login failed");
		}
		finally{
			extent.flush();
			driver.quit();
>>>>>>> temp-branch
		}
	}	
}
