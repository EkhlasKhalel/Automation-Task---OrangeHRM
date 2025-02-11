package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    private WebDriver driver;

    private final By Username = By.cssSelector("[placeholder=Username]");
    private final By Password = By.cssSelector("[placeholder=Password]");
    private final By LoginButton = By.tagName("button");
    private final By ErrorMessage = By.xpath("//p[text()='Invalid credentials']");
    private final By RequiredSpan = By.xpath("//span[text()='Required']");


    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

   public P01_LoginPage enterUsername(String username) {
       Utility.sendData(driver, Username, username);
       return this;
    }

    public P01_LoginPage enterPassword(String password) {
        Utility.sendData(driver, Password, password);
        return this;
    }

    public P02_HomePage clickOnLoginButton() {
        Utility.clickOnElement(driver, LoginButton);
        return new P02_HomePage(driver);
    }

    public boolean validLogin( String expectedUrl) {
        return driver.getCurrentUrl().equals(expectedUrl);
    }
    public boolean verifyErrorMessage(String expectedMessage, By ErrorMessage) {
        try {
            String actualMessage = driver.findElement(ErrorMessage).getText();
            return actualMessage.equals(expectedMessage);
        } catch (NoSuchElementException e) {
            System.out.println("Error message element not found!");
            return false;
        }
    }
    public boolean verifyRequiredSpan(String expectedMessage, By RequiredSpan) {
        try {
            String actualMessage = driver.findElement(RequiredSpan).getText();
            return actualMessage.equals(expectedMessage);
        } catch (NoSuchElementException e) {
            System.out.println("Error message element not found!");
            return false;
        }
    }
}
