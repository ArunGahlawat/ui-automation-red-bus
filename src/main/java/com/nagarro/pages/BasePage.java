package com.nagarro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.nagarro.utils.Common.driver;
import static com.nagarro.utils.Common.getConfig;

public class BasePage {
	public static WebDriverWait wait;

	public BasePage() {
		wait = new WebDriverWait(driver, getConfig("config").getInt("DEFAULT_WEB_DRIVER_WAIT_SECONDS"));
	}

	//Click Method
	public static void click(String xpath) {
		By elementLocator = By.xpath(xpath);
		driver.findElement(elementLocator).click();
	}

	//Input Text
	public static void inputText(String xpath, String text) {
		By elementLocator = By.xpath(xpath);
		driver.findElement(elementLocator).sendKeys(text);
	}

	//Read Text
	public static String readText(String xpath) {
		By elementLocator = By.xpath(xpath);
		return driver.findElement(elementLocator).getText();
	}

	public static void clearText(String xpath) {
		By elementLocator = By.xpath(xpath);
		driver.findElement(elementLocator).clear();
	}

	//Wait
	public static void waitUntilElementIsVisible(String xpath) {
		By elementLocator = By.xpath(xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
	}

	public static void waitUntilElementIsNotVisible(String xpath) {
		By elementLocator = By.xpath(xpath);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
	}

	public static boolean verifyElementIsPresent(String xpath) {
		By elementLocator = By.xpath(xpath);
		try {
			driver.findElement(elementLocator);
		} catch (NoSuchElementException nse) {
			return false;
		}
		return true;
	}

	public static boolean verifyElementIsNotPresent(String xpath) {
		By elementLocator = By.xpath(xpath);
		try {
			driver.findElement(elementLocator);
		} catch (NoSuchElementException nse) {
			return true;
		}
		return false;
	}

	public static boolean verifyElementIsVisible(String xpath) {
		By elementLocator = By.xpath(xpath);
		return driver.findElement(elementLocator).isDisplayed();
	}

	public static boolean verifyElementIsNotVisible(String xpath) {
		return !verifyElementIsVisible(xpath);
	}

	public static boolean verifyElementIsEnabled(String xpath) {
		By elementLocator = By.xpath(xpath);
		return driver.findElement(elementLocator).isEnabled();
	}

	public static boolean verifyElementIsNotEnabled(String xpath) {
		return !verifyElementIsEnabled(xpath);
	}
}
