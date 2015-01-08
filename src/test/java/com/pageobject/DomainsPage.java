package com.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class DomainsPage {
	WebDriver driver;

	public DomainsPage(WebDriver driver){
		this.driver = driver;
	}

	public String getLoggedInUser(){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		WebElement loggedInUserField = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.className("mmR"));
			}
		});

		return loggedInUserField.getText();
	}

	public WarmupSubDomain algorithmsWarmup(){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement warmupLink = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.linkText("Warmup"));
			}
		});

		warmupLink.click();
		return PageFactory.initElements(driver, WarmupSubDomain.class);
	}



}
