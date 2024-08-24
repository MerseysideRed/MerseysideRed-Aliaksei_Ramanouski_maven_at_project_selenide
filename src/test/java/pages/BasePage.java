package pages;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    public static void navigateToURL(String urlString) {

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        open(urlString);
    }
}
