package utils.extentReports;

import com.nagarro.utils.Common;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extentReports;

	public synchronized static ExtentReports getReporter() {
		if (extentReports == null) {
			String workingDir = System.getProperty("user.dir");
			extentReports = new ExtentReports(
					Common.getConfig("config").getString("TEST_RESULTS_PATH") +
							System.getProperty("file.separator") + "ExtentReportResults.html", true);
		}
		return extentReports;
	}
}
