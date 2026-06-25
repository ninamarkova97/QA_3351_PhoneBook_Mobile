package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.Contact;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddNewContactScreen extends BaseScreen {
    public AddNewContactScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputName")
    WebElement nameEditText;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputLastName")
    WebElement lastNameEditText;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement emailEditText;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPhone")
    WebElement phoneEditText;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputAddress")
    WebElement addressEditText;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputDesc")
    WebElement descriptionEditText;
    @AndroidFindBy(xpath = "//*[@text='CREATE']")
    WebElement createBtn;

    public AddNewContactScreen fillContactForm(Contact contact) {
        should(nameEditText, 5);
        type(nameEditText, contact.getName());
        type(lastNameEditText, contact.getLastName());
        type(emailEditText, contact.getEmail());
        type(phoneEditText, contact.getPhone());
        type(addressEditText, contact.getAddress());
        type(descriptionEditText, contact.getDescription());

        return this;
    }

    public ContactListScreen submitContactForm() {
        createBtn.click();
        return new ContactListScreen(driver);
    }

    public AddNewContactScreen submitContactFormNegative() {
        createBtn.click();
        return this;
    }
    public AddNewContactScreen isErrorMessageHasText(String text){
        checkAlertText(text);
        return this;

    }
}
