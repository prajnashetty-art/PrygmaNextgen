package Courses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class dataprovider {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	@BeforeClass
	public void setup()
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_11.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Signup_signout_signin");
		extent.attachReporter(spark);
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@Test(dataProvider="insertdata")
	public void register(String name, String email, String password, String confirmpassword) throws InterruptedException
	{
		test=extent.createTest("Signup_signout_signin");
		driver.get("https://www.prygmanextgen.com/");
		
		//sign up
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div[2]")).click();
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmPassword")).sendKeys(confirmpassword);
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
		
		//sign out
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/button")).click();
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div/div/button")).click();
		Thread.sleep(3000);
		System.out.println("Signed out successfully");
		test.pass("Signed out successfully");
		
		//signin
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div[1]")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		System.out.println("Logged in successfully");
		test.pass("Logged in successfully");
		
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[1]")).click();
		Thread.sleep(2000);
		System.out.println("Landed on courses page");
		test.pass("Landed on courses page");
		driver.quit();
	}
	@DataProvider(name="insertdata")
	public Object[][] passdata()
	{
		Object [][] data= new Object[2][4];
		data[0][0]="Raha";
		data[0][1]="wotosm456@fentaoba.com";
		data[0][2]="Raha@123";
		data[0][3]="Raha@123";
		
		data[1][0]="ahan";
		data[1][1]="wipxe8136@iaciu.com";
		data[1][2]="Ahan@123";
		data[1][3]="Ahan@123";
		return data;
	}
	@AfterClass
	public void tearDown() throws Exception
	{
		//driver.quit();
		extent.flush();
	}
}
