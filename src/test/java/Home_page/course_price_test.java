package Home_page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class course_price_test {
	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;

    @BeforeClass
    public void setUp() {
    	extent=new ExtentReports();
    	String projectpath=System.getProperty("user.dir");
    	spark=new ExtentSparkReporter(projectpath+"/Reports/courseprice_HomePage.html");
    	spark.config().setTheme(Theme.STANDARD);
    	spark.config().setDocumentTitle("Course Price List in Home Page");
    	extent.attachReporter(spark);
    	test=extent.createTest("Course Price Validation");
    
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.prygmanextgen.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
    }

    // DataProvider with expected course names and prices
    @DataProvider(name = "courseData")
    public Object[][] courseData() {
    	Object[][] data=new Object[3][2];
		data[0][0]="Full Stack QA Program";
		data[0][1]="₹39999";
		
		data[1][0]="Manual Testing Masterclass";
		data[1][1]="₹499";
		
		data[2][0]="API Testing Masterclass";
		data[2][1]="₹644";
		return data;
    }
    @Test(dataProvider = "courseData")
    public void validateCoursePrice(String courseName, String expectedPrice) {
        try {
            // Locate course by name dynamically
            WebElement coursename = driver.findElement(By.xpath("//h3[normalize-space()='" + courseName + "']"));

            // Extract price relative to course card
            String actualPrice = coursename.findElement(By.xpath("./ancestor::div[contains(@class,'swiper-slide')]//span[contains(@class,'text-2xl')]")).getText().trim();

            System.out.println("Course: " + courseName + " | Price: " + actualPrice);

            // Assertion
            Assert.assertEquals(actualPrice, expectedPrice);
            System.out.println("Course '" + courseName + "' has the expected price: " + expectedPrice);
            test.pass("Course '" + courseName + "' has the expected price: " + expectedPrice);

        } catch (AssertionError e) {
        	System.out.println("Failed to validate price for course");
        	test.fail("Failed to validate price for course");
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
    	extent.flush();
        driver.quit();
    }
}
   