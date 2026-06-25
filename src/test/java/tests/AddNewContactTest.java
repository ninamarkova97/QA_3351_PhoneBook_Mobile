package tests;

import config.AppiumConfig;
import models.Contact;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class AddNewContactTest extends AppiumConfig {

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
    public void createNewContactSuccessAll(){
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Maf"+i)
                .email("maf"+i+"@gmail.com")
                .phone("12345678"+i)
                .address("TA")
                .description("family")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());
    }
    @Test
    public void createNewContactSuccessReq(){
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Maf"+i)
                .email("maf"+i+"@gmail.com")
                .phone("12345678"+i)
                .address("TA")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());
    }

    @Test
    public void createNewContactWithEmptyName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("Maf")
                .email("maf@gmail.com")
                .phone("1234567878")
                .address("TA")
                .description("empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("{name=must not be blank}");

    }



    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver).logOut();
    }



}

