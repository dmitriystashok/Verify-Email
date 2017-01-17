package email;

import com.sun.org.apache.xml.internal.security.Init;
import email.EmailPage;
import email.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Dmytro_Stashok on 12/17/2016.
 */
public class VerifyLogin {
        WebDriver webDriver;
    // Tried Facade pattern
        InitPage initPage;
    // Could be done with PageObject pattern
//        LoginPage loginPage;
//        EmailPage emailPage;

    @BeforeTest
    public void setUp(){
        webDriver = InitializeDriver.startBrowser("http://www.i.ua/");
        //Such way if we use PageObject pattern
//        loginPage = new LoginPage(webDriver);
//        emailPage = new EmailPage(webDriver);
        //Facade pattern
        initPage = new InitPage(webDriver);
        initPage.loginPage().fillLoginForm("dmitriy_stashok1", "dmitriy_stashok123456");
        initPage.loginPage().clickLoginButton();
    }

    @Test (priority = 1)
    public void checkThatLogged(){
        Assert.assertTrue(initPage.emailPage().emailName.isDisplayed(), "email name is not displayed");
        System.out.println("Logged successfully");
    }


    @Test(priority = 2)
    public void checkLogOut() throws InterruptedException {
        initPage.emailPage().logOut();
        // Verify that we are logged out
        Assert.assertTrue(initPage.loginPage().loginField.isDisplayed(), "Login field is not displayed");
        System.out.println("We are logged out");
    }

    @Test(enabled = false)
    public void checkSendEmail(){
        initPage.emailPage().clickCreateMessageButton();
        initPage.writeMailPage().sendEmail("dmitriy_stashok1@i.ua","Test Letter", "Test");
        initPage.writeMailPage().pressSendBtn();
        Assert.assertTrue(initPage.emailPage().messageSentInfo.isDisplayed(), "Message sent info is not displayed");
    }

    @Test (enabled = false)
    public void checkFailedEmailSend(){
        initPage.emailPage().clickCreateMessageButton();
        initPage.writeMailPage().sendEmail("Test","Test Letter", "Test");
        initPage.writeMailPage().pressSendBtn();
        Assert.assertTrue(initPage.writeMailPage().errorMessage.isDisplayed(), "Error message is not displayed");
    }

    @Test (enabled = false)
    public void checkMessageIsInInBox(){
        initPage.emailPage().clickCreateMessageButton();
        initPage.writeMailPage().sendEmail("dmitriy_stashok1@i.ua","Test Letter", "Test");
        initPage.writeMailPage().pressSendBtn();
        initPage.emailPage().openInBox();
        Assert.assertEquals("Test Letter", initPage.emailPage().themeName.getText());
        System.out.println("Sent message with theme " + initPage.emailPage().themeName.getText() + " is in the inBox");
    }

    @Test (enabled = false)
    public void checkSelectingCheckBox(){
        initPage.emailPage().openInBox();
        initPage.emailPage().selectCheckBox();
    }

    @AfterClass
    public void tearDown(){
        webDriver.close();
    }

}
