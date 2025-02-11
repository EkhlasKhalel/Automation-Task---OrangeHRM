package Tests;
import Pages.P01_LoginPage;
import Utilities.DataUtilities;
import Utilities.LogUtilities;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtilities.getPropertyValue;

public class TC01_LoginPage {

    @BeforeMethod
    public void setup() throws IOException {
        setDriver(getPropertyValue("environment", "browser"));
        LogUtilities.info("chrome driver is opened");
        getDriver().get(getPropertyValue("environment", "Base_Url"));
        LogUtilities.info("page is redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validLoginTc() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "Username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "Password"))
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).validLogin(getPropertyValue("environment", "Home_Url")));
    }
    @Test
    public void InvalidUserAndValidPassword() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "invalidUsername"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "Password"))
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).verifyErrorMessage("Invalid credentials", By.xpath("//p[text()='Invalid credentials']")));
    }

    @Test
    public void InvalidLogin() throws FileNotFoundException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "invalidUsername"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "invalidPassword"))
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).verifyErrorMessage("Invalid credentials", By.xpath("//p[text()='Invalid credentials']")));
    }
    @Test
    public void ValidUserAndInvalidPassword() throws FileNotFoundException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "Username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "invalidPassword"))
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).verifyErrorMessage("Invalid credentials", By.xpath("//p[text()='Invalid credentials']")));
    }
    @Test
    public void BothFieldsEmpty() throws FileNotFoundException {
        new P01_LoginPage(getDriver())
                .enterUsername(" ")
                .enterPassword(" ")
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).verifyRequiredSpan("Required", By.xpath("//span[text()='Required']")));
    }
    @Test
    public void OnlyPasswordEmpty() throws FileNotFoundException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "Username"))
                .enterPassword(" ")
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).verifyRequiredSpan("Required", By.xpath("//span[text()='Required']")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}