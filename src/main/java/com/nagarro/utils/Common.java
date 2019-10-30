package com.nagarro.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Common {

	public static WebDriver driver;
	private static Log log = LogFactory.getLog(Common.class);
	private static String driverPath = null;
	public static Browser browser = null;

	public static Configuration getConfig(String propertiesFile) {
		log.debug("Loading properties : " + propertiesFile);
		Parameters params = new Parameters();
		propertiesFile = propertiesFile + ".properties";
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
				new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
						.configure(params.properties().setFileName(propertiesFile));
		Configuration config = null;
		try {
			config = builder.getConfiguration();
		} catch (ConfigurationException cex) {
			Assert.fail("Properties File : " + propertiesFile + " is not loaded due to error :" + cex.getMessage());
		}
		return config;
	}

	public static WebDriver setChromeDriver() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("profile.password_manager_enabled", 0);
		prefs.put("credentials_enable_service", 0);
		try {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("disable-notifications");
			chromeOptions.setExperimentalOption("prefs", prefs);
			if (StringUtils.isNotBlank(driverPath))
				System.setProperty("webdriver.chrome.driver", driverPath);
			return new ChromeDriver(chromeOptions);
		} catch (Exception e) {
			Assert.fail("Error initialising chrome driver. Please provide system properties 'browser=chrome' and " +
					"'driverPath=<path of chromedriver>'\n\nException : " + e.getMessage());
		}
		return null;
	}

	public static WebDriver setFirefoxDriver() {
		try {
			if (StringUtils.isNotBlank(driverPath))
				System.setProperty("webdriver.gecko.driver", driverPath);
			FirefoxOptions options = new FirefoxOptions().setProfile(new FirefoxProfile());
			return new FirefoxDriver(options);
		} catch (Exception e) {
			Assert.fail("Error initialising firefox driver. Please provide system properties 'browser=firefox' and " +
					"'driverPath=<path of gecodriver>'\n\nException : " + e.getMessage());
		}
		return null;
	}

	public static WebDriver setIEDriver() {
		try {
			if (StringUtils.isNotBlank(driverPath))
				System.setProperty("webdriver.ie.driver", driverPath);
			return new InternetExplorerDriver();
		} catch (Exception e) {
			Assert.fail("Error initialising IE driver. Please provide system properties 'browser=ie' and " +
					"'driverPath=<path of iedriver>'\n\nException : " + e.getMessage());
		}
		return null;
	}

	public static void initBrowser() {
		if (browser == null) {
			driverPath = System.getProperty("driverPath");
			try {
				browser = Browser.valueOf(System.getProperty("browser").toUpperCase());
			} catch (Exception e) {
				log.info("System property 'browser' not specified. Using default : '" +
						getConfig("config").getString("DEFAULT_BROWSER") + "' from config.properties");
				browser = Browser.valueOf(getConfig("config").getString("DEFAULT_BROWSER"));
			}
			switch (browser) {
				case CHROME:
					driver = setChromeDriver();
					break;
				case FIREFOX:
					driver = setFirefoxDriver();
					break;
				case IE:
					driver = setIEDriver();
					break;
				default:
					Assert.fail("Wrong browser provided in system properties");
					break;
			}
			if (driver != null) {
				driver.manage().timeouts().implicitlyWait(getConfig("config").getInt(
						"DEFAULT_IMPLICIT_WAIT_SECONDS"), TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}
		}
	}

	public static void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
