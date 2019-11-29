/*-------------------------------------------------------------------------------------------------------------------*\
|  Copyright (C) 2014 PayPal                                                                                          |
|                                                                                                                     |
|  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance     |
|  with the License.                                                                                                  |
|                                                                                                                     |
|  You may obtain a copy of the License at                                                                            |
|                                                                                                                     |
|       http://www.apache.org/licenses/LICENSE-2.0                                                                    |
|                                                                                                                     |
|  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed   |
|  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for  |
|  the specific language governing permissions and limitations under the License.                                     |
\*-------------------------------------------------------------------------------------------------------------------*/

package com.mycompany.myproject.sample.selion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mycompany.myproject.sample.BAIDU;
import com.mycompany.myproject.utilities.server.TestServerUtils;
import com.paypal.selion.annotations.WebTest;

/**
 * This sample demonstrates the Page Object Model that SeLion supports for interacting with web pages.
 * It leverages on the page classes that were created from selion code generator plugin.
 *
 */
public class UIFlowUsingSeLionPageObjectsTest {

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

    @AfterClass
    public void shutdownLocalServer () throws Exception {
        TestServerUtils.stopServer();
    }

}
