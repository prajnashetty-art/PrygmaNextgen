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

public class Help_and_support {
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
	      spark = new ExtentSparkReporter(projectPath + "/Reports/Help&supportReport.html");
	      spark.config().setTheme(Theme.STANDARD);
	      spark.config().setDocumentTitle("Help and Support Page Automation Report");
	      extent.attachReporter(spark); 
	    }

	    @BeforeMethod
	    public void setup() throws InterruptedException 
	    {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	        driver.get("https://www.prygmanextgen.com/support");
	        Thread.sleep(4000);
	    }
	    @Test(priority = 1)
	    public void verifyPageLoad()
	    {
	        test = extent.createTest("Verify Help and Support page loads");
	        String currentUrl = driver.getCurrentUrl();
	     try {
	        Assert.assertTrue(currentUrl.contains("/support"));
	        System.out.println("Help and Support page loaded successfully");
	        test.pass("Help and Support page loaded successfully");
	    }catch (AssertionError e)
	      {
	    	System.out.println("Help and Support page loading failed");
	        test.fail("Help and Support page loading failed");
	        throw e;
	      }
	    }
	    @Test(priority = 2)
	    public void verifyTitle() 
	    {
	        test = extent.createTest("Verify Help and Support page title");
	        String title = driver.getTitle();
	        try {
	        	 Assert.assertTrue(title.contains("Help"));
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
	        test = extent.createTest("Verify Help and Support header");
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Help & Support']")));
	        try {
	        	Assert.assertTrue(header.isDisplayed());
		        System.out.println("Header 'Help and Support' is visible");
		        test.pass("Header 'Help and Support' is visible");
	        }catch(AssertionError e)
	        {
	        	System.out.println("Header 'Help and Support' is not visible");
		        test.fail("Header 'Help and Support' is not visible");
	        	throw e;
	        }
	    }
	    @Test(priority=4)
	    public void verifywithvalidinputs() throws InterruptedException
	    {
	    	test=extent.createTest("Verify with valid Inputs");
	    	try {
	    		JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,800)", "");
				Thread.sleep(2000);
				driver.findElement(By.id("name")).sendKeys("Givaa");
				driver.findElement(By.id("email")).sendKeys("gehil80833@ostahie.com");
				driver.findElement(By.id("subject")).sendKeys("Course");
				driver.findElement(By.id("message")).sendKeys("Unable to view courses page");
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				wait = new WebDriverWait(driver,Duration.ofSeconds(5));
				WebElement toastmsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//section[@aria-label='Notifications alt+T']//div//div[1]")));
				String textmsg=toastmsg.getText();
				Assert.assertTrue(textmsg.contains("Support ticket submitted successfully"));
				System.out.println("Submit ticket submitted successfully");
				test.pass("Submit ticket submitted successfully");
	    	}catch(AssertionError e)
	    	{
	    		System.out.println("Submit ticket submission failed");
				test.fail("Submit ticket submission failed");
				
	    	}
	    
	    }
	    @Test(priority=5)
		public void emptyfields() throws InterruptedException
		{
			test=extent.createTest("Verify with empty fields");
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,800)", "");
			Thread.sleep(2000);
			
			WebElement submitbtn=driver.findElement(By.xpath("//button[@type='submit']"));
			try {
				Assert.assertFalse(submitbtn.isEnabled());
				System.out.println("Submit Ticket button is disabled when the mandatory fields are empty");
				test.pass("Submit Ticket button is disabled when the mandatory fields are empty");
			}catch (AssertionError e)
			{
				System.out.println("Submit Ticket button is enabled when the mandatory fields are empty");
				test.fail("Submit Ticket button is enabled when the mandatory fields are empty");
				throw e;
			}
		}
	    @Test(priority=6)
		public void InvalidName() throws InterruptedException
		{
			test=extent.createTest("Verify with Invalid Name");
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,800)", "");
			Thread.sleep(2000);
			
			try {
				driver.findElement(By.id("name")).sendKeys("Gi@vaa");
	    		WebElement errorMsg1= driver.findElement(By.xpath("//p[@class='mt-1 text-sm text-red-600']")); 
				Assert.assertTrue(errorMsg1.isDisplayed());
				System.out.println("Appropriate error msg displayed");
				test.pass("Appropriate error msg displayed");
	    	}catch(AssertionError e)
	    	{
	    		System.out.println("No appropriate error msg");
				test.fail("No appropriate error msg");
				throw e;	
	    	}
	    }
	    @Test(priority=7)
	    public void invalidemail() throws InterruptedException
	    {
	    	test=extent.createTest("Verify with invalid email");
	    	try {
	    		JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,800)", "");
				Thread.sleep(2000);
	    		driver.findElement(By.id("name")).sendKeys("Givaa");
				driver.findElement(By.id("email")).sendKeys("gehil80833@ostahiecom");
				WebElement errorMsg2= driver.findElement(By.xpath("//p[normalize-space()='Please enter a valid email address']")); 
				Assert.assertTrue(errorMsg2.isDisplayed());
				System.out.println("Appropriate error msg displayed");
				test.pass("Appropriate error msg displayed");
	    	}catch(AssertionError e)
	    	{
	    		System.out.println("No appropriate error msg");
				test.fail("No appropriate error msg");
				throw e;	
	    	}
	    }
	    @Test(priority=8)
	    public void InvalidSubject1()throws InterruptedException
	    {
	    	test=extent.createTest("Verify with Invalid Subject");
	    	try {
	    		JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,800)", "");
				Thread.sleep(2000);
	    		driver.findElement(By.id("name")).sendKeys("Givaa");
				driver.findElement(By.id("email")).sendKeys("gehil80833@ostahie.com");
				driver.findElement(By.id("subject")).sendKeys("CS");
				WebElement errormsg=driver.findElement(By.xpath("//p[normalize-space()='Subject must be at least 3 characters long']"));
				Assert.assertTrue(errormsg.isDisplayed());
				System.out.println("Appropriate error msg displayed");
				test.pass("Appropriate error msg displayed");
		    }catch(AssertionError e)
	    	{
	    		System.out.println("No appropriate error msg");
				test.fail("No appropriate error msg");
				throw e;	
	    	}
	    }
	    @Test(priority=9)
	    public void InvalidSubject()throws InterruptedException
	    {
	    	test=extent.createTest("Verify with Invalid Subject");
	    	try {
	    		JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,800)", "");
				Thread.sleep(2000);
	    		driver.findElement(By.id("name")).sendKeys("Givaa");
				driver.findElement(By.id("email")).sendKeys("gehil80833@ostahie.com");
				driver.findElement(By.id("subject")).sendKeys("Course");
				driver.findElement(By.id("message")).sendKeys("Need to");
				WebElement errormsg=driver.findElement(By.xpath("//p[normalize-space()='Message must be at least 10 characters long']"));
				Assert.assertTrue(errormsg.isDisplayed());
				System.out.println("Appropriate error msg displayed");
				test.pass("Appropriate error msg displayed");
		    }catch(AssertionError e)
	    	{
	    		System.out.println("No appropriate error msg");
				test.fail("No appropriate error msg");
				throw e;	
	    	}
	    }
	    @Test(priority=10)
	    public void FAquestion() throws InterruptedException
	    {
	    	test=extent.createTest("close Frequently Asked Answer");
	    	try {
	    		WebElement faquestion=driver.findElement(By.xpath("//div[@class='rounded-lg bg-muted p-8 dark:bg-background']//div[2]//button[1]"));
		    	faquestion.click();//collapse
		    	Thread.sleep(5000);
		    	
		    	WebElement faqAnswer = driver.findElement(By.xpath("//div[contains(text(),'To enroll in a course, simply browse our course ca')]")); 
		    	Assert.assertFalse(faqAnswer.isDisplayed());
		    	System.out.println("FAQ answer is not  visible after click");
		    	test.pass("FAQ answer is not visible after click");
	    	}catch(AssertionError e)
	    	{
	    		System.out.println("FAQ answer is still visible after click");
		    	test.fail("FAQ answer is still visible after click");
		    	throw e;
	    	}		
	    }
	    @Test(priority=11)
	    public void FAQanswer() throws InterruptedException
	    {
	    	test=extent.createTest("Expand Frequently Asked Answer");
	    	try {
	    		WebElement faquestion=driver.findElement(By.xpath("/html/body/div[1]/main/section[2]/div/div/div[2]/div[1]/div/div[2]/button"));
		    	faquestion.click();//Expand
		    	Thread.sleep(5000);
		    	
		    	WebElement faqAnswer = driver.findElement(By.xpath("//div[contains(text(),'We accept various payment methods')]")); 
		    	Assert.assertTrue(faqAnswer.isDisplayed());
		    	System.out.println("FAQ answer is visible after click");
		    	test.pass("FAQ answer is visible after click");
	    	}catch(AssertionError e)
	    	{
	    		System.out.println("FAQ answer is not visible after click");
		    	test.fail("FAQ answer is not visible after click");
		    	throw e;
	    	}	
	    }
	    @Test(priority=12)
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
	    @Test(priority=13)
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
	    @Test(priority=14)
	    public void testLinksNavigation() throws InterruptedException {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("window.scrollBy(0,500)", "");
	            Thread.sleep(2000);
	            // Browse Courses Link
	            driver.findElement(By.xpath("//a[normalize-space()='Browse Courses']")).click();
	            Thread.sleep(2000);
	            Assert.assertTrue(driver.getCurrentUrl().contains("courses"));
	            System.out.println("Browse courses link is visible and navigates to courses page");
	            test.pass("Browse courses link is visible and navigates to courses page");
	            driver.navigate().back();
	            Thread.sleep(3000);
	            //View Workshops Link
	            driver.findElement(By.xpath("//a[normalize-space()='View Workshops']")).click();
	            Thread.sleep(2000);
	            Assert.assertTrue(driver.getCurrentUrl().contains("workshops"));
	            System.out.println("View Workshops link is visible and navigates to workshops page");
	            test.pass("View Workshops link is visible and navigates to workshops page");
	            
	            driver.navigate().back();
	            Thread.sleep(3000);
	            //Read Blog Link
	            driver.findElement(By.xpath("//a[normalize-space()='Read Blog']")).click();
	            Thread.sleep(2000);
	            Assert.assertTrue(driver.getCurrentUrl().contains("blog"));
	            System.out.println("Read blog link is visible and navigates to blog page");
	            test.pass("Read blog link is visible and navigates to blog page");

	        } catch (AssertionError e) {
	            System.out.println("Links are not working");
	            throw e;
	        }
	    }
	   @AfterMethod
	   public void exit()
	   {
		   driver.quit();
	   }
	   @AfterClass
	   public void generatereport()
	   {
		   extent.flush();
	   }
}
