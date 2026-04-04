package Signup_page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Normal_approach {

	public static void main(String[] args) throws Exception {
<<<<<<< HEAD
		// TODO Auto-generated method stub

		ExtentReports extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter("Report_4.html");
=======
		
		String projectpath=System.getProperty("user.dir");
		ExtentReports extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_6.html");
>>>>>>> temp-branch
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Signp_normal_approach");
		extent.attachReporter(spark);
		
		ExtentTest test=extent.createTest("Signp_normal_approach");
		ChromeDriver driver=new ChromeDriver();
<<<<<<< HEAD
		try
		{
=======
		try{
>>>>>>> temp-branch
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.get("https://www.prygmanextgen.com/");
			driver.navigate().to("https://www.prygmanextgen.com/signup");
			driver.findElement(By.id("name")).sendKeys("Dia K");
<<<<<<< HEAD
			driver.findElement(By.id("email")).sendKeys("rolelef805@fentaoba.com");
=======
			driver.findElement(By.id("email")).sendKeys("roleg805@fentaoba.com");
>>>>>>> temp-branch
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
<<<<<<< HEAD
		catch(Exception e)
		{
=======
		catch(Exception e){
>>>>>>> temp-branch
			System.out.println("Sign_up unsuccessful");
			test.fail("Sign_up unsuccessful");
		}
		finally {
			driver.quit();
			extent.flush();
		}
<<<<<<< HEAD
		}
=======
	}
>>>>>>> temp-branch
}
