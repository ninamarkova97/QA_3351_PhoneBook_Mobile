package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {


//    {
//        "platformName": "Android",
//            "appium:automationName": "UiAutomator2",
//            "appium:deviceName": "my",
//            "appium:platformVersion": "11.0",
//            "appium:appPackage": "com.sheygam.contactapp",
//            "appium:appActivity": ".SplashActivity"
//    }


    protected AndroidDriver driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("my");
        options.setPlatformVersion("11.0");
        options.setAppPackage("com.sheygam.contactapp");
        options.setAppActivity(".SplashActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

}



