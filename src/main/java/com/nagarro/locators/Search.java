package com.nagarro.locators;

import com.nagarro.utils.Common;

public enum Search {
	FILTERED_BUS_FOUND("FILTERED_BUS_FOUND"),
	MODIFY_ONWARD_BUTTON("MODIFY_ONWARD_BUTTON"),
	ADD_A_RETURN_TICKET("ADD_A_RETURN_TICKET"),
	BUS_TYPE_FILTER("BUS_TYPE_FILTER"),
	AMENITIES_FILTER("AMENITIES_FILTER"),
	CLEAR_FILTERS("CLEAR_FILTERS"),
	SEARCH_BUTTON("SEARCH_BUTTON");

	String locator;

	Search(String locator) {
		this.locator = locator;
	}

	public String value() {
		return Common.getConfig("locators" + System.getProperty("file.separator") + "SearchPage").getString(this.locator);
	}
}
