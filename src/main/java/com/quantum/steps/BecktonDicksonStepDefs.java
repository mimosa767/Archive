package com.quantum.steps;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class BecktonDicksonStepDefs {
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
    @Then("^I create my custom step definition")

    public void  iCreateCustomStep() throws InterruptedException {
        System.out.println();

    }
}

