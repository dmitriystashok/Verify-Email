package email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dmytro_Stashok on 12/20/2016.
 */
public class WriteMailPage {
    private WebDriver webDriver;

    public WriteMailPage (WebDriver driver){
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id="to") WebElement toWhomField;
    @FindBy(name="subject") WebElement themeField;
    @FindBy(name="send") WebElement sendBtn;
    @FindBy(id="text") WebElement textField;
    @FindBy(id="to_errCtrl") WebElement errorMessage;

    public void sendEmail(String toWhom, String theme, String text){
        toWhomField.sendKeys(toWhom);
        themeField.sendKeys(theme);
        textField.sendKeys(text);
    }
    public void pressSendBtn(){
        sendBtn.click();
    }
}
