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

public class HomePage extends BaseUtils {
    Wait wait;

    @FindBy(xpath = "//a[contains(text(),'Welcome')]")
    WebElement WelcomeMessage;

    public HomePage() throws IOException {
        wait = new WebDriverWait(baseDriver,30);
        PageFactory.initElements(baseDriver,this);
    }
    public WebElement getWelcomeMessage(){
        wait.until(ExpectedConditions.textToBePresentInElement(WelcomeMessage,"Welcome Paul"));
        return WelcomeMessage;
    }
}
