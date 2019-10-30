package com.nagarro.locators;

import com.nagarro.utils.Common;

public enum AboutUs {
	CEO_TITLE("CEO_TITLE"),
	;

	String locator;

	AboutUs(String locator) {
		this.locator = locator;
	}

	public String value() {
		return Common.getConfig("locators" + System.getProperty("file.separator") + "AboutUsPage").getString(this.locator);
	}
}
