package email;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass {
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
    public void checkSendEmail() throws InterruptedException {
        emailPage.clickCreateMessageButton();
        writeMailPage.sendEmail(SendMailTo, EmailTheme, EmailTheme);
        Assert.assertTrue(emailPage.messageSentInfo.isDisplayed(), "Message sent info is not displayed");
        emailPage.logOut();
    }


    @AfterClass
    public void tearDown() {
        webDriver.close();
    }


}
