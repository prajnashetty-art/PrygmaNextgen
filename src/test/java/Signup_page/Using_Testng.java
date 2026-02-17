package Signup_page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Using_Testng {

	WebDriver driver;
	@BeforeMethod
	public void setup() 
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signup");
	}
	@Test
	public void inputdata() throws InterruptedException
	{
		driver.findElement(By.id("name")).sendKeys("Dia K");
		driver.findElement(By.id("email")).sendKeys("rolelef804@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("Role@123");
		driver.findElement(By.id("confirmPassword")).sendKeys("Role@123");
		Thread.sleep(2000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement element=driver.findElement(By.xpath("//button[@type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='terms']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	@AfterMethod
	public void result()
	{
		System.out.println("Registered successfully");
		driver.quit();
	}
}
