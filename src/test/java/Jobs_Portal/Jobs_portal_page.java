package Jobs_Portal;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class Jobs_portal_page {
	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	@BeforeClass 
	public void reportsetup()
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		spark=new ExtentSparkReporter(projectpath+"/Reports/JobsPortal_report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Jobs Portal page Report");
		extent.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.prygmanextgen.com/jobs");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		Thread.sleep(3000);
	}
	@Test(priority = 1)
	public void verifyPageLoad()
	{
		test=extent.createTest("Jobs Portal Page Loads");
		String currentUrl=driver.getCurrentUrl();
		try {
			Assert.assertTrue(currentUrl.contains("/jobs"));
			System.out.println("Jobs Portal page loads successfully");
			test.pass("Jobs Portal page loads successfully");
		}catch(AssertionError e) {
			System.out.println("Jobs Portal page loading failed");
			test.fail("Jobs Portal page loading failed");
			throw e;
		}	
	}
	@Test(priority = 2)
	   public void verifyTitle() 
    {
        test = extent.createTest("Verify Jobs Portal page title");
        String title = driver.getTitle();
        try {
        	 Assert.assertTrue(title.contains("Jobs"));
 	         System.out.println("Page title is correct");
 	         test.pass("Page title is correct");
        }catch (AssertionError e)
        {
        	System.out.println("Page title is incorrect");
	        test.fail("Page title is incorrect");
	        throw e;
        }
    }
    @Test(priority = 3)
    public void verifyHeader() 
    {
        test = extent.createTest("Verify Jobs Portal page header");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Unlock Your Career Potential']")));
        try {
        	Assert.assertTrue(header.isDisplayed());
	        System.out.println("Header is visible");
	        test.pass("Header is visible");
        }catch(AssertionError e)
        {
        	System.out.println("Header is not visible");
	        test.fail("Header is not visible");
        	throw e;
        }
    }
    @Test(priority = 4)
    public void capturecookies() 
    {
        test = extent.createTest("Verify cookies ");
        try {
        Set <Cookie> cookies=driver.manage().getCookies();
        System.out.println("number of cookies "+ cookies.size());
        test.pass("No.of cookies captured");
     
        for(Cookie cookie:cookies)
        {
        	System.out.println(cookie.getName()+":"+cookie.getValue());
        	System.out.println("cookies are captured");
        	test.pass("Cookies are captured");
        }
        }catch(Exception e)
        {
        	System.out.println("cookies are not captured");
        	test.fail("Cookies are not captured");
        }
    }
    @Test(priority=5)
    public void scrollTillBottom() throws InterruptedException
    {
    	test = extent.createTest("Scroll full page");
    try {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
    	Thread.sleep(3000);
    	System.out.println("Scrolling successful");
    	test.pass("Scrolling successful");
    }catch(Exception e) {
    	System.out.println("Scrolling failed");
    	test.fail("Scrolling failed");
    	throw e;
    }
    }
	@Test(priority = 6)
    public void alllinks()
    {
    	test = extent.createTest("Capture all links");	    	
    	try {
    		List<WebElement> alltags=driver.findElements(By.tagName("a"));
	    	System.out.println("Total number of links present in the page: "+alltags.size());
	    	for(int i=0; i<alltags.size();i++)
	    	 {
	    		 String href = alltags.get(i).getAttribute("href");
	             String text = alltags.get(i).getText();

	             System.out.println("Link: " + href + " | Text: " + text);
    	}
	    	 test.pass("Captured all links successfully");
    	}catch(Exception e)
    	{
    		System.out.println("Failed to capture all links");
    		test.fail("Failed to capture all links");
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

