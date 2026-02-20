package Signup_page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Crossbrowsers {

		WebDriver driver;
		ExtentReports extent;
		ExtentTest test;
		@BeforeMethod
		public void report()
		{
			String projectpath=System.getProperty("user.dir");
			extent=new ExtentReports();
			ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_7.html");
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Signup_crossbrowsing_approach");
			extent.attachReporter(spark);
		}
		@Test
		@Parameters("browsers")
		
		public void registerpage(String browsername) throws InterruptedException
		{
			try {
				test=extent.createTest("Signup_crossbrowser_approach");
				if(browsername.equalsIgnoreCase("edge"))
				{
					 driver=new EdgeDriver();
				}
				else if(browsername.equalsIgnoreCase("chrome"))
				{
					driver=new ChromeDriver();
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				driver.get("https://www.prygmanextgen.com/");
				driver.navigate().to("https://www.prygmanextgen.com/signup");
				driver.findElement(By.id("name")).sendKeys("Dia K");
				driver.findElement(By.id("email")).sendKeys("rolghl805@fentaoba.com");
				driver.findElement(By.id("password")).sendKeys("Role@123");
				driver.findElement(By.id("confirmPassword")).sendKeys("Role@123");
				Thread.sleep(2000);
				
				JavascriptExecutor js=(JavascriptExecutor)driver;
				WebElement element=driver.findElement(By.xpath("//button[@type='submit']"));
				js.executeScript("arguments[0].scrollIntoView();", element);
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//input[@id='terms']")).click();
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				System.out.println("Registered successfully");
				test.pass("Registered successfully");
				
			}
			catch(Exception e)
			{
				System.out.println("Sign_up unsuccessful");
				test.fail("Sign_up unsuccessful");
			}
		}
			@AfterMethod
			public void output()
			{
				driver.quit();
				extent.flush();
			}
}
