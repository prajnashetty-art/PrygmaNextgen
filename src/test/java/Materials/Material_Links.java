package Materials;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class Material_Links {

	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	@BeforeClass 
	public void reportsetup()
	{
		String projectpath=System.getProperty("user.dir");
		extent=new ExtentReports();
		spark=new ExtentSparkReporter(projectpath+"/Reports/MaterialsPage_report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Materials page Report");
		extent.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.prygmanextgen.com/materials");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		Thread.sleep(3000);
	}
	@Test
    public void testLinksNavigation() throws InterruptedException {
        try {
        	test=extent.createTest("Links functionality");
        	JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,500)", "");
	        Thread.sleep(2000);
         
	        driver.findElement(By.xpath("//h3[normalize-space()='Manual Testing']")).click();
	        Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("/manual-testing"));
            System.out.println("Manual Testing link is visible and navigates to relevent page");
            test.pass("Manual Testing link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            driver.findElement(By.xpath("//h3[normalize-space()='Git & GitHub']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("/git-github"));
            System.out.println("Git-GitHub link is visible and navigates to relevent page");
            test.pass("Git-GitHub link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            driver.findElement(By.xpath("//h3[normalize-space()='Jenkins CI/CD']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("jenkins-cicd"));
            System.out.println("Jenkins CI/CD link is visible and navigates to relevent page");
            test.pass("Jenkins CI/CD link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
	        js1.executeScript("window.scrollBy(0,500)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='API Testing with Rest Assured']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("api-testing-rest-assured"));
            System.out.println("API-testing-rest-assured link is visible and navigates to relevent page");
            test.pass("API-testing-rest-assured link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            driver.findElement(By.xpath("//h3[normalize-space()='MySQL Database']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("mysql-database"));
            System.out.println("MySQL Database link is visible and navigates to relevent page");
            test.pass("MySQL Database link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            driver.findElement(By.xpath("//h3[normalize-space()='C++ Programming']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("cpp-programming"));
            System.out.println("C++ Programming link is visible and navigates to relevent page");
            test.pass("C++ Programming link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
	        js2.executeScript("window.scrollBy(0,500)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='C Programming']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("c-programming"));
            System.out.println("C Programming link is visible and navigates to relevent page");
            test.pass("C Programming link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
	        
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
	        js3.executeScript("window.scrollBy(0,500)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='Tosca']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("tosca"));
            System.out.println("Tosca link is visible and navigates to relevent page");
            test.pass("Tosca link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            

            JavascriptExecutor js4 = (JavascriptExecutor) driver;
	        js4.executeScript("window.scrollBy(0,500)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='JavaScript']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("javascript"));
            System.out.println("JavaScript link is visible and navigates to relevent page");
            test.pass("JavaScript link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            JavascriptExecutor js5 = (JavascriptExecutor) driver;
	        js5.executeScript("window.scrollBy(0,700)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='HTML + CSS']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("html-css"));
            System.out.println("HTML + CSS link is visible and navigates to relevent page");
            test.pass("HTML + CSS link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
	        
            JavascriptExecutor js6 = (JavascriptExecutor) driver;
	        js6.executeScript("window.scrollBy(0,700)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='Python Top 100 Programs']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("python-top-100-programs"));
            System.out.println("Python Top 100 Programs link is visible and navigates to relevent page");
            test.pass("Python Top 100 Programs link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
	        
            JavascriptExecutor js7 = (JavascriptExecutor) driver;
	        js7.executeScript("window.scrollBy(0,700)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='Java Top 100 Programs']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("java-top-100-programs"));
            System.out.println("Java Top 100 Programs link is visible and navigates to relevent page");
            test.pass("Python Top 100 Programs link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
            JavascriptExecutor js8 = (JavascriptExecutor) driver;
	        js8.executeScript("window.scrollBy(0,1200)", "");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//h3[normalize-space()='API Testing with Postman']")).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.getCurrentUrl().contains("api-testing-postman"));
            System.out.println("API Testing with Postman link is visible and navigates to relevent page");
            test.pass("API Testing with Postman link is visible and navigates to relevent page");
            driver.navigate().back();
            Thread.sleep(3000);
            
        } catch (AssertionError e) {
            System.out.println("Links are not working");
            test.fail("Links are not working");
            throw e;
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
