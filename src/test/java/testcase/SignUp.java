package testcase;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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

public class SignUp {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void reportsetup() {
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/SignUp_Report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Signup_using_TestNG");
		extent.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signup");
	}
	@Test(priority=1)
	public void inputdata() throws Exception {
	    test = extent.createTest("Verify Sign Up with valid inputs");
	    try {
	        driver.findElement(By.id("name")).sendKeys("Dia K");
	        driver.findElement(By.id("email")).sendKeys("rotwey@f8entaoba.com");
	        driver.findElement(By.id("password")).sendKeys("Role@123");
	        driver.findElement(By.id("confirmPassword")).sendKeys("Role@123");

	        JavascriptExecutor js=(JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,300)", "");
	        Thread.sleep(1000);
	        driver.findElement(By.id("terms")).click();
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        // Wait for either redirect or error
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        if (wait.until(ExpectedConditions.urlContains("courses"))) {
	            System.out.println("Registered successfully and landed on courses page");
	            test.pass("Registered successfully and landed on courses page");
	        }
	    } catch (TimeoutException e) {
	        System.out.println("Unexpected failure");
	        test.fail("Unexpected failure");
	    }
	}

	@Test(priority=2)
	public void emptyFieldsSignup() throws InterruptedException {
		test = extent.createTest("Verify with Empty fields");
	    try {
	    	JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(2000);
	        WebElement CreateAccountBtn=driver.findElement(By.xpath("//button[@type='submit']"));
		    Assert.assertFalse(CreateAccountBtn.isEnabled());
			System.out.println("Create Account button is disabled when the mandatory fields are empty");
			test.pass("Create Account button is disabled when the mandatory fields are empty");
			}catch (AssertionError e){
				System.out.println("Create Account button is enabled when the mandatory fields are empty");
				test.fail("Create Account button is enabled when the mandatory fields are empty");
				throw e;
			}
		}
	 @Test(priority=3)
	    public void InvalidName() {
	    	test=extent.createTest("Verify with invalid name");
	    	try {
	    		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("G@ivaa");
	    		WebElement errorMsg1= driver.findElement(By.xpath("//p[@class='mt-1 text-sm text-red-600']")); 
				Assert.assertTrue(errorMsg1.isDisplayed());
				System.out.println("Appropriate error msg displayed");
				test.pass("Appropriate error msg displayed");
	    	}catch(AssertionError e){
	    		System.out.println("No appropriate error msg");
				test.fail("No appropriate error msg");
				throw e;	
	    	}
	    }
	@Test(priority=4)
    public void invalidemail() throws InterruptedException{
    	test=extent.createTest("Verify with invalid email");
    	try {
    		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Givaa S");
    		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("givaa@123m");
			WebElement errorMsg2= driver.findElement(By.xpath("//p[normalize-space()='Please enter a valid email address']")); 
			Assert.assertTrue(errorMsg2.isDisplayed());
			System.out.println("Appropriate error msg displayed");
			test.pass("Appropriate error msg displayed");
    	}catch(AssertionError e){
    		System.out.println("No appropriate error msg");
			test.fail("No appropriate error msg");
			throw e;	
    	}
    }
	@Test(priority=5)
	public void passwordMismatchSignup() {
		test = extent.createTest("Verify password mismatch");
	    try {
	        driver.findElement(By.id("name")).sendKeys("Mismatch User");
	        driver.findElement(By.id("email")).sendKeys("mismatch@test.com");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,300)", "");
	        driver.findElement(By.id("password")).sendKeys("Password@123");
	        driver.findElement(By.id("confirmPassword")).sendKeys("Password@456");
	        //driver.findElement(By.xpath("//button[@type='submit']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Passwords do not match')]")));
	        String actualMessage = errorMsg.getText();
	        String expectedMessage = "Passwords do not match";
	        Assert.assertEquals(actualMessage, expectedMessage);
	        System.out.println("Appropriate error msg displayed");
	        test.pass("Appropriate error msg displayed");
	    } catch (Exception e) {
	        System.out.println("No appropriate error msg");
	        test.fail("No appropriate error msg");
	        }
	}
	@Test(priority=6)
	public void weakPasswordSignup() {
		test = extent.createTest("Verify weak password");
	    try {
	        driver.findElement(By.id("name")).sendKeys("User");
	        driver.findElement(By.id("email")).sendKeys("weakpass@test.com");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,300)", "");
	        driver.findElement(By.id("password")).sendKeys("12345");
	        
	        WebElement errorMsg = driver.findElement(By.xpath("//ul[@class='list-disc list-inside ml-2 space-y-1']"));
	        Assert.assertTrue(errorMsg.isDisplayed());
	        System.out.println("Signup failed as expected due to weak password");
	        test.pass("Signup failed as expected due to weak password");
	    } catch (Exception e) {
	    	System.out.println("No intruction for weak password");
	        test.fail("No intruction for weak password");
	    }
	}
	@Test(priority=7)
	public void uncheckedTermsSignup() throws InterruptedException {
	    test = extent.createTest("Verify unchecked terms");
	    try {
		    driver.findElement(By.id("name")).sendKeys("User");
		    driver.findElement(By.id("email")).sendKeys("user@test.com");
		    driver.findElement(By.id("password")).sendKeys("ValidPass123!");
		    driver.findElement(By.id("confirmPassword")).sendKeys("ValidPass123!");
		    JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(2000);
	        WebElement CreateAccountBtn=driver.findElement(By.xpath("//button[@type='submit']"));
		    Assert.assertFalse(CreateAccountBtn.isEnabled());
			System.out.println("Create Account button is disabled for unchecked terms");
			test.pass("Create Account button is disabled for unchecked terms");
			}catch (AssertionError e) {
				System.out.println("Create Account button is enabled for unchecked terms");
				test.pass("Create Account button is enabled for unchecked terms");
			}
	    }
	@Test(priority=8)
    public void verifyPageLoad()
    {
        test = extent.createTest("Verify SignUp page loads");
        String currentUrl = driver.getCurrentUrl();
     try {
        Assert.assertTrue(currentUrl.contains("/signup"));
        System.out.println("Sign Up page loaded successfully");
        test.pass("Sign Up page loaded successfully");
    }catch (AssertionError e)
      {
    	System.out.println("Sign Up page loading failed");
        test.fail("Sign Up page loading failed");
        throw e;
      }
    }
	 @Test(priority=9)
	  public void verifyTitle(){
		 test = extent.createTest("Verify Sign Up page title");
	     String title = driver.getTitle();
	     try {
	    	 Assert.assertTrue(title.contains("Sign Up"));
 	         System.out.println("Page title is correct");
 	         test.pass("Page title is correct");
        }catch (AssertionError e){
        	System.out.println("Page title is correct");
	        test.fail("Page title is correct");
	        throw e;     	 
	 }
	 }
	@Test(priority=10)
    public void verifyHeader() {
		test = extent.createTest("Verify Sign Up header");
	    try {
	    	  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	          WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Create Account']")));
	          Assert.assertTrue(header.isDisplayed());
	          System.out.println("Header 'Create Account' is visible");
	          test.pass("Header 'Create Account' is visible");
        }catch(AssertionError e){
        	System.out.println("Header 'Create Account' is not visible");
	        test.fail("Header 'Create Account' is not visible");
        	throw e;
        }
	}
	@Test(priority=11)
	public void scrolltillbottom() throws InterruptedException{
		test=extent.createTest("Sign Up Scrolling functionality");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
			System.out.println("Scrolling successful");
			test.pass("Scrolling successful");
		}catch (Exception e){
			System.out.println("Scrolling failed");
			test.fail("Scrolling failed");
			throw e;
		}
	}
	 @Test(priority=12)
	  public void capturecookies() {
		test = extent.createTest("Verify cookies ");
	  	try {
	  		Set <Cookie> cookies=driver.manage().getCookies();
	  		System.out.println("number of cookies "+ cookies.size());
	  		test.pass("No.of cookies captured");
	   
	      for(Cookie cookie:cookies){
	      	System.out.println(cookie.getName()+":"+cookie.getValue());
	      	System.out.println("cookies are captured");
	      	test.pass("Cookies are captured");
	      }
	  	}catch(Exception e){
	      	System.out.println("cookies are not captured");
	      	test.fail("Cookies are not captured");
	      }
	}
    @Test(priority=13) 
    public void alllinks(){
    	test = extent.createTest("Capture all links");	    	
    	try {
    		List<WebElement> alltags=driver.findElements(By.tagName("a"));
	    	System.out.println("Total number of links present in the page: "+alltags.size());
	    	for(int i=0; i<alltags.size();i++) {
	    		 String href = alltags.get(i).getAttribute("href");
	             String text = alltags.get(i).getText();

	             System.out.println("Link: " + href + " | Text: " + text);
    	}
	    	 System.out.println("Captured all links successfully");
	    	 test.pass("Captured all links successfully");
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Failed to capture all links");
    		test.fail("Failed to capture all links");
    	}
    } 
	@AfterMethod
	public void result(){
		driver.quit();
	}
	@AfterClass
	public void tearDown() {
		extent.flush();
	}
}