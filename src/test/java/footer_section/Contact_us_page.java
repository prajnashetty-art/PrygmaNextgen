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

public class Contact_us_page {
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
	      spark = new ExtentSparkReporter(projectPath + "/Reports/ContactUsReport.html");
	      spark.config().setTheme(Theme.STANDARD);
	      spark.config().setDocumentTitle("Contact Us Page Automation Report");
	      extent.attachReporter(spark); 
	    }

	    @BeforeMethod
	    public void setup() throws InterruptedException 
	    {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	        driver.get("https://www.prygmanextgen.com/contact");
	        Thread.sleep(4000);
	    }
	    @Test(priority = 1)
	    public void verifyPageLoad()
	    {
	        test = extent.createTest("Verify Contact Us page loads");
	        String currentUrl = driver.getCurrentUrl();
	     try {
	        Assert.assertTrue(currentUrl.contains("/contact"));
	        System.out.println("Contact Us page loaded successfully");
	        test.pass("Contact Us page loaded successfully");
	    }catch (AssertionError e)
	      {
	    	System.out.println("Contact Us page loading failed");
	        test.fail("Contact Us page loading failed");
	        throw e;
	      }
	    }
	    @Test(priority = 2)
	    public void verifyTitle() 
	    {
	        test = extent.createTest("Verify Contact Us page title");
	        String title = driver.getTitle();
	        try {
	        	 Assert.assertTrue(title.contains("Contact"));
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
	        test = extent.createTest("Verify Contact Us header");
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Contact Us')]")));
	        try {
	        	Assert.assertTrue(header.isDisplayed());
		        System.out.println("Header 'Contact Us' is visible");
		        test.pass("Header 'Contact Us' is visible");
	        }catch(AssertionError e)
	        {
	        	System.out.println("Header 'Contact Us' is not visible");
		        test.fail("Header 'Contact Us' is not visible");
	        	throw e;
	        }
	    }
		@Test(priority=4)
		public void scrolltillbottom() throws InterruptedException
		{
			test=extent.createTest("Contact Us Scrolling functionality");
			Thread.sleep(5000);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			try {
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
				System.out.println("Scrolling successful");
				test.pass("Scrolling successful");
			}catch (Exception e)
			{
				System.out.println("Scrolling failed");
				test.fail("Scrolling failed");
				throw e;
			}
		}
		@Test(priority=5)
		public void SendMsgWithValidData() throws InterruptedException
		{
			test=extent.createTest("Send Message with Valid Data");
			try {
				driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys("Givaa");
				driver.findElement(By.xpath("//input[@placeholder='Enter your email']")).sendKeys("givaa78709@ostahie.com");
				Thread.sleep(2000);
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@placeholder='Enter subject']")).sendKeys("Courses");
				driver.findElement(By.xpath("//input[@placeholder='Enter your phone number']")).sendKeys("9111911191");
				driver.findElement(By.xpath("//textarea[@placeholder='Type your message']")).sendKeys("Need to to know about the courses you offer");
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/ol/li/div[2]/div")));
				String messageText = toastMessage.getText();
				Assert.assertEquals(messageText, "Thank you for your message. We'll get back to you soon!");
				System.out.println("Message sent successfully with valid data");
				test.pass("Message sent successfully with valid data");
			}catch (AssertionError e)
			{
				System.out.println("No confirmation for sent message");
				test.fail("No confirmation for sent message");
			}
		}
		
		@Test(priority=6)
		public void emptyfields() throws InterruptedException
		{
			test=extent.createTest("Verify with empty fields");
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,500)", "");
			Thread.sleep(2000);
			
			WebElement sendmsgbtn=driver.findElement(By.xpath("//button[@type='submit']"));
			try {
				Assert.assertFalse(sendmsgbtn.isEnabled());
				System.out.println("send message button is disabled when the mandatory fields are empty");
				test.pass("send message button is disabled when the mandatory fields are empty");
			}catch (AssertionError e)
			{
				System.out.println("send message button is enabled when the mandatory fields are empty");
				test.fail("send message button is enabled when the mandatory fields are empty");
				throw e;
			}
		}
	   
	    @Test(priority=7)
	    public void InvalidName() 
	    {
	    	test=extent.createTest("Verify with invalid name");
	    	try {
	    		driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys("G@ivaa");
	    		WebElement errorMsg1= driver.findElement(By.xpath("//p[contains(text(),'Name can only contain letters, spaces, hyphens (-)')]")); 
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
	    
	    @Test(priority=8)
	    public void invalidemail() throws InterruptedException
	    {
	    	test=extent.createTest("Verify with invalid email");
	    	try {
	    		driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys("Givaa");
	    		driver.findElement(By.xpath("//input[@placeholder='Enter your email']")).sendKeys("givaa@123m");
				WebElement errorMsg2= driver.findElement(By.xpath("//p[@class='mt-1 text-sm text-red-600']")); 
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
	    @Test(priority=9)
		public void invalidphoneNumber() throws InterruptedException
		{
			test=extent.createTest("Verify with invalid Phone Number");
			try {
				driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys("Givaa");
		    	driver.findElement(By.xpath("//input[@placeholder='Enter your email']")).sendKeys("givaa78709@ostahie.com");
				driver.findElement(By.xpath("//input[@placeholder='Enter your phone number']")).sendKeys("ccc911191");
				WebElement errormsg3=driver.findElement(By.xpath("//p[normalize-space()='Please enter a valid phone number (10-15 digits)']"));
				Assert.assertTrue(errormsg3.isDisplayed());
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
	    public void InvalidSubject()throws InterruptedException
	    {
	    	test=extent.createTest("Verify with Invalid Subject");
	    	try {
	    		driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys("Givaa");
		    	driver.findElement(By.xpath("//input[@placeholder='Enter your email']")).sendKeys("givaa78709@ostahie.com");
				Thread.sleep(2000);
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@placeholder='Enter subject']")).sendKeys("CS");
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
	    @Test(priority=11)
	    public void InvalidMessage()throws InterruptedException
	    {
	    	test=extent.createTest("Verify with Invalid Message");
	    	try {
	    		driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys("Givaa");
		    	driver.findElement(By.xpath("//input[@placeholder='Enter your email']")).sendKeys("givaa78709@ostahie.com");
				Thread.sleep(2000);
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@placeholder='Enter subject']")).sendKeys("Course");
				driver.findElement(By.xpath("//textarea[@placeholder='Type your message']")).sendKeys("Need to");
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
	    @Test(priority = 12)
	    public void capturecookies() 
	    {
	        test = extent.createTest("Verify cookies ");
	        Set <Cookie> cookies=driver.manage().getCookies();
	        System.out.println("number of cookies "+ cookies.size());
	        test.pass("No.of cookies captured");
	        
	        try {
	        	
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
		public void teardown()
		{
			driver.quit();
		}
		@AfterClass
		public void reportgenerate()
		{
			extent.flush();
		}
	}
