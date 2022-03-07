package com.quantum.steps;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.testng.ReportiumTestNgListener;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.ReportUtils;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.TimeUnit;

@QAFTestStepProvider
public class NHSBSAStepDefs
{
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
    JavascriptExecutor js = DeviceUtils.getQAFDriver();

    @Then("^I check patient first name \"([^\"]*)\" last name \"([^\"]*)\" with DOB \"([^\"]*)\" at postcode \"([^\"]*)\" is exempt from costs$")
    public void ICheckPatientExempt(String firstName, String lastName, String dob, String postcode) {
        String day = dob.substring(0, dob.indexOf("/"));
        String month = dob.substring(dob.indexOf("/") + 1, dob.lastIndexOf("/"));
        String year = dob.substring(dob.lastIndexOf("/") + 1, dob.length());
        String message = "We couldn't match you to our records";

        Capabilities caps = DeviceUtils.getQAFDriver().getCapabilities();
        Set<String> capNames = caps.getCapabilityNames();

        if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS) && capNames.contains("bundleId") && caps.getCapability("bundleId").toString().toUpperCase().contains("CHROME"))
        {
            ReportUtils.logStepStart("Clear cookies");
            driver.waitForAllElementVisible();
            try { driver.findElementByXPath("//*[@label=\"I'm OK with analytics cookies\"]").click(); } catch (Exception ex) {;}
            driver.findElementByXPath("//*[@label=\"Start now\"]").click();
            try { Thread.sleep(3000); } catch (Exception ex) {;}

            ReportUtils.logStepStart("Enter date of birth");
            driver.waitForAllElementVisible();
            driver.findElementByXPath("//*[@label=\"main\"]//XCUIElementTypeOther[@label=\"What is your date of birth?\"]/XCUIElementTypeOther[4]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]").sendKeys(day);
            driver.findElementByXPath("//*[@label=\"main\"]//XCUIElementTypeOther[@label=\"What is your date of birth?\"]/XCUIElementTypeOther[4]/XCUIElementTypeOther[4]/XCUIElementTypeTextField[1]").sendKeys(month);
            driver.findElementByXPath("//*[@label=\"main\"]//XCUIElementTypeOther[@label=\"What is your date of birth?\"]/XCUIElementTypeOther[4]/XCUIElementTypeOther[6]/XCUIElementTypeTextField[1]").sendKeys(year);
            driver.findElementByXPath("//*[@label=\"Next\"]").click();
            try { Thread.sleep(3000); } catch (Exception ex) {;}

            ReportUtils.logStepStart("Enter full name");
            driver.waitForAllElementVisible();
            driver.findElementByXPath("//XCUIElementTypeOther[@label=\"What is your name?\"]/XCUIElementTypeOther[4]/XCUIElementTypeTextField[1]").sendKeys(firstName);
            driver.findElementByXPath("//XCUIElementTypeOther[@label=\"What is your name?\"]/XCUIElementTypeOther[7]/XCUIElementTypeTextField[1]").sendKeys(lastName);
            driver.findElementByXPath("//*[@label=\"Next\"]").click();
            try { Thread.sleep(3000); } catch (Exception ex) {;}

            ReportUtils.logStepStart("Enter postcode");
            driver.waitForAllElementVisible();
            driver.findElementByXPath("//XCUIElementTypeTextField").sendKeys(postcode);
            driver.findElementByXPath("//*[@label=\"Next\"]").click();
            try { Thread.sleep(5000); } catch (Exception ex) {;}

            ReportUtils.logStepStart("Check for message");
            driver.waitForAllElementVisible();
            ReportUtils.logAssert("Validating message", driver.findElementByXPath("//*[@value=\"We couldn't match you to our records\"]").getText().contains(message));

            // close tab
            driver.findElementByXPath("//*[@label=\"Show Tabs\"]").click();
            driver.findElementByXPath("//*[@name=\"GridCellCloseButtonIdentifier\"]").click();
            driver.findElementByXPath("//*[@label=\"Create new tab.\"]").click();
        }
        else if (capNames.contains("appPackage") && caps.getCapability("appPackage").toString().toUpperCase().contains("SBROWSER"))
        {
            ReportUtils.logStepStart("Clear cookies");
            driver.waitForAllElementVisible();
            try { samsungClick("OK with analytics"); } catch (Exception ex) {;}
            CommonSteps.ISwipeUpALot();
            CommonSteps.ISwipeUpALot();
            CommonSteps.ISwipeUpALot();
            samsungClick("Start now");

            ReportUtils.logStepStart("Enter date of birth");
            driver.waitForAllElementVisible();
            samsungClick("Day", "Above", "3%");
            CommonSteps.ISendKeys(day);
            samsungClick("Month", "Above", "3%");
            CommonSteps.ISendKeys(month);
            samsungClick("Year", "Above", "3%");
            CommonSteps.ISendKeys(year);
            samsungClick("Next");

            ReportUtils.logStepStart("Enter full name");
            driver.waitForAllElementVisible();
            samsungClick("First name", "Above", "3%");
            CommonSteps.ISendKeys(firstName);
            samsungClick("Last name", "Above", "3%");
            CommonSteps.ISendKeys(lastName);
            samsungClick("Next");

            ReportUtils.logStepStart("Enter postcode");
            driver.waitForAllElementVisible();
            samsungClick("What is", "Above", "5%");
            CommonSteps.ISendKeys(postcode);
            samsungClick("Next");

            ReportUtils.logStepStart("Check for message");
            try { Thread.sleep(5000); } catch (Exception ex) {;}
            driver.waitForAllElementVisible();
            samsungFind(message);

            // close tab
            DeviceUtils.closeApp(caps.getCapability("appPackage").toString(),"identifier");
            DeviceUtils.startApp(caps.getCapability("appPackage").toString(),"identifier");
        }
        else
        {
            ReportUtils.logStepStart("Clear cookies");
            driver.waitForAllElementVisible();
            try { driver.findElementByCssSelector("#nhsuk-cookie-banner__link_accept_analytics").click();} catch (Exception ex) {;}
            jsClickIfMac("#next-button");

            ReportUtils.logStepStart("Enter date of birth");
            driver.waitForAllElementVisible();
            driver.findElementByCssSelector("#dob-day").sendKeys(dob.substring(0, dob.indexOf("/")));
            driver.findElementByCssSelector("#dob-month").sendKeys(month);
            driver.findElementByCssSelector("#dob-year").sendKeys(year);
            jsClickIfMac("#next-button");

            ReportUtils.logStepStart("Enter full name");
            driver.waitForAllElementVisible();
            driver.findElementByCssSelector("#firstname").sendKeys(firstName);
            driver.findElementByCssSelector("#lastname").sendKeys(lastName);
            jsClickIfMac("#next-button");

            ReportUtils.logStepStart("Enter postcode");
            driver.waitForAllElementVisible();
            driver.findElementByCssSelector("#postcode").sendKeys(postcode);
            jsClickIfMac("#next-button");

            ReportUtils.logStepStart("Check for message");
            try { Thread.sleep(5000); } catch (Exception ex) {;}
            driver.waitForAllElementVisible();
            List<WebElement> list = driver.findElements(By.cssSelector("#content > div.grid-row > div > h1"));
            ReportUtils.logAssert("Validating message", list.size() > 0 && list.get(0).getText().contains(message));
        }
    }

    private void jsClickIfMac(String cssSelector)
    {
        String browserName =driver.getCapabilities().getCapability("browserName").toString();
        if (browserName.toUpperCase().contains("SAFARI")) {
            js.executeScript("arguments[0].click();", driver.findElementByCssSelector(cssSelector));
            try { Thread.sleep(3000); } catch (Exception ex) {;}
        } else {
            driver.findElementByCssSelector(cssSelector).click();
        }
    }

    private void samsungClick(String label)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("label", label);
        params.put("screen.top", "10%");
        params.put("screen.left", "0%");
        params.put("screen.width", "100%");
        params.put("screen.height", "80%");
        driver.executeScript("mobile:button-text:click", params);
        try { Thread.sleep(3000); } catch (Exception ex) {;}
    }

    private void samsungClick(String label, String direction, String offset)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("label", label);
        params.put("screen.top", "10%");
        params.put("screen.left", "0%");
        params.put("screen.width", "100%");
        params.put("screen.height", "80%");
        params.put("label.direction", direction);
        params.put("label.offset", offset);
        driver.executeScript("mobile:button-text:click", params);
        try { Thread.sleep(3000); } catch (Exception ex) {;}
    }

    private void samsungFind(String label) {
        Map<String, Object> params = new HashMap<>();
        params.put("content", label);
        driver.executeScript("mobile:text:find", params);
    }
}
