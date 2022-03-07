package com.quantum.steps;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.StringUtil;
import com.quantum.utils.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

import java.util.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

@QAFTestStepProvider
public class CommonSteps {

    @Then("I switch to frame \"(.*?)\"")
    public static void switchToFrame(String nameOrIndex) {
        if (StringUtil.isNumeric(nameOrIndex)) {
            int index = Integer.parseInt(nameOrIndex);
            new WebDriverTestBase().getDriver().switchTo().frame(index);
        } else {
            new WebDriverTestBase().getDriver().switchTo().frame(nameOrIndex);
        }

    }

    @Then("I switch to \"(.*?)\" frame by element")
    public static void switchToFrameByElement(String loc) {
        new WebDriverTestBase().getDriver().switchTo().frame(new QAFExtendedWebElement(loc));
    }

    @When("I am using an AppiumDriver")
    public void testForAppiumDriver() {
        if (ConfigurationUtils.getBaseBundle().getPropertyValue("driver.name").contains("Remote"))
            ConsoleUtils.logWarningBlocks("Driver is an instance of QAFExtendedWebDriver");
        else if (AppiumUtils.getAppiumDriver() instanceof IOSDriver)
            ConsoleUtils.logWarningBlocks("Driver is an instance of IOSDriver");
        else if (AppiumUtils.getAppiumDriver() instanceof AndroidDriver)
            ConsoleUtils.logWarningBlocks("Driver is an instance of AndroidDriver");
    }

