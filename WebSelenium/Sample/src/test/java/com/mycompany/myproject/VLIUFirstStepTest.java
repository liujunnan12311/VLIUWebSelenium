package com.mycompany.myproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VLIUFirstStepTest {
	public static void main(String [] args)
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://baidu.com");
		By intput = By.id("kw");
		WebElement element = driver.findElement(intput);
		element.click();
		element.sendKeys("vliu");
		element.click();
	}
	


}
