package email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Dmytro_Stashok on 12/17/2016.
 */
public class LoginPage {
    private WebDriver webDriver;

    @FindBy(name="login") WebElement loginField;

    @FindBy(name="pass") WebElement passField;

    @FindBy(css = ".content.clear>form>p>input") WebElement loginButton;

    public LoginPage(WebDriver driver){
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public void fillLoginForm (String uId,String uPass) {
        loginField.sendKeys(uId);
        passField.sendKeys(uPass);

    }
    public void clickLoginButton(){
        loginButton.click();
    }
}
