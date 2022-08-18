package com.quantum.steps;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class WinnDixieStepDefs {
    @When("^I login to Winn Dixie app")

    public void iLoginToPaneraBreadApp() throws InterruptedException {
        String username = "spenn6@gmail.com";
        String password = "Password123!";

        CommonStep.sendKeys(username, "winndixie.username");
        CommonStep.click("signinButton");
        CommonStep.sendKeys(password, "passwordField");
        CommonStep.click("signinButton");
    }
}
