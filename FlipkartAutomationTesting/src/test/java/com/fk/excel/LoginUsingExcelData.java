package com.fk.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginUsingExcelData {

	public static void main(String[] args) throws InvalidFormatException, IOException, InterruptedException {
		
		createExcelFile();
		readData();
		
		Map<String, String> data = userNameAndPassword(); 
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver(options);
		
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		for(String k : data.keySet()) {
			String username = k;
			String password = data.get(k);
			driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

			driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
			driver.switchTo().newWindow(WindowType.WINDOW);
//			Actions action = new Actions(driver);
//			action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("N").perform();
			driver.get("https://www.flipkart.com");
			Thread.sleep(3000);
			
		}

	}

	public static void createExcelFile() throws InvalidFormatException, IOException {
		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet sheet = wb.createSheet("LoginDetails");

		Map<String, Object[]> data = new HashMap<String, Object[]>();

		data.put("1", new Object[] { "S.No", "Username", "Password" });
		data.put("2", new Object[] { "1", "8602605990", "12345" });
		data.put("3", new Object[] { "2", "pranchul@gmail.com", "12345" });
		data.put("4", new Object[] { "3", "abc@gmail.com", "abz@345" });
		data.put("5", new Object[] { "4", "abc@hotmail.com", "xyz@123" });
		data.put("6", new Object[] { "5", "xyz@outlook.com", "abc@123" });
		data.put("7", new Object[] { "6", "8889434922", "1234455" });

		Set<String> keyset = data.keySet();

		int row = 0;

		for (String key : keyset) {

			Row rownum = sheet.createRow(row++);

			int cell = 0;

			Object[] objArr = data.get(key);

			for (Object obj : objArr) {

				Cell cellnum = rownum.createCell(cell++);

				if (obj instanceof String) {
					cellnum.setCellValue((String) obj);
				if (obj instanceof Integer) {
						cellnum.setCellValue((Integer) obj);
				}
				}

			}
		}
		
		FileOutputStream fout = new FileOutputStream("target//LoginDetails.xlsx");
		wb.write(fout);
		wb.close();
		fout.close();
		System.out.println("SuccessFully created and write data !!!");
	}
	
	public static void readData() throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream("target//LoginDetails.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(new File("target//LoginDetails.xlsx"));
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();
		for (Row r : sheet) {
			for (Cell c : r) {
				switch (fe.evaluateInCell(c).getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(c.getNumericCellValue() + "\t\t\t");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(c.getStringCellValue()+"\t\t");
					break;
				}
			}
			System.out.println();
		}
		wb.close();
		fis.close();

		System.out.println("SuccessFully read data !!!");
		
		
	}
	
	public static Map userNameAndPassword() throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream("target//LoginDetails.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(new File("target//LoginDetails.xlsx"));

		XSSFSheet sheet = wb.getSheetAt(0);

//		FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();
//		for (Row r : sheet) {
//			for (Cell c : r) {
//				switch (fe.evaluateInCell(c).getCellType()) {
//				case Cell.CELL_TYPE_NUMERIC:
//					System.out.print(c.getNumericCellValue() + "\t\t\t");
//					break;
//				case Cell.CELL_TYPE_STRING:
//					System.out.print(c.getStringCellValue() + "\t\t");
//					break;
//				}
//			}
//			System.out.println();
//		}
		Map<String, String> data = new HashMap<>();
		int rows = sheet.getLastRowNum();
		
		for(int r=1;r<=rows;r++) {
			
			XSSFRow row = sheet.getRow(r);
			
			
			XSSFCell cell = row.getCell(1);
			String username = cell.getStringCellValue();
			
			cell = row.getCell(2);
			String password = cell.getStringCellValue();
			
			
			data.put(username, password);
			System.out.println("Username: "+username+" Password: "+password);
		}
		
		wb.close();
		fis.close();

		System.out.println("SuccessFully read data !!!");
		return data;
	}

}
