package Signup_page;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class Through_gmail {
 
	public static void main(String[] args) throws InterruptedException {
		 
		String projectpath=System.getProperty("user.dir");
		ExtentReports extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_8.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Through_gmail");
		extent.attachReporter(spark);
		ExtentTest test=extent.createTest("Signup_through_gmail");
				
		EdgeDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.prygmanextgen.com/signup");
		driver.findElement(By.xpath("//span[normalize-space()='Continue with Google']")).click();
		Thread.sleep(3000);
		Set<String> windowshandle=driver.getWindowHandles();
		Iterator<String> iterator=windowshandle.iterator();
		 
		String parentwindow=iterator.next();
		String childwindow=iterator.next();
		driver.switchTo().window(childwindow);
		
		driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("Enter email id");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys("Enter password");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
		
		driver.switchTo().window(parentwindow);
		Thread.sleep(15000);
		String url = driver.getCurrentUrl();
		
		if (url.contains("/courses")) {
		    System.out.println("Sign up successful");
		    test.pass("Sign up successful");
		} else {
		    System.out.println("Sign up failed");
		    test.fail("Signup failed");
		}
		extent.flush();
		driver.quit();
	}		
}


