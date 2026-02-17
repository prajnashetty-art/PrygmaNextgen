package Loginpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Cross_browsing {


	WebDriver driver;
	@Test
	@Parameters("browsers")
	
		public void crossbrowsers(String browsername)
		{
			if(browsername.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(browsername.equalsIgnoreCase("edge"))
				{
				driver=new EdgeDriver();
				}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.get("https://www.prygmanextgen.com/");
			driver.navigate().to("https://www.prygmanextgen.com/signin");
			driver.findElement(By.id("email")).sendKeys("gocoxir121@fentaoba.com");
			driver.findElement(By.id("password")).sendKeys("goco@123");
			driver.findElement(By.id("remember-me")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			System.out.println("Logged in successfully");
			driver.quit();
		
	}

}
