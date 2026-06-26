package tests;

import config.AppiumConfig;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    @Test
    public void registrationSuccessTest() {
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("lolik"+i+"@mail.ru")
                .fillPassword("Lolik123!")
                .submitRegistration()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void registrationSuccessModelTest() {
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("lolik"+i+"@mail.ru")
                        .password("Lolik123!").build())
                .submitRegistration()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void registrationWrongEmailTest() {
        new AuthenticationScreen(driver)
                .fillEmail("lolikmail.ru")
                .fillPassword("Lolik123!")
                .submitRegistrationNegative()
                .isErrorMessageHasText("{username=must be a well-formed email address}");

    }  @Test
    public void registrationWrongPasswordTest() {
        new AuthenticationScreen(driver)
                .fillEmail("lolik@mail.ru")
                .fillPassword("Lolik123")
                .submitRegistrationNegative()
                .isErrorMessageHasText("{password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number; Can contain special characters [@$#^&*!]}");
    }


    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver).logOut();
    }

}
