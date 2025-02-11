package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_HomePage {

private WebDriver driver;

    private By dashboardHeader=By.xpath("//h6[text()='Dashboard']");

    public P02_HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isDashboardVisible() {
        return driver.findElement(dashboardHeader).isDisplayed();
    }


}
