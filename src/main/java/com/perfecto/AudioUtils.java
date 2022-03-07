package com.perfecto;

import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.listeners.QuantumReportiumListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.quantum.utils.DeviceUtils.switchToContext;

public class AudioUtils {

    public static void volumeUP() {
        HashMap<String,String> params = new HashMap<>();
        params.put("keySequence", "VOL_UP");
        getQAFDriver().executeScript("mobile:presskey", params);
    }

    public static void audioInject(String file)
    {
        HashMap<String,String> params = new HashMap<>();
        params.put("key", file);
        getQAFDriver().executeScript("mobile:audio:inject", params);
    }

    public static void audioCheck(Number db)
    {
        HashMap<String,Number> params = new HashMap<>();
        params.put("volume", db);
        getQAFDriver().executeScript("mobile:checkpoint:audio", params);
    }

    //Audio commands
    public static Object audioRecordingStart()
    {
        HashMap<String, String> params = new HashMap<>();
        return getQAFDriver().executeScript("mobile:audio.recording:start", params);
    }
    public static void audioRecordingStop()
    {
        HashMap<String, String> params = new HashMap<>();
        getQAFDriver().executeScript("mobile:audio.recording:stop", params);
    }

    public static void audioValidation(String deviceAudio,String key, String profile)
    {
        HashMap<String,String> params = new HashMap<>();
        params.put("deviceAudio", deviceAudio);
        params.put("key", key);
        params.put("profile", profile);
        getQAFDriver().executeScript("mobile:audio:validation", params);
    }

    public static void adbCall(String number)
    {
        String cmd = "am start -a android.intent.action.CALL -d tel:"+number;
        abdshell(cmd);
    }

    public static void adbAnswer()
    {
        String cmd = "input keyevent 5"; ////*[@resource-id="com.samsung.android.incallui:id/incomingCallImageWidget"]/android.widget.FrameLayout[1]/android.widget.ImageView[1]
        abdshell(cmd);
    }

    public static void adbHangUp()
    {
        String cmd = "input keyevent KEYCODE_ENDCALL";
        abdshell(cmd);
    }

    public static void abdshell(String cmd)
    {
        HashMap<String,String> params = new HashMap<>();
        params.put("command", cmd);
        getQAFDriver().executeScript("mobile:handset:shell", params);
    }

    public static QAFExtendedWebDriver getQAFDriver() {
        return (new WebDriverTestBase()).getDriver();
    }

}
