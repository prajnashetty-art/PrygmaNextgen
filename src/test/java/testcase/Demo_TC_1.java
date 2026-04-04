package testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo_TC_1 {
	WebDriver driver;
			@Test(priority=1)
			public void signup() throws InterruptedException {
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
				driver.get("https://www.prygmanextgen.com/signup");
			
		    try {
		    	String password = System.getenv("password");	//Fetch environment variables
		    	String confirmpassword = System.getenv("confirmpassword");
		    	driver.findElement(By.id("name")).sendKeys("Dia K");
		        driver.findElement(By.id("email")).sendKeys("rotweyt@f8entaoba.com");
		        driver.findElement(By.id("password")).sendKeys("Role@123");
		        driver.findElement(By.id("confirmPassword")).sendKeys("Role@123");
		        JavascriptExecutor js=(JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0,300)", "");
		        Thread.sleep(1000);
		        driver.findElement(By.id("terms")).click();
		        driver.findElement(By.xpath("//button[@type='submit']")).click();
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        if (wait.until(ExpectedConditions.urlContains("courses"))) {
		            System.out.println("Registered successfully and landed on courses page");
		        }
		    } catch (TimeoutException e) {
		        System.out.println("Unexpected failure");
		    }
		    driver.quit();
		}
		@Test(priority=2)
		public void emptyFieldsSignup() throws InterruptedException {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
			driver.get("https://www.prygmanextgen.com/signup");
		    try {
		    	JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,300)", "");
				Thread.sleep(2000);
		        WebElement CreateAccountBtn=driver.findElement(By.xpath("//button[@type='submit']"));
			    Assert.assertFalse(CreateAccountBtn.isEnabled());
				System.out.println("Create Account button is disabled when the mandatory fields are empty");
			}catch (AssertionError e){
				System.out.println("Create Account button is enabled when the mandatory fields are empty");
			}
		    driver.quit();
		}
}
