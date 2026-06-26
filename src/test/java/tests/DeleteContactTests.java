package tests;

import config.AppiumConfig;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DeleteContactTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("lolik@mail.ru")
                        .password("Lolik123!")
                        .build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test
    public void deleteFirstContact(){
        new ContactListScreen(driver).deleteFirstContact()
                .isListSizeLessOnOne();
    }
}
