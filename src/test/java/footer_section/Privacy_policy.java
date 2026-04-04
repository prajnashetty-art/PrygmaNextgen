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

public class Privacy_policy {
	  WebDriver driver;
	  ExtentReports extent;
	  ExtentSparkReporter spark;
	  ExtentTest test;

	  @BeforeClass
	  public void reportSetup()
	  {
		  String projectPath = System.getProperty("user.dir");
	      extent = new ExtentReports();
	      spark = new ExtentSparkReporter(projectPath + "/Reports/Privacypolicy_Report.html");
	      spark.config().setTheme(Theme.STANDARD);
	      spark.config().setDocumentTitle("Privacy Policy Page Automation Report");
	      extent.attachReporter(spark); 
	  }

	    @BeforeMethod
	    public void setup() throws InterruptedException 
	    {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	        driver.get("https://www.prygmanextgen.com/privacy");
	        Thread.sleep(4000);
	    }
	    @Test(priority = 1)
	    public void verifyPageLoad()
	    {
	        test = extent.createTest("Verify Privacy Policy page loads");
	        String currentUrl = driver.getCurrentUrl();
	     try {
	        Assert.assertTrue(currentUrl.contains("/privacy"));
	        System.out.println("Privacy Policy page loaded successfully");
	        test.pass("Privacy Policy page loaded successfully");
	    }catch (AssertionError e)
	      {
	    	System.out.println("Privacy Policy page loading failed");
	        test.fail("Privacy Policy page loading failed");
	        throw e;
	      }
	    }
	    @Test(priority = 2)
	    public void verifyTitle() 
	    {
	        test = extent.createTest("Verify Privacy Policy page title");
	        String title = driver.getTitle();
	        try {
	        	 Assert.assertTrue(title.contains("Privacy"));
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
	        test = extent.createTest("Verify Privacy Policy header");
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Privacy Policy']")));
	        try {
	        	Assert.assertTrue(header.isDisplayed());
		        System.out.println("Header 'Privacy Policy' is visible");
		        test.pass("Header 'Privacy Policy' is visible");
	        }catch(AssertionError e)
	        {
	        	System.out.println("Header 'Privacy Policy' is not visible");
		        test.fail("Header 'Privacy Policy' is not visible");
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
