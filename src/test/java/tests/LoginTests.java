package tests;

import config.AppiumConfig;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {


    @Test
    public void loginSuccess(){
    //  boolean result =  new SplashScreen(driver).checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("lolik@mail.ru")
                .fillPassword("Lolik123!")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }



    @Test
    public void loginSuccessModel() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("lolik@mail.ru")
                        .password("Lolik123!").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel1() {
Assert.assertTrue( new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("lolik@mail.ru")
                        .password("Lolik123!").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));

    }

    @Test
    public void loginWrongEmail(){
    new AuthenticationScreen(driver)
            .fillLoginRegistrationForm(User.builder()
                    .email("lolikmail.ru")
                    .password("Lolik123!")
                    .build())
        .submitLoginNegative()
            .isErrorMessageHasText("Login or Password incorrect");
    }
    @Test
    public void loginWrongPassword() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("lolik@mail.ru")
                        .password("Lolik12")
                        .build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }
    @Test
    public void loginUnregistered() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("lol@mail.ru")
                        .password("Lolik123!")
                        .build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }


}
