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

    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }

}
