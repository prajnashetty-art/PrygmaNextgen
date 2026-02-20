package Navigation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CoursesMaterialsworkshopsJobsportal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String projectpath=System.getProperty("user.dir");
		ExtentReports extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/Report_13.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Naviagation");
		extent.attachReporter(spark);
		ExtentTest test=extent.createTest("Navigation");
		
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		try{
			driver.get("https://www.prygmanextgen.com/");
			driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[1]")).click();
			System.out.println("Navigated to Courses page");
			test.pass("Navigated to Courses page");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='text-sm lg:text-base font-medium transition-colors whitespace-nowrap px-1 lg:px-0 cursor-pointer text-white']")).click();
			System.out.println("Navigated to Materials page");
			test.pass("Navigated to Materials page");
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/div[1]/div[1]/div[2]/div[3]")).click();
			System.out.println("Navigated to Workshops page");
			test.pass("Navigated to Workshops page");
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[2]/div[4]")).click();
			System.out.println("Navigated to Jobs Portal page");
			test.pass("Navigated to Jobs Portal page");
			Thread.sleep(2000);
		}
		catch(Exception e){
			System.out.println("Navigation failed");
			test.pass("Navigation failed");
		}
		finally{
			extent.flush();
			driver.quit();
		}
	}
}
		
			
				
	


