package OrangeCRMApllicatiion;

import com.tdd.core.utils.BaseUtils;
import com.tdd.core.utils.TestAsserts;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;

public class LoginTests {

    BaseUtils basePage;

    @BeforeTest
    public void setUp() throws IOException {
        basePage = new BaseUtils();
        basePage.launchBrowser("edge");
    }

    @Test
    public void ValidateLogin() throws InterruptedException {
        basePage.launchURL("https://opensource-demo.orangehrmlive.com/");
        basePage.typeInto("LoginPage.tbx_Username", "Admin");
        basePage.typeInto("LoginPage.tbx_Password", "admin123");
        basePage.clickOn("LoginPage.btn_login");
        basePage.sleepFor(5);
        TestAsserts.assertIfVisible(basePage.getElementFromWebPage("DashboardPage.lnk_WelcomeMessage"));
        System.out.println("Login Succesfull");

    Thread.sleep(5000);
        basePage.clickOn("DashboardPage.lnk_WelcomeMessage");
        basePage.clickOn("DashboardPage.Tab_Logout");
    }
    @Test
    public void inValidateLogin() throws InterruptedException {
        basePage.launchURL("https://opensource-demo.orangehrmlive.com/");
        basePage.typeInto("LoginPage.tbx_Username", "Admin1");
        basePage.typeInto("LoginPage.tbx_Password", "admin123");
        basePage.clickOn("LoginPage.btn_login");
        basePage.sleepFor(5);
        TestAsserts.assertIfVisible(basePage.getElementFromWebPage("Error.lnk_message"));
        System.out.println("Error message displayed");
    }

    @AfterTest
    public void tearDown() {
        basePage.closeBrowser();
    }
}


