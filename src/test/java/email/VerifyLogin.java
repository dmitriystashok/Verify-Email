package email;

import com.sun.org.apache.xml.internal.security.Init;
import email.EmailPage;
import email.LoginPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @Before
    public void setUp(){
        webDriver = InitializeDriver.startBrowser("http://www.i.ua/");
        //Such way if we use PageObject pattern
//        loginPage = new LoginPage(webDriver);
//        emailPage = new EmailPage(webDriver);
        //Facade pattern
        initPage = new InitPage(webDriver);
        initPage.loginPage().fillLoginForm("", "dmitriy_stashok123456");
        initPage.loginPage().clickLoginButton();
    }

    @Test
    public void checkThatLogged(){
        assert(initPage.emailPage().emailName.isDisplayed());
        System.out.println("Logged successfully");
    }

    @Test
    public void checkLogOut() throws InterruptedException {
        initPage.emailPage().logOut();
        // Verify that we are logged out
        assert (initPage.loginPage().loginField.isDisplayed());
        System.out.println("We are logged out");
    }
    @Ignore
    @Test
    public void checkSendEmail(){
        initPage.emailPage().clickCreateMessageButton();
        initPage.writeMailPage().sendEmail("dmitriy_stashok1@i.ua","Test Letter", "Test");
        initPage.writeMailPage().pressSendBtn();
        assert(initPage.emailPage().messageSentInfo.isDisplayed());
    }
    @Ignore
    @Test
    public void checkFailedEmailSend(){
        initPage.emailPage().clickCreateMessageButton();
        initPage.writeMailPage().sendEmail("Test","Test Letter", "Test");
        initPage.writeMailPage().pressSendBtn();
        assert(initPage.writeMailPage().errorMessage.isDisplayed());
    }
    @Ignore
    @Test
    public void checkMessageIsInInBox(){
        initPage.emailPage().clickCreateMessageButton();
        initPage.writeMailPage().sendEmail("dmitriy_stashok1@i.ua","Test Letter", "Test");
        initPage.writeMailPage().pressSendBtn();
        initPage.emailPage().openInBox();
        Assert.assertEquals("Test Letter", initPage.emailPage().themeName.getText());
        System.out.println("Sent message with theme " + initPage.emailPage().themeName.getText() + " is in the inBox");
    }
    @Ignore
    @Test
    public void checkSelectingCheckBox(){
        initPage.emailPage().openInBox();
        initPage.emailPage().selectCheckBox();
    }

    @After
    public void tearDown(){
        webDriver.close();
    }

}
