package utils.extentReports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
	static Map extentTestMap = new HashMap();
	static ExtentReports extentReports = ExtentManager.getReporter();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extentReports.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extentReports.startTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
