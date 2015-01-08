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

public class WarmupSubDomain {
	WebDriver driver;

	public WarmupSubDomain(WebDriver driver){
		this.driver = driver;
	}

	public SolveMeFirstPage solveMeFirst(){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		WebElement solveMeFirstLink = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.linkText("Solve me first"));
			}
		});

		solveMeFirstLink.click();
		return PageFactory.initElements(driver, SolveMeFirstPage.class);
	}

}
