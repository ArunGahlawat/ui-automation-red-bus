package tests;

import com.nagarro.pages.HomePage;
import com.nagarro.utils.Common;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.nagarro.utils.Common.getConfig;

public class AboutUsPageTests {

	@BeforeSuite(alwaysRun = true)
	public void suiteSetup() {
		Common.initBrowser();
	}

	@AfterSuite(alwaysRun = true)
	public void suiteTeardown() {
		Common.closeBrowser();
	}

	@Test(description = "Verify About Us page is present and loaded with CEO and CTO details")
	public void verifyAboutUsPageDetails() {
		HomePage homePage = new HomePage();
		homePage.goToRedBus()
				.goToAboutUsPage()
				.verifyCeoDetails(getConfig("testData"+System.getProperty("file.separator")+"AboutUs").getString("CEO"));
	}
}
