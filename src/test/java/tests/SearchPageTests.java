package tests;

import com.nagarro.pages.HomePage;
import com.nagarro.utils.Common;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.nagarro.utils.Common.getConfig;

public class SearchPageTests {

	@BeforeSuite(alwaysRun = true)
	public void suiteSetup() {
		Common.initBrowser();
	}

	@AfterSuite(alwaysRun = true)
	public void suiteTeardown() {
		Common.closeBrowser();
	}

	@Test(description = "Verify applying only 1 filter of Bus Type")
	public void verifyBusTypeFilters() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.verifyHomePageLoaded()
				.clearOrigin()
				.clearDestination()
				.inputOrigin(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ORIGIN"))
				.selectOptionFromSuggestion()
				.inputDestination(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("DESTINATION"))
				.selectOptionFromSuggestion()
				.selectDate(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ONWARD_DATE"), true)
				.searchBuses()
				.verifyFilteredResultsAppear()
				.applyBusTypeFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("BUS_TYPE"))
				.verifyFilteredResultsAppear();
	}

	@Test(description = "Verify applying more than 1 bus type filters")
	public void verifyMoreThan1BusTypeFilters() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.verifyHomePageLoaded()
				.clearOrigin()
				.clearDestination()
				.inputOrigin(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ORIGIN"))
				.selectOptionFromSuggestion()
				.inputDestination(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("DESTINATION"))
				.selectOptionFromSuggestion()
				.selectDate(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ONWARD_DATE"), true)
				.searchBuses()
				.verifyFilteredResultsAppear()
				.applyBusTypeFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("BUS_TYPE"))
				.verifyFilteredResultsAppear()
				.applyBusTypeFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("BUS_TYPE_2"))
				.verifyFilteredResultsAppear();

	}

	@Test(description = "Verify applying amenities filters")
	public void verifyAmenitiesFilter() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.verifyHomePageLoaded()
				.clearOrigin()
				.clearDestination()
				.inputOrigin(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ORIGIN"))
				.selectOptionFromSuggestion()
				.inputDestination(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("DESTINATION"))
				.selectOptionFromSuggestion()
				.selectDate(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ONWARD_DATE"), true)
				.searchBuses()
				.verifyFilteredResultsAppear()
				.applyAmenitiesFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("AMENITIES"))
				.verifyFilteredResultsAppear();
	}

	@Test(description = "Verify applying Bus Type filter along with amenities filter ")
	public void verifyMixedFilters() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.verifyHomePageLoaded()
				.clearOrigin()
				.clearDestination()
				.inputOrigin(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ORIGIN"))
				.selectOptionFromSuggestion()
				.inputDestination(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("DESTINATION"))
				.selectOptionFromSuggestion()
				.selectDate(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ONWARD_DATE"), true)
				.searchBuses()
				.verifyFilteredResultsAppear()
				.applyBusTypeFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("BUS_TYPE"))
				.verifyFilteredResultsAppear()
				.applyAmenitiesFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("AMENITIES"))
				.verifyFilteredResultsAppear();
	}

	@Test(description = "Verify applying Bus Type filter along with amenities filter ")
	public void verifyResetFilters() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.verifyHomePageLoaded()
				.clearOrigin()
				.clearDestination()
				.inputOrigin(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ORIGIN"))
				.selectOptionFromSuggestion()
				.inputDestination(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("DESTINATION"))
				.selectOptionFromSuggestion()
				.selectDate(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ONWARD_DATE"), true)
				.searchBuses()
				.verifyFilteredResultsAppear()
				.applyBusTypeFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("BUS_TYPE"))
				.verifyFilteredResultsAppear()
				.applyAmenitiesFilter(getConfig("testData"+System.getProperty("file.separator")+"Filters").getString("AMENITIES"))
				.verifyFilteredResultsAppear()
				.resetFilters();
	}
}
