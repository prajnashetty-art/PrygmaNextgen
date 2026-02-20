package Forgot_password;

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

public class dataprovider {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void setup()
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_15.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Forgotpassword_dataProvider");
		extent.attachReporter(spark);
		
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
	}
	@Test(dataProvider="automatedata")
	public void forgotpassword(String email) throws InterruptedException
	{
		test=extent.createTest("Forgotpassword_dataProvider");
		driver.get("https://www.prygmanextgen.com/signin");
		driver.findElement(By.xpath("//a[normalize-space()='Forgot your password?']")).click();
		Thread.sleep(3000);
		String title=driver.getTitle();
		if(title.equals("Forgot Password | PRYGMA NEXTGEN"))
		{
			System.out.println("navigated to reset password window");
			test.pass("navigated to reset password window");
		}
		else
		{
			System.out.println("navigation unsuccessful");
		}
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String pagemsg=driver.findElement(By.cssSelector(".text-sm.font-medium.text-green-800")).getText();
		System.out.println(pagemsg);
		test.pass(pagemsg);
	}
	@DataProvider(name="automatedata")
	public Object[][] passdata()
	{
		Object[][] data=new Object[3][1];
		data[0][0]="wiwoci914@iaciu.com";
		data[1][0]="digoka999@fentaoba.com";
		data[2][0]="gorixi824@iaciu.com";
		return data;
	}
	@AfterClass
	public void teardown()
	{
		extent.flush();
		driver.quit();
	}
}
