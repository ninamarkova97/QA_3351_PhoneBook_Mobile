package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.extern.slf4j.Slf4j;
import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Slf4j
public class AuthenticationScreen extends BaseScreen {
    public AuthenticationScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement emailEditText;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPassword")
    WebElement passwordEditText;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/loginBtn")
    WebElement loginBtn;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/regBtn" )
    WebElement registrationBtn;

    public AuthenticationScreen fillEmail(String email) {
        //pause(4000)
        should(emailEditText,10);
        type(emailEditText, email);
        return this;
    }



    public AuthenticationScreen fillPassword(String password) {
        type(passwordEditText, password);
        return this;
    }
    public ContactListScreen submitLogin(){
        loginBtn.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen fillLoginRegistrationForm(User user) {
        should(emailEditText,10);
        type(emailEditText, user.getEmail());
        type(passwordEditText, user.getPassword());
        return this;
    }

    public AuthenticationScreen submitLoginNegative() {
        loginBtn.click();
        return this;
    }

    public AuthenticationScreen isErrorMessageHasText(String text){
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();

        return this;


    }

    public ContactListScreen submitRegistration() {
        registrationBtn.click();
        return new ContactListScreen(driver);
    }
}
