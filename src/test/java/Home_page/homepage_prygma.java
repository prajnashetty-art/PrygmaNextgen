package Home_page;

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

public class homepage_prygma {
	  WebDriver driver;
	  ExtentReports extent;
	  ExtentSparkReporter spark;
	  ExtentTest test;

	  @BeforeClass
	  public void reportSetup()
	  {
		  String projectPath = System.getProperty("user.dir");
	      extent = new ExtentReports();
	      spark = new ExtentSparkReporter(projectPath + "/Reports/Homepage_Report.html");
	      spark.config().setTheme(Theme.STANDARD);
	      spark.config().setDocumentTitle("Home Page Automation Report");
	      extent.attachReporter(spark); 
	  }

	   @BeforeMethod
	   public void setup() throws InterruptedException 
	   {
		   driver = new ChromeDriver();
	       driver.manage().window().maximize();
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	       driver.get("https://www.prygmanextgen.com/");
	       Thread.sleep(4000);  
	    }
	    @Test(priority = 1)
	    public void verifyPageLoad()
	    {
	       test = extent.createTest("Home page loads");
	       String currentUrl = driver.getCurrentUrl();
	       try {
	        Assert.assertTrue(currentUrl.contains("www.prygmanextgen.com"));
	        System.out.println("Home page loaded successfully");
	        test.pass("Home page loaded successfully");
	    }catch (AssertionError e)
	      {
	    	System.out.println("Home page loading failed");
	        test.fail("Home page loading failed");
	        throw e;
	      }
	    }
	    @Test(priority = 2)
	    public void verifyTitle() 
	    {
	        test = extent.createTest("Verify Home page title");
	        String title = driver.getTitle();
	        try {
	        	 Assert.assertTrue(title.contains("Home"));
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
	        test = extent.createTest("Verify Home page header");
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Your Gateway to Knowledge, Learn and Elevate Your ')]")));
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
	    }catch(Exception e)
	    {
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
	    	{e.printStackTrace();
	    		System.out.println("Failed to capture all links");
	    		test.fail("Failed to capture all links");
	    	}
	    }
	   @Test(priority=7)
	    public void testLinksNavigation() throws InterruptedException {
	        try {
	        	test=extent.createTest("Links functionality");
	          
	            // Get Started Link
	            driver.findElement(By.xpath("//a[normalize-space()='Get Started']")).click();
	            Thread.sleep(2000);
	            Assert.assertTrue(driver.getCurrentUrl().contains("signup"));
	            System.out.println("Get Started link is visible and navigates to sign up page");
	            test.pass("Get Started link is visible and navigates to sign up page");
	            driver.navigate().back();
	            Thread.sleep(3000);
	            //Watch demo Link
	            driver.findElement(By.xpath("//a[normalize-space()='Watch Demo']")).click();
	            Thread.sleep(2000);
	            Assert.assertTrue(driver.getCurrentUrl().contains("demo"));
	            System.out.println("Watch Demo link is visible and navigates to demo page");
	            test.pass("Watch Demo link is visible and navigates to demo page");
	            
	            driver.navigate().back();
	            Thread.sleep(3000);
	            JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0,1000)", "");
		            Thread.sleep(2000);
	            //View all courses Link
	            driver.findElement(By.xpath("//a[normalize-space()='View All Courses']")).click();
	            Thread.sleep(2000);
	            Assert.assertTrue(driver.getCurrentUrl().contains("courses"));
	            System.out.println("View all courseslink is visible and navigates to courses page");
	            test.pass("View all courses link is visible and navigates to courses page");
	            driver.navigate().back();
	            Thread.sleep(3000);
	            js.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
		    	Thread.sleep(3000);
		    	driver.findElement(By.xpath("//a[normalize-space()='Browse Courses']")).click();
	            Thread.sleep(2000);
	            Assert.assertTrue(driver.getCurrentUrl().contains("courses"));
	            System.out.println("Browse courses link is visible and navigates to courses page");
	            test.pass("Browse courses link is visible and navigates to courses page");

	        } catch (AssertionError e) {
	            System.out.println("Links are not working");
	            throw e;
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


