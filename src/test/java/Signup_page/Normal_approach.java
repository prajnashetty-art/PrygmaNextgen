package Signup_page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Normal_approach {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signup");
		driver.findElement(By.id("name")).sendKeys("Dia K");
		driver.findElement(By.id("email")).sendKeys("rolelef805@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("Role@123");
		driver.findElement(By.id("confirmPassword")).sendKeys("Role@123");
		Thread.sleep(2000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement element=driver.findElement(By.xpath("//button[@type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='terms']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Registered successfully");
		driver.quit();
	}

}
