package com.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class LoginPage {
	WebDriver driver;

	@FindBy(how = How.NAME, using = "commit")
	WebElement loginButton;

	public LoginPage(WebDriver driver){
		this.driver = driver;
	}

	public DomainsPage login(String userId, String password){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		WebElement userIdField = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.cssSelector("input[id=login]"));
			}
		});

		userIdField.sendKeys(userId);

		WebElement passwordField = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.id("password"));
			}
		});

		passwordField.sendKeys(password);

		loginButton.click();

		return PageFactory.initElements(driver, DomainsPage.class);

	}

}
