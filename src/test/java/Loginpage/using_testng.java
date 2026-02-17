package Loginpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class using_testng {

	WebDriver driver;
	@BeforeMethod
	public void setup() throws Exception
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signin");
	}
	@Test
	public void inputcredentials()
	{
		driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("goco@123");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}
	@AfterMethod
	public void result()
	{
		System.out.println("Logged in successfully");
		driver.quit();
	}
	
}
