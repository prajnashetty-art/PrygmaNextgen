package Loginpage;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Signupsigncourses.ExcelUtil;

public class data_driven {

	public static void main(String[] args) throws IOException {
 
		WebDriver driver;
		ExtentReports extent;
		ExtentTest test;
		
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_5.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("data_driven_approach");
		extent.attachReporter(spark);
		test=extent.createTest("data_driven_approach");
		
		String excelpath="C:/Automation/signup_and_signin_data.xlsx";
		ExcelUtil excel=new ExcelUtil(excelpath,"Sheet2");
		for(int i=1; i<=excel.getRowCount();i++)
		{
			String email=excel.getCellData(i, 0);
			String password=excel.getCellData(i, 1);
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		
		try {
			driver.get("https://www.prygmanextgen.com/signin");
			driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div[1]")).click();
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("remember-me")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
			System.out.println("Login successful");
			test.pass("Login successful");
		}
		catch(Exception e){
			System.out.println("Login failed");
			test.fail("Login failed");
		}finally {
			driver.quit();
		}
	}
		excel.close();
		extent.flush();
  }
}
