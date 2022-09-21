package com.fk.database;

import java.sql.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class UsingJDBC {

	public static void main(String[] args) {

		String username = "";
		String password = "";
		WebDriver driver;
		System.setProperty("webdriver.edge.driver", "driver\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "demo", "1234");

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from fklogin");
			while (rs.next()) {
				username = rs.getString(1);
				password = rs.getString(2);
			}
			
			driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);


			driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
