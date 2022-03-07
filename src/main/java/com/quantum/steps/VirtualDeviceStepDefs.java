package com.quantum.steps;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.listeners.QuantumReportiumListener;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.AppiumUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.net.URL;

@QAFTestStepProvider

//@RunWith(JUnit4.class)
public class VirtualDeviceStepDefs {

    private static ReportiumClient reportiumClient;

    @Given("^I run iOS Expense Tracker")
    public void PerfectoExpenseTrackeriOS() throws IOException, InterruptedException {

        By userName = MobileBy.name("login_email");
        By password = MobileBy.name("login_password");
        By login = MobileBy.name("Login");
        By addExpenseButton = MobileBy.name("list_add_btn");
        By editHead = MobileBy.name("edit_head");
        By editHeadDropDown = MobileBy.className("XCUIElementTypePickerWheel");
        By amount = MobileBy.name("edit_amount");
        By editCurrency = MobileBy.name("edit_currency");
        By editCurrencyDropDown = MobileBy.className("XCUIElementTypePickerWheel");
        By editCategory = MobileBy.name("edit_category");
        By editCategoryDropDown = MobileBy.className("XCUIElementTypePickerWheel");
        By date = MobileBy.name("edit_date");
        By save = MobileBy.name("add_save_btn");
        By hamburgerMenu = MobileBy.name("list_left_menu_btn");
        By logout = MobileBy.name("list_logout_menu");

        IOSDriver<IOSElement> driver;
        driver = AppiumUtils.getIOSDriver();

        String sessionId = driver.getSessionId().toString();
        System.out.println("SessionID: " + sessionId);

        reportiumClient = QuantumReportiumListener.getReportClient();

        reportiumClient.stepStart("Enter User Name");
        driver.findElement(userName).click();
        driver.findElement(userName).setValue("test@perfecto.com");
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Click on Login");
        driver.findElement(login).click();
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Get Screenshot");
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Accept Pop-Up message");
        driver.switchTo().alert().accept();
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Enter Password");
        driver.findElement(password).click();
        driver.findElement(password).setValue("test123");
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Click on Login button");
        driver.findElement(login).click();
        reportiumClient.stepEnd();

        Thread.sleep(3000);

        reportiumClient.stepStart("Add a new expense. Click on 'Add Expense' button");
        driver.findElement(addExpenseButton).click();
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Enter 'Head'");
        driver.findElement(editHead).click();
        driver.findElement(editHeadDropDown).sendKeys("Taxi");
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Enter 'Amount'");
        driver.findElement(amount).click();
        driver.findElement(amount).setValue("1000");
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Enter 'Currency'");
        driver.findElement(editCurrency).click();
        driver.findElement(editCurrencyDropDown).sendKeys("");
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Enter 'Category'");
        driver.findElement(editCategory).click();
        driver.findElement(editCategoryDropDown).sendKeys("Miscellaneous");
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Enter 'Date'");
        driver.findElement(date).click();
        reportiumClient.stepEnd();

        reportiumClient.stepStart("Save expense");
        driver.findElement(save).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        reportiumClient.stepEnd();

        Thread.sleep(3000);

        System.out.println("Click on 'Hamburger Menu'");
        reportiumClient.stepStart("Click on 'Hamburger Menu'");
        driver.findElement(hamburgerMenu).click();
        reportiumClient.stepEnd();

        Thread.sleep(2000);

        reportiumClient.stepStart("Log Out");
        driver.findElement(logout).click();
        reportiumClient.stepEnd();

    }

