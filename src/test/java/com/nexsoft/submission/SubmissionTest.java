package com.nexsoft.submission;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SubmissionTest {
	public WebDriver driver;
	
	@BeforeClass
	public void init() {
		System.setProperty("url", "http://localhost/cicool");
		System.setProperty("webdriver.chrome.driver", "C:\\Antoni\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@Test(priority = 0)
	public void loginAndGoToCrud() {//for login first and into CRUD menu
		//login
		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();
	  	driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("antoni.nexsoft@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("antoni2000");
		
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		//go to CRUD menu and into userbiodata
		driver.findElement(By.xpath("//span[normalize-space()='CRUD Builder']")).click();
		driver.findElement(By.xpath("//tbody/tr[3]/td[5]/a[1]")).click();//go into user biodata table
	}
	
	@Test(priority = 1, dataProvider = "getMockData", dataProviderClass = com.nexsoft.submission.dataprovider.DataProviderSubmission.class)
	public void inputData(String param1, String param2, String param3, String param4) {
		//click add new data
		driver.findElement(By.xpath("//a[@id='btn_add_new']")).click();
		
		//input/upload file photo
		driver.findElement(By.cssSelector("input[title='file input']")).sendKeys("C:\\Antoni\\selenium\\profile.jpg");
		
		//input field text
		driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys(param1);
		driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys(param2);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(param3);
		driver.findElement(By.xpath("//input[@id='gender']")).sendKeys(param4);
		
		//click save and go to list (dengan type keyboard shortcut
		String kbType = Keys.chord(Keys.CONTROL, "d");
		driver.findElement(By.xpath("//input[@id='gender']")).sendKeys(kbType);
		
		//test kalau sudah di save di database
		System.out.println("Complete input for: "+param1+" "+param2);
		//karena terlalu cepat jadi di wait dulu
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
