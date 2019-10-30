package tests;

import com.nagarro.pages.HomePage;
import com.nagarro.utils.Common;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.nagarro.utils.Common.getConfig;

public class HomePageTests {

	@BeforeSuite(alwaysRun = true)
	public void suiteSetup() {
		Common.initBrowser();
	}

	@AfterSuite(alwaysRun = true)
	public void suiteTeardown() {
		Common.closeBrowser();
	}

	@Test(description = "Verify cross page links between home page and hotels page are not broken")
	public void verifyLinksBetweenHomePageAndHotelsPage() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.verifyHomePageLoaded()
				.goToHotelsPage()
				.verifyHotelsPageLoaded()
				.goToHomePage()
				.verifyHomePageLoaded();
	}

	@Test(description = "verify search bus feature with only forward trip")
	public void verifySearchBusFeatureWithOnlyOnwardTrip() {
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
				.verifyFilteredResultsAppear();
	}

	@Test(description = "Verify search bus should show error on entering source and destination same")
	public void verifySearchBusGivesErrorOnSameOriginDestination() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.verifyHomePageLoaded()
				.clearOrigin()
				.clearDestination()
				.inputOrigin(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("DESTINATION"))
				.selectOptionFromSuggestion()
				.inputDestination(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("DESTINATION"))
				.verifyNoSuggestionsArePresent()
				.selectDate(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("ONWARD_DATE"), true)
				.clickSearch()
				.verifyError("DESTINATION");
	}

	@Test(description = "verify search bus feature with round trip option")
	public void verifySearchBusFeatureWithroundTrip() {
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
				.selectDate(getConfig("testData"+System.getProperty("file.separator")+"BusSearch").getString("RETURN_DATE"), false)
				.searchBuses()
				.verifyFilteredResultsAppear();
	}

}
