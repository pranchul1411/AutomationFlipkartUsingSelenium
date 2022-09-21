package com.fk;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddToCart {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys("8602605990");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345");

		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"))
				.sendKeys("iphone 13");

		driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();

// Implicit Wait(No condition required)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/a[1]/div[2]/div[2]"))
				.click();

// Unconditional Wait
// Thread.sleep(5000);
// driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/a[1]/div[2]/div[2]")).click();

// Explicit Wait(With Condition)
// WebDriverWait wait = new WebDriverWait(driver, 30);
// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/a[1]/div[2]/div[2]"))).click();

// FluentWait fwait = new FluentWait(driver);
// fwait.withTimeout(Duration.ofSeconds(30));
// fwait.pollingEvery(Duration.ofSeconds(5));

		Set<String> window = driver.getWindowHandles();
		Iterator<String> iterate = window.iterator();
		iterate.next();
		String secondWindow = iterate.next();
		driver.switchTo().window(secondWindow);

		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
	}

}