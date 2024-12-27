package Pavanproj.TestComponents;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PavanProject.resourses.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportsNG.GetReportObject();
	ThreadLocal<ExtentTest> extentest = new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
    	test = extent.createTest(result.getMethod().getMethodName());
    	
    	extentest.set(test);//it will create unique ids
        //System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	extentest.get().log(Status.PASS, "Test is passed");
       // System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	extentest.get().fail(result.getThrowable());
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String filePath=null;
    	try {
			filePath= GetScreenShot(result.getMethod().getMethodName(),driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	extentest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
       // System.out.println("Test failed: " + result.getName());
        // You could add code here to take a screenshot, log the failure, etc.
    }

    @Override
    public void onTestSkipped(ITestResult result) {
       // System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
       // System.out.println("Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
       // System.out.println("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
      //  System.out.println("Test suite finished: " + context.getName());
    	extent.flush();
        
    }
	
}
