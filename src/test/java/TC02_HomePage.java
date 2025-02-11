import Pages.P01_LoginPage;
import Pages.P02_HomePage;
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

public class TC02_HomePage {

    @BeforeMethod
    public void setup() throws IOException {
        setDriver(getPropertyValue("environment", "browser"));
        LogUtilities.info("chrome driver is opened");
        getDriver().get(getPropertyValue("environment", "Base_Url"));
        LogUtilities.info("page is redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void DashboardVisibility() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "Username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "Password"))
                .clickOnLoginButton();
        Assert.assertTrue(new P02_HomePage(getDriver()).isDashboardVisible(), "Dashboard is not visible");
    }
    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
