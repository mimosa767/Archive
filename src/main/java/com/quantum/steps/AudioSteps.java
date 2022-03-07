package com.quantum.steps;

import com.perfecto.AudioUtils;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.DeviceUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

@QAFTestStepProvider

public class AudioSteps {

    private boolean bIsIOS = DeviceUtils.getDeviceProperty("os").contains("iOS");

    @Then("^I turn volume up$")
    public void ITurnVolUp()
    {
        AudioUtils.volumeUP();
    }

    @Then("^I start audio recording$")
    public void IStartAudioRecording()
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        HashMap<String, String> params = new HashMap<>();
        String url=driver.executeScript("mobile:audio.recording:start", params).toString();
        getBundle().setProperty("recordingUrl",url);
    }

    @Then("^I start audio recording sequence \"(.*?)\"$")
    public void IStartAudioRecordingSequence(String clipIndex)
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        HashMap<String, String> params = new HashMap<>();
        String url=driver.executeScript("mobile:audio.recording:start", params).toString();
        getBundle().setProperty("recordingUrl" + clipIndex,url);
    }

    @Then("^I stop audio recording$")
    public void IStopAudioRecording()
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        HashMap<String, String> params = new HashMap<>();
        driver.executeScript("mobile:audio.recording:stop", params);
    }

    @Then("^I make audio call \"(.*?)\"$")
    public void IMakeAudioCall(String number)
    {
        AudioUtils.adbCall(number); // can also do: adb shell am start -a android.intent.action.CALL -d tel:150
    }

    @Then("^I pick up the phone$")
    public void IPickUpPhone()
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        try {
            if (bIsIOS) {
//                driver.findElementByXPath("//*[@label=\"Accept\"]").click();
                            HashMap<String, Object> params1 = new HashMap<>();
                            params1.put("location", "1130,280");
                            Object result1 = driver.executeScript("mobile:touch:tap", params1);
            } else {
                AudioUtils.adbAnswer();
            }
        }catch (Exception ex) {ex.printStackTrace();}
    }

    @Then("^I hang up the phone$")
    public void IHangUpPhone()
    {
        AudioUtils.adbHangUp();

//        if (bIsIOS) {
//            driver.findElementByXPath("//*[@label=\"End call\"]").click();
////            HashMap<String, Object>  params2 = new HashMap<>();
////            params2.put("location", "610,2201");
////            Object result2 = driver.executeScript("mobile:touch:tap", params2);
//        } else {
//            AudioUtils.adbHangUp();
//            // driver.findElementByXPath("//*[@resource-id=\"com.samsung.android.incallui:id/endCallButton\"]").click();
//        }
    }

    @Then("^I validate audio \"(.*?)\"$")
    public void IValidateAudio(String file)
    {
        String url = getBundle().getString("recordingUrl");
        AudioUtils.audioValidation(getBundle().getString("recordingUrl"), file, "voip"); // voip, basic, volte, voip_rms
    }

    @Then("^I press \"(.*?)\" on the keypad")
    public void IPressNumOnKeypad(String numKey)
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        try {
            if (!bIsIOS) {
                if (numKey.equals("one")) driver.findElementByXPath("//*[@resource-id=\"com.samsung.android.incallui:id/dialpad_key_number\" and @text=\"1\"]").click();
                else driver.findElementByXPath("//*[@text='" + numKey + "']").click();
            }
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @Then("^I validate audio sequence \"(.*?)\" against \"(.*?)\"$")
    public void IValidateAudioSequenceAgainstText(String clipIndex, String txtReference)
    {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        HashMap<String, Object> params1 = new HashMap<>();
        params1.put("content", txtReference);
        params1.put("deviceAudio", getBundle().getString("recordingUrl" + clipIndex));
        params1.put("match", "contain");
        params1.put("threshold", "80");
        params1.put("confidence", "70");
        params1.put("language", "uk-english");
        params1.put("profile", "accuracy");
        Object result1 = driver.executeScript("mobile:audio-text:validation", params1);
    }

    @Then("^I validate audio sequences:$")
    public void IValidateAudioSequences(List<Map<Object,Object>> dataList) {
        QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
        for (int i=1; i<dataList.size(); i++)
        {
            Map<Object,Object> dataMap = dataList.get(i);
            HashMap<String, Object> params1 = new HashMap<>();
            params1.put("content", dataMap.get("message").toString());
            params1.put("deviceAudio", getBundle().getString("recordingUrl" + dataMap.get("seq").toString()));
            params1.put("match", "contain");
            params1.put("threshold", "80");
            params1.put("confidence", "70");
            params1.put("language", "uk-english");
//            params1.put("profile", "accuracy");
            Object result1 = driver.executeScript("mobile:audio-text:validation", params1);
        }
//        for (Map<Object, Object> dataMap : dataList) {
//            {
//                for (Map.Entry<Object, Object> entry : dataMap.entrySet()) {
//                    HashMap<String, Object> params1 = new HashMap<>();
//                    params1.put("content", entry.getValue().toString());
//                    params1.put("deviceAudio", getBundle().getString("recordingUrl" + entry.getKey().toString()));
//                    params1.put("match", "contain");
//                    params1.put("threshold", "80");
//                    params1.put("confidence", "70");
//                    params1.put("language", "uk-english");
//                    params1.put("profile", "accuracy");
//                    Object result1 = driver.executeScript("mobile:audio-text:validation", params1);
//                }
//            }
//        }
    }

}
