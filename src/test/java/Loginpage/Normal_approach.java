package Loginpage;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Normal_approach {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://www.prygmanextgen.com/");
		driver.navigate().to("https://www.prygmanextgen.com/signin");
		driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
		driver.findElement(By.id("password")).sendKeys("goco@123");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.quit();
		System.out.println("Logged in successfully");
		
	}

}
