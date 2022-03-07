package com.quantum.steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.quantum.pages.Cafe24Page;
import com.quantum.pages.GooglePage;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.DriverUtils;
import com.quantum.utils.ReportUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class Cafe24StepDefs {

	Cafe24Page cafe24page = new Cafe24Page();
	QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();

	@When("^I click on hamburger menu and signup$")
	public void iClickOnHamburgerMenuAndSignup() throws InterruptedException {
		cafe24page.clickHamburgermenu();
		Thread.sleep(1000);
		cafe24page.clickSignup();
		Thread.sleep(2000);
		cafe24page.clickAgreeAllConditions();
		Thread.sleep(1000);
		cafe24page.clickNext();
		Thread.sleep(1000);
	}

	@And("^I enter details and signup$")
	public void I_signup() {
		cafe24page.signUp();
	}

	@Then("^User should be created$")
	public void It_should_create_user() {
		ReportUtils.logAssert("User Creation", cafe24page.verifyUserCreationMessage().contains("Thank you for signing up"));
		cafe24page.clickComplete();
	}

	@And("^I order product$")
	public void I_order_product() throws InterruptedException {
		cafe24page.orderProduct();
	}

	@And("^I checkout providing all details$")
	public void I_checkout() {
		cafe24page.checkout();
	}

	@Then("^Order should be successfully placed$")
	public void Verify_order() {
		cafe24page.verifyOrderSuccess();
		System.out.println(cafe24page.orderNumber());
	}

	@Given("^I login as admin$")
	public void I_login_as_admin() {
		cafe24page.login_admin();
	}

	@And("^I search for the created order$")
	public void I_search_for_created_order() {
		cafe24page.search_order();
	}

	@Then("^I cancel the order$")
	public void I_cancel_order() {
		// cafe24page.select_order();
		cafe24page.cancel_order();
		cafe24page.accept_cancel_order();
		// System.out.println(cafe24page.validate_cancellation());
//		if (cafe24page.validate_cancellation())
			ReportUtils.logAssert("Order cancelled", true);
//		else
//			ReportUtils.logAssert("Order cancelled", false);
	}

}