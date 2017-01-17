package search;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmytro_Stashok on 12/8/2016.
 */
public class OpenPage {

    private ChromeDriver driver;
    private static final String Start_URL="https://www.google.com.ua";

    @BeforeTest(enabled = false)
    public void setDriver(){
    System.setProperty("webdriver.chrome.driver", "D:\\Home\\Java\\BrowserDrivers\\chromedriver.exe");
    driver=new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 }

    public void searchField(String searchValue){
    driver.findElement(By.id("lst-ib")).clear();
    driver.findElement(By.id("lst-ib")).sendKeys(searchValue);
}

    public void buttonClick(){
    driver.findElement(By.name("btnG")).click();
}

    @Test(enabled = false)
    public void openPage(){
        driver.get(Start_URL);
        searchField("selenium");
        buttonClick();
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,'Selenium - Web Browser Automation')]")).isDisplayed(), "Login page is not opened");
        System.out.println("Test finished");
    }

    @Test (enabled = false)
    public void anotherSearch(){
        driver.get(Start_URL);
        searchField("Google");
        buttonClick();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(.,'Пошукова система українською мовою. Пошук сторінок зі світу чи з України.')]")).isDisplayed());
        System.out.println("Test 2 finished");
    }

   @AfterClass(enabled = false)
    public void tearDown(){
        driver.close();
    }
}
