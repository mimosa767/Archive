package com.quantum.steps;

import java.util.HashMap;
import java.util.Map;

import com.perfecto.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import com.perfecto.ShadowDomUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

@QAFTestStepProvider
public class ExpenseTrackerStepDefs {

    @And("^I add expense with head \"([^\"]*)\" , amount \"([^\"]*)\" , \"([^\"]*)\" currency and category \"([^\"]*)\"$")
    public void addBasicExpense(String Head, String Amount, String Currency, String Category) throws InterruptedException {

        if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
                || ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {

            Thread.currentThread().sleep(1000);

            if (ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {
                CommonUtils.clickShadowDOMElem("#list_add_btn","a");
//                new QAFExtendedWebElement("add.buttonweb").click();
                if(ConfigurationManager.getBundle().getProperty("remote.server").toString().contains("fast")){
                    CommonUtils.sendKeys("C:\\temp\\example.png", "attachReceipt.button");
                }
            } else {
                CommonUtils.click("add.buttonmobile");
            }
            Thread.currentThread().sleep(1000);

            // click Head menu
            CommonUtils.clickShadowDOMElem("#add_head","button");
            // CommonUtils.click("head.dropdown");
            CommonUtils.click("head.meals");
//            DeviceUtils.getQAFDriver()
//                    .findElementByXPath(
//                            "//div[contains(text()," + Head + ")]/..//*[@class=\"alert-radio-label sc-ion-alert-md\"]")
//                    .click();
            CommonUtils.click("ok.button");

            CommonUtils.sendKeys(Amount, "amount.text.box");

//            CommonUtils.click("currency.dropdown");
//            DeviceUtils.getQAFDriver().findElementByXPath("//div[contains(text()," + Currency + ")]/..//*[@class=\"alert-radio-icon sc-ion-alert-md\"]").click();
//            CommonUtils.click("ok.button");

            // click Category
            CommonUtils.clickShadowDOMElem("#add_category","button");
//            CommonUtils.click("category.dropdown");
            CommonUtils.click("category.business");
//            DeviceUtils.getQAFDriver().findElementByXPath("//div[contains(text()," + Category + ")]/..//*[@class=\"alert-radio-icon sc-ion-alert-md\"]").click();
            CommonUtils.click("ok.button");

            if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")) {
                DeviceUtils.swipe("50%,90%", "50%,50%");
            }

            CommonUtils.sendKeys("Client Visit Bill", "details.text.box");

        } else {

            CommonUtils.click("add.label");
            CommonUtils.click("head.text.box");

            if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
//                if (driver.getCapabilities().getCapability("automationName").equals("PerfectoMobile"))
//                    DeviceUtils.getQAFDriver().findElementByXPath("//*[@text()='" + Head + "']").click();
//                else
                    DeviceUtils.getQAFDriver().findElementByXPath("//*[@text='" + Head + "']").click();
            } else {
                try {
                    DeviceUtils.setPickerWheel("pickerWheel1", "next", Head);
                } catch (Exception ex) {
                    CommonUtils.sendKeys(Head, "head.edit");
                }
            }
            CommonUtils.clear("amount.text.box");
            CommonUtils.sendKeys(Amount, "amount.text.box");
            CommonUtils.click("currency.text.box");

            if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
                DeviceUtils.getQAFDriver().findElementByXPath("//*[contains(@text," + Currency + ")]").click();
                DeviceUtils.hideKeyboard();
            }

            else {
                try {
                    DeviceUtils.setPickerWheel("pickerWheel1", "next", "USD - $");
                } catch (Exception ex) {
                    CommonUtils.clear("currency.edit");
                    CommonUtils.sendKeys("USD - $", "currency.edit");
                }
            }

