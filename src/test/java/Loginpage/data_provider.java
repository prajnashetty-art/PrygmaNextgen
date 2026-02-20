package Loginpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class data_provider {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void setup()
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_4.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Signin_using_dataprovider");
		extent.attachReporter(spark);
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
	}
	@Test(dataProvider="Automate_data")
	public void inputdata(String email, String password)
	{
		test=extent.createTest("Signin_using_dataprovider");
		driver.get("https://www.prygmanextgen.com/signin");
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Login successful");
		test.pass("Login successful");
	}
	@DataProvider(name="Automate_data")
	public Object[][] passdata()
	{
		Object[][] data=new Object[3][2];
		data[0][0]="fofik4135@fentaoba.com";
		data[0][1]="fofi@457";
		
		data[1][0]="nitih5112@iaciu.com";
		data[1][1]="niti#789";
		
		data[2][0]="tevoxe69294@fentaoba.com";
		data[2][1]="teco@699";
		return data;
	}
	@AfterClass
	public void tearDown()
	{
		extent.flush();
		driver.quit();
	}
}
