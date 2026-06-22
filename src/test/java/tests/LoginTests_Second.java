package tests;

import config.AppiumConfig;
import models.User;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginTests_Second extends AppiumConfig {

    @Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillEmail("lolik@mail.ru")
                .fillPassword("Lolil123!")
                .submitLogin()
                .isAccountOpened()
                .logout();

    }
    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("lolik@mail.ru")
                        .password("Lolik123!").build())
                .submitLogin()
                .isAccountOpened()
                .logout();

    }

}
