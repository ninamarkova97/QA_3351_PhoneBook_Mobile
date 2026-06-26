package screens;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//*[@resource-id ='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    WebElement activityTextView;

    @AndroidFindBy(xpath = "//*[@content-desc ='More options']")
    WebElement menuOptions;

    @AndroidFindBy(xpath = "//*[@text='Logout']")
    WebElement logoutBtn;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    WebElement plusBtn;

    @AndroidFindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<WebElement> contactNameList;


    @AndroidFindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/rowContainer']")
    List<WebElement> contactList;

    @AndroidFindBy(id = "android:id/button1")
    WebElement yesBtn;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
            WebElement noContactsHereTextView;

    int countBefore;
    int countAfter;

    public boolean isActivityTitleDisplayed(String text) {
        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }


    public AuthenticationScreen logOut() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }


    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        if (activityTextView.getText().equals("Contact list")) {
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);

    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        // List<WebElement> list = driver.findElements(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowName]"));
        isShouldHave(activityTextView, "Contact list", 10);
        System.out.println("size of list " + contactNameList.size());
        boolean isPresent = false;
        for (WebElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen deleteFirstContact() {
        isActivityTitleDisplayed("Contact list");
        countBefore = contactList.size();
        System.out.println(countBefore);

        WebElement first = contactList.get(0);

        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
        // int xTo = rectangle.getX()+(rectangle.getWidth()/8)*7;
        int xTo = rectangle.getWidth() - xFrom;
        int y = rectangle.getY() + rectangle.getHeight() / 2;

//        TouchAction<?> touchAction = new TouchAction<>(driver);
//        touchAction.longPress(PointOption.point(xFrom, y))
//                .moveTo(PointOption.point(xTo, y)).release().perform();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),xFrom,y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger,Duration.ofMillis(1000)));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600),PointerInput.Origin.viewport(),xTo,y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));

        should(yesBtn, 8);
        yesBtn.click();
        pause(30);
        countAfter = contactList.size();
        System.out.println(countAfter);

        return this;
    }

    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }

    public ContactListScreen removeAllContacts() {
        pause(1000);
        while (contactList.size()>0){
            deleteFirstContact();
        }
        return this;
    }

    public ContactListScreen isNoContactsHere() {
        isShouldHave(noContactsHereTextView,
                "No Contacts. Add One more!",10);
        return this;
    }
}
