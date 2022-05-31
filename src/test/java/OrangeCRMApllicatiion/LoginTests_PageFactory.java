package OrangeCRMApllicatiion;

import com.tdd.core.Pages.HomePage;
import com.tdd.core.Pages.LoginPage;
import com.tdd.core.utils.BaseUtils;
import com.tdd.core.utils.TestAsserts;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests_PageFactory {

    BaseUtils basePage;

    @BeforeTest
    public void setUp() throws IOException {
        basePage = new BaseUtils();
        basePage.launchBrowser("chrome");
    }

    @Test
    public void ValidateLogin() throws InterruptedException, IOException {

        LoginPage loginPage = new LoginPage();
        loginPage.launchURL();
        loginPage.LoginToApplication("Admin","admin123");

        basePage.sleepFor(2);
        HomePage homePage= new HomePage();
        Thread.sleep(2000);
        TestAsserts.assertIfVisible(homePage.getWelcomeMessage());
        System.out.println("Login Succesfull");

        Thread.sleep(5000);
     }

    @AfterTest
    public void tearDown() {
        basePage.closeBrowser();
    }
}


