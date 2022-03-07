package com.quantum.pages;


import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.quantum.utils.DeviceUtils;
import com.quantum.utils.DriverUtils;
import org.openqa.selenium.support.ui.Select;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.PropertyUtil;
import com.quantum.utils.ReportUtils;


public class Cafe24Page extends WebDriverBaseTestPage<WebDriverTestPage> {

    PropertyUtil props = ConfigurationManager.getBundle();

    @Override
    protected void openPage(PageLocator locator, Object... args) {

    }

    @FindBy(locator = "btn.hamburgermenu")
    private QAFExtendedWebElement btnhamburgermenu;
    @FindBy(locator = "btn.signup")
    private QAFExtendedWebElement btnsignup;
    @FindBy(locator = "btn.log")
    private QAFExtendedWebElement btnlog;
    @FindBy(locator = "chkbox.agreeallconditions")
    private QAFExtendedWebElement chkboxagreeallconditions;
    @FindBy(locator = "btn.next")
    private QAFExtendedWebElement btnnext;
    @FindBy(locator = "inp.email")
    private QAFExtendedWebElement inpemail;
    @FindBy(locator = "inp.pwd")
    private QAFExtendedWebElement inppwd;
    @FindBy(locator = "inp.confpwd")
    private QAFExtendedWebElement inpconfpwd;
    @FindBy(locator = "inp.name")
    private QAFExtendedWebElement inpname;
    @FindBy(locator = "inp.lastname")
    private QAFExtendedWebElement inplastname;
    @FindBy(locator = "inp.phone")
    private QAFExtendedWebElement inpphone;
    @FindBy(locator = "p.successmessage")
    private QAFExtendedWebElement psuccessmessage;
    @FindBy(locator = "btn.complete")
    private QAFExtendedWebElement btncomplete;
    @FindBy(locator = "inp.id")
    private QAFExtendedWebElement inpid;
    @FindBy(locator = "inp.passwd")
    private QAFExtendedWebElement inppasswd;
    @FindBy(locator = "btn.login")
    private QAFExtendedWebElement btnlogin;
    @FindBy(locator = "lnk.outerwear")
    private QAFExtendedWebElement lnkouterwear;
    @FindBy(locator = "img.product1")
    private QAFExtendedWebElement imgproduct1;
    @FindBy(locator = "btn.buy")
    private QAFExtendedWebElement btnbuy;
    @FindBy(locator = "sel.province")
    private QAFExtendedWebElement selprovince;
    @FindBy(locator = "sel.city")
    private QAFExtendedWebElement selcity;
    @FindBy(locator = "sel.barangay")
    private QAFExtendedWebElement selbarangay;
    @FindBy(locator = "inp.phone2")
    private QAFExtendedWebElement inpphone2;
    @FindBy(locator = "btn.placeorder")
    private QAFExtendedWebElement btnplaceorder;
    @FindBy(locator = "btn.conforder")
    private QAFExtendedWebElement btnconforder;
    @FindBy(locator = "p.ordermessage")
    private QAFExtendedWebElement pordermessage;
    @FindBy(locator = "txt.orderid")
    private QAFExtendedWebElement txtorderid;
    
    @FindBy(locator = "inp.storeid")
    private QAFExtendedWebElement inpstoreid;
    @FindBy(locator = "btn.storenext")
    private QAFExtendedWebElement btnstorenext;
    @FindBy(locator = "inp.storepwd")
    private QAFExtendedWebElement inpstorepwd;
    @FindBy(locator = "btn.storelogin")
    private QAFExtendedWebElement btnstorelogin;
    @FindBy(locator = "lnk.storeorders")
    private QAFExtendedWebElement lnkstoreorders;
    @FindBy(locator = "lnk.storeallorders")
    private QAFExtendedWebElement lnkstoreallorders;
    @FindBy(locator = "inp.ordernum")
    private QAFExtendedWebElement inpordernum;
    @FindBy(locator = "btn.search")
    private QAFExtendedWebElement btnsearch;
    @FindBy(locator = "inp.orderchkbox")
    private QAFExtendedWebElement inporderchkbox;
    @FindBy(locator = "inp.order")
    private QAFExtendedWebElement inporder;
    @FindBy(locator = "inp.ordercancel.chkbox")
    private QAFExtendedWebElement inpordercancelchkbox;
    @FindBy(locator = "btn.ordercancel")
    private QAFExtendedWebElement btnordercancel;
    @FindBy(locator = "btn.acceptcancel")
    private QAFExtendedWebElement btnacceptcancel;
    @FindBy(locator = "sel.cancelreason")
    private QAFExtendedWebElement selcancelreason;
    @FindBy(locator = "btn.acceptcancellation")
    private QAFExtendedWebElement btnacceptcancellation;
    @FindBy(locator = "txt.msg")
    private QAFExtendedWebElement txtmsg;

    String username;
    public static String orderid;
    String parent;
    String child1;
    
    public void clickHamburgermenu(){
        btnhamburgermenu.waitForVisible(10000);
    	btnhamburgermenu.click();
    }
    
    public void clickSignup(){
        btnsignup.waitForVisible(10000);
    	btnsignup.click();
    }
    
