package com.tdd.core.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseUtils {
    public static WebDriver baseDriver;
    PropertiesLoader ORProps;
    String ORFilePath;


//    public BaseUtils() throws IOException {
//
//        ORProps = new PropertiesLoader();
//        ORFilePath = System.getProperty("user.dir") + "//src//main//resources//OR.properties";
//        ORProps.loadAllProperties(ORFilePath);
//    }

    public void launchBrowser(String browserType) {

        if (browserType.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            // to block popups add

            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory",("user+dir")+ "//browserDownload//");
            prefs.put("excludeSwitches", Arrays.asList("disable-popup-blocking"));
            prefs.put("credencials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("useAutomationExtension", false);
            options.setExperimentalOption("prefs", prefs);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\chromedriver.exe");
            baseDriver = new ChromeDriver(options);

        } else if (browserType.equalsIgnoreCase("IE")) {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            ieOptions.destructivelyEnsureCleanSession();
            ieOptions.introduceFlakinessByIgnoringSecurityDomains();
            ieOptions.ignoreZoomSettings();
            ieOptions.requireWindowFocus();
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\IEDriverServer.exe");
            baseDriver = new InternetExplorerDriver(ieOptions);

        } else if (browserType.equalsIgnoreCase("Edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\msedgedriver.exe");
            baseDriver = new EdgeDriver();
        }

        baseDriver.manage().window().maximize();
        baseDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        baseDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void closeAllBrowsers() {
        baseDriver.quit();
    }

    public void closeBrowser() {
        baseDriver.close();

    }

    public void refreshBrowser() {
        baseDriver.navigate().refresh();

    }

    public void launchURL(String url) {
        baseDriver.navigate().to(url);

    }

    public void sleepFor(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    public void clickOn(String elementName) {
        ElementFinder obj = new ElementFinder(baseDriver);
        String value = ORProps.getProperty(elementName);
        String by = value.split("-")[0];                         // or use  String by = splittedValues[0];
        String byValue = value.split("-")[1];                   //or use  String byValue = splittedValues[0];
        WebElement element = obj.findElementsBy(by, byValue);
        element.click();
    }

    public void typeInto(String elementName, String testValue) {
        ElementFinder obj = new ElementFinder(baseDriver);
        String value = ORProps.getProperty(elementName);
//        String[] splittedValues = value.split("-");
//        String by = splittedValues[0];
//        String byValue = splittedValues[0];
        String by = value.split("-")[0];
        String byValue = value.split("-")[1];
        WebElement element = obj.findElementsBy(by, byValue);
        element.sendKeys(testValue);
    }

    public WebElement getElementFromWebPage(String elementName) {
        ElementFinder obj = new ElementFinder(baseDriver);
        String value = ORProps.getProperty(elementName);
        String by = value.split("-")[0];                         // or use  String by = splittedValues[0];
        String byValue = value.split("-")[1];                   //or use  String byValue = splittedValues[0];
        WebElement element = obj.findElementsBy(by, byValue);
        element.click();
        return element;
    }

    public void switchToSecondWindow(){
        //step1--> get no of windows.
        Set<String> windows = baseDriver.getWindowHandles();  //because when u click on tab new tab opens that's index[1]

        //step2-->convert set into a list so that we can fetch the window by index.
        List<String> windowList = new ArrayList<String>(windows);
        baseDriver.switchTo().window(windowList.get(1));
    }
    public void switchToOriginalWindow(){

        Set<String> windows = baseDriver.getWindowHandles();
        List<String> windowList = new ArrayList<String>(windows);
        baseDriver.switchTo().window(windowList.get(0));
    }
    public void switchToLastWindow(){

        Set<String> windows = baseDriver.getWindowHandles();
        List<String> windowList = new ArrayList<String>(windows);
        baseDriver.switchTo().window(windowList.get(windowList.size()-1));
    }
    public void switchToSecondLastWindow(){

        Set<String> windows = baseDriver.getWindowHandles();
        List<String> windowList = new ArrayList<String>(windows);
        baseDriver.switchTo().window(windowList.get(windowList.size()-2));
    }
    public void switchToFrame(WebElement element){

        baseDriver.switchTo().defaultContent();
    }
    public void switchToJavaScriptAlert(){

        baseDriver.switchTo().alert().accept();
    }
    public void switchToJavaScriptAlertAndDismiss(){

        baseDriver.switchTo().alert().dismiss();
    }


}