    @Given("^I run Droid Expense Tracker")
    public void PerfectoExpenseTrackerDroid() throws IOException, InterruptedException {

        By username = MobileBy.id("io.perfecto.expense.tracker:id/login_email");
        By password = MobileBy.id("io.perfecto.expense.tracker:id/login_password");
        By loginButton = MobileBy.id("io.perfecto.expense.tracker:id/login_login_btn");
        By addExpenseButton = MobileBy.id("io.perfecto.expense.tracker:id/list_add_btn");
        By editHead = MobileBy.id("io.perfecto.expense.tracker:id/add_head");
        By amount = MobileBy.id("io.perfecto.expense.tracker:id/add_amount");
        By editCurrency = MobileBy.id("io.perfecto.expense.tracker:id/signup_currency");
        By editCategory = MobileBy.id("io.perfecto.expense.tracker:id/til_category");
        By save = MobileBy.id("io.perfecto.expense.tracker:id/add_save_btn");

        AppiumDriver<AndroidElement> driver;
        driver = AppiumUtils.getAndroidDriver();

        String sessionId = driver.getSessionId().toString();
        System.out.println("SessionID: " + sessionId);

        reportiumClient = QuantumReportiumListener.getReportClient();

        driver.getScreenshotAs(OutputType.BYTES);
        Thread.sleep(3000);

        try {
            System.out.println("Accept Pop-Up message");
            reportiumClient.stepStart("Accept Pop-Up message");
            driver.switchTo().alert().accept();
            reportiumClient.stepEnd();
        } catch (Exception e) {
            System.out.println("No Pop-Up");
        }

        System.out.println("Enter User Name");
        reportiumClient.stepStart("Enter User Name");
        driver.findElement(username).click();
        driver.findElement(username).setValue("test@perfecto.com");
        reportiumClient.stepEnd();

        System.out.println("Get Screenshot");
        reportiumClient.stepStart("Get Screenshot");
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        System.out.println("Enter Password");
        reportiumClient.stepStart("Enter Password");
        driver.getScreenshotAs(OutputType.BYTES);
        driver.findElement(password).click();
        driver.getScreenshotAs(OutputType.BYTES);
        driver.findElement(password).setValue("test123");
        reportiumClient.stepEnd();

        System.out.println("Click on Login button");
        reportiumClient.stepStart("Click on Login button");
        driver.findElement(loginButton).click();
        reportiumClient.stepEnd();

        Thread.sleep(2000);

        System.out.println("Add a new expense. Click on 'Add Expense' button");
        reportiumClient.stepStart("Add a new expense. Click on 'Add Expense' button");
        driver.findElement(addExpenseButton).click();
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        System.out.println("Enter 'Head'");
        reportiumClient.stepStart("Enter 'Head'");
        driver.findElement(editHead).click();
        driver.getScreenshotAs(OutputType.BYTES);
        driver.findElementByXPath("//*[@text='Taxi']").click();
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        System.out.println("Enter 'Amount'");
        reportiumClient.stepStart("Enter 'Amount'");
        driver.findElement(amount).click();
        driver.findElement(amount).setValue("1000");
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        Thread.sleep(2000);

        System.out.println("Enter 'Currency'");
        reportiumClient.stepStart("Enter 'Currency'");
        driver.findElement(editCurrency).click();

        Thread.sleep(2000);

        driver.findElementByXPath("//*[@text='USD-$']").click();
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        System.out.println("Enter 'Category'");
        reportiumClient.stepStart("Enter 'Category'");
        driver.findElement(editCategory).click();
        driver.findElementByXPath("//*[@text='Personal']").click();
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        System.out.println("Save Expense");
        reportiumClient.stepStart("Save Expense");
        driver.findElement(save).click();

        Thread.sleep(2000);

        driver.getScreenshotAs(OutputType.BYTES);
        // driver.switchTo().alert().accept();
        reportiumClient.stepEnd();

        Thread.sleep(2000);

        System.out.println("Click on 'Hamburger Menu'");
        reportiumClient.stepStart("Click on 'Hamburger Menu'");
        driver.findElementByXPath("//*[@content-desc='Open Drawer']").click();
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();

        Thread.sleep(2000);

        System.out.println("Log Out");
        reportiumClient.stepStart("Log Out");
        driver.findElementByXPath("//*[@text='Logout']").click();
        Thread.sleep(2000);
        driver.getScreenshotAs(OutputType.BYTES);
        reportiumClient.stepEnd();
    }

}
