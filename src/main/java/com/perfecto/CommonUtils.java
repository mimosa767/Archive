package com.perfecto;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.quantum.utils.DeviceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.core.TestBaseProvider;
import com.qmetry.qaf.automation.data.MetaData;
import com.qmetry.qaf.automation.ui.JsToolkit;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.StringUtil;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class CommonUtils {

    public static void comment(Object value) {
        System.out.printf("COMMENT: %s \n", value);
    }

    /**
     * Store last step result with the given name. In bdd if step returns value
     * and you want to store the return value with name for further use you can
     * call this step.
     * <p>
     * Example:<br/>
     * <code>
     * get text of 'my.ele.loc'<br/>
     * AND store into 'myeletext'<br/>
     * COMMENT: '${myeletext}'<br/>
     * </code>
     *
     * @param var
     *            : {0} : the name of variable you in which want to store last
     *            step result
     */
    public static void storeLastStepResultInto(String var) {
        Object val = getBundle().getProperty("last.step.result");
        getBundle().addProperty(var, val);
    }

    /**
     * Store a value in variable to use later on. Stored The variable can be
     * passed as argument to the other steps as ${varname}
     * <p>
     * Example:<br/>
     * <code>
     * store 5 into 'price'<br/>
     * COMMENT: '${myeletext}'<br/>
     * </code>
     *
     * @param val
     *            : {0} : value to be stored in variable
     * @param var
     *            : {1} : variable name
     */
    public static void store(Object val, String var) {
        getBundle().addProperty(var, val);
    }

    /**
     * Insert text in the given locator
     * <p>
     * Example:<br/>
     * <code>
     * <br/>
     * </code>
     *
     * @param text
     *            : {0} : String to be insert
     * @param loc
     *            : {1} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void sendKeys(String text, String loc) {
        $(loc).sendKeys(text);
    }

    /**
     * Verify that the specified element is somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' is present<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertPresent(String loc) {
        $(loc).assertPresent();
    }

    /**
     * Verify that the specified link text is somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert link with text 'Perfecto' is present"<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param linkText
     *            : {0} : link text to be verified in browser page.
     */
    public static void assertLinkWithTextPresent(String linkText) {
        $("link=" + linkText).assertPresent();
    }

    /**
     * Verify that the specified partial link is somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert link with partial text 'Perfecto' is present"<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param linkText
     *            : {0} : partial link text to be verified in browser page.
     */
    public static void assertLinkWithPartialTextPresent(String linkText) {
        $("partialLink=" + linkText).assertPresent();
    }

    /**
     * Asserts that the specified element is somewhere on the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' is present<br/>
     * KWD
     * </code>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static boolean verifyPresent(String loc) {
        return $(loc).verifyPresent();
    }

    /**
     * Asserts that the specified link with given text is somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify link with text 'About Us' is present<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param linkText
     *            : {0} : link text to be verified in browser page.
     */
    public static boolean verifyLinkWithTextPresent(String linkText) {
        return $("link=" + linkText).verifyPresent();
    }

    /**
     * Asserts that the specified link with given partial text is somewhere on
     * the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify link with partial text 'Perfecto' is present<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param linkText
     *            : {0} : partial link text to be verified in browser page.
     */
    public static boolean verifyLinkWithPartialTextPresent(String linkText) {
        return $("partialLink=" + linkText).verifyPresent();
    }

    /**
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertVisible(String loc) {
        $(loc).assertVisible();
    }

    /**
     * Verify that the specified element is visible somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' is visible<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static boolean verifyVisible(String loc) {
        return $(loc).verifyVisible();
    }

    /**
     * Opens an URL in the browser. This accepts both relative and absolute
     * URLs. The "get" command waits for the page to load before proceeding, *
     * <p>
     * Example:<br/>
     * <code>
     * get 'http://www.Perfecto.com'<br/>
     * get '/'<br/>
     * get '/Resources/case-studies.php'<br/>
     * </code>
     *
     * @param url
     *            : {0} : the URL to open; may be relative or absolute
     */
    public static void get(String url) {
        new WebDriverTestBase().getDriver().get(url);
    }

    /**
     * Switch from one driver to another and visa-versa during the test.
     * <p>
     * This will create a driver session if given driver is not available in
     * current thread. Note that it will not tear-down the current driver
     * session.
     * <p>
     * It is useful for some use cases involving browser and device - There are
     * some use cases involving more than 2 devices/browsers (ex.: video
     * conference)
     * <p>
     * For single driver in entire test case you don't required to use this
     * method.
     *
     * @since 2.1.11
     * @param driverName
     *            : driver name to create driver object. Refer QAF documentation
     *            to understand what can be the driver name
     */
    public static void switchDriver(String driverName) {
        TestBaseProvider.instance().get().setDriver(driverName);
    }

    /**
     * This step will tear down driver. It will quite and end current browser
     * session.
     */
    public static void tearDownDriver() {
        TestBaseProvider.instance().get().tearDown();
    }

    /**
     * Switch the context to the driver to new window Example:<br/>
     * <code>
     * switchToWindow '2'<br/>
     * switchToWindow 'Forgot Password Popup'<br/>
     * </code>
     *
     * @param windowNameOrIndex
     *            : title or index of the window
     */
    public static void switchToWindow(String nameOrIndex) {
        if (StringUtil.isNumeric(nameOrIndex)) {
            int index = Integer.parseInt(nameOrIndex);
            Set<String> windows = new WebDriverTestBase().getDriver().getWindowHandles();
            Iterator<String> itr = windows.iterator();
            for (int i = 0; i < windows.size() && i <= index; i++) {
                nameOrIndex = itr.next();
            }
            new WebDriverTestBase().getDriver().switchTo().window(nameOrIndex);
        } else {
            new WebDriverTestBase().getDriver().switchTo().window(nameOrIndex);
        }
    }

    /**
     * clear the specified element value from the field
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * clear 'my.ele.loc'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void clear(String loc) {
        $(loc).clear();
    }

    /**
     * Retrieve the value for specified element.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * get text of 'my.ele.loc'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @return The text contains by the specified locator
     */
    public static String getText(String loc) {
        return $(loc).getText();
    }

    /**
     * Submit the specified page. This is particularly useful for page without
     * submit buttons, e.g. single-input "Search" page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * submit 'my.ele.loc'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */

    public static void submit(String loc) {
        $(loc).submit();

    }

    /**
     * Clicks on a link, button, checkbox or radio button. If the click action
     * causes a new page to load (like a link usually does), call
     * waitForPageToLoad.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * click on 'my.ele.loc'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void click(String loc) {
        $(loc).click();
    }

    /**
     * A convenience step for drag and drop that performs click-and-hold at the
     * location of the source element, moves to the location of the target
     * element, then releases the mouse.
     *
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * drag 'source.ele.loc' and drop on 'target.ele.loc'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     * <code>
     * dragAndDrop|['source.ele.loc','target.ele.loc']|
     * </code>
     *
     * @param source
     *            : {0} : source element locator to emulate button down at.
     * @param target
     *            : {1} : target element locator to move to and release the
     *            mouse at.
     */
    public static void dragAndDrop(String source, String target) {
        QAFExtendedWebElement src = (QAFExtendedWebElement) $(source);
        Actions actions = new Actions(src.getWrappedDriver());
        actions.dragAndDrop(src, $(target));
    }

    /**
     * Wait for the specified element is visible Determines if the specified
     * element is visible. An element can be rendered invisible by setting the
     * CSS "visibility" property to "hidden", or the "display" property to
     * "none", either for the element itself or one if its ancestors. This
     * method will wait till its presence if the element is not present.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' to be visible<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForVisible(String loc) {
        $(loc).waitForVisible();
    }

    public static void waitForVisible(String loc, long sec) {
        $(loc).waitForVisible(sec * 1000);
    }

    /**
     * Determines if the specified element is not visible. This method will wait
     * till its invisibility, if the element is visible.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' not to be visible<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForNotVisible(String loc) {
        $(loc).waitForNotVisible();
    }

    public static void waitForNotVisible(String loc, long sec) {
        $(loc).waitForNotVisible(sec * 1000);
    }

    /**
     * Determines if the specified element is disable. This method will wait for
     * the element to be disable, if the element is enable.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' to be disable<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForDisabled(String loc) {
        $(loc).waitForDisabled();
    }

    public static void waitForDisabled(String loc, long sec) {
        $(loc).waitForDisabled(sec * 1000);

    }

    /**
     * Determines if the specified element is enable. This method will wait for
     * the element to be enable, if the element is disable.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' to be enable<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForEnabled(String loc) {
        $(loc).waitForEnabled();

    }

    public static void waitForEnabled(String loc, long sec) {
        $(loc).waitForEnabled(sec * 1000);

    }

    /**
     * Determines if the specified element is present. This method will wait for
     * the element to be present, if the element is not present.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' to be present<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForPresent(String loc) {
        $(loc).waitForPresent();
        ;

    }

    public static void waitForPresent(String loc, long sec) {
        $(loc).waitForPresent(sec * 1000);

    }

    /**
     * Determines if the specified element is not present. This method will wait
     * for the element not to be present, if the element is present.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' is not present<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForNotPresent(String loc) {
        $(loc).waitForNotPresent();

    }

    public static void waitForNotPresent(String loc, long sec) {
        $(loc).waitForNotPresent(sec * 1000);

    }

    /**
     * Gets the text of an element. This works for any element that contains
     * text. This command uses either the textContent or the innerText of the
     * element, which is the rendered text shown to the user.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' text 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The text of element locator
     */
    public static void waitForText(String loc, String text) {
        $(loc).waitForText(text);

    }

    public static void waitForText(String loc, String text, long sec) {
        $(loc).waitForText(text, sec * 1000);

    }

    /**
     * Gets the text of an element. This works for any element that contains
     * text. This command uses either the textContent or the innerText of the
     * element, which is the rendered text shown to the user.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' text is not 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The text of element locator
     */
    public static void waitForNotText(String loc, String text) {
        $(loc).waitForNotText(text);

    }

    public static void waitForNotText(String loc, String text, long sec) {
        $(loc).waitForNotText(text, sec * 1000);
    }

    /**
     * Gets the (whitespace-trimmed) value of an input field (or anything else
     * with a value parameter).
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' value is 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param value
     *            : {1} : The object of value for element locator
     */
    public static void waitForValue(String loc, Object value) {
        $(loc).waitForValue(value);
    }

    public static void waitForValue(String loc, Object value, long sec) {
        $(loc).waitForValue(value, sec * 1000);
    }

    /**
     * Gets the (whitespace-trimmed) value of an input field (or anything else
     * with a value parameter).
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' value is not 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param value
     *            : {1} : The object of value for element locator
     */
    public static void waitForNotValue(String loc, Object value) {
        $(loc).waitForNotValue(value);

    }

    public static void waitForNotValue(String loc, Object value, long sec) {
        $(loc).waitForNotValue(value, sec * 1000);
    }

    /**
     * This method wait until the locator in the specified page will be
     * selected.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' to be selected<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForSelected(String loc) {
        $(loc).waitForSelected();
    }

    public static void waitForSelected(String loc, long sec) {
        $(loc).waitForSelected(sec * 1000);

    }

    /**
     * This method wait until the locator in the specified page will not be
     * selected.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' is not selected<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void waitForNotSelected(String loc) {
        $(loc).waitForNotSelected();

    }

    public static void waitForNotSelected(String loc, long sec) {
        $(loc).waitForNotSelected(sec * 1000);

    }

    /**
     * This method wait until it gets the value of an element attribute for
     * specified element locator.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' for attribute 'type' value is 'send'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param attr
     *            : {1} : attribute name which value to be verified
     * @param value
     *            : {2} : value of attribute
     */
    public static void waitForAttribute(String loc, String attr, String value) {
        $(loc).waitForAttribute(attr, value);

    }

    public static void waitForAttribute(String loc, String attr, String value, long sec) {
        $(loc).waitForAttribute(attr, value, sec * 1000);

    }

    /**
     * This method wait until it can not gets the value of an element attribute
     * for specified element locator.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' for attribute 'type' value is not 'send'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param attr
     *            : {1} : attribute name which value to be verified
     * @param value
     *            : {2} : value of attribute
     */
    public static void waitForNotAttribute(String loc, String attr, String value) {
        $(loc).waitForNotAttribute(attr, value);
    }

    public static void waitForNotAttribute(String loc, String attr, String value, long sec) {
        $(loc).waitForAttribute(attr, value, sec * 1000);
    }

    /**
     * This method wait until it gets the CSS class name for specified element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' css class name is 'ClassName'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param className
     *            : {1} : CSS class name to be verified
     */
    public static void waitForCssClass(String loc, String className) {
        $(loc).waitForCssClass(className);
    }

    public static void waitForCssClass(String loc, String className, long sec) {
        $(loc).waitForCssClass(className, sec * 1000);
    }

    /**
     * This method wait until it can not gets the CSS class name for specified
     * element locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' css class name is not 'ClassName'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param className
     *            : {1} : CSS class name to be verified
     */
    public static void waitForNotCssClass(String loc, String className) {
        $(loc).waitForNotCssClass(className);
    }

    public static void waitForNotCssClass(String loc, String className, long sec) {
        $(loc).waitForCssClass(className, sec * 1000);

    }

    /**
     * This method wait until it gets the property value for specified element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' property 'propertyStyle' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : property (css style) to be verified
     * @param value
     *            : {2} : value of property (ie. css style property value)
     */
    public static void waitForCssStyle(String loc, String prop, String value) {
        $(loc).waitForCssStyle(prop, value);

    }

    // @QAFTestStep(stepName = "waitForCssStyleWithTimeout", description =
    // "wait {3}sec for {0} property {1} value is {2} ")
    public static void waitForCssStyle(String loc, String prop, String value, long sec) {
        $(loc).waitForCssStyle(prop, value, sec * 1000);

    }

    /**
     * This method wait until it can not gets the property value for specified
     * element locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' property 'propertyStyle' value is not 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : property (css style) to be verified
     * @param value
     *            : {2} : value of property (ie. css style property value)
     */
    public static void waitForNotCssStyle(String loc, String prop, String value) {
        $(loc).waitForNotCssStyle(prop, value);

    }

    /**
     * This method wait until it gets the css color property value for specified element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' color 'propertyStyle' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : color property (css style) to be verified
     * @param value
     *            : {2} : value of color in hex or rgb or rgba
     */
    public static void waitForCssStyleColor(String loc, String prop, String value) {
        $(loc).waitForCssStyleColor(prop, value);
    }

    /**
     * This method wait until not the css color property value of element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * wait until 'my.ele.loc' color 'propertyStyle' value is not 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : color property (css style) to be verified
     * @param value
     *            : {2} : value of color in hex or rgb or rgba
     */
    public static void waitForNotCssStyleColor(String loc, String prop, String value) {
        $(loc).waitForNotCssStyleColor(prop, value);
    }

    // @QAFTestStep(stepName = "waitForNotCssStyleWithTimeout", description =
    // "wait {3}sec for {0} css property {1} vaule is {2} ")
    public static void waitForNotCssStyle(String loc, String prop, String value, long sec) {
        $(loc).waitForNotCssStyle(prop, value, sec * 1000);

    }

    /**
     * Assert that the specified element is not somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' not present<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @return <b>true</b> if the element locator is present, <b>false</b>
     *         otherwise
     */
    public static boolean verifyNotPresent(String loc) {

        return $(loc).verifyNotPresent();
    }

    /**
     * Will check and wait until in-progress AJAX call get completed from any of
     * the {@link JsToolkit}. If you know the toolkit used in your AUT use
     * {@link #waitForAjaxToComplete(String)}
     */
    public static void waitForAjaxToComplete() {
        new WebDriverTestBase().getDriver().waitForAjax();
    }

    /**
     * Will check and wait until in-progress AJAX call get completed from
     * provide toolkit. Following are supported toolkit:
     * <ul>
     * <li>DOJO
     * <li>EXTJS
     * <li>JQUERY
     * <li>YUI
     * <li>PHPJS
     * <li>PROTOTYPE
     *
     * @param jstoolkit
     *            must be one of the {@link JsToolkit}
     */
    public static void waitForAjaxToComplete(String jstoolkit) {
        new WebDriverTestBase().getDriver().waitForAjax(JsToolkit.valueOf(jstoolkit));
    }

    /**
     * Assert that the specified element is not visible somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' not visible<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : an element locator, can be direct locator value or a locator
     *            key stored in locator repository
     * @return <b>true</b> if the element locator is visible, <b>false</b>
     *         otherwise
     */
    public static boolean verifyNotVisible(String loc) {
        return $(loc).verifyNotVisible();
    }

    /**
     * Assert that the specified element is not enable somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' not enabled<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : an element locator, can be direct locator value or a locator
     *            key stored in locator repository
     * @return <b>true</b> if the element locator is enable, <b>false</b>
     *         otherwise
     */
    public static boolean verifyEnabled(String loc) {
        return $(loc).verifyEnabled();
    }

    /**
     * Assert that the specified element is disabled somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' disabled<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : an element locator, can be direct locator value or a locator
     *            key stored in locator repository
     * @return <b>true</b> if the element locator is disabled, <b>false</b>
     *         otherwise
     */
    public static boolean verifyDisabled(String loc) {
        return $(loc).verifyDisabled();
    }

    /**
     * Asserts the text of an element. This works for any element that contains
     * text. This command uses either the textContent or the innerText of the
     * element, which is the rendered text shown to the user.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' text is 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The text of element locator
     * @return <b>true</b> if the element locator text is verified, <b>false</b>
     *         otherwise
     */

    public static boolean verifyText(String loc, String text) {
        return $(loc).verifyText(text);
    }

    /**
     * Asserts the text of an element. This works for any element that contains
     * text. This command uses either the textContent or the innerText of the
     * element, which is the rendered text shown to the user.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' text is not 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The text of element locator
     * @return <b>true</b> if the element locator text is not verified,
     *         <b>false</b> otherwise
     */
    public static boolean verifyNotText(String loc, String text) {
        return $(loc).verifyNotText(text);
    }

    /**
     * Asserts the value of an element.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' value is 'Type Value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The type value of element locator
     * @return <b>true</b> if the element locator type value is verified,
     *         <b>false</b> otherwise
     */
    public static <T> boolean verifyValue(String loc, T value) {
        return $(loc).verifyValue(value);
    }

    /**
     * Asserts not the value of an element.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' value is not 'Type Value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The type value of element locator
     * @return <b>true</b> if the element locator type value is not verified,
     *         <b>false</b> otherwise
     */
    public static <T> boolean verifyNotValue(String loc, T value) {
        return $(loc).verifyNotValue(value);
    }

    /**
     * Assert that the specified element is selected somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' is selected<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @return <b>true</b> if the element locator is selected, <b>false</b>
     *         otherwise
     */
    public static boolean verifySelected(String loc) {

        return $(loc).verifySelected();
    }

    /**
     * Assert that the specified element is not selected somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' is not selected<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @return <b>true</b> if the element locator is not selected, <b>false</b>
     *         otherwise
     */
    public static boolean verifyNotSelected(String loc) {
        return $(loc).verifyNotSelected(loc);
    }

    /**
     * Assert attribute value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' attribute 'type' value is 'send'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param attr
     *            : {1} : attribute name which value to be verified
     * @param value
     *            : {2} : value of attribute
     * @return <b>true</b> if the element locator attribute value is verified,
     *         <b>false</b> otherwise
     */
    public static boolean verifyAttribute(String loc, String attr, String value) {
        return $(loc).verifyAttribute(attr, value);
    }

    /**
     * Assert not attribute value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' attribute 'type' value is not 'send'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param attr
     *            : {1} : attribute name which value to be verified
     * @param value
     *            : {2} : value of attribute
     * @return <b>true</b> if the element locator attribute value is not
     *         verified, <b>false</b> otherwise
     */
    public static boolean verifyNotAttribute(String loc, String attr, String value) {
        return $(loc).verifyNotAttribute(attr, value);
    }

    /*
     * @QAFTestStep(description =
     * "verify {0} not attribute {1} value {3} to be match {2}") public boolean
     * verifyNotAttribute(String loc, String attr, StringMatcher matcher,
     * String... label) { return new
     * QAFExtendedWebElement(loc).verifyNotAttribute(attr, matcher, label); }
     */
    /**
     * Assert css class name for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' css class name is 'class name'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param className
     *            : {1} : css class name not to be verified
     * @return <b>true</b> if the element locator css class name is verified,
     *         <b>false</b> otherwise
     */
    public static boolean verifyCssClass(String loc, String className) {
        return $(loc).verifyCssClass(className);
    }

    /**
     * Assert not css class name for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' css class name is not 'class name'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param className
     *            : {1} : css class name not to be verified
     * @return <b>true</b> if the element locator css class name is not
     *         verified, <b>false</b> otherwise
     */
    public static boolean verifyNotCssClass(String loc, String className) {
        return $(loc).verifyNotCssClass(className);
    }

    /**
     * Assert css property value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' property 'Style' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : property (css style) to be verified
     * @param value
     *            : {2} : value of property (i.e css style property value)
     * @return <b>true</b> if the element locator color property value is verified,
     *         <b>false</b> otherwise
     */
    public static boolean verifyCssStyle(String loc, String prop, String value) {
        return $(loc).verifyCssStyle(prop, value);
    }

    /**
     * Assert not css property value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' property 'Style' value is not 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : property (css style) to be verified
     * @param value
     *            : {2} : value of property (i.e css style property value)
     * @return <b>true</b> if the element locator color property value is not
     *         verified, <b>false</b> otherwise
     */
    public static boolean verifyNotCssStyle(String loc, String prop, String value) {
        return $(loc).verifyNotCssStyle(prop, value);
    }

    /**
     * This method verify the css color property value of is equal to value provide for element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' color 'propertyStyle' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : color property (css style) to be verified
     * @param value
     *            : {2} : value of color in hex or rgb or rgba
     */
    public static void verifyCssStyleColor(String loc, String prop, String value) {
        $(loc).verifyCssStyleColor(prop, value);
    }
    /**
     * This method verify the css color property value of is not equal to value provide for element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * verify 'my.ele.loc' color 'propertyStyle' value is not 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : color property (css style) to be verified
     * @param value
     *            : {2} : value of color in hex or rgb or rgba
     */
    public static void verifyNotCssStyleColor(String loc, String prop, String value) {
        $(loc).verifyNotCssStyleColor(prop, value);
    }
    /**
     * Verify that the specified element is not present somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' is not present<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertNotPresent(String loc) {
        $(loc).assertNotPresent();
    }

    /**
     * Verify that the specified element is not visible somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' is not visible<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertNotVisible(String loc) {
        $(loc).assertNotVisible();
    }

    /**
     * Verify that the specified element is enable somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' is enable<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertEnabled(String loc) {
        $(loc).assertEnabled();
    }

    /**
     * Verify that the specified element is disable somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' is disable<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertDisabled(String loc) {
        $(loc).assertDisabled();
    }

    /**
     * Verify the text of an element. This works for any element that contains
     * text. This command uses either the textContent or the innerText of the
     * element, which is the rendered text shown to the user.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' text is 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The text of element locator
     */
    public static void assertText(String loc, String text) {
        $(loc).assertText(text);
    }

    /**
     * Verify an element is not contain the text. This works for any element
     * that contains text. This command uses either the textContent or the
     * innerText of the element, which is the rendered text shown to the user.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' text is not 'Perfecto'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param text
     *            : {1} : The text of element locator
     */
    public static void assertNotText(String loc, String text) {
        $(loc).assertNotText(text);
    }

    /**
     * Verify the (whitespace-trimmed) value of an input field (or anything else
     * with a value parameter).
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param value
     *            : {1} : The value for element locator
     */
    public static void assertValue(String loc, String value) {
        $(loc).assertValue(value);
    }

    /**
     * Not verify the (whitespace-trimmed) value of an input field (or anything
     * else with a value parameter).
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' value is not 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param value
     *            : {1} : The object of value for element locator
     */
    public static <T> void assertNotValue(String loc, T value) {
        $(loc).assertNotValue(value);
    }

    /**
     * Verify that the specified element is selected somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' is selected<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertSelected(String loc) {
        $(loc).assertSelected();
    }

    /**
     * Verify that the specified element is not selected somewhere on the page
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' is not selected<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     */
    public static void assertNotSelected(String loc) {
        $(loc).assertNotSelected();
    }

    /**
     * Verify attribute value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' attribute 'type' value is 'send'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param attr
     *            : {1} : attribute name which value to be asserted
     * @param value
     *            : {2} : value of attribute
     */
    public static void assertAttribute(String loc, String attr, String value) {
        $(loc).assertAttribute(attr, value);
    }

    /**
     * Verify not attribute value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' attribute 'type' value is not 'send'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param attr
     *            : {1} : attribute name which value not to be asserted
     * @param value
     *            : {2} : value of attribute
     */
    public static void assertNotAttribute(String loc, String attr, String value) {
        $(loc).assertNotAttribute(attr, value);
    }

    /**
     * verify css class name for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' css class name is 'class name'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param className
     *            : {1} : css class name to be asserted
     */
    public static void assertCssClass(String loc, String className) {
        $(loc).assertCssClass(className);
    }

    /**
     * verify not css class name for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' css class name is not 'class name'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param className
     *            : {1} : css class name not to be asserted
     */
    public static void assertNotCssClass(String loc, String className) {
        $(loc).assertNotCssClass(className);
    }

    /**
     * Verify css property value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' property 'Style' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : property (css style) to be asserted
     * @param value
     *            : {2} : value of property (i.e css style property value)
     */
    public static void assertCssStyle(String loc, String prop, String value) {
        $(loc).assertCssStyle(prop, value);
    }

    /**
     * Verify not css property value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' property 'Style' value is not 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : property (css style) to be asserted
     * @param value
     *            : {2} : value of property (i.e css style property value)
     */
    public static void assertNotCssStyle(String loc, String prop, String value) {
        $(loc).assertNotCssStyle(prop, value);
    }

    /**
     * This method verify the css color property value of is equal to value provide for element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' color 'propertyStyle' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : color property (css style) to be verified
     * @param value
     *            : {2} : value of color in hex or rgb or rgba
     */
    public static void assertCssStyleColor(String loc, String prop, String value) {
        $(loc).assertCssStyleColor(prop, value);
    }

    /**
     * This method verify the css color property value of is not equal to value provide for element
     * locator
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * assert 'my.ele.loc' color 'propertyStyle' value is not 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param prop
     *            : {1} : color property (css style) to be verified
     * @param value
     *            : {2} : value of color in hex or rgb or rgba
     */
    public static void assertNotCssStyleColor(String loc, String prop, String value) {
        $(loc).assertNotCssStyleColor(prop, value);
    }
    /**
     * set the attribute value for specific locator somewhere in the page.
     * <p>
     * Example:
     * <p>
     * BDD
     * </p>
     * <code>
     * set 'my.ele.loc' attribute 'type' value is 'value'<br/>
     * </code>
     * <p>
     * KWD
     * </p>
     *
     * @param loc
     *            : {0} : an element locator, can be direct locator value or a
     *            locator key stored in locator repository
     * @param attr
     *            : {1} : attribute name which value not to be asserted
     * @param value
     *            : {2} : value of attribute
     */
    public static void setAttribute(String loc, String attr, String value) {
        $(loc).setAttribute(attr, value);
    }

    /**
     * add cookie, This will valid for the entire domain
     *
     * @param name
     *            : {0} name of the cookie
     * @param value
     *            : {1} value of the cookie
     */
    public static void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        new WebDriverTestBase().getDriver().manage().addCookie(cookie);
    }

    /**
     * Delete the named cookie from the current domain. This is equivalent to
     * setting the named cookie's expiry date to some time in the past.
     *
     * @param name
     *            : {0} name of the cookie to be deleted
     */
    public static void deleteCookie(String name) {
        new WebDriverTestBase().getDriver().manage().deleteCookieNamed(name);
    }

    /**
     * Delete all the cookies for the current domain.
     */
    public static void deleteAllCookies() {
        new WebDriverTestBase().getDriver().manage().deleteAllCookies();
    }

    /**
     * Get a cookie with a given name
     *
     * @param name
     *            : {0} name of the cookie
     */
    public static void getCookieValue(String name) {
        new WebDriverTestBase().getDriver().manage().getCookieNamed(name).getValue();
    }

    public static void mouseOver(String loc) {
        new WebDriverTestBase().getDriver().getMouse().mouseMove(((QAFExtendedWebElement) $(loc)).getCoordinates());
    }

    /**
     * Start time tracking which can be stopped by subsequent call to
     * {@link #stopTransaction()}. It will group all steps and track time with
     * given threshold comparison.
     *
     * @param name
     * @param threshold
     */
    public static void startTransaction(String name, int threshold) {

    }

    /**
     * Start time tracking which can be stopped by subsequent call to
     * {@link #stopTransaction()}. It will group all steps and track time.
     *
     * @param name
     */
    public static void startTransaction(String name) {

    }

    public static void stopTransaction() {

    }

    /**
     * @param locator
     *            - locator of frame
     * @return
     */
    public static Object switchToFrame(String locator) {
        return new WebDriverTestBase().getDriver().switchTo().frame(new QAFExtendedWebElement(locator));
    }

    /**
     * switches the webdriver context to the parent frame
     *
     * @return
     */
    public static Object switchToParentFrame() {
        return new WebDriverTestBase().getDriver().switchTo().parentFrame();
    }

    /**
     * switched to the defult window. Generally used to come out of any frame
     * context
     *
     * @return
     */
    public static Object switchToDefaultWindow() {
        return new WebDriverTestBase().getDriver().switchTo().defaultContent();
    }

    public static void clickShadowDOMElem(String rootSelector, String elemSelector) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentElement", DeviceUtils.getQAFDriver().findElement(By.cssSelector(rootSelector)));
        params.put("innerSelector", elemSelector);
        new ShadowDomUtils().clickElementShadowDOM(((RemoteWebDriver)DeviceUtils.getQAFDriver()), params);
    }

    public static void sendKeysToShadowDOMElem(String rootSelector, String elemSelector, String textToEnter) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentElement", DeviceUtils.getQAFDriver().findElement(By.cssSelector(rootSelector)));
        params.put("innerSelector", elemSelector);
        params.put("characterSequence", textToEnter);
        new ShadowDomUtils().sendKeysShadowDOM(((RemoteWebDriver)DeviceUtils.getQAFDriver()), params);
    }
}