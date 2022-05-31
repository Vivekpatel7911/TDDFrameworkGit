package com.tdd.core.Pages;

import com.tdd.core.utils.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class LoginPage extends BaseUtils {

Wait explicitWait;


    @FindBy(xpath = "//input[@id = 'txtUsername']")
    WebElement tbx_Username;

    @FindBy(xpath = "//input[@id = 'txtPassword']")
    WebElement tbx_Password;

    @FindBy(id = "btnLogin")
    WebElement btn_login;

    public LoginPage() throws IOException {
        explicitWait = new WebDriverWait(baseDriver,30 );
        PageFactory.initElements(baseDriver,this);
    }
    
    public void launchURL(){
        baseDriver.navigate().to("https://opensource-demo.orangehrmlive.com/");
    }

    public void LoginToApplication(String username, String password){
        explicitWait.until(ExpectedConditions.visibilityOf(tbx_Password));
        tbx_Username.sendKeys(username);
        tbx_Password.sendKeys(password);
        btn_login.click();
//        explicitWait.until(ExpectedConditions.invisibilityOf(btn_login));
    }
}
