package email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Dmytro_Stashok on 12/20/2016.
 */
public class InitPage {
    private WebDriver webDriver;

    public InitPage(WebDriver driver){
        webDriver = driver;
    }

    public LoginPage loginPage(){return new LoginPage(webDriver);}
    public EmailPage emailPage(){return new EmailPage(webDriver);}
    public WriteMailPage writeMailPage(){return new WriteMailPage(webDriver);}
}