    @Then("^I click on \"(.*?)\" and ignore errors$")
    public static void iClickAndIgnore(String locator) {
        try {
            CommonStep.click(locator);
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @Then("^I scroll up a bit$")
    public static void IScrollUpABit()
    {
        DeviceUtils.swipe("50%,40%", "50%,60%");
    }

    @Then("^I scroll down a bit$")
    public static void IScrollDownABit()
    {
        if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS) ||
                DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.ANDROID)) {
            DeviceUtils.swipe("50%,60%", "50%,40%");
        }
    }

    @Then("^I scroll up a lot$")
    public static void IScrollUpALot()
    {
        DeviceUtils.swipe("50%,20%", "50%,80%");
    }

    @Then("^I scroll down a lot$")
    public static void IScrollDownALot()
    {
        DeviceUtils.swipe("50%,90%", "50%,10%");
    }

    @Then("^I swipe up a lot$")
    public static void ISwipeUpALot() { DeviceUtils.swipe("50%,50%", "50%,10%"); }

    @Then("^I swipe up a bit$")
    public static void ISwipeUpABit()
    {
        DeviceUtils.swipe("50%,50%", "50%,30%");
    }

    @Then("^I swipe down a lot$")
    public static void ISwipeDownALot()
    {
        DeviceUtils.swipe("50%,50%", "50%,90%");
    }

    @Then("^I swipe left a lot$")
    public static void ISwipeLeftALot()
    {
        DeviceUtils.swipe("90%,40%", "10%,40%");
    }

    @Then("^I swipe right a lot$")
    public static void ISwipeRightALot()
    {
        DeviceUtils.swipe("10%,60%", "90%,60%");
    }

    @Then("^I swipe to go back$")
    public static void ISwipe2GoBack()
    {
        DeviceUtils.swipe("1%,50%", "60%,50%");
    }

    @Then("^I open control centre$")
    public static void IOpenControlCentre()
    {
        if (DeviceUtils.getDeviceProperty("os").contains("iOS")) {
            DeviceUtils.swipe("90%,1%", "90%,50%");
        }
    }

    @Then("^I answer phone call$")
    public static void IAnswerPhoneCall()
    {
        try {
            QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
            if (DeviceUtils.getDeviceProperty("os").contains(Constants.ANDROID)) {
                Map<String, Object> params7 = new HashMap<>();
                params7.put("label", "ANSWER");
                params7.put("interval", "1");
                params7.put("timeout", "10");
                params7.put("ignorecase", "nocase");
                Object result7 = driver.executeScript(Constants.MOBILEBUTTONTEXTCLICK, params7);
            } else if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS)) {
                Map<String, Object> params9 = new HashMap<>();
                params9.put("label", "PRIVATE:script/AnswerButton.png");
                params9.put("timeout", "10");
                Object result9 = driver.executeScript(Constants.MOBILEBUTTONIMAGECLICK, params9);
            }
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @Then("^I end phone call$")
    public static void IEndPhoneCall()
    {
        try {
            QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
            if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.ANDROID)) {
                DeviceUtils.switchToContext(Constants.NATIVEAPP);
                driver.findElementByXPath("//*[@resource-id=\"com.samsung.android.incallui:id/endCallButton\"]").click();
            } else if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS)) {
                Map<String, Object> params10 = new HashMap<>();
                params10.put("label", "PRIVATE:script/EndCallButton.png");
                Object result10 = driver.executeScript(Constants.MOBILEBUTTONIMAGECLICK, params10);
            }
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @Then("^I turn on WiFi$")
    public static void ITurnOnWiFi()
    {
        try {
            QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
            if (DeviceUtils.getDeviceProperty("os").contains(Constants.ANDROID)) {
                Map<String,Object> params1 = new HashMap<>();
                params1.put("wifi", "enabled");
                Object res1 = driver.executeScript(Constants.MOBILENETWORKSETTINGSSET, params1);
                Object res2 = driver.executeScript(Constants.MOBILENETWORKSETTINGSSET, params1);
            } else if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS)) {
                IOpenControlCentre();
                Map<String, Object> params9 = new HashMap<>();
                params9.put("label", "GROUP:Julius/script/WiFiOff.png");
                params9.put("timeout", "5");
                Object result9 = driver.executeScript(Constants.MOBILEBUTTONIMAGECLICK, params9);
                IScrollUpABit();
            }
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @Then("^I turn off WiFi$")
    public static void ITurnOffWiFi()
    {
        try {
            QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
            if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
                Map<String,Object> params1 = new HashMap<>();
                params1.put("wifi", "disabled");
                Object res1 = driver.executeScript(Constants.MOBILENETWORKSETTINGSSET, params1);
                Object res2 = driver.executeScript(Constants.MOBILENETWORKSETTINGSSET, params1);
            } else if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS)) {
                IOpenControlCentre();
                Map<String, Object> params9 = new HashMap<>();
                params9.put("label", "GROUP:Julius/script/WiFi.png");
                params9.put("timeout", "5");
                Object result9 = driver.executeScript(Constants.MOBILEBUTTONIMAGECLICK, params9);
                IScrollUpABit();
            }
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @Then("^I instrument app \"(.*?)\"$")
    public static void IInstrumentApp(String filePath) {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        Map<String, String> params = new HashMap<>();
        params.put("file", filePath);
        params.put("instrument", "instrument");
        params.put("sensorInstrument", "sensor");
        params.put("resign","true");
        driver.executeScript(Constants.MOBILEAPPLICATIONINSTALL, params);
    }

    @Then("^I install app \"(.*?)\"$")
    public static void IInstallApp(String filePath) {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        Map<String, String> params = new HashMap<>();
        params.put("file", filePath);
        params.put("resign","true");
        driver.executeScript(Constants.MOBILEAPPLICATIONINSTALL, params);
    }

    @Then("^I zoom in on map")
    public static void IZoomInOnMap()
    {
        Map<String, Object> params1 = new HashMap<>();
        if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS)) {
            params1.put("start", "50%,55%");
            params1.put("end", "50%,75%");
        } else if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.ANDROID)) {
            params1.put("start", "50%,45%");
            params1.put("end", "50%,65%");
        }
        params1.put("operation", "zoom");
        Object result1 = DeviceUtils.getQAFDriver().executeScript(Constants.MOBILETOUCHGESTURE, params1);
    }

    @Then("^I zoom out on map")
    public static void IZoomOutnOnMap()
    {
        Map<String, Object> params1 = new HashMap<>();
        if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS)) {
            params1.put("start", "50%,45%");
            params1.put("end", "50%,15%");
        } else if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.ANDROID)) {
            params1.put("start", "50%,15%");
            params1.put("end", "50%,45%");
        }
        params1.put("operation", "zoom");
        Object result1 = DeviceUtils.getQAFDriver().executeScript(Constants.MOBILETOUCHGESTURE, params1);
    }

    @Then("^I click on \"(.*?)\" and ignore errors$")
    public static void iClick(String locator) {
        try {
            CommonStep.click(locator);
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @When("^I receive a phone call$")
    public static void iReceiveAPhoneCall()
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.PROPERTY, Constants.DEVICEID);
        String deviceId = (String) driver.executeScript(Constants.MOBILEHANDSETINFO, params);
        params.remove(Constants.PROPERTY);
        params.put(Constants.TOHANDSET, deviceId);
        driver.executeScript(Constants.MOBILEGATEWAYCALL, params);
        params.remove(Constants.TOHANDSET);
    }

    @When("^I receive an SMS with the text: \"(.*?)\"$")
    public static void iReceiveAnSMSWithTheText(String message)
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.PROPERTY, Constants.DEVICEID);
        String deviceId = (String) driver.executeScript(Constants.MOBILEHANDSETINFO, params);
        params.remove(Constants.PROPERTY);
        params.put(Constants.TOHANDSET, deviceId);
        params.put(Constants.BODY, message);
        driver.executeScript(Constants.MOBILEGATEWAYSMS, params);
        params.remove(Constants.TOHANDSET);
        params.remove(Constants.BODY);
    }

    @Then("^I take a device screenshot$")
    public static void iTakeADeviceScreenshot()
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        driver.getScreenshotAs(OutputType.BYTES);
    }

    @Given("^I am on \"(.*?)\"$")
    public static void IAmOn(String url) throws Throwable
    {
        Capabilities caps = DeviceUtils.getQAFDriver().getCapabilities();
        Set<String> capNames = caps.getCapabilityNames();

        if ((DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS))) {
            if (capNames.contains("bundleId") && caps.getCapability("bundleId").toString().toUpperCase().contains("CHROME")) {
                QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
                Thread.sleep(20000);
                driver.waitForAllElementVisible();
                driver.findElementByXPath("//*[@name=\"NTPHomeFakeOmniboxAccessibilityID\"]").click();
                driver.getKeyboard().sendKeys(url);
                driver.findElementByXPath("//*[@label=\"go\"]").click();
            } else {
                Map<String, Object> params = new HashMap<>();
                params.put("url", url);
                Object result1 = DeviceUtils.getQAFDriver().executeScript("mobile:browser:goto", params);
            }
        } else if ((DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.ANDROID)))
        {
            if (capNames.contains("appPackage") && caps.getCapability("appPackage").toString().toUpperCase().contains("SBROWSER"))
            {
                QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
                try {
                    driver.findElementByXPath("//*[@resource-id=\"com.sec.android.app.sbrowser:id/help_intro_legal_agree_button\"]").click();
                    driver.findElementByXPath("//*[@resource-id=\"com.sec.android.app.sbrowser:id/help_intro_legal_agree_button\"]").click();
                } catch (Exception ex) {ex.printStackTrace();}
                try { driver.findElementByXPath("//*[@resource-id=\"android:id/button1\"]").click(); } catch (Exception ex) {ex.printStackTrace();}
                try { driver.findElementByXPath("//*[@resource-id=\"com.sec.android.app.sbrowser:id/help_intro_legal_agree_button\"]").click(); } catch (Exception ex) {ex.printStackTrace();}
                driver.findElementByXPath("//*[@resource-id=\"com.sec.android.app.sbrowser:id/url_bar_container\"]").click();
                driver.findElementByXPath("//*[@resource-id=\"com.sec.android.app.sbrowser:id/url_bar_container\"]").clear();
                driver.getKeyboard().sendKeys(url);

                Map<String, Object> EnterKeyEvent  = new HashMap<>();
                EnterKeyEvent.put("key", "66");
                driver.executeScript("mobile:key:event", EnterKeyEvent);
            }
            else DeviceUtils.getQAFDriver().get(url);
        } else {
            DeviceUtils.getQAFDriver().get(url);
            if (caps.getCapability("platformName").toString().toUpperCase().contains("WINDOWS") || caps.getCapability("platformName").toString().toUpperCase().contains("MAC") )
                DeviceUtils.getQAFDriver().manage().window().maximize();
        }
        Thread.sleep(3000);
    }

    @When("^I click on \"(.*)\" when visible$")
    public static void IClickOnWhenVisible(String loc)
    {
        QAFExtendedWebElement element = new QAFExtendedWebElement(loc);
        element.waitForVisible(10000);
        element.click();
    }

    @When("^I click on \"(.*)\" using JavaScript")
    public static void IClickUsingJavaScript(String loc)
    {
        QAFExtendedWebElement element = new QAFExtendedWebElement(loc);
        JavascriptExecutor js = DeviceUtils.getQAFDriver();
        js.executeScript("arguments[0].click();", element);
    }

    @When("I press on back button")
    public static void IPressOnBackButton()
    {
        DeviceUtils.getQAFDriver().navigate().back();
    }

    @When("I maximize window")
    public static void IMaximizeWindow()
    {
        if (!(DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.ANDROID)) && !(DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS)))
        {
            DeviceUtils.getQAFDriver().manage().window().maximize();
        }
    }

    @Given("^I clean Android app by name \"(.*)\"")
    public static void ICleanAndroidAppByName(String name)
    {
        if (DeviceUtils.getDeviceProperty("os").contains("Android"))
        {
            DeviceUtils.cleanApp(name, "name");
        }
        else
        {
            ReportUtils.logVerify("Only supported on Android. Nothing done.", true);
        }
    }

    @Then("^I zoom in")
    public static void IZoomIn()
    {
        Map<String, Object> params1 = new HashMap<>();
        params1.put("start", "40%,40%");
        params1.put("end", "20,20%");
        params1.put("operation", "zoom");
        Object result1 = DeviceUtils.getQAFDriver().executeScript("mobile:touch:gesture", params1);
    }

    @Then("^I zoom out")
    public static void IZoomOut()
    {
        Map<String, Object> params1 = new HashMap<>();
        params1.put("start", "20%,20%");
        params1.put("end", "20,40%");
        params1.put("operation", "zoom");
        Object result1 = DeviceUtils.getQAFDriver().executeScript("mobile:touch:gesture", params1);
    }

    @Then("^I get my phone number$")
    public static void IgetMyPhoneNo() {
        DeviceUtils.switchToContext("NATIVE_APP");
        String phoneNumber = DeviceUtils.getDeviceProperty("phoneNumber").replaceAll("/(?<!^)\\+|[^\\d+]+/","");
        getBundle().setProperty("phoneNumber",phoneNumber);
    }

    @Then("^I send keys \"(.*)\"")
    public static void ISendKeys(String keySequence)
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        if (DeviceUtils.getDeviceProperty(Constants.OS).contains(Constants.IOS))
        {
            driver.getKeyboard().sendKeys(keySequence);
        } else {
            for (char charToType : keySequence.toCharArray()) {
                Map<String, Object> EnterKeyEvent = new HashMap<>();
                int keycode=0;
                int metastate = 0;
                switch (charToType) {
                    case 'A':
                        keycode = 29;
                        metastate = 1;
                        break;
                    case 'B':
                        keycode = 30;
                        metastate = 1;
                        break;
                    case 'C':
                        keycode = 31;
                        metastate = 1;
                        break;
                    case 'D':
                        keycode = 32;
                        metastate = 1;
                        break;
                    case 'E':
                        keycode = 33;
                        metastate = 1;
                        break;
                    case 'F':
                        keycode = 34;
                        metastate = 1;
                        break;
                    case 'G':
                        keycode = 35;
                        metastate = 1;
                        break;
                    case 'H':
                        keycode = 36;
                        metastate = 1;
                        break;
                    case 'I':
                        keycode = 37;
                        metastate = 1;
                        break;
                    case 'J':
                        keycode = 38;
                        metastate = 1;
                        break;
                    case 'K':
                        keycode = 39;
                        metastate = 1;
                        break;
                    case 'L':
                        keycode = 40;
                        metastate = 1;
                        break;
                    case 'M':
                        keycode = 41;
                        metastate = 1;
                        break;
                    case 'N':
                        keycode = 42;
                        metastate = 1;
                        break;
                    case 'O':
                        keycode = 43;
                        metastate = 1;
                        break;
                    case 'P':
                        keycode = 44;
                        metastate = 1;
                        break;
                    case 'Q':
                        keycode = 45;
                        metastate = 1;
                        break;
                    case 'R':
                        keycode = 46;
                        metastate = 1;
                        break;
                    case 'S':
                        keycode = 47;
                        metastate = 1;
                        break;
                    case 'T':
                        keycode = 48;
                        metastate = 1;
                        break;
                    case 'U':
                        keycode = 49;
                        metastate = 1;
                        break;
                    case 'V':
                        keycode = 50;
                        metastate = 1;
                        break;
                    case 'W':
                        keycode = 51;
                        metastate = 1;
                        break;
                    case 'X':
                        keycode = 52;
                        metastate = 1;
                        break;
                    case 'Y':
                        keycode = 53;
                        metastate = 1;
                        break;
                    case 'Z':
                        keycode = 54;
                        metastate = 1;
                        break;
                    case 'a':
                        keycode = 29;
                        break;
                    case 'b':
                        keycode = 30;
                        break;
                    case 'c':
                        keycode = 31;
                        break;
                    case 'd':
                        keycode = 32;
                        break;
                    case 'e':
                        keycode = 33;
                        break;
                    case 'f':
                        keycode = 34;
                        break;
                    case 'g':
                        keycode = 35;
                        break;
                    case 'h':
                        keycode = 36;
                        break;
                    case 'i':
                        keycode = 37;
                        break;
                    case 'j':
                        keycode = 38;
                        break;
                    case 'k':
                        keycode = 39;
                        break;
                    case 'l':
                        keycode = 40;
                        break;
                    case 'm':
                        keycode = 41;
                        break;
                    case 'n':
                        keycode = 42;
                        break;
                    case 'o':
                        keycode = 43;
                        break;
                    case 'p':
                        keycode = 44;
                        break;
                    case 'q':
                        keycode = 45;
                        break;
                    case 'r':
                        keycode = 46;
                        break;
                    case 's':
                        keycode = 47;
                        break;
                    case 't':
                        keycode = 48;
                        break;
                    case 'u':
                        keycode = 49;
                        break;
                    case 'v':
                        keycode = 50;
                        break;
                    case 'w':
                        keycode = 51;
                        break;
                    case 'x':
                        keycode = 52;
                        break;
                    case 'y':
                        keycode = 53;
                        break;
                    case 'z':
                        keycode = 54;
                        break;
                    case '0':
                        keycode = 7;
                        break;
                    case '1':
                        keycode = 8;
                        break;
                    case '2':
                        keycode = 9;
                        break;
                    case '3':
                        keycode = 10;
                        break;
                    case '4':
                        keycode = 11;
                        break;
                    case '5':
                        keycode = 12;
                        break;
                    case '6':
                        keycode = 13;
                        break;
                    case '7':
                        keycode = 14;
                        break;
                    case '8':
                        keycode = 15;
                        break;
                    case '9':
                        keycode = 16;
                        break;
                    case ' ':
                        keycode = 62;
                        break;
                }
                EnterKeyEvent.put("key", Integer.toString(keycode));
                if (metastate > 0) EnterKeyEvent.put("metastate", "1");
                driver.executeScript("mobile:key:event", EnterKeyEvent);
                Map<String, Object> params = new HashMap<>();
                params.put("mode", "off");
                String res = (String) driver.executeScript("mobile:keyboard:display", params);
            }
        }
    }

    @Then("^I inject \"(.*?)\" image to application name \"(.*?)\"$")
    public static void imageInjectionByAppName(String repositoryFile, String name) {
        DeviceUtils.startImageInjection(repositoryFile, name, "name");
    }

}
