package com.mycompany.myproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mycompany.myproject.sample.BAIDU;
import com.mycompany.myproject.utilities.server.TestServerUtils;
import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;

public class TESTDemo {

	    @BeforeClass
	    public void startLocalServer () throws Exception {
	        TestServerUtils.startServer();
	    }

	    @Test
	    @WebTest
	    public void myTest () {
	  
			BAIDU BaiDu = new BAIDU();
			Grid.open("https://baidu.com");
			BaiDu.getFirstNameTextField().click(BaiDu);
			BaiDu.getFirstNameTextField().type("aaa");
			////BaiDu.getFirstNameTextField().click(BaiDu);
			
		
	    }

}
