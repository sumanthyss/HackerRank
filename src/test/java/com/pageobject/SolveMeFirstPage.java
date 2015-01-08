package com.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class SolveMeFirstPage {
	WebDriver driver;

	public SolveMeFirstPage(WebDriver driver){
		this.driver = driver;
	}

	public void runCode(){

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement runCodeButton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.xpath("//*[@id='codeshell-wrapper']/div[4]/div[1]/button[1]"));
			}
		});

		runCodeButton.click();
	}

	public String getAck(){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement ack = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.xpath("//*[@id='testcase1']/p"));
			}
		});
		return ack.getText();
	}

}
