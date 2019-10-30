package com.nagarro.locators;

import com.nagarro.utils.Common;

public enum Hotels {
	PR_BANNER("PR_BANNER"),
	;

	String locator;

	Hotels(String locator) {
		this.locator = locator;
	}

	public String value() {
		return Common.getConfig("locators" + System.getProperty("file.separator") + "HotelsPage").getString(this.locator);
	}
}
