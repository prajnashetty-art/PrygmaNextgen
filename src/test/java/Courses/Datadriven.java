package Courses;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Datadriven {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver;
		ExtentReports extent;
		ExtentTest test;
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_12.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("datadriven_approach");
		extent.attachReporter(spark);
		
		test=extent.createTest("Datadriven approach");
		
		String Excelpath="C:/Automation/signup_and_signin_data.xlsx";
		ExcelUtil excel=new ExcelUtil(Excelpath,"Sheet1");
		
		for(int i=1; i<excel.getRowCount(); i++)
		{
			String name=excel.getCellData(i,0);
			String email=excel.getCellData(i,1);
			String password=excel.getCellData(i,2);
			String confirmpassword=excel.getCellData(i,3);
			
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			try {
				driver.get("https://www.prygmanextgen.com/");
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
				
				driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/button")).click();
				driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[5]/div/div/div/button")).click();
				Thread.sleep(3000);
				System.out.println("Signed out successfully");
				test.pass("Signed out successfully");
				
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
				
			}catch (Exception e){
				System.out.println("Naviagtion_failed");
				test.fail("Naviagtion_failed");
			}finally {
				driver.quit();
			}
		
		excel.close();
		extent.flush();
	}
}
}