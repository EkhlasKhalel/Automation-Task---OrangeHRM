package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utility {

    /**
     *utility function for clicking on element after waiting to be clickable
     * @param driver driver
     * @param locator locator
     * */

    private static final String ScreenShots_Path="test-outputs/ScreenShots/";
    //اى function هعملها جوا ال utility class ده هتبقى بتاخد منى حاجه ثابته دايما هى الدرايفر والجزء التانى هيبقى معتمد على ال function لو بتاخد مثلا داتا هبعتلها string وهكذا
    //وايضا الحاجات اللى هحتاجها هى ال byte webElement ان انا اكون بجيب الحاجه بتاعتى ك object By وبترجع ليا WebElement وهحتاجها كتير لان معظم ال function بتاخد ال parameter as WebElement
    //TODO:clicking on element
    public static void clickOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        //بعمل كليك على العنصر عن طريق سيلينيوم عادى ولكن مزود حته على سيلينيوم انى قبل ما اعمل كليك اعمل ويت على العنصر
    }

    //نفس الفكره لو عايز ابعت داتا لاى عنصر نفس الحاجه سيلينيوم مش بيعمل وايت مش كل ما احتاج ابعت داتا لاى حاجه هفضل اروح واقرر فى السطر بتاع الوايت ده
    //TODO:sending data to element
    public static void sendData(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }
    //TODO:getting data from element
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(locator));
        generalWait(driver).until(ExpectedConditions.alertIsPresent());
        return driver.findElement(locator).getText();
    }
    public static WebDriverWait generalWait(WebDriver driver) {
         return new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public static void scrolling (WebDriver driver,By locator){
       // ((JavascriptExecutor)driver).executeScript("arguments[0].scrollInView();",findWebElement(driver, locator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollInView();",driver.findElement(locator));
    }
    public static WebElement findWebElement(WebDriver driver, By locator){
        return driver.findElement(locator);
    }
    public static void Shots(WebDriver driver,String imageName) throws IOException {
       // String path = "E:\\projects\\untitled1\\src\\main\\resources\\";
        File source=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File target=new File(ScreenShots_Path+imageName+"-"+getTimeStamp()+".png"); //"E:\\projects\\untitled1\\src\\main\\resources\\test.png"
        FileUtils.copyFile(source,target);//هنا بجيب العنصر منين واعمله اضافه فين
        //ده الجزء انى اخد اسكرين شوت لصفحه كامله

        //Attach the screenshot to Allure
        Allure.addAttachment(imageName, Files.newInputStream(Path.of(target.getPath())));
        }

    // TimeStamp function
    public static String getTimeStamp(){

        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }
    //TODO:selecting from dropdown
     public static void SelectingFromDropDown(WebDriver driver,By locator,String option){
          new Select(driver.findElement(locator)).selectByVisibleText(option);

     }

    //TODO:scrolling to element
    //TODO:selecting from dropdown
    //TODO:taking screenshot
    //TODO:converting by to element
    //TODO:general wait
    //TODO:uploading files using roobot
    //TODO:get timestamp
}

