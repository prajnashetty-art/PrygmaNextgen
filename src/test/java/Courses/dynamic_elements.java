package Courses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class dynamic_elements {
	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	@BeforeClass 
	public void reportsetup()
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		spark=new ExtentSparkReporter(projectpath+"/Reports/Courses_flter_report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Jobs Portal page Report");
		extent.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("Filter By Course Type");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		Thread.sleep(3000);
	}
	@Test
	 public void filterByCourseType() {
	    try {
	           test = extent.createTest("Filter By Course Type");

	           WebElement courseTypeFilter = driver.findElement(By.xpath("//span[normalize-space()='Course Type']"));
	           courseTypeFilter.click();
	           
	           driver.findElement(By.xpath("//span[normalize-space()='Live Course']")).click();
	           
	           List<WebElement> courses = driver.findElements(By.cssSelector("span[normalize-space()='Live Course']\r\n"));
	           Assert.assertTrue(courses.size() > 0, "No courses found for Live filter");

	           for (WebElement course : courses) {
	               String courseTitle = course.findElement(By.cssSelector("span[normalize-space()='Live Course']\r\n")).getText().trim();
	               System.out.println("Course Title: " + courseTitle);

	               // If the course type is displayed separately, locate it:
	               WebElement typeElement = course.findElement(By.xpath(".//span[contains(@class,'course-type')]"));
	               String type = typeElement.getText().trim();

	               Assert.assertEquals(type, "Live Course", "Course type mismatch!");
	               test.pass("Filter by Course Type validated successfully");
	           }

	           
	       }catch (AssertionError e) {
	           test.fail("Failed to validate Course Type filter");
	           throw e;
	       }
	   }
	 
		@AfterMethod()
		public void tearDown()
		{
			driver.quit();
		}
		@AfterClass
		public void generatereport()
		{
			extent.flush();
		}
	}


