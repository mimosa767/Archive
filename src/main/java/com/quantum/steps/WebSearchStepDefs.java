package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

@QAFTestStepProvider
public class WebSearchStepDefs
{
    @Then("^I navigate to sites:$")
    public void INavigateToSites(List<String> results)
    {
        QAFExtendedWebElement searchResultElement;
        for (String result : results)
        {
            QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
//            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            if (DeviceUtils.getDeviceProperty("os").contains("iOS"))
            {
//                DeviceUtils.switchToContext("WEBVIEW");
                try {
                    driver.navigate().to(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else driver.get(result);
        }
    }
}
