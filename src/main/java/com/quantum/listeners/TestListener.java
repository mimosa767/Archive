package com.quantum.listeners;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.quantum.steps.CommonSteps;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.quantum.steps.PerfectoApplicationSteps;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ISuiteListener, ITestListener {

    @Override
    public void onTestSuccess(ITestResult testResult) {

    }

    @Override
    public void onTestFailure(ITestResult testResult) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onFinish(ISuite arg0) {
        try {
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void onStart(ISuite arg0) {
    }

    @Override
    public void onTestStart(ITestResult result) {
        String appName = ConfigurationManager.getBundle().getString("appName");
        String appId = ConfigurationManager.getBundle().getString("appId");
        String appPath = ConfigurationManager.getBundle().getString("appPath");
        String instrument = ConfigurationManager.getBundle().getString("instrument");
        try {
            if (appName == null || appName.isEmpty())
                PerfectoApplicationSteps.uninstallAppById(appId);
            else
                PerfectoApplicationSteps.uninstallAppByName(appName);
        } catch (Exception ex) {}

        try {
            if (instrument == null || instrument.isEmpty())
                CommonSteps.IInstallApp(appPath);
            else
                CommonSteps.IInstrumentApp(appPath);
        } catch (Exception ex) {}
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
    }

}
