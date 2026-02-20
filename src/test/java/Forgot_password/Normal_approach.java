package Forgot_password;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Normal_approach {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		ExtentReports extent;
		ExtentTest test;
		
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_14.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Forgotpassword_normal_approach");
		extent.attachReporter(spark);
		test=extent.createTest("Forgotpassword_normal_approach");
		
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
		driver.findElement(By.id("email")).sendKeys("vojibw786@iaciu.com");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String pagemsg=driver.findElement(By.cssSelector(".text-sm.font-medium.text-green-800")).getText();
		System.out.println(pagemsg);
		test.pass(pagemsg);
		
		extent.flush();
		driver.quit();
	}

}
