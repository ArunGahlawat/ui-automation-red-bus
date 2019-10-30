package com.nagarro.pages;

import com.nagarro.locators.Search;
import com.nagarro.utils.Common;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import static com.nagarro.locators.Search.CLEAR_FILTERS;
import static com.nagarro.locators.Search.FILTERED_BUS_FOUND;

public class SearchPage extends BasePage {
	public SearchPage() {
	}

	public SearchPage verifyFilteredResultsAppear() {
		Assert.assertTrue(verifyElementIsVisible(FILTERED_BUS_FOUND.value()));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	public SearchPage applyBusTypeFilter(String busType) {
		String busTypeXpath = Search.BUS_TYPE_FILTER.value().replace("DESIRED_BUS_TYPE", busType);
		Assert.assertTrue(verifyElementIsVisible(busTypeXpath));
		click(busTypeXpath);
		waitUntilElementIsVisible(Search.CLEAR_FILTERS.value());
		return this;
	}

	public SearchPage applyAmenitiesFilter(String amenities) {
		String amenitiesXpath = Search.AMENITIES_FILTER.value().replace("DESIRED_AMENITIES", amenities);
		Assert.assertTrue(verifyElementIsVisible(amenitiesXpath));
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Common.driver;
		javascriptExecutor.executeScript("scroll(0, 450);");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(amenitiesXpath);
		waitUntilElementIsVisible(Search.CLEAR_FILTERS.value());
		return this;
	}

	public SearchPage resetFilters() {
		Assert.assertTrue(verifyElementIsVisible(Search.CLEAR_FILTERS.value()));
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Common.driver;
		javascriptExecutor.executeScript("scroll(0, -450);");
		click(CLEAR_FILTERS.value());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
}
