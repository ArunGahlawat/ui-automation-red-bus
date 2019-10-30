package utils.listners;

import com.nagarro.utils.Common;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.extentReports.ExtentTestManager;

public class Retry implements IRetryAnalyzer {

	private static int maxTry = 1;
	private int count = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (count < maxTry) {
				count++;
				result.setStatus(ITestResult.FAILURE);
				String base64Screenshot =
						"data:image/png;base64," + ((TakesScreenshot) Common.driver).getScreenshotAs(OutputType.BASE64);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
						ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
				return false;
			}
		} else {
			result.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}
}
