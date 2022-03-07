package com.quantum.steps;

import com.perfecto.PerfectoLabUtils;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.CloudUtils;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

@QAFTestStepProvider
public class TestStepDefs
{
    @Then("^I upload \"(.*?)\" to \"(.*?)\"")
    public void IUploadAndInstallApp(String path, String repoPath) throws IOException {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        String username = getBundle().getPropertyValue("remote.user");
        String remoteServer = getBundle().getPropertyValue("remote.server");
        String cloudName = remoteServer.substring(8, remoteServer.indexOf("/nexperience"));
        String securityToken = getBundle().getPropertyValue("driver.capabilities.securityToken");
        CloudUtils.uploadMedia(cloudName, username, securityToken, path, repoPath);
    }

    @Then("^I test HL CET page")
    public void ITestHLCETPage() throws IOException {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        driver.get("https://integration.hl.co.uk/investment-services/isa/apply-now?aem");
        driver.manage().deleteAllCookies();
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");
    }
}