            CommonUtils.click("category.text.box");
            if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
                DeviceUtils.getQAFDriver().findElementByXPath("//*[@text='" + Category + "']").click();

            } else {
                try {
                    DeviceUtils.setPickerWheel("pickerWheel1", "next", Category);
                } catch (Exception ex) {
                    CommonUtils.sendKeys(Category, "category.edit");
                }
                CommonUtils.click("date.text.box");
            }
        }

    }

    @When("^I login to Expense Tracker app with username and password")
    public void loginToInvoiceApp() throws InterruptedException {

        String username = "test@perfecto.com";
        String password = "test123";

        if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
                || ConfigurationManager.getBundle().getProperty("appType").equals("Native")) {
            if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")) {
                DeviceUtils.switchToContext("WEBVIEW_1");
                System.out.println(DeviceUtils.getCurrentContext());
            }
            if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")) {
                Actions actions = new Actions(DeviceUtils.getQAFDriver());
                CommonUtils.click("username.text.box");
                actions.sendKeys(username).build().perform();
            } else {
                CommonUtils.sendKeys(username, "username.text.box");
            }
            CommonUtils.sendKeys(password, "password.text.box");
            CommonUtils.click("touchID_toggle");
            CommonUtils.click("login.button");
            try {
                Thread.currentThread().sleep(1000);
                DeviceUtils.getQAFDriver().findElementByXPath("//*[@label='OK']").click();
            } catch (Exception ex) {ex.printStackTrace();}
            Thread.currentThread().sleep(1200);

            String appPackage = (String) ConfigurationManager.getBundle().getProperty("appPackageType");
            String identifier = (String) ConfigurationManager.getBundle().getProperty(appPackage);

            // Fail biometrics
            try {
                DeviceUtils.setSensorAuthentication("name", "Perfecto Expense Tracker", "fail", "authFailed");
            } catch (Exception ex){}
            Thread.currentThread().sleep(2000);

            if (DeviceUtils.getDeviceProperty("os").contains("iOS")) {
                CommonUtils.click("Ok.button"); //this is where the issue is
                Thread.currentThread().sleep(2000);
                CommonUtils.click("login.button");
            }
            Thread.currentThread().sleep(2000);

            // Pass biometrics
            try {
                DeviceUtils.setSensorAuthentication("name", "Perfecto Expense Tracker", "success", "authFailed");
            } catch (Exception ex){}

            Thread.currentThread().sleep(2000);

        } else if (ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {

            Capabilities caps = DeviceUtils.getQAFDriver().getCapabilities();
            DeviceUtils.getQAFDriver().get("http://expensetracker.perfectomobile.com");
            if (caps.getCapability("platformName").toString().toUpperCase().contains("WINDOWS") || caps.getCapability("platformName").toString().toUpperCase().contains("MAC") )
                DeviceUtils.getQAFDriver().manage().window().maximize();
            CommonUtils.sendKeys(username, "username.text.box");
            CommonUtils.sendKeys(password, "password.text.box");
            CommonUtils.click("login.button");

        }

    }

    @When("^I logout of application$")
    public void logout() {

        if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
                || ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {

            if (ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {
                CommonUtils.clickShadowDOMElem("body > app-root > ion-app > ion-split-pane > ion-menu > ion-content > ion-list > ion-menu-toggle:nth-child(4) > ion-item > ion-icon", "div");
            } else {

                Map<String, Object> params1 = new HashMap<>();
                params1.put("content", "PUBLIC:ExpenseTracker/Images/hamburger.png"); //PUBLIC:ExpenseTracker/Images/hamburger.png
                params1.put("timeout", "20");
                params1.put("threshold", "90");
                params1.put("match", "bounded");
                Object result1 = DeviceUtils.getQAFDriver().executeScript("mobile:image:select", params1);

                CommonUtils.click("logout.button");
            }

        } else {

            if (DeviceUtils.getDeviceProperty("os").contains("Android")) {

                CommonUtils.click("hamburger.menu.button");
                CommonUtils.click("logout.button");
                CommonUtils.click("ok.button");
            } else {
                CommonUtils.click("hamburger.menu.button");
                CommonUtils.click("logout.button");
            }

        }

    }

    @When("^I attach receipt$")
    public void attachReceipt() {

        if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
                || ConfigurationManager.getBundle().getProperty("appType").equals("Native")) {

            String appPackage = (String) ConfigurationManager.getBundle().getProperty("appPackageType");
            String identifier = (String) ConfigurationManager.getBundle().getProperty(appPackage);

            CommonUtils.click("attachment.link");
            DeviceUtils.startImageInjection("PUBLIC:ExpenseTracker/Images/CoffeeReceipt.jpg", identifier, "identifier");
            CommonUtils.click("camera.button");

            try {Thread.currentThread().sleep(2000);}catch (Exception ex) {}
            if (ConfigurationManager.getBundle().getProperty("appType").equals("Native")) {
                if (DeviceUtils.getDeviceProperty("os").contains("iOS")) {
                    try {CommonUtils.click("Ok.button");}catch (Exception ex) {}
                    CommonUtils.click("capture.button");
                    CommonUtils.click("usePhoto.button");
                    CommonUtils.click("save.button");
                    CommonUtils.click("Ok.button");
                } else {
                    CommonUtils.click("ok.button");
                    DeviceUtils.hideKeyboard();
                    CommonUtils.click("viewAttachment.button");
                    DeviceUtils.getQAFDriver().navigate().back();
                    CommonUtils.click("save.button");
                }
            } else {
                DeviceUtils.switchToContext("NATIVE");
                CommonUtils.click("addAttachmentAllow.btn");
                CommonUtils.click("addAttachmentAllow.btn");
                DeviceUtils.switchToContext("WEBVIEW");

                DeviceUtils.swipe("50%,90%", "50%,50%");
                CommonUtils.click("save.button");
            }

            DeviceUtils.stopImageInjection();
        } else {
            CommonUtils.clickShadowDOMElem("#add_save_btn","button");
        }
    }

}
