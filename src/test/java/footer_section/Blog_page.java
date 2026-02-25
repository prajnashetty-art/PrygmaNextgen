package footer_section;

import java.time.Duration;
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

public class Blog_page {

	 WebDriver driver;
	  ExtentReports extent;
	  ExtentSparkReporter spark;
	  ExtentTest test;
	  WebDriverWait wait;

	  @BeforeClass
	  public void reportSetup()
	  {
		  String projectPath = System.getProperty("user.dir");
	      extent = new ExtentReports();
	      spark = new ExtentSparkReporter(projectPath + "/Reports/BlogReport.html");
	      spark.config().setTheme(Theme.STANDARD);
	      spark.config().setDocumentTitle("Blog Page Automation Report");
	      extent.attachReporter(spark); 
	    }

	    @BeforeMethod
	    public void setup() throws InterruptedException 
	    {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	        driver.get("https://www.prygmanextgen.com/blog");
	        Thread.sleep(4000);
	    }
	    @Test(priority = 1)
	    public void verifyPageLoad()
	    {
	        test = extent.createTest("Verify Blog page loads");
	        String currentUrl = driver.getCurrentUrl();
	     try {
	        Assert.assertTrue(currentUrl.contains("/blog"));
	        System.out.println("Blog page loaded successfully");
	        test.pass("Blog page loaded successfully");
	    }catch (AssertionError e)
	      {
	    	System.out.println("Blog page loading failed");
	        test.fail("Blog page loading failed");
	        throw e;
	      }
	    }
	    @Test(priority = 2)
	    public void verifyTitle() 
	    {
	        test = extent.createTest("Verify Blog page title");
	        String title = driver.getTitle();
	        try {
	        	 Assert.assertTrue(title.contains("Blog"));
	 	         System.out.println("Page title is correct");
	 	         test.pass("Page title is correct");
	        }catch (AssertionError e)
	        {
	        	System.out.println("Page title is correct");
		        test.fail("Page title is correct");
		        throw e;
	        }
	    }
	    
	    @Test(priority = 3)
	    public void verifyHeader() 
	    {
	        test = extent.createTest("Verify Blog header");
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Blog']")));
	        try {
	        	Assert.assertTrue(header.isDisplayed());
		        System.out.println("Header 'Blog' is visible");
		        test.pass("Header 'Blog' is visible");
	        }catch(AssertionError e)
	        {
	        	System.out.println("Header 'Blog' is not visible");
		        test.fail("Header 'Blog' is not visible");
	        	throw e;
	        }
	    }
	    @Test(priority = 4)
	    public void verifyExplorecoursesButton() throws InterruptedException 
	    {
	        test = extent.createTest("Verify Explore courses button functionality");
	        JavascriptExecutor js=(JavascriptExecutor)driver;
	        js.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
	        Thread.sleep(3000);
	        WebElement viewCoursesBtn = driver.findElement(By.xpath("//a[normalize-space()='Explore Courses']"));
	        try {
	        	Assert.assertTrue(viewCoursesBtn.isDisplayed());
	        	viewCoursesBtn.click();
	        	System.out.println("Explore courses button is visible and clickable");
	        	test.pass("Explore courses button is visible and clickable");
	        	Assert.assertTrue(driver.getCurrentUrl().contains("courses"));
	        	System.out.println("Explore courses button navigates to Courses page");
		        test.pass("Explore courses button navigates to Courses page");
	        }catch(AssertionError e)
	        {
	        	System.out.println("Explore courses button doesnot navigates to Courses page");
		        test.fail("Explore courses button doesnot navigates to Courses page");
		        throw e;
	        }
	        
	    }
	    @Test(priority = 5)
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

	    @AfterMethod
	    public void tearDown() 
	    {
	        driver.quit();
	    }

	    @AfterClass
	    public void generateReport()
	    {
	        extent.flush();
	    }
	}

