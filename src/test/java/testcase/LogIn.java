package testcase;

import java.time.Duration;
import java.util.Iterator;
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

public class LogIn {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
 
	@BeforeClass
	public void reportsetup() {
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"/Reports/LogIn_Report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Login_using_TestNG");
		extent.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.prygmanextgen.com/signin");
	}
	@Test (priority=1)
	public void validInputs()
	{
		test=extent.createTest("Verify login with valid inputs");
		try {
			driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
			driver.findElement(By.id("password")).sendKeys("goco@123");
			driver.findElement(By.id("remember-me")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.urlContains("dashboard"));
	        System.out.println("Logged in successfully");
	        test.pass("Logged in successfully");
		}catch (TimeoutException e) {
	        System.out.println("Login failed");
	        test.fail("Login failed");
	    }
	}
	@Test(priority=2)
	public void emptyFieldsSignup() throws InterruptedException {
		test = extent.createTest("Verify with Empty fields");
	    try {
	        WebElement SigninBtn=driver.findElement(By.xpath("//button[normalize-space()='Sign in']"));
		    Assert.assertFalse(SigninBtn.isEnabled());
			System.out.println("Sign In button is disabled when the mandatory fields are empty");
			test.pass("Sign In button is disabled when the mandatory fields are empty");
	    }catch (AssertionError e){
			System.out.println("Sign In button is enabled when the mandatory fields are empty");
			test.fail("Sign In button is enabled when the mandatory fields are empty");
			throw e;
		}
	}
	@Test(priority=3)
	public void invalidEmailSignin() {
	    test = extent.createTest("Verify sign-in with invalid email format");
	    try {
	    	driver.findElement(By.id("email")).sendKeys("G@ivaa");
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='space-y-4 rounded-md shadow-sm']")));
		    WebElement errormsg =driver.findElement(By.xpath("//form[contains(@class,'mt-8 space-y-6')]"));
		    Assert.assertTrue(errormsg.getText().toLowerCase().contains("invalid email")); //Error Message
		    System.out.println("Appropriate error msg is displayed");
		    test.pass("Appropriate error msg is displayed");
		}catch(AssertionError e){
			System.out.println("No appropriate error msg");
		    test.fail("No appropriate error msg");
	    }
	}
	@Test(priority=4)
	public void incorrectPassword() {
	    test = extent.createTest("Verify sign-in with invalid passsword format");
	    try {
	    	driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='mt-8 space-y-6']")));
		    WebElement errormsg =driver.findElement(By.xpath("//form[contains(@class,'mt-8 space-y-6')]"));
		    Assert.assertTrue(errormsg.getText().toLowerCase().contains("incorrect password")); //Error Message
		    System.out.println("Appropriate error msg is displayed");
		    test.pass("Appropriate error msg is displayed");
		}catch(AssertionError e){
			System.out.println("No appropriate error msg");
		    test.fail("No appropriate error msg");
	    }
	}
	@Test(priority=5)
    public void verifyPageLoad(){
        test = extent.createTest("Verify Login page loads");
        String currentUrl = driver.getCurrentUrl();
        try {
        	Assert.assertTrue(currentUrl.contains("/signin"));
            System.out.println("Sign In page loaded successfully");
            test.pass("Sign In page loaded successfully");
        }catch (AssertionError e){
        	System.out.println("Sign In page loading failed");
            test.fail("Sign In page loading failed");
            throw e;
          }
        }     
	@Test(priority=6)
	public void verifyTitle(){
		test = extent.createTest("Verify Sign In page title");
	    String title = driver.getTitle();
	    try {
	    	Assert.assertTrue(title.contains("Sign In"));
	        System.out.println("Page title is correct");
	        test.pass("Page title is correct");
       }catch (AssertionError e){
       	System.out.println("Page title is correct");
	        test.fail("Page title is correct");
	        throw e;
       }
	 }
	@Test(priority=7)
    public void verifyHeader() {
		test = extent.createTest("Verify Sign In header");
	    try {
	    	  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	          WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Sign in']")));
	          Assert.assertTrue(header.isDisplayed());
	          System.out.println("Header 'Sign in' is visible");
	          test.pass("Header 'Sign in' is visible");
        }catch(AssertionError e){
        	System.out.println("Header 'Sign in' is not visible");
	        test.fail("Header 'Sign in' is not visible");
        	throw e;
        }
	}
	@Test(priority=8)
	public void scrolltillbottom() throws InterruptedException{
		test=extent.createTest("Sign In Scrolling functionality");
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
	@Test(priority=9)
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
	@Test(priority=10) 
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
	@Test(priority=11)
	public void interfaceOfForgotYourPassword() throws InterruptedException {
		test=extent.createTest("Interace of Forgot Your Password");
		try {
			driver.findElement(By.xpath("//a[normalize-space()='Forgot your password?']")).click();
			Thread.sleep(1000);
			Assert.assertTrue(driver.getCurrentUrl().contains("forgot-password"));
			System.out.println("Forgot password link navigates to reset password window");
			test.pass("Forgot password link navigates to reset password window");
		}catch (AssertionError e) {
			System.out.println("Forgot password link is not working");
			test.fail("Forgot password link is not working");
		}
	}
	@Test(priority=12)
	public void loginViaGoogle() throws InterruptedException {
	    test = extent.createTest("Interface of sign-in via Google account");
	    try {
	        driver.findElement(By.xpath("//span[normalize-space()='Continue with Google']")).click();
	        Thread.sleep(5000);
	        Set<String> windowHandles = driver.getWindowHandles();
	        Iterator<String> iterator = windowHandles.iterator();
	        String parentWindow = iterator.next();
	        String childWindow = iterator.next();
	        driver.switchTo().window(childWindow);
	        Assert.assertTrue(driver.getTitle().contains("Google"));
	        System.out.println("Navigated to Google login window successfully");
	        test.pass("Navigated to Google login window successfully");
	    }catch (AssertionError e) {
	        System.out.println("Failed to navigate to Google login window");
	        test.fail("Failed to navigate to Google login window");
	    }
	}
	@Test(priority=13)
	public void interfaceOfCreateNewAccount() throws InterruptedException {
		test=extent.createTest("Interace of create a new account");
		try {
			driver.findElement(By.linkText("create a new account")).click();
			Thread.sleep(1000);
			Assert.assertTrue(driver.getCurrentUrl().contains("signup"));
			System.out.println("Create a new account link navigates to Sign Up page");
			test.pass("Create a new account link navigates to Sign Up page");
		}catch (AssertionError e) {
			System.out.println("Create a new account link doesn't navigates to Sign Up page");
			test.fail("Create a new account link doesn't navigates to Sign Up page");
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
