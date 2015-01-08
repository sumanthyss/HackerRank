package com.hackerrank;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.intuit.tools.commontestbase.IBrowserTestNGBase;
import com.pageobject.DomainsPage;
import com.pageobject.HomePage;
import com.pageobject.LoginPage;
import com.pageobject.SolveMeFirstPage;
import com.pageobject.WarmupSubDomain;

public class RunCodeTest extends IBrowserTestNGBase{

	@DataProvider(name = "signInData")
    public Object[][] createData(Method testMethod) {
        Object[][] myData = new Object[][] {
            {"sumanthyss@gmail.com", "hackhack"},
        };
        return mergeWithTestPlatforms(testMethod, myData);
	}

	//1. Login
	//2. Assert for the logged in user
	//3. Choose Algorithms domain
	//4. Choose Warmup subdomain
	//5. Run the code
	//6. Assert for the success message

	@Test(dataProvider = "signInData", enabled = true)
	public void testRunCode(WebDriver driver, String userId, String password) throws Exception{

		if (driver instanceof RemoteWebDriver) {
            String jobid = ((RemoteWebDriver)driver).getSessionId().toString();
       System.out.println(("SauceOnDemandSessionID" + "=" + jobid + "job-name" + "=" + "testRunCode"));
        }

		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = homePage.clickLoginButton();
		DomainsPage domainsPage = loginPage.login(userId, password);
		Assert.assertEquals("sumanthyss", domainsPage.getLoggedInUser());
		WarmupSubDomain warmupSubDomain = domainsPage.algorithmsWarmup();
		SolveMeFirstPage solveMeFirstPage = warmupSubDomain.solveMeFirst();
		solveMeFirstPage.runCode();
		Assert.assertEquals("Congratulations, you passed this test case!", solveMeFirstPage.getAck());
	}

}
