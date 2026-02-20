package Forgot_password;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Courses.ExcelUtil;

public class Datadriven {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver;
		ExtentReports extent;
		ExtentTest test;
		
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_16.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Forgotpassword_datadriven");
		extent.attachReporter(spark);
		test=extent.createTest("Forgotpassword_datadriven");
		
		String excelpath="C:/Automation/signup_and_signin_data.xlsx";
		ExcelUtil excel=new ExcelUtil(excelpath,"Sheet2");
		
		for(int i=1; i<=excel.getRowCount(); i++)
		{
			String email=excel.getCellData(i, 0); 
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		
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
			driver.quit();
		}
		excel.close();
		extent.flush();
		}
	}

