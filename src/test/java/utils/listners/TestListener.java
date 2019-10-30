package utils.listners;

import com.nagarro.utils.Common;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.extentReports.ExtentManager;
import utils.extentReports.ExtentTestManager;

import java.util.List;

public class TestListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	private static String getTestMethodDescription(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(getTestMethodName(result), getTestMethodDescription(result));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		List<String> messages = Reporter.getOutput(result);
		for (String message : messages) {
			ExtentTestManager.getTest().log(LogStatus.PASS, message);
		}
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String base64Screenshot =
				"data:image/png;base64," + ((TakesScreenshot) Common.driver).getScreenshotAs(OutputType.BASE64);
		List<String> messages = Reporter.getOutput(result);
		for (String message : messages) {
			ExtentTestManager.getTest().log(LogStatus.PASS, message);
		}
		ExtentTestManager.getTest().log(LogStatus.ERROR, result.getThrowable());
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		List<String> messages = Reporter.getOutput(result);
		for (String message : messages) {
			ExtentTestManager.getTest().log(LogStatus.INFO, message);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, result.getThrowable());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test Failed but in Pass percentage");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Starting test executions : " + context.getName());
		context.setAttribute("WebDriver", Common.driver);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Finishing test execution" + context.getName());
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}
}
