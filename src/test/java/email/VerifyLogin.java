package email;

import com.sun.org.apache.xml.internal.security.Init;
import email.EmailPage;
import email.LoginPage;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Dmytro_Stashok on 12/17/2016.
 */
public class VerifyLogin {
    WebDriver webDriver;
    // Tried Facade pattern
//        InitPage initPage;
    // Could be done with PageObject pattern
    private LoginPage loginPage;
    private EmailPage emailPage;
    private WriteMailPage writeMailPage;

    private String LoginName = "dmitriy_stashok1";
    private String Password = "dmitriy_stashok123456";
    private String SendMailTo = "dmitriy_stashok1@i.ua";
    private String WrongEmailAddress = "123";
    private String EmailTheme = "Test Letter";
    private String EmailText = "Test";

    @BeforeClass
    public void setUp() {
        webDriver = InitializeDriver.startBrowser("http://www.i.ua/");
        //Such way if we use PageObject pattern
        loginPage = new LoginPage(webDriver);
        emailPage = new EmailPage(webDriver);
        writeMailPage = new WriteMailPage(webDriver);
        //Facade pattern
//        initPage = new InitPage(webDriver);
        loginPage.fillLoginForm(LoginName, Password);
//          not required as we added click to fillloginform method
//        loginPage.clickLoginButton();
    }


    @Test(priority = 1)
    public void checkThatLogged() {
        Assert.assertTrue(emailPage.emailName.isDisplayed(), "email name is not displayed");
        System.out.println("Logged successfully");
    }


    @Test(priority = 2)
    public void checkLogOut() throws InterruptedException {
        emailPage.logOut();
        // Verify that we are logged out
        Assert.assertTrue(loginPage.loginField.isDisplayed(), "Login field is not displayed");
        System.out.println("We are logged out");
    }

    @Test(priority = 3, enabled = false)
    public void checkSendEmail() throws InterruptedException {
        loginPage.fillLoginForm(LoginName, Password);
        emailPage.clickCreateMessageButton();
        writeMailPage.sendEmail(SendMailTo, EmailTheme, EmailTheme);
        Assert.assertTrue(emailPage.messageSentInfo.isDisplayed(), "Message sent info is not displayed");
        emailPage.logOut();
    }

    @Test(priority = 4, enabled = false)
    public void checkFailedEmailSend() throws InterruptedException {
        loginPage.fillLoginForm(LoginName, Password);
        emailPage.clickCreateMessageButton();
        writeMailPage.sendEmail(WrongEmailAddress, EmailTheme, EmailText);
        Assert.assertTrue(writeMailPage.errorMessage.isDisplayed(), "Error message is not displayed");
        emailPage.logOut();
    }

    @Test(priority = 4, enabled = false)
    public void checkMessageIsInInBox() throws InterruptedException {

        loginPage.fillLoginForm(LoginName, Password);
        emailPage.clickCreateMessageButton();
        writeMailPage.sendEmail(SendMailTo, EmailTheme, EmailTheme);
        emailPage.openInBox();
        Assert.assertEquals("Test Letter", emailPage.themeName.getText(), "Message is not in inbox");
        System.out.println("Sent message with theme " + emailPage.themeName.getText() + " is in the inBox");
        emailPage.logOut();
    }

    @AfterClass
    public void tearDown() {
        webDriver.close();
    }

}
