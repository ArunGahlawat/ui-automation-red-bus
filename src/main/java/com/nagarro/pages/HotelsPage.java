package com.nagarro.pages;

import com.nagarro.locators.Home;
import org.testng.Assert;
import org.testng.Reporter;

import static com.nagarro.locators.Home.LINK_BUSES;
import static com.nagarro.locators.Hotels.PR_BANNER;

public class HotelsPage extends BasePage {
	public HomePage goToHomePage() {
		waitUntilElementIsVisible(LINK_BUSES.value());
		click(LINK_BUSES.value());
		waitUntilElementIsVisible(Home.TEXT_OPTIONAL.value());
		return new HomePage();
	}

	public HotelsPage verifyHotelsPageLoaded() {
		Reporter.log("Verifying Hotels page loaded successfully");
		Assert.assertTrue(verifyElementIsVisible(PR_BANNER.value()));
		return this;
	}
}