    public void clickAgreeAllConditions()
    {
        chkboxagreeallconditions.waitForVisible(10000);
        chkboxagreeallconditions.click();
    }
    
    public void clickNext()
    {
    	btnnext.waitForVisible(10000);
        btnnext.click();
    }
    
    public void signUp() {
        try {
            btnnext.waitForVisible(10000);
            // Generate random ID
            double number = (Math.random() * ((99999 - 100) + 1)) + 100;
            int randomNo = Double.valueOf(number).intValue();
            String username = "uat" + randomNo;
            inpemail.sendKeys(username + "@" + randomNo + ".com");
            inppwd.sendKeys("TestingUser123#");
            inpconfpwd.sendKeys("TestingUser123#");
            inpname.sendKeys(username);
            inplastname.sendKeys("dot");
            selectDropdown(selprovince, "ABRA");
            selectDropdown(selcity, "BOLINEY");
            selectDropdown(selbarangay, "Amti");
            inpphone.sendKeys(String.valueOf(randomNo));
            Thread.sleep(2000);
            btnnext.click();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
    
    public String verifyUserCreationMessage()
    {
        psuccessmessage.waitForVisible(10000);
    	return(psuccessmessage.getText());
    }

    public void clickComplete()
    {
        btncomplete.waitForVisible(10000);
    	btncomplete.click();
    }

    public void orderProduct() throws InterruptedException
    {
        lnkouterwear.waitForVisible(10000);
    	lnkouterwear.click();
        imgproduct1.waitForVisible(10000);
    	imgproduct1.click();
        btnbuy.waitForVisible(10000);
        Thread.sleep(3000);
    	btnbuy.click();
        selprovince.waitForVisible(10000);
    }
    
    public void checkout()
    {
    	try {
            selprovince.waitForVisible(10000);
            selectDropdown(selprovince,"ABRA");
            selcity.waitForVisible(10000);
            selectDropdown(selcity,"BOLINEY");
            selbarangay.waitForVisible(10000);
            selectDropdown(selbarangay,"Amti");
            inpphone2.waitForVisible(10000);
            inpphone2.sendKeys("9999999999");
            btnplaceorder.waitForVisible(10000);
            btnplaceorder.click();
            btnconforder.waitForVisible(10000);
            btnconforder.click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public boolean verifyOrderSuccess()
    {
        pordermessage.waitForVisible(10000);
    	return(pordermessage.isDisplayed());	
    }
    
    public String orderNumber()
    {
        txtorderid.waitForVisible(10000);
    	orderid=txtorderid.getText();
    	return(orderid);
    }
    
    public void login_admin()
    {
        inpstoreid.waitForVisible(10000);
    	inpstoreid.sendKeys("vestworks24");
    	btnstorenext.click();
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Thread.sleep(3000);
    	inpstorepwd.sendKeys("worksvest42");
    	btnstorelogin.click();
    }
    
    public void search_order()
    {
        lnkstoreorders.waitForVisible(10000);
    	lnkstoreorders.click();
    	lnkstoreallorders.click();
    	inpordernum.sendKeys(orderid);
    	btnsearch.click();
    }
    
    public void select_order()
    {
    }
    
    public void cancel_order()
    {
        inporderchkbox.waitForVisible(10000);
    	parent=driver.getWindowHandle();
    	inporderchkbox.click();
    	inporder.click();
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Set<String>s=driver.getWindowHandles();

    	// Now iterate using Iterator
    	Iterator<String> I1= s.iterator();
        while (I1.hasNext())
    	{
            String child_window= I1.next();
            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);
                child1=driver.getWindowHandle();
    		}
    	}
    	inpordercancelchkbox.click();
    	btnordercancel.click();
    }
    
    public void accept_cancel_order()
    {
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//String parent=driver.getWindowHandle();
    	Set<String>s=driver.getWindowHandles();

    	// Now iterate using Iterator
    	Iterator<String> I1= s.iterator();
        while (I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window) && !child1.equals(child_window))
                driver.switchTo().window(child_window);
        }
        inpordercancelchkbox.waitForVisible(10000);
    	inpordercancelchkbox.click();
    	btnacceptcancel.click();
    	selectDropdown(selcancelreason,"Change of mind");
    	btnacceptcancellation.click();
    	driver.switchTo().alert().accept();
    }
    
    public boolean validate_cancellation()
    {
    	driver.switchTo().window(child1);
    	boolean exists = txtmsg.isDisplayed();
    	driver.close();
    	driver.switchTo().window(parent);
    	return(exists);

    }
    
    public void selectDropdown(QAFExtendedWebElement ele,String value)
    {
    	try {
			Thread.sleep(1000);
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	Select dropdown = new Select(ele);
        ele.click();
    	dropdown.selectByVisibleText(value);
    }



//    public void verifyResult(String result){
//        QAFExtendedWebElement searchResult = new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("search.result.link"), result));
//        ReportUtils.logAssert("Expected result: " + result, searchResult.isDisplayed());
//    }
//
//    public void verifyResult(List<String> results){
//        QAFExtendedWebElement searchResultElement;
//        for (String result : results) {
//            QAFExtendedWebElement searchResult = new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("search.result.link"), result));
//            ReportUtils.logAssert("Expected result: " + result, searchResult.isDisplayed());
//        }
//    }
}
