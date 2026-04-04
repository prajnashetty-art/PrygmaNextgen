package Dashboard_section;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class profile_settings {

		WebDriver driver;
		ExtentReports extent;
		ExtentSparkReporter spark;
		ExtentTest test;
	@BeforeClass
	public void reportsetup()
	{
		String projectpath=System.getProperty("user.dir");
		spark=new ExtentSparkReporter(projectpath+"/Reports/Report_19.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Functionality of profile settings");
		extent=new ExtentReports();
		extent.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.navigate().to("https://www.prygmanextgen.com/signin");
		driver.findElement(By.id("email")).sendKeys("gocoyx121@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("Goco@123");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Login successful");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement profilesettings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Profile Settings")));
		driver.findElement(By.linkText("Profile Settings")).click();
		Thread.sleep(2000);
	}
	@Test(priority=1)
	public void profilesettings() throws InterruptedException
	{
		test=extent.createTest("My profile section visibility");
		try {
			Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/div[2]/div/div[1]/h1")).isDisplayed());
			System.out.println("My Profile section is visible");
			test.pass("My Profile section is visible");
		}catch(AssertionError e)
		{
			System.out.println("My Profile section is not visible");
			test.fail("My Profile section is not visible");
			throw e;
		}
	}
	@Test(priority=2)
	public void verifytitle()
	{
		test=extent.createTest("Title of profile settings page");
		String actualtitle=driver.getTitle();
		try {
			Assert.assertTrue(actualtitle.contains("profile"));
			System.out.println("Page Title is correct");
			test.pass("Page Title is correct");
			}catch (AssertionError e) {
				System.out.println("Page Title is incorrect");
				test.fail("Page Title is incorrect");
				throw e;
			}
	} 
	@Test(priority=3)
	public void scrolltillbottom() throws InterruptedException
	{
		test=extent.createTest("Profile settings-Scrolling functionality");
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
	@Test(priority=4)
	public void updateprofilewithvaliddata() throws InterruptedException
	{
		test=extent.createTest("Update Profile with Valid Data");
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,200)", "");
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
			driver.findElement(By.id("firstName")).sendKeys("Givaa");
			driver.findElement(By.id("lastName")).sendKeys("DS");
			driver.findElement(By.id("phone")).sendKeys("9325863333");
			driver.findElement(By.xpath("//input[@value='female']")).click();
			
			driver.findElement(By.id("linkedin")).sendKeys("https://www.linkedin.com/in/givaa");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='go2072408551']")));
			String messageText = toastMessage.getText();
			Assert.assertEquals(messageText, "Profile updated successfully!");
			System.out.println("Profile updated successfully with valid data");
			test.pass("Profile updated successfully with valid data");
		}catch (AssertionError e)
		{
			System.out.println("Profile updated successfully! message not displayed");
			test.fail("Profile updated successfully! message not displayed");
		}
	}
	@Test(priority=5)
	public void savebutton() throws InterruptedException
	{
		test=extent.createTest("Save button accessibility");
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("lastName")).clear();
		Thread.sleep(2000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)", "");
		Thread.sleep(2000);
		WebElement savebutton= driver.findElement(By.xpath("//button[@type='submit']"));
		try {
			Assert.assertFalse(savebutton.isEnabled());
			System.out.println("Save button is disabled when the mandatory fields are empty");
			test.pass("Save button is disabled when the mandatory fields are empty");
		}catch (AssertionError e)
		{
			System.out.println("Save button is enabled even if the mandatory fields are empty");
			test.fail("Save button is enabled even if the mandatory fields are empty");
			throw e;
		}
		
	}
	@Test (priority=6)
	public void emptymandatoryfields() throws InterruptedException
	{
		test=extent.createTest("Empty mandatory fields");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)", "");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
		try {
			WebElement errorMsg1= driver.findElement(By.xpath("//p[normalize-space()='First name is required']")); 
			Assert.assertTrue(errorMsg1.isDisplayed());
			
			WebElement errormsg2=driver.findElement(By.xpath("//p[normalize-space()='Last name is required']"));
			Assert.assertTrue(errormsg2.isDisplayed());
			
			WebElement errormsg3=driver.findElement(By.xpath("//p[normalize-space()='Phone number is required']"));
			Assert.assertTrue(errormsg3.isDisplayed());
			System.out.println("Proper error messages displayed");
			test.pass("Proper error messages displayed");
		}catch (AssertionError e){
			System.out.println("No Proper error messages displayed");
			test.fail("No Proper error messages displayed");
			throw e;
		}
	}
	@Test(priority=7)
	public void specialcharactersinnames()
	{
		test=extent.createTest("special characters in names");
		driver.findElement(By.id("firstName")).sendKeys("Gi@vaa");
		driver.findElement(By.id("lastName")).sendKeys("D#S");
		try {
			WebElement errormsg3=driver.findElement(By.xpath("//p[contains(text(),'First name can only contain letters, spaces, hyphens, and apostrophes')]"));
			Assert.assertTrue(errormsg3.isDisplayed());
			WebElement errormsg4=driver.findElement(By.xpath("//p[contains(text(),'First name can only contain letters, spaces, hyphens, and apostrophes')]"));
			Assert.assertTrue(errormsg4.isDisplayed());
			System.out.println("Proper error messages displayed");
			test.pass("Proper error messages displayed");
		}catch(AssertionError e)
		{
			System.out.println("No Proper error messages displayed");
			test.fail("No Proper error messages displayed");
			throw e;
		}
	}
	@Test(priority=8)
	public void visibilitydeleteavatar()
	{
		test=extent.createTest("Delete avatar button visibility");
		WebElement deleteavatar= driver.findElement(By.xpath("//button[normalize-space()='Delete Avatar']"));
		try {
		Assert.assertTrue(deleteavatar.isDisplayed());
		System.out.println("Delete avatar button is visible");
		test.pass("Delete avatar button is visible");
		}catch(AssertionError e)
		{
			System.out.println("Delete avatar button is not visible");
			test.fail("Delete avatar button is not visible");
			throw e;
		}
		
	}
	@Test(priority=9)
	public void deleteavatarfunctionality() throws InterruptedException
	{
		test=extent.createTest("Delete avatar button functionality");
		try {
			
		driver.findElement(By.xpath("//button[normalize-space()='Delete Avatar']")).click();
	
		WebDriverWait wait=new WebDriverWait (driver,Duration.ofSeconds(5));
		WebElement toast=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Avatar deleted successfully!')]")));
		String text=toast.getText();
		Assert.assertEquals(text, "Avatar deleted successfully!");
		System.out.println("Avatar deletion Succesful");
		test.pass("Avatar deletion Succesful");
		}catch (AssertionError e)
		{
			System.out.println("Avatar deletion failed");
			test.fail("Avatar deletion failed");
			throw e;
		}
	}
	@Test(priority=10)
	public void uploadavatar()
	{
		test=extent.createTest("Upload avatar functionality");
		try {
			driver.findElement(By.xpath("//button[normalize-space()='Upload New']")).click();
			WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
			fileInput.sendKeys("C:\\Prygma_Nextgen\\Snaps\\Check_boxes.png"); 
			
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			WebElement toastmessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Avatar uploaded successfully')]")));
			String Testmsg=toastmessage.getText();
			Assert.assertEquals(Testmsg, "Avatar uploaded successfully!");
			System.out.println("Avatar uploaded successfully");
			test.pass("Avatar uploaded successfully");
		}catch(AssertionError e)
		{	System.out.println("Avatar upload failed");
			test.fail("Avatar upload failed");
			throw e;
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

