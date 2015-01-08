package com.hackerrank;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.intuit.tools.commontestbase.IBrowserTestNGBase;
import com.pageobject.DomainsPage;
import com.pageobject.HomePage;
import com.pageobject.LoginPage;
import com.pageobject.SolveMeFirstPage;
import com.pageobject.WarmupSubDomain;

public class RunCodeTest extends IBrowserTestNGBase{

	//1. Login
	//2. Assert for the logged in user
	//3. Choose Algorithms domain
	//4. Choose Warmup subdomain
	//5. Run the code
	//6. Assert for the success message

	@Test(dataProvider = "from-json")
	public void testHelloWorld(WebDriver driver) throws Exception{
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = homePage.clickLoginButton();
		DomainsPage domainsPage = loginPage.login("sumanthyss@gmail.com", "hackhack");
		Assert.assertEquals("sumanthyss", domainsPage.getLoggedInUser());
		WarmupSubDomain warmupSubDomain = domainsPage.algorithmsWarmup();
		SolveMeFirstPage solveMeFirstPage = warmupSubDomain.solveMeFirst();
		solveMeFirstPage.runCode();
		Assert.assertEquals("Congratulations, you passed this test case!", solveMeFirstPage.getAck());
	}

}
