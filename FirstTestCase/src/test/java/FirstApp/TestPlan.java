package FirstApp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import org.openqa.selenium.By;
import static FirstApp.Utils.*;

public class TestPlan {
    public static void main(String[] args) throws InterruptedException {

        // ChromeDriver location set up in Utils class
        System.setProperty("WebDriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);

        // Launch browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        System.out.println("Hello, welcome to CVT page");
        driver.get(Utils.BASE_URL);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        // Open CVT page and click Forget Password
        WebElement forgetPW = driver.findElement(By.id("forgot-password-button"));
        forgetPW.click();
        WebElement email = driver.findElement(By.id("usernameField"));
        email.sendKeys("plata8@yopmail.com");
        WebElement next = driver.findElement(By.id("submit-button"));
        next.click();
        String checkMail = driver.findElement(By.id("check-email-title")).getAttribute("innerHTML");
        System.out.println(checkMail);
        System.out.println("Goodbye, closing CVT page");

        // Open YOP mail page and login to reset password
        driver.get(Utils.EMAIL_URL);
        Thread.sleep(5000);
        System.out.println("Hello, welcome to YOP mail page");
        WebElement email2 = driver.findElement(By.xpath("//input[@id='login']"));
        email2.sendKeys(EMAIL);
        WebElement submit = driver.findElement(By.xpath("//button[@class='md']"));
        submit.click();
        Thread.sleep(5000);

        //Switch to mailbox window
        driver.switchTo().frame("ifmail");
        WebElement resetPW = driver.findElement(By.xpath("//div[@id='mail']//td//td//td//td//td/a"));
        System.out.println("Resetting Password");
        resetPW.click();
        System.out.println("Goodbye, closing YOP mail page");

        //Switch to CVT page to enter new password
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        //driver.close();
        //driver.switchTo().window(tabs2.get(0));

        //enter invalid password
        WebElement newPW = driver.findElement(By.id("newPassword"));
        newPW.clear();
        newPW.sendKeys(pw1);
        System.out.println("Enter New Password");
        WebElement createPW = driver.findElement(By.id("submit-button"));
        createPW.click();
        Thread.sleep(5000);
        String validationError1 = driver.findElement(By.id("newPass-help-block")).getText();
        System.out.println(validationError1 + ". Please enter a new password.");

        //enter invalid password
        newPW.clear();
        newPW.sendKeys(pw2);
        createPW.click();
        Thread.sleep(5000);
        String validationError2 = driver.findElement(By.id("newPass-help-block")).getText();
        System.out.println(validationError2 + ". Please enter a new password.");

        //enter invalid password
        newPW.clear();
        newPW.sendKeys(pw3);
        createPW.click();
        Thread.sleep(5000);
        String validationError3 = driver.findElement(By.id("newPass-help-block")).getText();
        System.out.println(validationError3 + ". Please enter a new password.");

        //enter invalid password
        newPW.clear();
        newPW.sendKeys(pw4);
        createPW.click();
        Thread.sleep(5000);
        String validationError4 = driver.findElement(By.id("newPass-help-block")).getText();
        System.out.println(validationError4 + ". Please enter a new password.");

        //enter correct password
        newPW.clear();
        newPW.sendKeys(pw5);
        createPW.click();
        Thread.sleep(5000);
        String passwordChange = driver.findElement(By.id("password-changed-title")).getText();
        System.out.println(passwordChange);
        WebElement done = driver.findElement(By.id("login-url-continue"));
        done.click();
        System.out.println("New password is:" + pw5 + "  using you're username:" + EMAIL);
        Thread.sleep(5000);

        //Login with new password
        WebElement login = driver.findElement(By.id("username-input"));
        login.clear();
        login.sendKeys(EMAIL);
        WebElement password = driver.findElement(By.id("password-input"));
        password.clear();
        password.sendKeys(pw5);
        WebElement loginButton = driver.findElement(By.id("submit-button"));
        loginButton.click();
        Thread.sleep(10000);

        String welcomeMSG = driver.findElement(By.xpath("//*[@id='main-content']/div/div[2]/div[2]/div/h1/span")).getText();
        System.out.println(welcomeMSG);
        System.out.println("Successful login!");
        Thread.sleep(1000);
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}


