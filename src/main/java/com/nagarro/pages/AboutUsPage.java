package com.nagarro.pages;

import com.nagarro.locators.AboutUs;
import org.testng.Assert;

public class AboutUsPage extends BasePage {
	public AboutUsPage() {
	}

	public AboutUsPage verifyCeoDetails(String expectedCeoDetails) {
		verifyElementIsVisible(AboutUs.CEO_TITLE.value());
		Assert.assertTrue(verifyElementIsVisible(AboutUs.CEO_TITLE.value()));
		Assert.assertTrue(verifyElementIsEnabled(AboutUs.CEO_TITLE.value()));
		String actualCeoDetails = readText(AboutUs.CEO_TITLE.value());
		actualCeoDetails = actualCeoDetails.replace(", CEO", "");
		Assert.assertEquals(actualCeoDetails, expectedCeoDetails);
		return this;
	}
}
