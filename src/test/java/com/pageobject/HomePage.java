package com.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage clickLoginButton(){
		driver.get("https://www.hackerrank.com/");

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement loginButton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.linkText("Log In"));
			}
		});

		loginButton.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
}
