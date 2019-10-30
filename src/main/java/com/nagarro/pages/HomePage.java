package com.nagarro.pages;

import com.nagarro.locators.AboutUs;
import com.nagarro.locators.Home;
import com.nagarro.locators.Hotels;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.nagarro.locators.Home.*;
import static com.nagarro.locators.Search.FILTERED_BUS_FOUND;
import static com.nagarro.utils.Common.driver;
import static com.nagarro.utils.Common.getConfig;

public class HomePage extends BasePage {
	public HomePage() {
	}

	public HomePage goToRedBus() {
		Reporter.log("Loadinng homepage");
		driver.get(getConfig("config").getString("HOME_PAGE_URL"));
		waitUntilElementIsVisible(Home.TEXT_OPTIONAL.value());
		Reporter.log("Load successful");
		return this;
	}

	public HomePage verifyHomePageLoaded() {
		Reporter.log("Verifying search button is present on home page");
		Assert.assertTrue(verifyElementIsVisible(SEARCH_BUS_BUTTON.value()));
		return this;
	}

	public HotelsPage goToHotelsPage() {
		Reporter.log("Navigating to Hotels page by clicking link on home page");
		waitUntilElementIsVisible(LINK_HOTELS.value());
		click(LINK_HOTELS.value());
		waitUntilElementIsVisible(Hotels.PR_BANNER.value());
		return new HotelsPage();
	}

	public AboutUsPage goToAboutUsPage() {
		driver.get(getConfig("config").getString("ABOUT_US_URL"));
		waitUntilElementIsVisible(AboutUs.CEO_TITLE.value());
		return new AboutUsPage();
	}

	public HomePage inputOrigin(String origin) {
		Reporter.log("Entering origin : " + origin);
		waitUntilElementIsVisible(SEARCH_BUS_ORIGIN.value());
		inputText(SEARCH_BUS_ORIGIN.value(), origin);
		return this;
	}

	public HomePage clearOrigin() {
		clearText(SEARCH_BUS_ORIGIN.value());
		return this;
	}

	public HomePage clearDestination() {
		clearText(SEARCH_BUS_DESTINATION.value());
		return this;
	}


	public HomePage selectOptionFromSuggestion() {
		Assert.assertTrue(verifyElementIsVisible(SELECTED_CITY.value()));
		click(SELECTED_CITY.value());
		return this;
	}

	public HomePage verifyNoSuggestionsArePresent() {
		Assert.assertTrue(verifyElementIsNotPresent(SELECTED_CITY.value()));
		return this;
	}

	public HomePage inputDestination(String destination) {
		Reporter.log("Entering destination : " + destination);
		waitUntilElementIsVisible(SEARCH_BUS_DESTINATION.value());
		inputText(SEARCH_BUS_DESTINATION.value(), destination);
		return this;
	}

	public HomePage selectDate(String onwardDate, boolean isOnward) {
		String[] dateParts = onwardDate.split("-");
		List<Integer> desiredDate = new ArrayList<>();
		if (dateParts.length != 3 ||
				(Integer.parseInt(dateParts[0]) < 1 || Integer.parseInt(dateParts[0]) > 31) ||
				(Integer.parseInt(dateParts[1]) < 6 || Integer.parseInt(dateParts[1]) > 12) ||
				(Integer.parseInt(dateParts[2]) != 2019)) {
			Assert.fail("wrong date provided");
		} else {
			for (int i = 0; i < dateParts.length; i++)
				desiredDate.add(i, Integer.parseInt(dateParts[i]));
		}
		if (isOnward) {
			waitUntilElementIsVisible(SEARCH_BUS_ONWARD_DATE_LABEL.value());
			click(SEARCH_BUS_ONWARD_DATE_LABEL.value());
			waitUntilElementIsVisible(SEARCH_BUS_ONWARD_DATE_ACTIVE_CALENDAR.value());
		} else {
			waitUntilElementIsVisible(SEARCH_BUS_RETURN_DATE_LABEL.value());
			click(SEARCH_BUS_RETURN_DATE_LABEL.value());
			waitUntilElementIsVisible(SEARCH_BUS_RETURN_DATE_ACTIVE_CALENDAR.value());
		}
		navigateCalendar(desiredDate);
		click(TEXT_OPTIONAL.value());
		return this;
	}

	public void navigateCalendar(List<Integer> desiredDate) {
		HashMap<String, Integer> monthMap = new HashMap<>();
		monthMap.put("Jun", 6);
		monthMap.put("Jul", 7);
		monthMap.put("Aug", 8);
		monthMap.put("Sep", 9);
		monthMap.put("Oct", 10);
		monthMap.put("Nov", 11);
		monthMap.put("Dec", 12);
		waitUntilElementIsVisible(SEARCH_BUS_MONTH_TITLE.value());
		String currentDate = readText(SEARCH_BUS_MONTH_TITLE.value());
		String currentMonth = currentDate.split(" ")[0].trim();
		int currentIntegerMonth = monthMap.get(currentMonth);
		int desiredMonth = desiredDate.get(1);
		if (desiredMonth > currentIntegerMonth) {
			for (int i = 1; i <= (desiredMonth - currentIntegerMonth); i++) {
				waitUntilElementIsVisible(SEARCH_BUS_CALENDAR_NEXT_MONTH.value());
				click(SEARCH_BUS_CALENDAR_NEXT_MONTH.value());
			}
		} else if (desiredMonth < currentIntegerMonth) {
			for (int i = 1; i <= (currentIntegerMonth - desiredMonth); i++) {
				waitUntilElementIsVisible(SEARCH_BUS_CALENDAR_PREVIOUS_MONTH.value());
				click(SEARCH_BUS_CALENDAR_PREVIOUS_MONTH.value());
			}
		}
		String desiredDayXpath = SEARCH_BUS_CALENDAR_DAY.value().replace("DESIRED_DAY", desiredDate.get(0).toString());
		Assert.assertTrue(verifyElementIsVisible(desiredDayXpath));
		click(desiredDayXpath);
	}

	public HomePage clickSearch() {
		Assert.assertTrue(verifyElementIsVisible(SEARCH_BUS_BUTTON.value()));
		click(SEARCH_BUS_BUTTON.value());
		return this;
	}

	public SearchPage searchBuses() {
		clickSearch();
		waitUntilElementIsVisible(FILTERED_BUS_FOUND.value());
		return new SearchPage();
	}

	public HomePage verifyError(String fieldName) {
		if (StringUtils.isNotBlank(fieldName) && fieldName.trim().equalsIgnoreCase("DESTINATION"))
			Assert.assertTrue(verifyElementIsVisible(SEARCH_BUS_DESTINATION_ERROR.value()));
		else if (StringUtils.isNotBlank(fieldName) && fieldName.trim().equalsIgnoreCase("ORIGIN"))
			Assert.assertTrue(verifyElementIsVisible(SEARCH_BUS_ORIGIN_ERROR.value()));
		return this;
	}

}
