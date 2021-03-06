package email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Dmytro_Stashok on 12/19/2016.
 */
public class EmailPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public EmailPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 5);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//span[contains(.,'dmitriy_stashok1@i.ua')]")
    WebElement emailName;
    @FindBy(css = ".icon-ho.ho_settings.ho_i_settings")
    WebElement settings;
    @FindBy(xpath = "//*[@id='accountSettingsPopup']/ul/li[7]/a")
    WebElement exit;
    @FindBy(css = ".make_message>a")
    WebElement createMailButton;
    @FindBy(css = ".content.clear")
    WebElement messageSentInfo;
    @FindBy(css = ".new>a[href^='/list/INBOX']")
    WebElement inBox;
    @FindBy(xpath = "//*[@id='mesgList']//span[contains(.,'Test Letter')]")
    WebElement themeName;
    @FindBy(xpath = "//*[@type='checkbox']")
    WebElement checkBox;

    public void logOut() throws InterruptedException {
        settings.click();
        wait.until(ExpectedConditions.elementToBeClickable(exit));
        exit.click();
    }

    public void clickCreateMessageButton() {
        createMailButton.click();
    }

    public void openInBox() {
        inBox.click();
    }

    public void selectCheckBox() {
        checkBox.click();
    }


}
