package email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmytro_Stashok on 12/20/2016.
 */
public class InitializeDriver {
    static WebDriver webDriver;
    public static WebDriver startBrowser(String startURL){
        System.setProperty("webdriver.chrome.driver", "D:\\Home\\Java\\BrowserDrivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get(startURL);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }
}
