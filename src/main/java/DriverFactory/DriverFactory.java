package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

private static ThreadLocal<WebDriver>driverThreadLocal=new ThreadLocal<>();
public static void setDriver(String browser){
    switch (browser.toLowerCase()){
        case"chrome":
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--start-maximized");
            driverThreadLocal.set(new ChromeDriver(options));
            break;
        case"firefox":
            driverThreadLocal.set(new FirefoxDriver());
            break;
        default:
            EdgeOptions options1=new EdgeOptions();
            options1.addArguments("--start-maximized");
            driverThreadLocal.set(new EdgeDriver(options1));

    }
}
public static WebDriver getDriver(){
     return driverThreadLocal.get();
}
public static void quitDriver(){
    getDriver().quit();
    driverThreadLocal.remove();
}
}
