###Prerequisites :
- Maven : version 3 - Download from https://maven.apache.org/download.cgi
	> Ensure maven binary is in system PATH. use this guide to setup ` https://maven.apache
	.org/guides/getting-started/windows-prerequisites.html`
- Java SDK : version 8 - Download from https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	> Ensure JAVA_HOME is set in system path. verify by running `java --version` in cmd
	
- ####Desired Webdriver binaries
	- Chromedriver : `https://chromedriver.storage.googleapis.com/index.html?path=75.0.3770.8/`
    - GeckoDriver : `https://github.com/mozilla/geckodriver/releases`
    - IEDriver :  `https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/`
	
###Steps to execute
- #####Required system properties
	- `browser` : can be `chrome` / `firefox` / `ie` : name of browser to execute tests on
	- `driverPath` : Absolute path of webdriver binary file eg. `c:\Users\Shivani\Desktop\chromedriver`
	- `surefire.suiteXmlFiles` : can be `testng.xml` / `testng_about.xml` / `testng_home.xml` / `testng_search.xml` :
	 name of testng xml file to execute tests from

- **Test execution**
	> clean compile test -Dsurefire.suiteXmlFiles=testng.xml -Dbrowser=chrome 
	-DdriverPath="C:\Users\Shivani\Desktop\chromedriver"

###Project structure
- `src\main\resources\locators\<pagename>.properties` : xpaths of the specific page
- `src\main\java\com\nagarro\locators\<pagename>.java` : enums corresponding to xpaths
- `src\main\java\com\nagarro\pages\<pagename>.java` : page related action definitions to be used in test cases 
- `src\main\java\com\nagarro\utils\Browser.java` : Supported Browser enums
- `src\main\java\com\nagarro\utils\Common.java` : Common methods for webdriver setup / teardowns, loading configurations
- `src\main\resources\testData\<pagename>.properties` : Test data to be used on specific page
- `src\main\resources\config.properties` : common configurations like urls, default browser, test results path and 
driver waits
- `test\java\tests\<pagename>.java` : Test suit for specific page
- `test\java\utils\extentReports\` : Methods for extent report generation
- `test\java\utils\listners\` : TestNG listners for reporting

### Credits and Externel libraries
- **selenium-java** : `https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java`
- **testng** : `https://mvnrepository.com/artifact/org.testng/testng`
- **commons-lang3** : `https://mvnrepository.com/artifact/org.apache.commons/commons-lang3`
- **commons-logging** : `https://mvnrepository.com/artifact/commons-logging/commons-logging`
- **commons-configuration2** : `https://mvnrepository.com/artifact/org.apache.commons/commons-configuration2`
- **commons-beanutils** : `https://mvnrepository.com/artifact/commons-beanutils/commons-beanutils`
- **extentreports** : `https://mvnrepository.com/artifact/com.relevantcodes/extentreports`

### Owner
**Shivani Rathee** - `shivani.rathee@nagarro.com`