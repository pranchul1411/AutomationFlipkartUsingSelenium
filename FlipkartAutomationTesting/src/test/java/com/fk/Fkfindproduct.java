package com.fk;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Fkfindproduct {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
				
		driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys("8602605990");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();

//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Thread.sleep(3000);
		Actions act = new Actions(driver);

		act.moveToElement(driver.findElement(By.xpath("//img[@alt='Fashion']"))).perform();
//		act.moveToElement(driver.findElement(By.xpath("//a[@class='_6WOcW9 _2-k99T']"))).perform();
		driver.findElement(By.xpath("//a[@class='_6WOcW9 _2-k99T']")).click();
//		act.moveToElement(driver.findElement(By.xpath("//a[text()='Men's Kurtas']"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//div[@class='_1YokD2 _3Mn1Gg']//div[2]//div[1]//div[3]//div[1]//a[1]//div[1]//div[1]//div[1]//div[1]//img[1]")).click();
		
		
		Set<String> window = driver.getWindowHandles();
		Iterator<String> iterate = window.iterator();
		iterate.next();
		String secondWindow = iterate.next();
		driver.switchTo().window(secondWindow);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[3]/div[1]/div[2]/div[5]/div[1]/div[2]/div[1]/ul[1]/li[4]/a[1]")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//button[text()='ADD TO CART']")));
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
		
		
		
	}
}