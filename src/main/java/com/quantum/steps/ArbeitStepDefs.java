package com.quantum.steps;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class ArbeitStepDefs {
    @When("^I login to Winn Dixie app")

    public void iLoginToPaneraBreadApp() throws InterruptedException {
        String username = "spenn6@gmail.com";
        String password = "Password123!";

        CommonStep.sendKeys(username, "winndixie.username");
        CommonStep.click("signinButton");
        Thread.sleep(1000);
        CommonStep.sendKeys(password, "passwordField");
        CommonStep.click("signinButton");
    }
    @Then("^I login to the Winn Dixie Web Application")

    public void  iLoginToWinnDixieApp() throws InterruptedException {
        String username = "spenn6@gmail.com";
        String password = "Password123!";

        CommonStep.sendKeys(username, "winn.email.field");
        Thread.sleep(2000);
        CommonStep.click("winn.signin.button");
        Thread.sleep(2000);
        CommonStep.sendKeys(password, "winn.password.field");
        Thread.sleep(2000);
        CommonStep.click("winn.signin.button");
        Thread.sleep(2000);

    }
}

