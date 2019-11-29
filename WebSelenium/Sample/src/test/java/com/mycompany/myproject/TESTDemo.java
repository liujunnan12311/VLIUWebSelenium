package com.mycompany.myproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mycompany.myproject.sample.BAIDU;
import com.mycompany.myproject.utilities.server.TestServerUtils;
import com.paypal.selion.annotations.WebTest;

public class TESTDemo {

	    @BeforeClass
	    public void startLocalServer () throws Exception {
	        TestServerUtils.startServer();
	    }

	    @Test
	    @WebTest
	    public void myTest () {
	    	WebDriver driver = new ChromeDriver();
			driver.get("https://baidu.com");
			BAIDU BaiDu = new BAIDU();
			BaiDu.getFirstNameTextField().click();
			BaiDu.getFirstNameTextField().type("aaa");
			BaiDu.getFirstNameTextField().click(BaiDu);
			
		
	    }

}
