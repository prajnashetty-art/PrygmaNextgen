package Loginpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Cross_browsing {
  
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
 
	@BeforeMethod
	public void attachreport()
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_2.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Login_crossbrowsing_approach");
		extent.attachReporter(spark);
	}
	@Test
	@Parameters("browsers")
	
	public void crossbrowsers(String browsername)
	{
		test=extent.createTest("Login_Test_crossbrowsing approach");
		   
		if(browsername.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
			{
			driver=new EdgeDriver();
			}
		try{
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.get("https://www.prygmanextgen.com/");
			driver.navigate().to("https://www.prygmanextgen.com/signin");
			driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
			driver.findElement(By.id("password")).sendKeys("Goco@123");
			driver.findElement(By.id("remember-me")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			System.out.println("Logged in successfully");
			test.pass("Logged in successfully");
			}
		catch(Exception e){
			System.out.println("Login failed");
			test.fail("Login failed");
		}
		finally{
		driver.quit();
		}
	}
		@AfterMethod
		public void generatereport()
		{
			extent.flush();
		}
}
		
			
				
		

		 