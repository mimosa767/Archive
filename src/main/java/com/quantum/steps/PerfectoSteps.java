package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.ReportUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

@QAFTestStepProvider
public class PerfectoSteps
{
  @Then("^I want to see the image \"(.*)\" with a threshold of \"(.*)\"$")
  public static void verifyImageWithThreshold(String img, String threshold)
  {
    String context = DeviceUtils.getCurrentContext();
    DeviceUtils.switchToContext("VISUAL");
    Map<String, Object> params = new HashMap<>();
    params.put("content", img);
    params.put("measurement", "accurate");
    params.put("source", "primary");
    params.put("threshold", threshold);
    params.put("timeout", 15);
    params.put("match", "bounded");
    //params.put("imageBounds.needleBound", 25);
    Object result = DeviceUtils.getQAFDriver().executeScript("mobile:checkpoint:image", params);
    DeviceUtils.switchToContext(context);
    Validator.verifyThat("Image " + img + " should be visible", result.toString(), Matchers.equalTo("true"));
  }

  @Given("^I restart application by name \"(.*)\"")
  public static void IRestartApplicationByName(String name)
  {
    DeviceUtils.closeApp(name, "name", true);
    DeviceUtils.startApp(name,"name");
  }

  @Given("^I restart application by ID \"(.*)\"")
  public static void IRestartApplicationByID(String identifier)
  {
    DeviceUtils.closeApp(identifier, "identifier", true);
    DeviceUtils.startApp(identifier,"identifier");
  }

  @Given("^I try to close application by name \"(.*)\" and ignore error")
  public static void ITryToCloseApplicationByNameAndIgnoreError(String name)
  {
    DeviceUtils.closeApp(name, "name", true);
  }

  @Given("^I try to close application by ID \"(.*)\" and ignore error")
  public void ITryToCloseApplicationByIDAndIgnoreError(String identifier)
  {
    DeviceUtils.closeApp(identifier, "identifier", true);
  }

  @Then("^I validate biometrics on \"(.*?)\"$")
  public static void IValidateBiometrics(String appName) throws Exception {
    try {
      DeviceUtils.switchToContext(Constants.NATIVEAPP);
      DeviceUtils.setSensorAuthentication("name", appName, Constants.SUCCESS, Constants.AUTHFAILED);
    } catch (Exception ex)
    {;}
  }

  @Then("^I audit accessibility on page \"(.*?)\"$")
  public static void IAuditAccessibility(String pageName)
  {
    DeviceUtils.checkAccessibility(pageName);
  }

  @When("^I start network virtualization with \"(.*?)\" profile$")
  public static void IStartNetworkVirtualisationWithProfile(String profile)
  {
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
    Map<String, Object> params = new HashMap<>();
    params.put("profile", profile);
    driver.executeScript(Constants.MOBILEVNETWORKSTART, params);
  }

  @When("^I start network virtualization with HAR capture$")
  public static void IStartNetworkVirtualisationWithHAR()
  {
    DeviceUtils.generateHAR();
  }

  @Then("^I stop network virtualization$")
  public static void IStopNetworkVirtualisation()
  {
    DeviceUtils.stopGenerateHAR();
  }

  @When("^I set the network packet loss to \"(.*?)\" Percent$")
  public static void ISetTheDevicePacketLossPercentage(String packetLoss)
  {
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PACKETLOSS, packetLoss);
    driver.executeScript(Constants.MOBILEVNETWORKSTART, params);
    params.remove(Constants.PACKETLOSS);
  }

  @When("^I update the virtual network to \"(.*?)\"$")
  public static void IUpdateTheVirtualNetworkTo(String profile)
  {
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PROFILE, profile);
    driver.executeScript(Constants.MOBILEVNETWORKUPDATE, params);
    params.remove(Constants.PROFILE);
  }

  @When("^I start device logging$")
  public static void IStartDeviceLogging()
  {
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
    Map<String, Object> params = new HashMap<>();
    driver.executeScript(Constants.MOBILELOGSSTART, params);
  }

  @Then("^I stop device logging$")
  public void IStopDeviceLogging()
  {
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
    Map<String, Object> params = new HashMap<>();
    driver.executeScript(Constants.MOBILELOGSSTOP, params);
  }

  @When("^I start DevTunnel on \"(.*?)\"$") // mac or windows
  public static void IStartDevTunnel(String os)
  {
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();

    Map<String, Object> paramsDT = new HashMap<>();
    paramsDT.put("action", "start");
    paramsDT.put("os", os);
    Object res = driver.executeScript("mobile:devtunnel:execute", paramsDT);

    try {
      Desktop desktop = java.awt.Desktop.getDesktop();
      URI oURL = new URI(res.toString());
      desktop.browse(oURL);
    } catch (Exception ex) {}
  }

  @Then("^I stop DevTunnel$")
  public static void IStopDevTunnel()
  {
    QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
    Map<String, Object> paramsDT2 = new HashMap<>();
    paramsDT2.put("action", "stop");
    paramsDT2.put("os", "mac");
    driver.executeScript("mobile:devtunnel:execute", paramsDT2);
  }

  @When("^I start device vital monitoring every \"(.*?)\" seconds$")
  public static void IStartDeviceVitals(String interval)
  {
    DeviceUtils.startVitals();
  }

  @Then("^I stop device vitals$")
  public static void IStopDeviceVitals()
  {
    DeviceUtils.stopVitals();
  }

  @Then("^I visually press on \"(.*?)\"$")
  public static void IVisualPressOn(String inField) throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("label",inField);
    DeviceUtils.getQAFDriver().executeScript(Constants.MOBILEBUTTONTEXTCLICK, params);
  }

  @Then("^I visually enter \"(.*?)\" on \"(.*?)\"$")
  public static void IVisualEnter(String inText, String inField) throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("label",inField);
    params.put("text",inText);
    DeviceUtils.getQAFDriver().executeScript(Constants.MOBILEEDITTEXTSET, params);
  }

  @Then("^I visually press on button \"(.*?)\"$")
  public static void IVisualPressOnButton(String inField) throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("label", inField);
    DeviceUtils.getQAFDriver().executeScript(Constants.MOBILEBUTTONIMAGECLICK, params);
  }

  @Then("^I visually press on label where \"(.*?)\" is \"(.*?)\" \"(.*?)\"$")
  public static void IVisualPressOnBy(String inField, String offset, String direction) throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("label",inField);
    params.put("label.direction", direction);
    params.put("label.offset", offset);
    DeviceUtils.getQAFDriver().executeScript(Constants.MOBILEBUTTONTEXTCLICK, params);
  }

}
